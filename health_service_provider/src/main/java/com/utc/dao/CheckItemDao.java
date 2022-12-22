package com.utc.dao;

import com.github.pagehelper.Page;
import com.utc.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;

public interface CheckItemDao {
    public int add(CheckItem checkItem);

    Page<CheckItem> getCheckItemList(String queryString);

    long getCountById(@Param("id") Integer id);
    int deleteById(Integer id);
}
