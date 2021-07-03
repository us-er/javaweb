package com.blog.mapper;

import com.blog.bean.BlogImg;
import com.blog.bean.BlogImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogImgMapper {
    int countByExample(BlogImgExample example);

    int deleteByExample(BlogImgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogImg record);

    int insertSelective(BlogImg record);

    List<BlogImg> selectByExample(BlogImgExample example);

    BlogImg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogImg record, @Param("example") BlogImgExample example);

    int updateByExample(@Param("record") BlogImg record, @Param("example") BlogImgExample example);

    int updateByPrimaryKeySelective(BlogImg record);

    int updateByPrimaryKey(BlogImg record);
}