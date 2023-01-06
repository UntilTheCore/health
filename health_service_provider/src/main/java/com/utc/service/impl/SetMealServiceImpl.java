package com.utc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.utc.dao.SetMealDao;
import com.utc.pojo.Setmeal;
import com.utc.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = SetMealService.class)
@Transactional
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    SetMealDao setMealDao;

    @Override
    public void add(Setmeal setmeal, Integer[] checkGroupIds) {
        setMealDao.add(setmeal);
        Integer id = setmeal.getId();

        if(checkGroupIds != null && checkGroupIds.length > 0) {
            for (Integer checkGroupId : checkGroupIds) {
                setMealDao.addCheckGroupAssociation(id, checkGroupId);
            }
        }
    }


    @Override
    public void addAssociation(Integer[] checkGroupIds) {

    }
}
