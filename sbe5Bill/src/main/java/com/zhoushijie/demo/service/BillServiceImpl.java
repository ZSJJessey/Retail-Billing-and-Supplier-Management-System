package com.zhoushijie.demo.service;

import com.zhoushijie.demo.entity.Bill;
import com.zhoushijie.demo.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillMapper billMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<Bill> findBillByWhere(Bill bill) {
        //判断缓存里面是否有值  从缓存中拿（get）名为list的元素  (List<Bill>)--》强制转型
        List<Bill> list = (List<Bill>) redisTemplate.opsForValue().get("list");
        if (list == null) {
            //如果没有则调用接口多条件查询的方法拿到数据库查询到的结果
            List<Bill> billByWhere = billMapper.findBillByWhere(bill);
            //放到名为list的缓存中，显示在可视化工具Redis
            redisTemplate.opsForValue().set("list", billByWhere);
        }else {
            //如果缓存有值则更新缓存
            List<Bill> billByWhere = billMapper.findBillByWhere(bill);
            //放到名为list的缓存中，显示在可视化工具Redis
            redisTemplate.opsForValue().set("list", billByWhere);
        }
        //最后再从缓存中拿（get）名为list的元素
        return (List<Bill>) redisTemplate.opsForValue().get("list");
//        return billMapper.findBillByWhere(bill);
    }

    @Override
    public int insertBill(Bill bill) {
        int i = billMapper.insertBill(bill);
        if (i > 0) {
            //key为“insertBill”名并以加以更新的主键
            redisTemplate.opsForValue().set("insertBill" + bill.getBillid(), bill);
            //set更新完在页面上显示所有更新完的数据
            List<Bill> billByWhere = billMapper.findBillByWhere(null);
            redisTemplate.opsForValue().set("list", billByWhere);
            i = 1;
        }
        return i;
    }

    @Override
    public Bill selectBill(Integer id) {
        return billMapper.selectBill(id);
    }

    @Override
    public int updateBill(Bill bill) {
        int i = billMapper.updateBill(bill);
        if (i > 0) {
            //key为“updateBill”名并以加以更新的主键
            redisTemplate.opsForValue().set("updateBill" + bill.getBillid(), bill);
            //set更新完在页面上显示所有更新完的数据
            List<Bill> billByWhere = billMapper.findBillByWhere(null);
            redisTemplate.opsForValue().set("list", billByWhere);
            i = 1;
        }
        return i;
    }

    @Override
    public int deleteBillById(Integer id) {
        int i = billMapper.deleteBillById(id);
        if (i > 0) {
            redisTemplate.delete("deleteBillById" + id);
            List<Bill> billByWhere = billMapper.findBillByWhere(null);
            redisTemplate.opsForValue().set("list", billByWhere);
            i = 1;
        }
        return i;
    }
}
