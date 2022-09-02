package com.example.demo.common;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author beijing.lv
 * @version 1.0
 * @date 2022/9/1 18:14
 * 雪花算法
 * 在Java中64bit的证书是long类型，所以在SnowFlake算法生成的ID就是long类存储的
 *
 * 优点 1.毫秒在高维，自增序列在低位，整个ID都是趋势递增的，因为有datacenterId 和 workerId来做区分
 * 2.不依赖数据库等第三方系统，以服务的方式部署，稳定性更高，生成ID的性能也非常高
 * 3.可以根据自身的业务特性分配bit位非常灵活
 * 缺点：1.依赖机器时钟，如果机器时钟回拨，会导致重复ID生成
 * 2.在单机上是递增的，但由于涉及到分布式环境，每台机器上的时钟不可能完全同步，有时候会出现不是全局递增的情况，
 * 此缺点可以认为无所谓，一般分布式ID只要求趋势递增，并不会严格要求递增，90%的需求只要求趋势递增。
 *
 * 为了解决时钟回拨问题，导致ID重复，后面有人专门提出了解决的方案
 * 百度开源的分布式唯一ID生成器 UidGenerator
 * Leaf - 美团点评分布式ID生成系统
 */

@Slf4j
@Component
public class IdGeneratorSnowflake {
    private long workerId = 0;
    private long datacenterId = 1;
    private Snowflake snowflake = IdUtil.createSnowflake(workerId,datacenterId);

    @PostConstruct
    public void init(){
        try{
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器的workerId：{}", workerId);
        }catch(Exception e){
            e.printStackTrace();
            log.warn("当前机器的worker获取失败",e);
            workerId = NetUtil.getLocalhostStr().hashCode();
        }
    }

    public synchronized long snowflakeId(){
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workerId,long datacenterId){
        Snowflake snowflake = IdUtil.createSnowflake(workerId,datacenterId);
        return snowflake.nextId();
    }

}
