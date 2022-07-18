## JAVA多线程实现方式

[官方文档](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html)

通常我们可以分为两类，Oracle也是这么说的
准确的讲，创建线程只有一种方式那就是构造Thread类，而实现线程的执行单元有两种方式

1. [方法一：实现Runnable接口的run方法，并把Runnable实 例传给Thread类](./src/test/java/com/soft/thread/ThreadRunnable.java)
2. [方法二：重写Thread的run方法(继承Thread类)](./src/test/java/com/soft/thread/ThreadExtends.java)

[演示入口](./src/test/java/com/soft/thread/ThreadTest.java)
