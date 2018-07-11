package com.zt.test;

import com.zt.entity.Movie;
import com.zt.mapper.MovieMapper;
import com.zt.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangtian
 * @date 2018/7/10
 */

public class MovieMapperTestCase {

    Logger logger = LoggerFactory.getLogger(MovieMapperTestCase.class);
    private SqlSession sqlSession;
    private MovieMapper movieMapper;

    @Before
    public void before(){
        sqlSession = MybatisUtils.getSqlSession();
        movieMapper = sqlSession.getMapper(MovieMapper.class);
    }

    @After
    public void after(){
        sqlSession.close();
    }

    @Test
    public void testFindByKeys(){
        String title= "%老友记%";
        List<Movie> movieList = movieMapper.findByKeys(title);

        logger.debug("size:{}",movieList.size());
    }

    @Test
    public void testFindByParam(){
        Map<String , Object> maps = new HashMap<>();
        maps.put("title","%老友记%");
        maps.put("directory","%Bright%");

        List<Movie> movieList = movieMapper.findByParam(maps);

        logger.debug("size:{}",movieList.size());

    }

    @Test
    public void testFindById(){
        List<Integer> idList = Arrays.asList(5,2);
        List<Movie> movieList = movieMapper.findById(idList);

        logger.debug("size:{}", movieList.size());
    }



}
