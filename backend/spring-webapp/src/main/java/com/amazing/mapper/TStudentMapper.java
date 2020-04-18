package com.amazing.mapper;

import com.amazing.domain.TStudent;

/**
 * @author zhangweiwei
 * @description Data Access Object for domain model
 * @date 2020-04-18 23:18:57
 */
public interface TStudentMapper {
    /**
     * @author zhangweiwei
     * @description 按主键删除记录
     * @param id 主键
     * @date 2020-04-18 23:18:57
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @author zhangweiwei
     * @description 插入记录
     * @param record 
     * @date 2020-04-18 23:18:57
     */
    int insert(TStudent record);

    /**
     * @author zhangweiwei
     * @description 选择性插入记录
     * @param record 
     * @date 2020-04-18 23:18:57
     */
    int insertSelective(TStudent record);

    /**
     * @author zhangweiwei
     * @description 按主键查询记录
     * @param id 主键
     * @date 2020-04-18 23:18:57
     */
    TStudent selectByPrimaryKey(Integer id);

    /**
     * @author zhangweiwei
     * @description 按主键选择性更新记录
     * @param record 
     * @date 2020-04-18 23:18:57
     */
    int updateByPrimaryKeySelective(TStudent record);

    /**
     * @author zhangweiwei
     * @description 按主键更新记录
     * @param record 
     * @date 2020-04-18 23:18:57
     */
    int updateByPrimaryKey(TStudent record);
}