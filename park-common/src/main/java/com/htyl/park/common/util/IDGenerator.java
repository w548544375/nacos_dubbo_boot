package com.htyl.park.common.util;/**
 * @description workID 生成器
 * @author: weiguang
 * @create: 9:00 下午 2019/11/21
 **/

/**
 * @description workID 生成器
 * @author: weiguang
 * @create: 9:00 下午  2019/11/21
 **/
public class IDGenerator {

    private long val;

    private static final long JUDGE = 0x00000001;

    public IDGenerator() {
        this.val = 0;
    }

    public IDGenerator(long val) {
        this.val = val;
    }


    /**
     * 判断索引位置的bit字节是0还是1
     *
     * @param index 索引位置[0,64)
     * @return true 索引位置为1 false 索引位置为0
     */
    private boolean isOn(int index) {
        return (val >> index & JUDGE) > 0;
    }

    /**
     * 将索引位置的值设置为1
     *
     * @param index 索引位置
     * @return 设置索引数值为1之后的数值
     */
    private long turnOn(int index) {
        return (JUDGE << index) | val;
    }

    /**
     * 将索引位置的值设置为0
     *
     * @param index 索引位置
     * @return 设置了索引数值为0之后的数值
     */
    private long turnOff(int index) {
        return (JUDGE << index) ^ val;
    }

    /**
     * 从最低位开始，获取第一个bit位为0的序列号
     *
     * @return
     */
    public int getID() {
        int reVal = 0;
        for (int i = 0; i < Long.SIZE - 1; i++) {
            if (!isOn(i)) {
                val = i;
                break;
            }
        }
        this.val = turnOn(reVal);
        return reVal;
    }

    /**
     * 归还id
     *
     * @param val
     */
    public void revertID(int val) {
        if (val < 0 || val > Long.SIZE -1){
            return;
        }
        this.val = turnOff(val);
    }


    public long Get(){
        return this.val;
    }
}
