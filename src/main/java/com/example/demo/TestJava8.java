package com.example.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.Apple;
import com.example.demo.pojo.AppleCopy;
import com.sun.corba.se.impl.copyobject.JavaStreamObjectCopierImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @author beijing.lv@hand-china.com
 * @version 1.0
 * @date 2020/12/17 14:30
 */
public class TestJava8 {

    private static final Logger logger = LoggerFactory.getLogger(TestJava8.class);

    public static void main(String[] args) {

        //匿名内部类
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);
        Apple apple = new Apple(1, "2021-07-21 02:02:02", new BigDecimal("11.3"), 3);
        logger.info("-----{}", JSON.toJSONString(apple));

        //Lambda 表达式
        Comparator<Integer> com1 = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> ts1 = new TreeSet<>(com1);

        List<Apple> appleList = Arrays.asList(
                new Apple(1, "2021-07-21 02:02:02", new BigDecimal("11.3"), 3),
                new Apple(2, "2021-07-21 02:02:02", new BigDecimal("11.4"), 3),
                new Apple(3, "2021-07-21 02:02:02", new BigDecimal("11.4"), 3),
                new Apple(4, "2021-07-22 02:02:02", new BigDecimal("16.3"), 3),
                new Apple(5, "2021-07-20 02:02:02", new BigDecimal("10.3"), 3),
                new Apple(6, "2021-07-24 02:02:02", new BigDecimal("11.1"), 16),
                new Apple(6, "2021-07-26 02:02:02", new BigDecimal("11"), 15));

        appleList.stream().sorted(comparing(Apple::getName)).collect(Collectors.toList());
        logger.info("对象集合升序：{}", JSON.toJSONString(appleList));

        //查询总数大于10的
        List<Apple> collect = appleList.stream().filter(e -> e.getNum() > 10).collect(Collectors.toList());
        logger.info("查询总数大于10的:{}", JSON.toJSONString(collect));

        //查询总数大于10的,取前两条
        List<Apple> collect1 = appleList.stream().filter(e -> e.getNum() > 10).limit(2).collect(Collectors.toList());
        logger.info("查询总数大于10的,取前两条：{}", JSON.toJSONString(collect1));

        //查询总数大于10的,取第2条之后的数据
        List<Apple> collect2 = appleList.stream().filter(e -> e.getNum() > 10).skip(2).collect(Collectors.toList());
        logger.info("查询总数大于10的,取第2条之后的数据：{}", JSON.toJSONString(collect2));

        //去重
        List<Apple> collect3 = appleList.stream().distinct().collect(Collectors.toList());
        logger.info("去重：{}", JSON.toJSONString(collect3));

        //提取集合中某个字段的值
        List<Integer> collect4 = appleList.stream().map(Apple::getNum).collect(Collectors.toList());
        logger.info("提取集合中某个字段的值：{}", JSON.toJSONString(collect4));

        //将流中的每个值都换成另一个流，然后把所有的流连成一个流。
        List<String> list1 = Arrays.asList("aaa", "bbb", "ccc");
        Collection<Character> collect5 = list1.stream().flatMap(TestJava8::fileMapTest).collect(Collectors.toList());
        logger.info("将流中的每个值都换成另一个流，然后把所有的流连成一个流：{}", JSON.toJSONString(collect5));

        List<String> list = Arrays.asList("aaa", "eee", "bbb", "ccc", "ddd");
        List<String> collect6 = list.stream().sorted().collect(Collectors.toList());
        logger.info("自然排序：{}", JSON.toJSONString(collect6));

        List<Apple> collect7 = appleList.stream().sorted(comparing(Apple::getNum)).collect(Collectors.toList());
        logger.info("对象集合升序：{}", JSON.toJSONString(collect7));

        List<Apple> collect8 = appleList.stream().sorted(comparing(Apple::getNum, Comparator.reverseOrder())).collect(Collectors.toList());
        logger.info("对象集合降序：{}", JSON.toJSONString(collect8));

        List<Apple> collect9 = appleList.stream().sorted(comparing(Apple::getNum).thenComparing(Apple::getMoney)).collect(Collectors.toList());
        logger.info("对象集合以类属性一升序 属性二升序：{}", JSON.toJSONString(collect9));

        List<Apple> collect10 = appleList.stream().sorted(comparing(Apple::getNum, Comparator.reverseOrder()).thenComparing(Apple::getMoney, Comparator.reverseOrder())).collect(Collectors.toList());
        logger.info("对象集合以类属性一降序 属性二降序：{}", JSON.toJSONString(collect10));

        List<Apple> collect11 = appleList.stream().sorted(comparing(Apple::getNum, Comparator.reverseOrder()).thenComparing(Apple::getMoney)).collect(Collectors.toList());
        logger.info("对象集合以类属性一降序 属性二升序：{}", JSON.toJSONString(collect11));

        List<Apple> collect12 = appleList.stream().sorted(comparing(Apple::getNum).thenComparing(Apple::getMoney, Comparator.reverseOrder())).collect(Collectors.toList());
        logger.info("对象集合以类属性一升序 属性二降序：{}", JSON.toJSONString(collect12));

        Boolean aBoolean = appleList.stream().allMatch(e -> e.getNum().equals(3));
        logger.info("检查集合所有的元素，如果都符合条件则返回true，否则false：{}", aBoolean);

