package com.htyl.park.common.enums;

/**
 * @description 错误代码
 * @author: weiguang
 * @create: 2:22 下午 2019/12/3
 **/
public enum ErrorCode {
    /*全局错误*/
    // 操作不被允许
    GL_OPERATION_NOT_ALLOWED(800000,"Operation not allowed=>[%s]"),
    /* UAC错误，使用1开头 */
    // 用户ID未找到异常
    UAC_USER_ID_NOT_FOUND(100000,"Requested User Not Found,UserId=%s"),
    //用户名未找到异常
    UAC_USER_ACCOUNT_NOT_FOUND(100002,"Requested User Not Found,User Account=%s"),
    // 邮箱未找到
    UAC_USER_EMAIL_NOT_FOUND(100004,"Requested User Not Found,User Email=%s"),
    // 电话未找到
    UAC_USER_PHONE_NOT_FOUND(100008,"Requested User Not Found,User Phone = %s"),
    // 用户已经存在
    UAC_USER_ACCOUNT_EXISTS(100100,"user account already exists"),
    // 邮箱已被绑定存在
    UAC_USER_EMAIL_BOUND(100102,"email has been bound."),
    // 手机已经被绑定
    UAC_PHONE_BOUND(100104,"phone number has benn bound.")
    ;

    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return code;
    }

    public String msg() {
        return msg;
    }

    public static ErrorCode codeFor(int code){
        for (ErrorCode ec : ErrorCode.values()){
            if (ec.code() == code){
                return ec;
            }
        }
        return null;
    }
}
