package com.zt.mapper;

import com.zt.entity.Student;
import org.apache.ibatis.annotations.Param;

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

}
