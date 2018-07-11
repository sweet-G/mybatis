package com.zt.mapper;

import com.zt.entity.Movie;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;

/**
 * @author zhangtian
 * @date 2018/7/10
 */

public interface MovieMapper {

    /**
     *根据title模糊查询
     * @param title
     * @return List<Movie>
     */
    List<Movie> findByKeys(@Param("title") String title);

    /**
     * map集合进行多个模糊查询
     * @param map
     * @return List<movie>
     */
    List<Movie> findByParam(Map<String, Object> map);

    /**
     *根据多个id进行批量查询
     * @param idList
     * @return List<Movie>
     */
    List<Movie> findById(@Param("idList") List<Integer> idList);




}
