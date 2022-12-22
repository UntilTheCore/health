package com.utc.service;

import com.utc.entity.PageResult;
import com.utc.entity.QueryPageBean;
import com.utc.pojo.CheckItem;

public interface CheckItemService {
    int add(CheckItem checkItem);
    PageResult getList(QueryPageBean queryPageBean);
}
