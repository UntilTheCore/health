package com.utc.dao;

import com.github.pagehelper.Page;
import com.utc.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface CheckGroupDao {
    int add(CheckGroup checkGroup);

    Page<CheckGroup> findPage(@Param("queryString") String queryString);
    void addAssociation(Map map);
}
