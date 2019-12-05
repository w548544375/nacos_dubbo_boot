package com.htyl.park.employee.provider;

import com.htyl.park.employee.api.IUserService;
import com.htyl.park.employee.biz.UserCreateReq;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @description
 * @author: weiguang
 * @create: 5:23 下午 2019/11/20
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class IUserServiceImplTest {

    @Autowired
    private IUserService IUserService;

    private static Validator validator;

    @BeforeClass
    public static void beforeClass() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure().messageInterpolator(
                new ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator("messages"))
        ).buildValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void TestUserConstraint() {
        UserCreateReq userCreateReq = new UserCreateReq();
        userCreateReq.setAccount("1");
        userCreateReq.setPassword("2");
        Set<ConstraintViolation<Object>> validate = validator.validate(userCreateReq);
        assertEquals(4, validate.size());
        validate.forEach(a -> {
            System.out.println(a.getMessage());
        });
    }

    @Test
    public void TestCreateUser(){
        UserCreateReq userCreateReq = new UserCreateReq();
        userCreateReq.setAccount("test01");
        userCreateReq.setPassword("test01");
        userCreateReq.setEmail("111122233@163.com");
        userCreateReq.setPhone("13555555555");
        IUserService.saveUser(userCreateReq);
    }

}