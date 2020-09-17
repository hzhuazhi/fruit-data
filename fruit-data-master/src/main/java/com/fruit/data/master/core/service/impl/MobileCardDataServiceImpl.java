package com.fruit.data.master.core.service.impl;

import com.fruit.data.master.core.common.dao.BaseDao;
import com.fruit.data.master.core.common.service.impl.BaseServiceImpl;
import com.fruit.data.master.core.mapper.MobileCardDataMapper;
import com.fruit.data.master.core.model.MobileCardDataModel;
import com.fruit.data.master.core.service.MobileCardDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description 银行的Service层的实现层
 * @Date 2020/5/18 19:08
 * @Version 1.0
 */
@Service
public class MobileCardDataServiceImpl<T> extends BaseServiceImpl<T> implements MobileCardDataService<T> {


    @Autowired
    private MobileCardDataMapper mobileCardDataMapper;

    public BaseDao<T> getDao() {
        return mobileCardDataMapper;
    }


    @Override
    public int addMobileCardData(MobileCardDataModel mobileCardDataModel) {
        return mobileCardDataMapper.addMobileCardData(mobileCardDataModel);
    }
}
