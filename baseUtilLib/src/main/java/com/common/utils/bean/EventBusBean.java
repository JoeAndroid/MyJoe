package com.common.utils.bean;


import com.common.utils.constant.BaseEventbusBean;

/**
 * EventBus 事件
 */
public class EventBusBean extends BaseEventbusBean<Object> {

    public EventBusBean(int type, Object obj) {
        super(type, obj);
    }

    //例子
    public static final int CUSTOMER_FILTER = 10000;

    //首页侧滑
    public static final int SHOP_MALL_HOME = 10001;
}
