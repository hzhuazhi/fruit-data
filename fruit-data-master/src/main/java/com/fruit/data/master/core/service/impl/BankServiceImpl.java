package com.fruit.data.master.core.service.impl;

import com.fruit.data.master.core.common.dao.BaseDao;
import com.fruit.data.master.core.common.service.impl.BaseServiceImpl;
import com.fruit.data.master.core.mapper.BankMapper;
import com.fruit.data.master.core.model.bank.BankModel;
import com.fruit.data.master.core.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description 银行的Service层的实现层
 * @Author yoko
 * @Date 2020/5/18 19:08
 * @Version 1.0
 */
@Service
public class BankServiceImpl<T> extends BaseServiceImpl<T> implements BankService<T> {
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
    private BankMapper bankMapper;

    public BaseDao<T> getDao() {
        return bankMapper;
    }


    @Override
    public int updateUseStatus(BankModel model) {
        return bankMapper.updateUseStatus(model);
    }
}
