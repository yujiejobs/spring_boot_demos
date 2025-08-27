package com.soft;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.io.Files;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class GuavaExamplesTest {

    // 1. 不可变集合示例
    @Test
    void immutableCollectionsExample() {
        // 创建不可变列表
        ImmutableList<String> immutableList = ImmutableList.of("Java", "Guava", "Spring");
        System.out.println("不可变列表: " + immutableList);

        // 创建不可变集合
        ImmutableSet<Integer> immutableSet = ImmutableSet.of(1, 2, 3, 4, 5);
        System.out.println("不可变集合: " + immutableSet);

        // 创建不可变映射
        ImmutableMap<String, Integer> immutableMap = ImmutableMap.of(
                "one", 1,
                "two", 2,
                "three", 3
        );
        System.out.println("不可变映射: " + immutableMap);
    }

    // 2. 集合工具类示例
    @Test
    void collectionUtilitiesExample() {
        // Lists工具类
        List<String> list = Lists.newArrayList("a", "b", "c");
        List<String> reversedList = Lists.reverse(list);
        System.out.println("反转列表: " + reversedList);

        // Sets工具类
        Set<String> setA = Sets.newHashSet("a", "b", "c");
        Set<String> setB = Sets.newHashSet("b", "c", "d");
        Set<String> union = Sets.union(setA, setB);
        System.out.println("集合并集: " + union);
        Set<String> intersection = Sets.intersection(setA, setB);
        System.out.println("集合并集: " + intersection);

        // Maps工具类
        Map<String, Integer> map = Maps.newHashMap();
        map.put("apple", 10);
        map.put("banana", 20);
        Map<String, Integer> filteredMap = Maps.filterValues(map, v -> v > 15);
        System.out.println("过滤后的映射: " + filteredMap);
    }

    // 3. 字符串处理示例
    @Test
    void stringProcessingExample() {
        // Joiner - 连接字符串
        List<String> words = Lists.newArrayList("Hello", "Guava", "World");
        String joined = Joiner.on(" ").join(words);
        System.out.println("连接字符串: " + joined);

        // 处理null值
        List<String> nullableWords = Lists.newArrayList("Hello", null, "Guava");
        String joinedWithNull = Joiner.on(" ").useForNull("[NULL]").join(nullableWords);
        System.out.println("处理null的连接: " + joinedWithNull);

        // Splitter - 拆分字符串
        String str = "apple,banana,,orange";
        List<String> splitList = Splitter.on(",").omitEmptyStrings().splitToList(str);
        System.out.println("拆分字符串: " + splitList);

        // 拆分键值对
        String keyValueStr = "name=John;age=30;city=NewYork";
        Map<String, String> keyValueMap = Splitter.on(";").withKeyValueSeparator("=").split(keyValueStr);
        System.out.println("拆分键值对: " + keyValueMap);
    }

    // 4. 缓存示例
    @Test
    void cacheExample() throws Exception {
        // 创建LoadingCache
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build(new com.google.common.cache.CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        // 模拟从数据库加载数据
                        System.out.println("加载数据: " + key);
                        return "Value for " + key;
                    }
                });

        // 获取缓存值
        System.out.println(cache.get("first"));
        // 第二次获取不会触发加载
        System.out.println(cache.get("first"));
        // 获取另一个键
        System.out.println(cache.get("second"));
    }

    // 5. 事件总线示例
    @Test
    void eventBusExample() {
        EventBus eventBus = new EventBus();
        EventListener listener = new EventListener();
        eventBus.register(listener);

        // 发布事件
        eventBus.post(new StringEvent("Hello Guava EventBus!"));
        eventBus.post(new IntegerEvent(100));
    }

    // 事件监听器
    public static class EventListener {
        @Subscribe
        public void handleStringEvent(StringEvent event) {
            System.out.println("处理字符串事件: " + event.getMessage());
        }

        @Subscribe
        public void handleIntegerEvent(IntegerEvent event) {
            System.out.println("处理整数事件: " + event.getValue());
        }
    }

    // 事件类
    public static class StringEvent {
        private final String message;

        public StringEvent(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class IntegerEvent {
        private final int value;

        public IntegerEvent(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    // 6. IO操作示例
    @Test
    void ioOperationsExample() throws IOException {
        File tempDir = Files.createTempDir();
        System.out.println("临时目录: " + tempDir.getAbsolutePath());

        File file = new File(tempDir, "test.txt");
        Files.write("Guava IO Example".getBytes(), file);

        String content = Files.toString(file, java.nio.charset.StandardCharsets.UTF_8);
        System.out.println("文件内容: " + content);
    }
}