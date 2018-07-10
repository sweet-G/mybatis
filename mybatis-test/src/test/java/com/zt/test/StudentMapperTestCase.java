package com.zt.test;

import com.zt.entity.Student;
import com.zt.mapper.StudentMapper;
import com.zt.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangtian
 * @date 2018/7/10
 */

public class StudentMapperTestCase {

    Logger logger = LoggerFactory.getLogger(StudentMapperTestCase.class);

    private SqlSession sqlSession;
    private StudentMapper studentMapper;

    @Before
    public void before(){
        sqlSession = MybatisUtils.getSqlSession();
        //动态代理
        studentMapper = sqlSession.getMapper(StudentMapper.class);
    }

    @After
    public void after(){
        sqlSession.close();
    }

    @Test
    public void testSave(){

        Student student = new Student();
        student.setStuName("jack");
        student.setEmail("jack@163.com");

        int count = studentMapper.save(student);
        logger.debug("受影响的行数：{}",count);

        //获得自动增长的主键值
        int id = student.getId();
        logger.debug("自动增长的主键值：{}",id);

        sqlSession.commit();

    }

    @Test
    public void testFindAll(){
        List<Student> studentList = studentMapper.findAll();

        for(Student student : studentList){
            logger.debug("student:{}",student.toString());
        }

    }

    @Test
    public void testFindById(){
        Student student = studentMapper.findById(3);
        logger.debug("student:{}",student.toString());
    }

    @Test
    public void testFindByPage(){
        List<Student> studentList = studentMapper.findByPage(0,3);

        for(Student student : studentList){
            logger.debug("student:{}",student.toString());
        }
    }

    @Test
    public void testFindByMap(){
        Map<String,Integer> maps = new HashMap<>();
        maps.put("start",0);
        maps.put("pageSize",3);

        List<Student> studentList = studentMapper.findByMap(maps);
        for(Student student : studentList){
            logger.debug("student:{}",student.toString());
        }
    }

    @Test
    public void testUpdate(){
        Student student = studentMapper.findById(2);

        student.setStuName("jack");
        student.setEmail("jsck@163.com");
        studentMapper.update(student);

        logger.debug("student:{}",student.toString());
        sqlSession.commit();

    }

    @Test
    public void testDel(){
        studentMapper.del(2);
        sqlSession.commit();
    }

}
