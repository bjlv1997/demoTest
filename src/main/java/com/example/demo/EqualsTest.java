package com.example.demo;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.pojo.Apple;
import com.example.demo.pojo.ProductDto;
import com.sun.corba.se.impl.copyobject.JavaStreamObjectCopierImpl;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author beijing.lv
 * @version 1.0
 * @date 2021/8/10 17:09
 */
@Slf4j
public class EqualsTest {
//    private static final Logger logger = LoggerFactory.getLogger(Controller1.class);

//    private static final Logger logger = LoggerFactory.getLogger(EqualsTest.class);
//    public static void main(String[] args) {
//        log.info("");
//        String x = "test";
//        String y = "test";
//        String z = new String("test");
//        String m = new String("test");
//
//        int a = 1;
//        int b = 1;
//        logger.info("{}", a == b);
//        ProductDto productDto = new ProductDto();
//        ProductDto productDto1 = new ProductDto();
//        logger.info("{}", productDto.equals(productDto1));
//        logger.info("{}", x.equals(m));
//
//
//        String str1 = "通话";
//        String str2 = "重地";
//
//        logger.info("str2--hashcode:{}", str2.hashCode());
//        logger.info("Math.round:{}", Math.round(-1.2));
//
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append("abcdefghigk");
//        logger.info("StringBuffer----{}", stringBuffer);
//        logger.info("StringBuffer----{}", stringBuffer.reverse());
//
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("abcd");
//        logger.info("stringBuilder----{}", stringBuilder);
//        logger.info("stringBuilder----{}", stringBuilder.reverse());
//        Hashtable hashtable = new Hashtable<String, String>();
//        hashtable.put("", "");
////        hashtable.put(null,"");
//        HashMap hashMap = new HashMap<String, String>(16);
//        hashtable.put("", "");
//        hashMap.put(null, "");
//
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//                    logger.info("123");
//                }
//
//            }
//        });
//        t.start();
//        String name = "test";
//        name.length();
//        test:
//        for (int i = 0; i < 5; i++) {
//            System.out.println("i======="+i);
//            for (int j = 0; j < 10; j++) {
//
//                if(i==2){
//                    System.out.println("结束");
//                   return;
//                }
//                System.out.println("j======="+j);
//            }
//        }
//        if("1".equals(1)){
//            logger.info("-------------");
//        }
//
//        Apple apple = new Apple();
//        apple.setId(1);
//        apple.setName("");
//
//
//        log.info("apple:{}", JSON.toJSONString(apple, SerializerFeature.WriteMapNullValue));
//    }

    public static void main(String[] args) throws Exception{

//        System.out.println("主线程 =====> 开始 =====> " + System.currentTimeMillis());
//
//        ExecutorService executorService = Executors.newFixedThreadPool(4);
//        executorService.submit(()->{
//            System.out.println("异步线程 =====> 开始 =====> " + System.currentTimeMillis());
//            try{
//                Thread.sleep(5000);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            System.out.println("异步线程 =====> 结束 =====> " + System.currentTimeMillis());
//        });
//        executorService.shutdown(); // 回收线程池
//
//        Thread.sleep(2000);
//
//        System.out.println("主线程 =====> 结束 =====> " + System.currentTimeMillis());

        System.out.println("主线程 =====> 开始 =====> " + System.currentTimeMillis());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("异步线程 =====> 开始 =====> " + System.currentTimeMillis());
                    System.out.println(Thread.currentThread().getName() + "run!" + System.currentTimeMillis());
                    Thread.sleep(3000);
                    System.out.println("异步线程 =====> 结束 =====> " + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 8, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        executor.execute(runnable);//1
        System.out.println("主线程 =====> 结束 =====> " + System.currentTimeMillis());
    }


}
