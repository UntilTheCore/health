package com.utc.service;

import com.utc.pojo.Setmeal;

public interface SetMealService {
    void add(Setmeal setmeal, Integer[] checkGroupIds);
}
