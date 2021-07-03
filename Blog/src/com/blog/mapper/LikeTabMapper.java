package com.blog.mapper;

import com.blog.bean.LikeTabExample;
import com.blog.bean.LikeTabKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LikeTabMapper {
    int countByExample(LikeTabExample example);

    int deleteByExample(LikeTabExample example);

    int deleteByPrimaryKey(LikeTabKey key);

    int insert(LikeTabKey record);

    int insertSelective(LikeTabKey record);

    List<LikeTabKey> selectByExample(LikeTabExample example);

    int updateByExampleSelective(@Param("record") LikeTabKey record, @Param("example") LikeTabExample example);

    int updateByExample(@Param("record") LikeTabKey record, @Param("example") LikeTabExample example);
}