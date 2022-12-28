package com.utc.dao;

import com.github.pagehelper.Page;
import com.utc.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckItemDao {
    public int add(CheckItem checkItem);

    Page<CheckItem> getCheckItemList(String queryString);

    long getCountById(@Param("id") Integer id);
    int deleteById(Integer id);
    int update(CheckItem checkItem);
    CheckItem findById(@Param("id") Integer id);
    List<CheckItem> findAll();
}
