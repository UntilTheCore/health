package com.utc.service;

import com.utc.entity.PageResult;
import com.utc.entity.QueryPageBean;
import com.utc.entity.Result;
import com.utc.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CheckGroupService {
    void add(CheckGroup checkGroup, Integer[] ids);
    PageResult findPage(QueryPageBean queryPageBean);
    CheckGroup findById(Integer id);
    List<Integer> findCheckItemsByCheckGroupId(Integer id);
    void deleteById(Integer id);
    void edit(CheckGroup checkGroup, Integer[] checkItemIds);
    List<CheckGroup> findAll();
}
