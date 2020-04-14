package com.LinYuda.www.po.user;

/**
 * 定义一个数字逻辑错无，判断数量错误时可以将其抛出
 */
public class IllegalNumberException extends Exception {
    public IllegalNumberException() {
    }

    public IllegalNumberException(String msg) {
        super(msg);
    }
}
