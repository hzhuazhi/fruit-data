package com.fruit.data.master.core.service;

import com.fruit.data.master.core.common.service.BaseService;
import com.fruit.data.master.core.model.MobileCardDataModel;

/**
 * @Description TODO
 * @Date 2020/9/16 21:19
 * @Version 1.0
 */
public interface MobileCardDataService <T> extends BaseService<T> {
    public  int addMobileCardData(MobileCardDataModel mobileCardDataModel);
}
