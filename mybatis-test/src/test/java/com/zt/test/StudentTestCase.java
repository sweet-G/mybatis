package com.zt.test;

import com.zt.entity.Student;
import com.zt.util.MybatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author zhangtian
 * @date 2018/7/9
 */

public class StudentTestCase {

    @Test
    public void testSave() throws IOException {
        //1.读取mybatis配置文件
        Reader reader = Resources.getResourceAsReader("mybatis.xml");

        //2.创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        //3.创建sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true); //非自动提交

        //4.操作数据库对象
        Student student = new Student();
        student.setStuName("jack");
        student.setEmail("jack@163.com");

        int res = sqlSession.insert("com.zt.mapper.StudentMapper.save",student);

        Assert.assertEquals(1,res);

        //5.释放资源
        sqlSession.close();
    }

    @Test
    public void testFindAll(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        List<Student> studentList = sqlSession.selectList("com.zt.mapper.StudentMapper.findAll");

        for(Student student : studentList){
            System.out.println(student);
        }

        sqlSession.close();
    }

    @Test
    public void testFindById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        Student student = sqlSession.selectOne("com.zt.mapper.StudentMapper.findById",2);
        System.out.println(student);

        sqlSession.close();
    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession = MybatisUtils.getSqlSession(true);

        Student student = sqlSession.selectOne("com.zt.mapper.StudentMapper.findById",3);
        student.setStuName("alex");
        student.setEmail("alex@163.com");

        sqlSession.update("com.zt.mapper.StudentMapper.update",student);
        sqlSession.close();

    }

    @Test
    public void testDel(){
        SqlSession sqlSession = MybatisUtils.getSqlSession(true);
        sqlSession.delete("com.zt.mapper.StudentMapper.del",1);

        sqlSession.close();
    }

}
