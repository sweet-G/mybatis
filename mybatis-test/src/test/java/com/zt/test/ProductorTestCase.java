package com.zt.test;

import com.zt.entity.Productor;
import com.zt.mapper.ProductorMapper;
import com.zt.mapper.StudentMapper;
import com.zt.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangtian
 * @date 2018/7/12
 */

public class ProductorTestCase {

    Logger logger = LoggerFactory.getLogger(ProductorTestCase.class);

    private SqlSession sqlSession;
    private ProductorMapper productorMapper;

    @Before
    public void before(){
        sqlSession = MybatisUtils.getSqlSession();
        //动态代理
        productorMapper = sqlSession.getMapper(ProductorMapper.class);
    }

    @After
    public void after(){
        sqlSession.close();
    }

    @Test
    public void testInsertProductor(){
        Productor productor = new Productor();
        productor.setProName("华为");
        productor.setProNum(100);

        int count = productorMapper.insertProductor(productor);
        logger.debug("受影响行数：{}", count);

        int id = productor.getId();

        sqlSession.commit();
        logger.debug("主键值：{}",id);
    }

}
