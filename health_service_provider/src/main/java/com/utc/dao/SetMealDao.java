package com.utc.dao;

import com.github.pagehelper.Page;
import com.utc.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

public interface SetMealDao {
    void add(Setmeal setmeal);
    void addCheckGroupAssociation(@Param("setMealId") Integer setMealId,@Param("checkGroupId") Integer checkGroupId);
    Page<Setmeal> findPage(@Param("queryString") String queryString);
    void deleteById(@Param("id") Integer id);
}
