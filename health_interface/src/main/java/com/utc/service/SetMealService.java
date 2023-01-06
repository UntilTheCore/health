package com.utc.service;

import com.utc.entity.PageResult;
import com.utc.entity.QueryPageBean;
import com.utc.pojo.Setmeal;

import java.util.List;

public interface SetMealService {
    void add(Setmeal setmeal, Integer[] checkGroupIds);

    PageResult findPage(QueryPageBean queryPageBean);

    void deleteById(Integer id);

    Setmeal findById(Integer id);

    List<Integer> findCheckGroupIdsBySetMealId(Integer id);

    void edit(Setmeal setmeal, Integer[] checkGroupIds);
}
