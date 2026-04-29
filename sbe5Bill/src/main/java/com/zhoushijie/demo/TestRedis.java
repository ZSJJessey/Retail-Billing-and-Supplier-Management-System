package com.zhoushijie.demo;


import com.zhoushijie.demo.entity.Bill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void testredis() {
        ArrayList<Bill> arrayList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Bill bill = new Bill();
            bill.setSupplier("欧亚" + i);
            arrayList.add(bill);
        }
        redisTemplate.opsForValue().set("bill1", arrayList);
    }
}
