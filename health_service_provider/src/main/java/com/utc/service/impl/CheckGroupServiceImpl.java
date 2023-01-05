package com.utc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.utc.dao.CheckGroupDao;
import com.utc.entity.PageResult;
import com.utc.entity.QueryPageBean;
import com.utc.entity.Result;
import com.utc.pojo.CheckGroup;
import com.utc.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
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
        this.setAssociation(id, ids);
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

    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    @Override
    public List<Integer> findCheckItemsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemsByCheckGroupId(id);
    }

    @Override
    public void deleteById(Integer id) {
        // 清空关联
        checkGroupDao.deleteCheckItemsByCheckGroupId(id);
        // 删除对应检查组
        checkGroupDao.deleteById(id);
    }

    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkItemIds) {
        checkGroupDao.updateCheckGroup(checkGroup);
        checkGroupDao.deleteCheckItemsByCheckGroupId(checkGroup.getId());
        this.setAssociation(checkGroup.getId(), checkItemIds);
    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }

    private void setAssociation(Integer checkGroupId, Integer[] checkItemIds) {
        if(checkItemIds != null && checkItemIds.length > 0) {
            for (Integer checkItemId : checkItemIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put("checkGroupId", checkGroupId);
                map.put("checkItemId", checkItemId);

                checkGroupDao.addAssociation(map);
            }
        }
    }
}
