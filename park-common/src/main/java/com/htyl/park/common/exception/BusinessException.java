package com.htyl.park.common.exception;/**
 * @description 异常基类
 * @author: weiguang
 * @create: 6:33 下午 2019/11/21
 **/

import com.htyl.park.common.enums.ErrorCode;

/**
 *@description 异常基类
 *
 *@author: weiguang
 *
 *@create: 6:33 下午  2019/11/21
 **/
public class BusinessException extends RuntimeException{

    protected Integer code;

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BusinessException(int code,String message){
        super(message);
        this.code =code;
    }

    public BusinessException(Integer code,String mesageformat,Object... args){
        super(String.format(mesageformat,args));
        this.code = code;
    }

    public BusinessException(ErrorCode ec, Object... args){
        super(String.format(ec.msg(),args));
        this.code = ec.code();
    }


}
