package com.htyl.park.employee.exceptions;/**
 * @description 用户验证异常
 * @author: weiguang
 * @create: 2:45 下午 2019/12/3
 **/

import com.htyl.park.common.enums.ErrorCode;
import com.htyl.park.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 *@description 用户验证异常
 *
 *@author: weiguang
 *
 *@create: 2:45 下午  2019/12/3
 **/
@Slf4j
public class UacBizException extends BusinessException {

    public UacBizException() {
    }

    public UacBizException(int code, String message) {
        super(code, message);
        log.info("[x]UacBizException,code={},message={}",this.code,super.getMessage());
    }

    public UacBizException(Integer code, String mesageformat, Object... args) {
        super(code, mesageformat, args);
        log.info("[x]UacBizException,code={},message={}",this.code,super.getMessage());
    }

    public UacBizException(ErrorCode ec, Object... args) {
        super(ec, args);
        log.info("[x]UacBizException,code={},message={}",this.code,super.getMessage());
    }

    public UacBizException(ErrorCode ec){
        super(ec.code(),ec.msg());
        log.info("[x]UacBizException,code={},message={}",this.code,super.getMessage());
    }
}
