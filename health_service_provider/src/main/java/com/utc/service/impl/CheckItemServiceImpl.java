package com.utc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.utc.dao.CheckItemDao;
import com.utc.pojo.CheckItem;
import com.utc.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
}
