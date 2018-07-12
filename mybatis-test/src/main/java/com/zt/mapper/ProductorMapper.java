package com.zt.mapper;

import com.zt.entity.Productor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * @author zhangtian
 * @date 2018/7/12
 */

public interface ProductorMapper {

    /**
     * 新增产品
     * @param productor
     * 逐渐自动增长
     * @return productor
     */
    @Insert("insert into productor (pro_name,pro_num) values (#{proName},#{proNum})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertProductor(Productor productor);

    //update、delete、select一致

}
