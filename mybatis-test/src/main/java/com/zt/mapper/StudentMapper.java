package com.zt.mapper;

import com.zt.entity.Student;
import com.zt.entity.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhangtian
 * @date 2018/7/10
 */

public interface StudentMapper {

    /**
     * 添加学生
     * @param student
     * @return student
     */
    int save(Student student);

    /**
     * 查询所有学生
     * @return List<Student>
     */
    List<Student> findAll();

    /**
     * 根据id查询
     * @param id
     * @return student
     */
    Student findById(Integer id);

    /**
     * 多个参数查询，并进行分页
     * @param start
     * @param pageSize
     * @return student
     */
    List<Student> findByPage(@Param("start") Integer start,@Param("pageSize") Integer pageSize);

    /**
     * map集合分页查询
     * @param map
     * @return student集合
     */
    List<Student> findByMap(Map<String,Integer> map);

    /**
     * 更新
     * @param student
     */
    void update(Student student);

    /**
     * 根据id删除
     * @param id
     */
    void del(Integer id);

    /**
     * 查询学校对应的学生，多对一/一对一
     * @param id
     * @return student
     */
    //Student findSchoolById(Integer id);

    /**
     * 查询学生对应的标签，一对多
     * @param id
     * @return student
     */
    //Student findTagById(Integer id);

    /**
     * 批量插入
     * @param tagList
     * @return count
     */
    int addBatch(@Param("tagList")List<Tag> tagList);

    /**
     * 注解形式、一对一/多对一
     * @param id
     * @return student对象
     */
    @Select("select student.id, stu_name, email, school_id from student where student.id = #{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "stu_name", property = "stuName"),
            @Result(column = "email", property = "email"),
            @Result(column = "school_id", property = "schoolId"),
            @Result(column = "school_id",property = "school",
                    one = @One(select = "com.zt.mapper.SchoolMapper.findById"))
    })
    Student findSchoolById(Integer id);

    /**
     * 注解形式、一对多
     * @param id
     * @return student对象
     */
    @Select("select id, stu_name, email, school_id from student where id = #{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "stu_name", property = "stuName"),
            @Result(column = "email", property = "email"),
            @Result(column = "school_id", property = "schoolId"),
            @Result(column = "id", property = "tagList",
                    many = @Many(select = "com.zt.mapper.TagMapper.findById") )
    })
    Student findTagById(Integer id);


}
