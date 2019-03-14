package com.dream.basics;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Result<T> implements Serializable {

    /**
     * 对象数据
     */
    private T data;
    /**
     * 获取枚举状态编码
     */
    private int code;
    /**
     * 获取枚举的消息值
     */
    private String msg;

    public Result(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
