package com.htyl.park.employee.biz;/**
 * @description 用户创建请求业务对象
 * @author: weiguang
 * @create: 3:37 下午 2019/11/20
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 *@description 用户创建请求业务对象
 *
 *@author: weiguang
 *
 *@create: 3:37 下午  2019/11/20
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateReq {

    @NotNull(message = "{user.account.not_null}")
    @Range(min = 4,max = 16,message = "{user.account.range}")
    private String account;


    @NotNull(message = "{user.password.not_null}")
    @Range(min = 6,max = 20,message = "{user.password.range}")
    private String password;

    private String name;

    private LocalDateTime birthday;

    private LocalDateTime entryDate;

    private String sex;

    @Email
    private String email;

    private String address;

    @NotNull
    @NotEmpty
    private String employeeNumber;

    private String phone;

    private List<Long> roleIds;

    private List<Long> siteIds;
}
