package com.fruit.data.master.core.mapper;
import com.fruit.data.master.core.common.dao.BaseDao;
import com.fruit.data.master.core.model.mobilecard.MobileCardModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description 手机卡的Dao层
 * @Author yoko
 * @Date 2020/5/18 17:22
 * @Version 1.0
 */
@Mapper
public interface MobileCardMapper<T> extends BaseDao<T> {

    /**
     * @Description: 更新手机卡的心跳
     * @param model
     * @return
     * @author yoko
     * @date 2020/9/16 11:22
    */
    public int updateHeartbeatStatus(MobileCardModel model);
}
