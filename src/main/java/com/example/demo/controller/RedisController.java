package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.ResultFactory;
import com.example.demo.common.ResultObject;
import com.example.demo.pojo.Apple;
import com.example.demo.pojo.Category;
import jdk.nashorn.internal.ir.CallNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author beijing.lv
 * @version 1.0
 * @date 2022/9/9 11:01
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisTemplate redisTemplate;

    public final static String STRING = "string:";
    public final static String OBJECT_APPLE = "object:apple";
    public final static String List_APPLE = "list:apple";
    public final static String MAP_APPLE = "map:apple";

    /**
     * 永久
     **/
    public final static String STRING_PERMANENT = "string_permanent:";
    public final static String OBJECT_APPLE_PERMANENT = "object_permanent:apple";
    public final static String List_APPLE_PERMANENT = "list_permanent:apple";
    public final static String MAP_APPLE_PERMANENT = "map_permanent:apple";

    public final static String MAP_APPLE_Hash = "map_Hash:apple";
    public final static String List_APPLE_LEFT = "list_left:apple";

    /**
     * opsForValue() 一 个key只能对应一个value
     * opsForList() 一个key可以分别先后添加多个value
     * opsForHash() 以键值对的形式存储
     **/


    @GetMapping("/setString")
    public ResultObject<Object> setString(@RequestParam("key") String key, @RequestParam("meaning") String meaning) {
        logger.info("key--------{}", key);
        logger.info("meaning--------{}", meaning);
        //key 有效期1小时
        redisTemplate.opsForValue().set(STRING + key, meaning, 1, TimeUnit.HOURS);
        //key 永久
        redisTemplate.opsForValue().set(STRING_PERMANENT + key, meaning);

        Object o = redisTemplate.opsForValue().get(STRING + key);
        return ResultFactory.success(o);
    }


    @PostMapping("/setObject")
    public ResultObject<Object> setObject(@RequestBody Apple apple) {
        logger.info("apple--------{}", JSON.toJSONString(apple));
        //序列化对象
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Apple.class));
        //key 有效期1小时
        redisTemplate.opsForValue().set(OBJECT_APPLE + apple.getId(), apple, 1, TimeUnit.HOURS);
        //key 永久
        redisTemplate.opsForValue().set(OBJECT_APPLE_PERMANENT + apple.getId(), apple);
        return ResultFactory.success(redisTemplate.opsForValue().get(OBJECT_APPLE + apple.getId()));
    }

    @PostMapping("/setList")
    public ResultObject<Object> setObject(@RequestBody List<Apple> appleList) {
        logger.info("appleList--------{}", JSON.toJSONString(appleList));
        //序列化对象
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Apple.class));
        //key 有效期1小时
        redisTemplate.opsForValue().set(List_APPLE, appleList, 1, TimeUnit.HOURS);
        //key 永久
        redisTemplate.opsForValue().set(List_APPLE_PERMANENT, appleList);

        return ResultFactory.success("success");
    }

    @PostMapping("/setMap")
    public ResultObject<Object> setObject(@RequestBody Map<String, String> map) {
        logger.info("map--------{}", JSON.toJSONString(map));
        //序列化对象
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Map.class));
        //key 有效期1小时
        redisTemplate.opsForValue().set(MAP_APPLE, map, 1, TimeUnit.HOURS);
        //key 永久
        redisTemplate.opsForValue().set(MAP_APPLE_PERMANENT, map);
        //以键值对的形式存到redis
        redisTemplate.opsForHash().putAll(MAP_APPLE_Hash, map);
        Object o = redisTemplate.opsForHash().get(MAP_APPLE_Hash, "name");
        return ResultFactory.success(o);
    }

    /**
     * 一个key对应多个value
     **/
    @GetMapping("/setStrings")
    public ResultObject<Object> setStrings() {
        redisTemplate.opsForList().rightPush(STRING + "user_list", "wangxinli");
        redisTemplate.opsForList().rightPush(STRING + "user_list", "yanxiaotang");
        //push时value传的是什么类型，range方法后接受的list<>中就传什么类型
        List<String> lists = redisTemplate.opsForList().range(STRING + "user_list", 0, -1);
        logger.info("lists------{}", JSON.toJSONString(lists));
        return ResultFactory.success(lists);
    }

    /**
     * 一个key对应多个value
     **/
    @GetMapping("/setObjects")
    public ResultObject<Object> setObjects() {

        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Category.class));
//        redisTemplate.opsForList().leftPush(List_APPLE + "apple_list", new Category(1L,"张三"));
//        redisTemplate.opsForList().leftPush(List_APPLE + "apple_list", new Category(2L,"李四"));
//
//        //取值全部的值
//        List range = redisTemplate.opsForList().range(List_APPLE + "apple_list", 0, -1);
//        logger.info("lists------{}", JSON.toJSONString(range));


        /**
         *leftPushAll方法的value值为数组或list集合,该方法会将value值一次性全部追加存入到该key
         *值对应的内容中；一般情况下同一个key值对应的追加的数据类型要保持一致，像下面那样先
         *往apple_list中添加apple对象，再往其中追加list集合或数组，这样的操作不常见，也无必要。
         */
        List<Category> appleList = Arrays.asList(
                new Category(1L,"张三"),
                new Category(2L,"李四"),
                new Category(3L,"王五"));
        redisTemplate.opsForList().leftPushAll(List_APPLE + "apple_list_1", appleList);
        List<Category> lists_1 = (List<Category>)redisTemplate.opsForList().range(List_APPLE + "apple_list_1", 0, -1);
        logger.info("lists_1------{}", JSON.toJSONString(lists_1));
        return ResultFactory.success("lists");
    }
}
