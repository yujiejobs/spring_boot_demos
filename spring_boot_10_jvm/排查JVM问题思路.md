## 对于还在正常运行的系统：
1. 可以使用imap来查看VM中各个区域的使用情况
2. 可以通过istack来查看线程的运行情况，比如哪些线程阻塞、是否出现了死锁
3. 可以通过jstat命令来查看垃圾回收的情况，特别是fullgc,如果发现fullgc比较频繁，那么就得进行调优了
4. 通过各个命令的结果，或者jvisualvm等工具来进行分析
5. 首先，初步猜测频繁发送ulgc的原因，如果频繁发生fugc但是又一直没有出现内存溢出，那么表示fullgc3实际上是回收了很多对象了，所以这
些对象最好能在younggci过程中就直接回收掉，避免这些对象进入到老年代，对于这种情况，就要考虑这些存活时间不长的对象是不是比较大，
导致年轻代放不下，直接进入到了老年代，尝试加大年轻代的大小，如果改完之后，fugc减心，则证明修改有效
6. 同时，还可以找到占用CPU最多的线程，定位到具体的方法，优化这个方法的执行，看是否能避免某些对象的创建，从而节省内存

## 对于已经发生了OOM的系统：
1. 一般生产系统中都会设置当系统发生了OOM时，生成当时的dump文件（-XX:+HeapDumpOnOutOfMemoryError-
XX:HeapDumpPath=/usr/local/base)
2. 我们可以利用jsisualvm等工具来分析dump文件
3. 根据dump文件找到异常的实例对象，和异常的线程(占用CPU高)，定位到具体的代码
4. 然后再进行详细的分析和调试

总之，调优不是一蹴而就的，需要分析、推理、实践、总结、再分析，最终定位到具体的问题


### jmap 命令参数

`map [options] pid`

命令参数说明： 
option: jmap命令的可选参数。如果没有指定这个参数，命令会显示Java虚拟机进程的内存映像信息
pid: 要打印配置信息的Java虚拟机的进程ID。


| option参数      | 描述                                                                                                                                                                                         |
|---------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| -heap         | 显示Java堆的如下信息 <br/>1.被指定的垃圾回收算法的信息，包括垃圾回收算法的名称和垃圾回收算法的详细信息。 <br/>2.堆的配置信息，可能是由命令行选项指定，或者由Java虚拟机根据服务器配置选择的。<br/>3.堆的内存空间使用信息，包括分代情况，每个代的总容量、已使用内存、可使用内存。如果某一代被继续细分(例如，年轻代)，则包含细分的空间的内存使用信息。 |
| -histo[:live] | 显示Java堆中对象的统计信息，包括：对象数量、占用内存大小(单位：字节)和类的完全限定名。<br/>比如，如指定行数则 `jmap -histo 8   <br/>另外命令含义可参考 jmap -histo 字段详解                                                                              | head -n 20`                                                                                                                                                                                    |
| 3             | A3                                                                                                                                                                                         |

#### jmap -histo 字段详解
1. instance 是对象的实例个数
2. bytes 是总占用的字节数 
3. class name 对应的就是 Class 文件里的 class 的标识

| class name描述              |
|---------------------------|
| B 代表 byte                 |
| C 代表 char                 |
| D 代表 double               |
| F 代表 float                |
| I 代表 int                  |
| J 代表 long                 |
| Z 代表 boolean              |
| 前边有 [ 代表数组， [I 就相当于 int[] |
| 对象用 [L+ 类名表示              |



### jvm内存调优工具

1. jps
全名：Java Virtual Machine Process Status Tool
与linux系统里的ps命令相似，能够输出正在运行的虚拟机进程并显示id号
用法：
jps [option][hostid] option命令选项，用来控制输出格式 hostid指特定主机，可以指IP地址、域名、协议、端口等，默认为本机
jps -q 只输出jvmid（即唯一id号）
jps -m 输出jvm启动时传递给主类的方法
jps -l 输出主类全名，如果就jar则输出jar路径
jps -v 输出jvm启动参数

注意：远程运行命令时要开启机器上的jstatd服务，开启需要权限（方法看参考文献 jvm性能调优工具之jps）

2. jstat
输出jvm的各种状态信息，如装载的类及内存使用量、堆内存统计、垃圾回收情况等。
用法： jstat [-命令选项][jvmid][间隔时间/毫秒][查询次数]

jstat -class 输出类加载的class数量以及空间信息
jstat -compiler 输出编译成功和失败的具体信息
jstat -gc 输出gc信息包括各个分区的使用情况
jstat -gccapacity 输出堆内存统计信息

3. jinfo
输出jvm各项参数信息，包括默认参数
Jinfo [option] pid

4. jmap
输出进程中所有对象的信息
用法：jmap [option] pid
jmap [option] executable core 输出core dump文件
jmap [option][server-id@] remote-hostname-or-IP 远程运行命令 （server-id唯一id，假如一台主机上多个远程debug服务，用此选项参数标识服务器）
参数
-dump:[live,]format=b,file={fileName} 生成对应的dump信息文件
-heap 输出堆的详细信息、垃圾回收器信息、参数配置以及分代情况
-histo 输出class的内存情况（实例数、大小、名字）
-histo:live,format=b,file=a.log pid 输出内存信息dump到a.log文件中
\jmap -histo pid | less 输出对象序号，实例数，所占内存大小，class name等信息

5. jstack
输出当前时刻的线程快照，称为threaddump文件。
jvm每个线程堆栈信息的集合，可以用来追踪线程状态（运行、阻塞、等待、未启动）
用法：jstack [option] pid
注意：windows环境只能使用-l命令