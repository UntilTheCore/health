package com.utc.jobs;

import com.utc.constant.SetMealConstant;
import com.utc.utils.QiNiuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.util.Set;

@Component
public class ImgJob {
    @Autowired
    private JedisPool jedisPool;
    public void run() {
        Set<String> sdiff = jedisPool.getResource().sdiff(SetMealConstant.SET_MEAL_PIC_RESOURCE, SetMealConstant.SET_MEAL_PIC_DB_RESOURCE);
        System.out.println("run del img..." + sdiff);
        sdiff.forEach(fileName -> {
            QiNiuUtils.deleteFileFromQiNiu(fileName);
            jedisPool.getResource().srem(SetMealConstant.SET_MEAL_PIC_RESOURCE, fileName);
        });
    }
}
