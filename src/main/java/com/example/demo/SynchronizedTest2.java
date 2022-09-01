package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.Apple;
import com.example.demo.pojo.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author beijing.lv@hand-china.com
 * @version 1.0
 * @date 2020/7/7 15:49
 */
public class SynchronizedTest2 {

    private static final Logger logger = LoggerFactory.getLogger(Controller1.class);


    public static void main(String[] args) throws ParseException {
//        List<Apple> appleList = new ArrayList<>();//存放apple对象集合
//
//        Apple apple1 = new Apple(1, "苹果1", new BigDecimal("3.25"), 10);
//        Apple apple12 = new Apple(1, "苹果2", new BigDecimal("1.35"), 20);
//        Apple apple2 = new Apple(2, "香蕉", new BigDecimal("2.89"), 30);
//        Apple apple3 = new Apple(3, "荔枝", new BigDecimal("9.99"), 40);
//
//        appleList.add(apple1);
//        appleList.add(apple12);
//        appleList.add(apple2);
//
//        //List 以ID分组 Map<Integer,List<Apple>>
//        Map<Integer, List<Apple>> groupBy = appleList.stream().collect(Collectors.groupingBy(Apple::getId));
//        logger.info("=======>{}", JSON.toJSONString(groupBy));
//
//
//        /**
//         * List -> Map
//         * 需要注意的是：
//         * toMap 如果集合对象有重复的key，会报错Duplicate key ....
//         * apple1,apple12的id都为1。
//         * 可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
//         */
//        Map<Integer, Apple> appleMap = appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a, (k1, k2) -> k1));
//        logger.info("=======>{}", JSON.toJSONString(appleMap));
//
//
//        //过滤出符合条件的数据
//        List<Apple> filterList = appleList.stream().filter(a -> a.getName().equals("香蕉")).collect(Collectors.toList());
//        logger.info("=======>{}", JSON.toJSONString(filterList));
//
//
//        //计算 总金额
//        BigDecimal totalMoney = appleList.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
//        logger.info("=======>{}", JSON.toJSONString(totalMoney));
//        Integer sum = appleList.stream().map(Apple::getNum).reduce(Integer::sum).orElse(0);
//        logger.info("=======>{}", JSON.toJSONString(sum));
//        Integer sum1 = appleList.stream().mapToInt(Apple::getNum).sum();
//        logger.info("=======>{}", JSON.toJSONString(sum1));
//
//
//        String result2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(1598854034000L);
//        logger.info("=======>{}", JSON.toJSONString(result2));
//        Apple apple = new Apple();
//        List<Apple> appleList1 = new ArrayList<>();
//        apple.setNum(123);
//        apple.setMoney(new BigDecimal("3.25"));
//        apple.setName("123");
//        for (int i = 0; i < 5; i++) {
//            Apple apple6 = new Apple();
//            BeanUtils.copyProperties(apple, apple6);
//            apple6.setId(i);
//            appleList1.add(apple6);
//        }
//
//        logger.info("=======>{}", JSON.toJSONString(appleList1));
//
//        String str = "沧州营旭防腐设备有限公司&000096";
//        logger.info("=======>{}", JSON.toJSONString(str.indexOf("&")));
//        if (str.indexOf("&") != -1) {
//            String str1 = str.substring(0, str.indexOf("&"));
//            logger.info("=======>{}", JSON.toJSONString(str1));
//        }
//
//        String a = "10.30.5.13:9000:1234121";
//
//        a = a.replace("10.30.5.13:9000", "srm-api-minio.htd.cn");
//        logger.info("=======>{}", JSON.toJSONString(a));
//        long timeMillis = System.currentTimeMillis();
//        logger.info("timeMillis=======>{}", timeMillis);
//
//        try {
//            Thread.sleep(3 * 1000);
//        } catch (Exception e) {
//
//        }
//        long timeMillis1 = System.currentTimeMillis();
//        logger.info("timeMillis1=======>{}", timeMillis1);
//        Long b = timeMillis1 - timeMillis;
//        logger.info("b=======>{}", b);
//
//        List<Category> list = new ArrayList<>();
//        Category category = new Category();
//        category.setCategoryName("123");
//        category.setCategoryId(1L);
//        list.add(category);
//
//        Category category1 = new Category();
//        category1.setCategoryName("123");
//        category1.setCategoryId(1L);
//        list.add(category1);
//
//        logger.info("=---{}", JSON.toJSONString(list));
//        List<Category> collect = list.stream().distinct().collect(Collectors.toList());
////        HashSet set = new HashSet(list);
////        list.clear();
////        list.addAll(set);
//        logger.info("=---{}", JSON.toJSONString(collect));
//
//        List<Apple> appleList3 = new ArrayList<>();//存放apple对象集合
//        Apple test1 = new Apple(1, "苹果1", new BigDecimal("3.25"), 10);
//        Apple test2 = new Apple(2, "苹果2", new BigDecimal("1.35"), 20);
//        appleList3.add(test1);
//        appleList3.add(test2);
//
//        List<Apple> appleList2 = new ArrayList<>();//存放apple对象集合
//        Apple test3 = new Apple(1, "苹果1", new BigDecimal("3.25"), 10);
//        appleList2.add(test3);
//
//        List<String> oldIds = new ArrayList<>();
//        List<String> newIds = new ArrayList<>();
//        appleList3.stream().forEach(it->oldIds.add(it.getId().toString()));
//        appleList2.stream().forEach(it->newIds.add(it.getId().toString()));
//
//        List<String> collectAdd = oldIds.stream().filter(it -> !newIds.contains(it)).collect(Collectors.toList());
//        logger.info("collectAdd==>{}",JSON.toJSONString(collectAdd));
//
//        List<Apple> collecta = appleList3.stream().filter(it -> collectAdd.contains(it.getId().toString())).collect(Collectors.toList());
//        logger.info("collecta==>{}",JSON.toJSONString(collecta));

//        BigDecimal b = new BigDecimal("1.0000000");
//        b.toString();
//        logger.info("====>" + b.toString());


//        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));
//        System.out.println(numbersList);
//        List<Integer> listWithoutDuplicates = numbersList.stream().distinct().collect(Collectors.toList());
//
//        numbersList.stream().distinct().collect(Collectors.toList());
//
//
//        logger.info("====>{}", JSON.toJSONString(listWithoutDuplicates));


//        ArrayList<String> numbers = new ArrayList<>(Arrays.asList("1", "1", "1", "2", "3", "4", "5"));
//        logger.info("====>数据大小：{}", JSON.toJSONString(numbers.size()));
//        List<String> collect = numbers.stream().distinct().collect(Collectors.toList());
//        logger.info("去重后的数据:{}", JSON.toJSONString(collect));
//        numbers.stream().distinct().collect(Collectors.toList());

//        Apple apple = new Apple();
//        logger.info("=====>{}", JSON.toJSONString(apple));
////        Optional.ofNullable(apple).orElse(apple);
//        Optional<Apple> apple1 = Optional.ofNullable(apple);
//        String test = null;
////        String s = Optional.ofNullable(test).orElse("123");
//

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date currdate = format.parse("2020-12-01 11:11:11");
//        System.out.println("现在的日期是：" + currdate);
//        Calendar ca = Calendar.getInstance();
//        Date d = format.parse("2020-12-01 11:11:11");
//        ca.setTime(d);
//        ca.add(Calendar.DATE, 3);// num为增加的天数，可以改变的
//        currdate = ca.getTime();
//        String enddate = format.format(currdate);
//        System.out.println("增加天数以后的日期：" + enddate);
//        logger.info("==={}", Calendar.DATE);
//        BigDecimal bef = new BigDecimal("100.00");
//        BigDecimal aft = new BigDecimal("90");
//        BigDecimal aft1 = new BigDecimal("20");
//        BigDecimal subtract = aft.subtract(bef);
//
//        logger.info("=====>{}", subtract);
//        logger.info("=====>{}", subtract.abs());
//        logger.info("=====>{}", aft.compareTo(bef));
//        logger.info("=====>{}", aft1.compareTo(subtract));

//        String a = "123";
////        int i = a.indexOf("1");
////        logger.info("=====>{}", a.indexOf("12"));

//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        list.add(6);
//
//        list.stream().parallel().forEach(item -> {
//            test(item);
//        });
//        logger.info("结束");
//
//    }
//
//    public static int test(int a) {
//        int c = 0;
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        logger.info("第" + a + "此执行开始时间" + format.format(new Date()));
//        for (int i = 0; i < 1000000; i++) {
//            for (int b = 0; b < 1000000; b++) {
//                c += i + b;
//            }
//        }
//        logger.info("第" + a + "此执行结束时间" + format.format(new Date()));
        //        return 0;
//
//        DecimalFormat bigDecimal = new DecimalFormat("######0.00");
//        int a = 1;
//        int b = 3;
//        double c = (double) a / b * 100;
//        String format = bigDecimal.format(c);
//        System.out.println(format);
//        Map<String, String> map = new HashMap<>();
//        if (Objects.isNull(map.get("name"))) {
//            System.out.println("123");
//        }
//        System.out.println(map.get("name"));
//
//
//        List<Map<String, String>> result = new ArrayList<>();
//        Map<String, String> map1 = new HashMap<>();
//        Map<String, String> map2 = new HashMap<>();
//        Map<String, String> map3 = new HashMap<>();
//        map1.put("name", "张三");
//        map1.put("value", "3");
//        map1.put("name", "3");
//        logger.info("----{}",JSON.toJSONString(map1));
//        result.add(map1);
//        map2.put("name", "lisi");
//        map2.put("value", "4");
//        result.add(map2);
//        map3.put("name", "wangwu");
//        map3.put("value", "5");
//        result.add(map3);
//        Map<String, Object> collect = result.stream().map(e->{
//            HashMap<String,String> map4= new HashMap<String, String>();
//            map4.put(e.get("name"),e.get("value"));
//            return map4;
//        }).flatMap(m -> m.entrySet().stream())
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

//        Map<String, String> collect =  result.stream().map(e->{
//            HashMap<String,String> map4= new HashMap<String, String>();
//            map4.put(e.get("name"),e.get("value"));
//            return map4;
//        })
//                .map(Map::entrySet)
//                .flatMap(Set::stream)
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

//        logger.info("=-==={}",JSON.toJSONString(collect));
//
//        String test = "Z10008_Z100123";
//        String substring = test.substring(test.lastIndexOf("_") + 1);
//        logger.info("substring=========>{}",test.lastIndexOf("_"));
//        logger.info("substring=========>{}",test.substring(0));
//
//        String test1 = "Z100123";
//
//        logger.info("substring1=========>{}",test1.indexOf("1"));
//        logger.info("substring1=========>{}",test1.indexOf("Z"));
//        logger.info("substring1=========>{}",-1==test.indexOf("5111111"));
//        logger.info("substring1=========>{}",test1.indexOf("Z100123"));
//
//        List<String> list = new ArrayList<>();
//        List<String> collect1 = list.stream().distinct().collect(Collectors.toList());
//        String join = String.join(",", collect1);
//        logger.info("-----------{}", JSON.toJSONString(join));
//
//
//
//        Integer num1 = 4;
//        Integer num2 = 121;
//        Integer num3 = 6;
//        String index = "12,13,14,1";
//        int i = index.indexOf(String.valueOf(num1));
//        logger.info("-----------:{}", JSON.toJSONString(i));
//        int j = index.indexOf(String.valueOf(num2));
//        logger.info("-----------:{}", JSON.toJSONString(j));
//        int k = index.indexOf(String.valueOf(num3));
//        logger.info("-----------:{}", JSON.toJSONString(k));
//        if(-1 != index.indexOf(String.valueOf(num2)) ){
//            logger.info("-----------:{}");
//        }
//
//        Map<String, Object> mapOb = new HashMap<>();
//        mapOb.put("ceshi", "123");

//        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
//        Date amDate = df.parse("09:10:00");
//        Date pmDate = df.parse("10:00:00");
//        long amTime = amDate.getTime();
//        long pmTime = pmDate.getTime();
//        String format = df.format(new Date());
//        long time = df.parse(format).getTime();
//        logger.info("time------------{}", JSON.toJSONString(time));
//        if (!(time > amTime && time < pmTime)) {
//            logger.info("当前时间在设定时间内");
//        } else {
//            logger.info("当前时间不在设定时间内");
//        }
//
//        Calendar today = Calendar.getInstance();
//
//        if (today.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || today.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//            System.out.println("今天是周末");
//            System.out.println("今天是一周的第" + today.get(Calendar.DAY_OF_WEEK) + "天(星期天为第一天)");
//        } else {
//            System.out.println("今天不是周末");
//            System.out.println("今天是一周的第" + today.get(Calendar.DAY_OF_WEEK) + "天(星期天为第一天)");
//
//        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:ss:mm");
        Date amDate = simpleDateFormat.parse("09:30:00");
        Date pmDate = simpleDateFormat.parse("10:30:00");
        long amTime = amDate.getTime();
        long pmTime = pmDate.getTime();

        logger.info("amTime----{}", amTime);
        logger.info("pmTime----{}", pmTime);
        //获取当前的时间
        String format = simpleDateFormat.format(new Date());
        long nowTime = simpleDateFormat.parse(format).getTime();
        logger.info("nowTime---{}", nowTime);
        if (nowTime > amTime && nowTime < pmTime) {
            logger.info("当前时间在范围内");
        }
        String accountName = "华泰证券沪市";
        List<String> list = Arrays.asList(accountName.split("/"));
        logger.info("----------list:{}", JSON.toJSONString(list));
//        String substring = accountName.substring(0, accountName.indexOf("/"));
//        logger.info("substring---------->:{}",substring);

//        String substring1 = accountName.substring(accountName.indexOf("/") + 1, accountName.lastIndexOf("/"));
//        logger.info("substring1---------->:{}",substring1);
        Map<String, String> map = new HashMap<>();
        if (map.isEmpty()) {
            logger.info("map是空的");
        } else {
            logger.info("map是非空的");
        }

        String buffer = "本人或亲属股票账户验证不通过，烦请尽快处理。详情为：<br />&nbsp; &nbsp; &nbsp; &nbsp;小明在证券账户开户代理机构名称有深市A股账户、沪市A股账户。<br />&nbsp; &nbsp; &nbsp; &nbsp;小明爸爸在华泰证券股份有限公司有深市A股账户，江南证券有沪市A股账户。<br />&nbsp; &nbsp; &nbsp; &nbsp;小明妈妈在南方证券有深市A股账户，西南证券有沪市A股账户。<br />";
        String replace = buffer.replaceAll("<br />", "").replaceAll("&nbsp; ", "").replaceAll("&nbsp;", "");
        logger.info("替换---------------》{}", replace);


        JSONArray jsonArray = new JSONArray();
        if (jsonArray.isEmpty()) {

            logger.info("json 数组为空");
        }

        JSONObject jsonObject = JSONObject.parseObject(null);
        logger.info("jsonObject====>{}",JSON.toJSONString(jsonObject));
        if(StringUtils.isEmpty(jsonObject)){
            logger.info("未获取到token123");
        }
//        String s = jsonObject.get("id").toString();
//        logger.info("s====>{}",s);
//        if(StringUtils.isEmpty(s)){
//            logger.info("未获取到token");
//        }
        int i1 = "2022-08-11 00:12:24".compareTo("2022-08-12 00:12:24");
        int i2 = "2022-08-13 00:12:24".compareTo("2022-08-12 00:12:24");
        int i3 = "2022-08-11 00:12:24".compareTo("2022-08-11 00:12:24");
        logger.info("i1----------{}", i1 );
        logger.info("i2----------{}", i2 );
        logger.info("i3----------{}", i3 );
    }


}
