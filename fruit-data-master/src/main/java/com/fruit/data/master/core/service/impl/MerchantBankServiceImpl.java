package com.fruit.data.master.core.service.impl;

import com.fruit.data.master.core.common.dao.BaseDao;
import com.fruit.data.master.core.common.service.impl.BaseServiceImpl;
import com.fruit.data.master.core.mapper.MerchantBankMapper;
import com.fruit.data.master.core.model.bank.BankModel;
import com.fruit.data.master.core.service.MerchantBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 卡商银行/卡商银行卡的Service层的实现层
 * @Author yoko
 * @Date 2020/9/8 15:14
 * @Version 1.0
 */
@Service
public class MerchantBankServiceImpl<T> extends BaseServiceImpl<T> implements MerchantBankService<T> {
    /**
     * 5分钟.
     */
    public long FIVE_MIN = 300;

    /**
     * 3分钟
     */
    public long THREE_MIN = 180;

    /**
     * 11分钟.
     */
    public long ELEVEN_MIN = 660;

    public long TWO_HOUR = 2;



    @Autowired
    private MerchantBankMapper merchantBankMapper;

    public BaseDao<T> getDao() {
        return merchantBankMapper;
    }


    @Override
    public int updateUseStatus(BankModel model) {
        return merchantBankMapper.updateUseStatus(model);
    }
}
