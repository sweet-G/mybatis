package com.zt.mapper;

import com.zt.entity.Tag;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhangtian
 * @date 2018/7/12
 */

public interface TagMapper {

    @Select("select * from tag where id in (select tag_id from student_tag where student_id = #{id})")
    List<Tag> findById(Integer id);
}
