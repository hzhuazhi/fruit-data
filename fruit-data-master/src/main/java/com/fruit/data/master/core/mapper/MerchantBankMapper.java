package com.fruit.data.master.core.mapper;

import com.fruit.data.master.core.common.dao.BaseDao;
import com.fruit.data.master.core.model.bank.BankModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description 卡商银行/卡商银行卡的Dao层
 * @Author yoko
 * @Date 2020/9/8 15:12
 * @Version 1.0
 */
@Mapper
public interface MerchantBankMapper<T> extends BaseDao<T> {

    /**
     * @Description: 更新银行卡的使用状态
     * @param model
     * @return
     * @author yoko
     * @date 2020/9/5 19:21
     */
    public int updateUseStatus(BankModel model);
}