        Boolean aBoolean1 = appleList.stream().anyMatch(e -> e.getNum().equals(3));
        logger.info("检查集合中的元素至少有一个符合条件则返回ture，都不符合则返回false：{}", aBoolean1);

        Boolean aBoolean2 = appleList.stream().noneMatch(e -> e.getNum().equals(0));
        logger.info("检查集合中的所有元素都不符合条件则返回ture，有符合则返回false：{}", aBoolean2);

        Apple apple21 = appleList.stream().sorted(comparing(Apple::getNum, Comparator.reverseOrder())).findFirst().orElse(null);
        logger.info("按照条件降序取第一条数据：{}", JSON.toJSONString(apple21));

        Apple apple1 = appleList.parallelStream().filter(e -> e.getNum().equals(3)).findAny().orElse(null);
        logger.info("按照条件并行查询取第一条数据：{}", JSON.toJSONString(apple1));

        long count = appleList.stream().filter(e -> e.getNum().equals(3)).count();
        logger.info("返回查询数据条数之和：{}", JSON.toJSONString(count));

        int sum = appleList.stream().mapToInt(Apple -> Apple.getNum()).sum();
        int max = appleList.stream().mapToInt(Apple -> Apple.getNum()).max().getAsInt();
        int min = appleList.stream().mapToInt(Apple -> Apple.getNum()).min().getAsInt();
        double avg = appleList.stream().mapToInt(Apple -> Apple.getNum()).average().getAsDouble();
        logger.info("最大值：" + max + "最小值：" + min + "总和：" + sum + "平均值：" + avg);

        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce = list2.stream().reduce(0, (x, y) -> x + y);
        logger.info("求和1： {}", reduce);
        Integer integer = list2.stream().reduce(Integer::sum).orElseGet(null);
        logger.info("求和2：{}", integer);
        Integer integer1 = appleList.stream().map(Apple::getNum).reduce(Integer::sum).orElseGet(null);
        logger.info("求和3：{}", integer1);


        List<Integer> collect13 = appleList.stream().map(Apple::getNum).collect(Collectors.toList());
        logger.info("把流中的数量转换成List集合：{}", JSON.toJSONString(collect13));

        Set<Integer> collect14 = appleList.stream().map(Apple::getNum).collect(Collectors.toSet());
        logger.info("把流中的数量转换成Set集合：{}", JSON.toJSONString(collect14));

        HashSet<Integer> collect15 = appleList.stream().map(Apple::getNum).collect(toCollection(HashSet::new));
        logger.info("把流中的数量转换成HashSet集合：{}", JSON.toJSONString(collect15));

        Long count1 = appleList.stream().collect(Collectors.counting());
        logger.info("求集合的大小：{}", count1);
        Double aDouble = appleList.stream().collect(Collectors.averagingDouble(Apple::getNum));
        logger.info("求集合中某个字段的平均数：{}", aDouble);
        Integer integer2 = appleList.stream().collect(Collectors.summingInt(Apple::getNum));
        logger.info("求集合中某个字段的总和：{}", integer2);
        Map<Integer, List<Apple>> listMap = appleList.stream().collect(Collectors.groupingBy(Apple::getNum));
        logger.info("按照某个字段分组：{}", JSON.toJSONString(listMap));

        Map<Boolean, List<Apple>> listMap1 = appleList.stream().collect(Collectors.partitioningBy((e) -> e.getNum() > 10));
        logger.info("按照某个字段分组分区：{}", JSON.toJSONString(listMap1));

        String string = appleList.stream().map(Apple::getName).distinct().collect(Collectors.joining(","));
        logger.info("按照查询条件拼接字符串：{}", string);
        Apple apple11 =  new Apple(1, "张三", new BigDecimal("11.3"), 3);
        AppleCopy appleCopy = new AppleCopy();
        BeanUtils.copyProperties(apple1, appleCopy);

        logger.info("按照条件降序取第一条数据：{}", JSON.toJSONString(apple1));
        logger.info("按照条件降序取第一条数据：{}", JSON.toJSONString(appleCopy));
        logger.info("按照条件降序取第一条数据：{}", appleCopy.getId());

        Integer[] a = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        List<Integer> list3 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        List<Integer> collect26 = list3.stream().map((x) -> x * x).collect(Collectors.toList());
        logger.info("平方：{}", JSON.toJSONString(collect26));

        long start = System.currentTimeMillis();
        long reduce1 = LongStream.rangeClosed(0, 10000000000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        logger.info("结果：{}，时间：{}", reduce1, end - start);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Map<Integer, Apple> collect16 = appleList.stream().collect(Collectors.toMap(Apple::getId, Function.identity()));
        logger.info("list集合转map：{}", JSON.toJSONString(collect16));

        Map<Integer, Apple> appleMap = appleList.stream().collect(Collectors.toMap(Apple::getId, b -> b, (k1, k2) -> k1));

        logger.info("list集合转map：{}", JSON.toJSONString(appleMap));

        BigDecimal totalMoney = appleList.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.err.println("计算总钱数:" + totalMoney);


        List<Apple> unique1 = appleList.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(comparing(Apple::getName))), ArrayList::new));
        logger.info("list集合根据name去重:{}", JSON.toJSONString(unique1));

        //根据name,age属性去重
        List<Apple> unique2 = appleList.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(comparing(o -> o.getName() + ";" + o.getMoney()))), ArrayList::new)
        );
        logger.info("list集合根据name和money去重:{}", JSON.toJSONString(unique2));



    }

    public static Stream<Character> fileMapTest(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }



}
