package com.common.utils.bean;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by qiaobing on 2017/5/31.
 */

public class JsonBean<E> implements Serializable {

    // 信息
    private String msg;

    // 编码
    private String code;

    // 栏目内容
    private E result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public E getData() {
        return result;
    }

    public void setData(E result) {
        this.result = result;
    }

    public boolean isSuccess(){
        return code!=null&&!TextUtils.isEmpty(code)&&code.equals("0");
    }

}
