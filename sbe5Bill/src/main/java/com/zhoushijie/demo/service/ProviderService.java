package com.zhoushijie.demo.service;

import com.zhoushijie.demo.entity.Provider;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface ProviderService {
    public List<Provider> findProviderByWhere(Provider provider);

    public int insertProvider(Provider provider);

    public Provider selectProvider(Integer id);

    public int updateProvider(Provider provider);

    public int deleteProviderById(Integer id);
}
