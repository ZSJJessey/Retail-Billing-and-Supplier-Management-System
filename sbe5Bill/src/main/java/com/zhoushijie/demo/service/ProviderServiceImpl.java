package com.zhoushijie.demo.service;

import com.zhoushijie.demo.entity.Provider;
import com.zhoushijie.demo.mapper.ProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    ProviderMapper providerMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<Provider> findProviderByWhere(Provider provider) {
        //判断缓存里面是否有值  从缓存中拿（get）名为provider的元素  (List<Provider>)--》强制转型
        List<Provider> list = (List<Provider>) redisTemplate.opsForValue().get("provider");
        //
        if (list == null) {
            //如果没有则调用接口多条件查询的方法拿到数据库查询到的结果
            List<Provider> billByWhere = providerMapper.findProviderByWhere(provider);
            //放到名为provider的缓存中，显示在可视化工具Redis
            redisTemplate.opsForValue().set("provider", billByWhere);
        }else {
            //如果缓存有值则更新缓存
            List<Provider> billByWhere = providerMapper.findProviderByWhere(provider);
            //放到名为provider的缓存中，显示在可视化工具Redis
            redisTemplate.opsForValue().set("provider", billByWhere);
        }
        //最后再从缓存中拿（get）名为provider的元素
        return (List<Provider>) redisTemplate.opsForValue().get("provider");
//        return providerMapper.findProviderByWhere(provider);
    }

    @Override
    public int insertProvider(Provider provider) {
        int i = providerMapper.insertProvider(provider);
        if (i > 0) {
            //key为“insertProvider”名并以加以更新的主键
            redisTemplate.opsForValue().set("insertProvider" + provider.getId(), provider);
            //set更新完在页面上显示所有更新完的数据
            List<Provider> providerByWhere = providerMapper.findProviderByWhere(null);
            redisTemplate.opsForValue().set("provider", providerByWhere);
            i = 1;
        }
        return i;
    }

    @Override
    public Provider selectProvider(Integer id) {
        return providerMapper.selectProvider(id);
    }

    @Override
    public int updateProvider(Provider provider) {
        int i = providerMapper.updateProvider(provider);
        if (i > 0) {
            //key为“updateProvider”名并以加以更新的主键
            redisTemplate.opsForValue().set("updateProvider" + provider.getId(), provider);
            //set更新完在页面上显示所有更新完的数据
            List<Provider> billByWhere = providerMapper.findProviderByWhere(null);
            redisTemplate.opsForValue().set("provider", billByWhere);
            i = 1;
        }
        return i;
    }

    @Override
    public int deleteProviderById(Integer id) {
        int i = providerMapper.deleteProviderById(id);
        if (i > 0) {
            redisTemplate.delete("deleteProviderById" + id);
            List<Provider> providerById = providerMapper.findProviderByWhere(null);
            redisTemplate.opsForValue().set("provider", providerById);
            i = 1;
        }
        return i;
    }
}
