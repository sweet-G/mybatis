package com.zt.test;

import com.zt.entity.Student;
import com.zt.mapper.StudentMapper;
import com.zt.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author zhangtian
 * @date 2018/7/12
 */

public class CacheTestCase {

    Logger logger = LoggerFactory.getLogger(CacheTestCase.class);

    @Test
    public void testCach(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        List<Student> studentLists = studentMapper.findAll();
        for(Student student : studentLists){
            logger.debug("student: {}", student);
        }
        sqlSession.close();

        SqlSession sqlSession2 = MybatisUtils.getSqlSession();
        StudentMapper studentMapper2 = sqlSession2.getMapper(StudentMapper.class);

        List<Student> studentLists2 = studentMapper2.findAll();
        for(Student student : studentLists2){
            logger.debug("student: {}", student);
        }
        sqlSession.close();
    }
}
