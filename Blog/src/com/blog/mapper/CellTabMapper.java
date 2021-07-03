package com.blog.mapper;

import com.blog.bean.CellTabExample;
import com.blog.bean.CellTabKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CellTabMapper {
    int countByExample(CellTabExample example);

    int deleteByExample(CellTabExample example);

    int deleteByPrimaryKey(CellTabKey key);

    int insert(CellTabKey record);

    int insertSelective(CellTabKey record);

    List<CellTabKey> selectByExample(CellTabExample example);

    int updateByExampleSelective(@Param("record") CellTabKey record, @Param("example") CellTabExample example);

    int updateByExample(@Param("record") CellTabKey record, @Param("example") CellTabExample example);
}