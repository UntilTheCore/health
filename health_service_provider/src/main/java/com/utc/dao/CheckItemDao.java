package com.utc.dao;

import com.github.pagehelper.Page;
import com.utc.entity.QueryPageBean;
import com.utc.pojo.CheckItem;

public interface CheckItemDao {
    public int add(CheckItem checkItem);
   Page<CheckItem> getCheckItemList(String queryString);
}
