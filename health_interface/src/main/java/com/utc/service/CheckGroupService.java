package com.utc.service;

import com.utc.entity.PageResult;
import com.utc.entity.QueryPageBean;
import com.utc.pojo.CheckGroup;

public interface CheckGroupService {
    void add(CheckGroup checkGroup, Integer[] ids);
    PageResult findPage(QueryPageBean queryPageBean);
}
