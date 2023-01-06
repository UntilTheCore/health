package com.utc.service;

import com.utc.entity.PageResult;
import com.utc.entity.QueryPageBean;
import com.utc.pojo.Setmeal;

public interface SetMealService {
    void add(Setmeal setmeal, Integer[] checkGroupIds);
    PageResult findPage(QueryPageBean queryPageBean);
    void deleteById(Integer id);
}
