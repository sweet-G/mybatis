import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zt.entity.Student;
import com.zt.entity.StudentExample;
import com.zt.mapper.StudentMapper;
import com.zt.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author zhangtian
 * @date 2018/7/12
 */

public class ExampleTestCase {

   Logger logger = LoggerFactory.getLogger(ExampleTestCase.class);

   private SqlSession sqlSession;
   private StudentMapper studentMapper;

   @Before
    public void before(){
       sqlSession = MybatisUtils.getSqlSession();
       studentMapper = sqlSession.getMapper(StudentMapper.class);
   }

   @After
   public void after(){
       sqlSession.close();
   }


   @Test
   public void testInsert(){
       Student student = new Student();
       student.setStuName("小易");
       student.setEmail("yi@qq.com");
       student.setSchoolId(2);

       studentMapper.insertSelective(student);
       sqlSession.commit();

   }

   @Test
   public void testDel(){
       StudentExample studentExample = new StudentExample();
       //通过studebtExample查找schoolId为3并且stuName为jack
       studentExample.createCriteria().andSchoolIdEqualTo(3).andStuNameEqualTo("jack");

       studentMapper.deleteByExample(studentExample);
       sqlSession.commit();

   }

   @Test
   public void testUpdate(){
        Student student = studentMapper.selectByPrimaryKey(5);

        student.setStuName("jack");
        //根据主键修改
        studentMapper.updateByPrimaryKeySelective(student);

        sqlSession.commit();
   }

   @Test
   public void testFindAll(){
       StudentExample studentExample = new StudentExample();

       //排序
       //studentExample.setOrderByClause("id desc");

       //去重
       //studentExample.setDistinct(true);

       //or 或者，筛选
       //studentExample.or().andSchoolIdEqualTo(2);
       //studentExample.or().andSchoolIdEqualTo(3);

       //schoolId为2并且stuName包含alex
       //studentExample.createCriteria().andSchoolIdEqualTo(2).andStuNameLike("%alex%");

       List<Student> studentList = studentMapper.selectByExample(studentExample);
       for(Student student : studentList){
           logger.debug("student: {}",student);
       }
   }

   @Test
   public void testPage(){
       //分页，第一页每页3条数据
       PageHelper.startPage(1,3);

       //从2开始，每页3条数据
       //PageHelper.offsetPage(2,3);

       StudentExample studentExample = new StudentExample();

       List<Student> studentList = studentMapper.selectByExample(studentExample);
       for(Student student : studentList){
           logger.debug("student: {}",student);
       }

       //转换为pageInfo对象
       PageInfo<Student> pageInfo = new PageInfo<>(studentList);
       System.out.println(pageInfo.getPageNum());
       System.out.println(pageInfo.getPageSize());

   }

}
