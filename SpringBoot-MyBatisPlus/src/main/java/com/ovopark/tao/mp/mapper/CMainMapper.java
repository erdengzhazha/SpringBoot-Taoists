package com.ovopark.tao.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.ovopark.tao.mp.entity.po.CMain;
import com.ovopark.tao.mp.entity.po.CMainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CMainMapper extends BaseMapper<CMain> {
    long countByExample(CMainExample example);

    int deleteByExample(CMainExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CMain record);

    int insertSelective(CMain record);

    List<CMain> selectByExample(CMainExample example);

    CMain selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CMain record, @Param("example") CMainExample example);

    int updateByExample(@Param("record") CMain record, @Param("example") CMainExample example);

    int updateByPrimaryKeySelective(CMain record);

    int updateByPrimaryKey(CMain record);
}