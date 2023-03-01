package com.utc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.utc.constant.SetMealConstant;
import com.utc.dao.SetMealDao;
import com.utc.entity.PageResult;
import com.utc.entity.QueryPageBean;
import com.utc.pojo.Setmeal;
import com.utc.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Service(interfaceClass = SetMealService.class)
@Transactional
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    SetMealDao setMealDao;
    @Autowired
    private JedisPool jedisPool;

    @Override
    public void add(Setmeal setmeal, Integer[] checkGroupIds) {
        setMealDao.add(setmeal);
        Integer id = setmeal.getId();
        this.setAssociation(checkGroupIds, id);
        jedisPool.getResource().sadd(SetMealConstant.SET_MEAL_PIC_DB_RESOURCE, setmeal.getImg());
    }

    private void setAssociation(Integer[] checkGroupIds, Integer id) {
        if(checkGroupIds != null && checkGroupIds.length > 0) {
            for (Integer checkGroupId : checkGroupIds) {
                setMealDao.addCheckGroupAssociation(id, checkGroupId);
            }
        }
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage, pageSize);
        Page<Setmeal> page = setMealDao.findPage(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        setMealDao.deleteById(id);
    }

    @Override
    public Setmeal findById(Integer id) {
        return setMealDao.findById(id);
    }

    @Override
    public List<Integer> findCheckGroupIdsBySetMealId(Integer id) {
        return setMealDao.findCheckGroupIdsBySetMealId(id);
    }

    @Override
    public void edit(Setmeal setmeal, Integer[] checkGroupIds) {
        // 先清空所有的关联
        setMealDao.deleteCheckGroupAssociationBySetMealId(setmeal.getId());
        setMealDao.edit(setmeal);
        // 添加新的关联
        this.setAssociation(checkGroupIds, setmeal.getId());
    }
}
