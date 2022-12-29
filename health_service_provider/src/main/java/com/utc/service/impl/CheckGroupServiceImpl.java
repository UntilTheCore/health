package com.utc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.utc.dao.CheckGroupDao;
import com.utc.entity.PageResult;
import com.utc.entity.QueryPageBean;
import com.utc.pojo.CheckGroup;
import com.utc.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Override
    public void add(CheckGroup checkGroup, Integer[] ids) {
        checkGroupDao.add(checkGroup);
        Integer id = checkGroup.getId();
        if(ids != null && ids.length > 0) {
            for (Integer checkItemId: ids) {
                Map<String, Integer> stringIntegerHashMap = new HashMap<>();
                stringIntegerHashMap.put("checkGroupId", id);
                stringIntegerHashMap.put("checkItemId", checkItemId);
                checkGroupDao.addAssociation(stringIntegerHashMap);
            }
        }
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckGroup> page = checkGroupDao.findPage(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

}
