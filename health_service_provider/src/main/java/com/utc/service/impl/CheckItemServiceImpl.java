package com.utc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.utc.dao.CheckItemDao;
import com.utc.entity.PageResult;
import com.utc.entity.QueryPageBean;
import com.utc.pojo.CheckItem;
import com.utc.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 如果使用了 @Transactional 注解，则需要告知 dubbo 当前类实现的是哪个接口
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public int add(CheckItem checkItem) {
        return checkItemDao.add(checkItem);
    }

    @Override
    public PageResult getList(QueryPageBean queryPageBean) {
        Integer pageNum = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(pageNum, pageSize);
        Page<CheckItem> page = checkItemDao.getCheckItemList(queryString);
        long total = page.getTotal();
        List<CheckItem> result = page.getResult();
        return new PageResult(total, result);
    }

    @Override
    public void deleteById(Integer id) {
        long count = checkItemDao.getCountById(id);
        if(count > 0) {
            throw new RuntimeException();
        }

        int i = checkItemDao.deleteById(id);

        System.out.println("check item delete count: " + i);

        if (i <= 0 ) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(CheckItem checkItem) {
        long count = checkItemDao.update(checkItem);
        if(count <= 0) {
            throw new RuntimeException();
        }
    }

    @Override
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}
