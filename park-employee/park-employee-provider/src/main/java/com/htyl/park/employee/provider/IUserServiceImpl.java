package com.htyl.park.employee.provider;/**
 * @description 用户服务实现
 * @author: weiguang
 * @create: 10:41 上午 2019/11/15
 **/

import com.htyl.park.common.enums.ErrorCode;
import com.htyl.park.common.utils.HmacSHA256Utils;
import com.htyl.park.employee.api.IUserService;
import com.htyl.park.employee.biz.UserCreateReq;
import com.htyl.park.employee.domain.User;
import com.htyl.park.employee.domain.UserExample;
import com.htyl.park.employee.exceptions.UacBizException;
import com.htyl.park.employee.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.util.Optional;

/**
 * @description 用户服务实现
 * @author: weiguang
 * @create: 10:41 上午  2019/11/15
 **/
@Slf4j
@Service(version = "1.0.0")
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 创建表
     *
     * @param partition 表后缀
     * @return
     */
    @Override
    public Integer createTable(int partition) {
        log.info("[UAC]Create table `sys_user` on partition_{}", partition);
        return userMapper.createTable(partition);
    }

    /**
     * 创建用户，对于传入参数的规则校验，使用切面进行校验，方法所实现的均为业务校验
     *
     * @param userCreateReq
     * @return
     * @throws UacBizException
     */
    @Override
    public Long saveUser(UserCreateReq userCreateReq) throws UacBizException {
        // 数据校验
        if (!checkUserExist(userCreateReq.getAccount())) {
            throw new UacBizException(ErrorCode.UAC_USER_ACCOUNT_EXISTS);
        }
        if (!checkPhoneBind(userCreateReq.getPhone())) {
            throw new UacBizException(ErrorCode.UAC_PHONE_BOUND);
        }
        if (!checkEmailBind(userCreateReq.getEmail())) {
            throw new UacBizException(ErrorCode.UAC_USER_EMAIL_BOUND);
        }
        User user = buildUserCreate(userCreateReq);
        int createResult = userMapper.insertSelective(user);
        log.info("[UAC]Create user:{},Result:{}", user.getAccount(),createResult);
        //TODO 保存用户角色关系
        return user.getUserId();
    }

    @Override
    public Integer realRemoveUser(Long userId) {
        log.info("[UAC]Real remove user,userId={}", userId);
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public Integer fakeRemoveUser(Long userId) {
        log.info("[UAC]Fake remove user,userID={}", userId);
        User user = new User();
        user.setUserId(userId);

        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int updateUser(User user) throws UacBizException {
        if (user == null || user.getUserId() == null) {
            return 0;
        }
        log.info("[UAC]Update user,userId={}", user.getUserId());
        User dbUser = userMapper.selectByPrimaryKey(user.getUserId());
        Optional.ofNullable(dbUser).orElseThrow(() -> new UacBizException(ErrorCode.UAC_USER_ID_NOT_FOUND, user.getUserId()));
        if (!StringUtils.isEmpty(user.getAccount()) && !dbUser.getAccount().equals(user.getAccount())) {
            throw new UacBizException(ErrorCode.GL_OPERATION_NOT_ALLOWED, "change user account");
        }
        // 更新用户信息的时候不允许直接修改密码，邮箱，手机,用户名
        user.setPassword(null)
                .setEmail(null)
                .setPhone(null)
                .setAccount(null)
                .setSalt(null);
        return userMapper.updateByPrimaryKeySelective(user);
        //TODO 更新用户角色关系
    }

    @Override
    public User findUserById(Long userId) throws UacBizException {
        log.info("[UAC]Find user by user_id,userId={}", userId);
        User user = userMapper.selectByPrimaryKey(userId);
        return Optional.ofNullable(user).orElseThrow(() -> new UacBizException(ErrorCode.UAC_USER_ID_NOT_FOUND));
    }

    @Override
    public User findUserByAccount(String accountName) throws UacBizException {
        log.info("[UAC]Find user by user account,account={}", accountName);
        User user = userMapper.selectByAccount(accountName);
        return Optional.ofNullable(user).orElseThrow(() -> new UacBizException(ErrorCode.UAC_USER_ACCOUNT_NOT_FOUND));
    }


    @Override
    public User findUserByEmail(String email) throws UacBizException{
        log.info("[UAC]Find user by user email,email={}", email);
        User user = userMapper.selectByEmail(email);
        return Optional.ofNullable(user).orElseThrow(() -> new UacBizException(ErrorCode.UAC_USER_EMAIL_NOT_FOUND));
    }

    @Override
    public User findUserByPhone(String phone) throws UacBizException{
        log.info("[UAC]Find user by user phone,phone={}", phone);
        User user = userMapper.selectByPhone(phone);
        return Optional.ofNullable(user).orElseThrow(() -> new UacBizException(ErrorCode.UAC_USER_PHONE_NOT_FOUND));
    }

    // 修改密码

    // 修改邮箱

    // 修改手机号码


    private User buildUserCreate(UserCreateReq req) {
        User user = new User();
        BeanUtils.copyProperties(req, user);
        // 将新用户放入数据库
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[16];
        secureRandom.nextBytes(bytes);
        String saltString = HmacSHA256Utils.byteArrayToHexString(bytes);
        String hash = HmacSHA256Utils.HashPassword(user.getAccount() + user.getPassword(), saltString);
        user.setPassword(hash);
        user.setSalt(saltString);
        return user;
    }

    /**
     * 检测账户是否存在
     *
     * @param account
     * @return
     */
    private boolean checkUserExist(String account) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountEqualTo(account);
        long l = userMapper.countByExample(example);
        return l == 0;
    }

    /**
     * 检测手机是否被绑定
     *
     * @param phone
     * @return
     */
    private boolean checkPhoneBind(@Nullable String phone) {
        if (phone == null) {
            return false;
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhoneEqualTo(phone);
        return userMapper.countByExample(userExample) == 0;
    }

    /**
     * 检测邮箱是否被绑定
     *
     * @param email
     * @return
     */
    private boolean checkEmailBind(@Nullable String email) {
        if (email == null) {
            return false;
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(email);
        return userMapper.countByExample(userExample) == 0;
    }
}
