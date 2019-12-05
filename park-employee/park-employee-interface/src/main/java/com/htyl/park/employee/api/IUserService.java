package com.htyl.park.employee.api;

import com.htyl.park.employee.biz.UserCreateReq;
import com.htyl.park.employee.domain.User;
import com.htyl.park.employee.exceptions.UacBizException;

/**
 * @description 用户服务
 * @author: weiguang
 * @create: 10:42 上午 2019/11/15
 **/
public interface IUserService {
    /**
     *  创建用户表
     * @param partition
     * @return
     */
    Integer createTable(int partition);

    /**
     *  保存用户信息
     * @param userCreateReq
     * @return
     */
    Long saveUser(UserCreateReq userCreateReq);

    /**
     * 从数据库中删除用户数据
     * @param userId
     * @return
     */
    Integer realRemoveUser(Long userId);

    /**
     * 将用户数据更改为删除状态
     * @param userId
     * @return
     */
    Integer fakeRemoveUser(Long userId);

    /**
     *  更新用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 通过userId 查找用户
     * @param userId 用户id
     * @return 查找到的用户
     * @throws UacBizException 用户查询不到
     */
    User findUserById(Long userId) throws UacBizException;

    /**
     *  通过用户名查询用户
     * @param accountName 用户名
     * @return 用户信息
     * @throws UacBizException 用户不存在
     */
    User findUserByAccount(String accountName) throws UacBizException;

    User findUserByEmail(String email) throws UacBizException;

    User findUserByPhone(String phone) throws UacBizException;
}
