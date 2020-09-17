package com.fruit.data.master.core.mapper;

import com.fruit.data.master.core.common.dao.BaseDao;
import com.fruit.data.master.core.model.MobileCardDataModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description 成功数据
 * @Date 2020/9/16 21:22
 * @Version 1.0
 */
@Mapper
public interface MobileCardDataMapper<T>  extends BaseDao<T> {
    public int  addMobileCardData(MobileCardDataModel mobileCardDataModel);
}
