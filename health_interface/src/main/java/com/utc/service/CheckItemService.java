package com.utc.service;

import com.utc.entity.PageResult;
import com.utc.entity.QueryPageBean;
import com.utc.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    int add(CheckItem checkItem);
    PageResult getList(QueryPageBean queryPageBean);

    void deleteById(Integer id);
    void update(CheckItem checkItem);
    CheckItem findById(Integer id);

    List<CheckItem> findAll();
}
