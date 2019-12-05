package com.htyl.park.employee.provider;/**
 * @description 非springboot的测试项
 * @author: weiguang
 * @create: 7:08 下午 2019/11/21
 **/

import com.htyl.park.common.util.IDGenerator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @description 非springboot的测试项
 * @author: weiguang
 * @create: 7:08 下午  2019/11/21
 **/

public class NoSpringBootTest {

    // 测试位操作
    @Test
    public void TestBitOperation() {
        for(long val = 10; val < 90000000;val++) {
            if (!isOn(val,30)){
                long afterOn = turnOn(val,30);
                long afterOff = turnOf(afterOn,30);
                assertEquals(val,afterOff);
            }
        }
    }

    // 当前位是否为1
    public boolean isOn(long val, int index) {
        return (val >> index & 0x00000001) > 0;
    }

    public long turnOn(long val,int index){
        return (0x00000001 << index) | val;
    }

    public long turnOf(long val,int index){
        return (0x00000001 << index)^ val;
    }

    @Test
    public void TestIDGenerator(){
        IDGenerator generator = new IDGenerator(1L);
        int id = generator.getID();
        System.out.printf("%d\n",id);
        generator.revertID(id);
        System.out.println(generator.Get());
    }
}
