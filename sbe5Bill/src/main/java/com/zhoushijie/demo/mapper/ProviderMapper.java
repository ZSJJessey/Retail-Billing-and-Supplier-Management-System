package com.zhoushijie.demo.mapper;


import com.zhoushijie.demo.entity.Provider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProviderMapper {
    public List<Provider> findProviderByWhere(Provider provider);

    public int insertProvider(Provider provider);

    public Provider selectProvider(Integer id);

    public int updateProvider(Provider provider);

    @Delete("delete from providertable where id=#{id}")
    public int deleteProviderById(Integer id);

//    //得到所以的供应商信息
//    public  List<Provider> getProviderDetail();
}
