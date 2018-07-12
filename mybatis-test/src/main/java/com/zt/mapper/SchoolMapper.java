package com.zt.mapper;

import com.zt.entity.School;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhangtian
 * @date 2018/7/12
 */

public interface SchoolMapper {

    @Select("select * from school where id = #{id}")
    School findById(Integer id);
}
