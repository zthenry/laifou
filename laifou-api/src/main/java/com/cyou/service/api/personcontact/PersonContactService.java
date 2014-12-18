package com.cyou.service.api.personcontact;

public interface PersonContactService
{
    /**
     * 导入联系人
     * 多个联系人电话用逗号隔开
     * 调用者在传入联系人电话的时候，尽量只将手机号码传过来
     * 服务端也会进行校验，将不符合的号码过滤
     * @param phoneNumber 注册人手机号
     * @param contacts    导入的联系人手机
     * @return
     * @see [类、类#方法、类#成员]
     */
    String importContacts(String phoneNumber,String contacts) throws InterruptedException;
    
    /**
     * 计算二度人脉
     * 根据一度人脉计算二度人脉
     * @param phoneNumber
     * @return
     * @see [类、类#方法、类#成员]
     */
    boolean calculateSecondDimension(String phoneNumber) throws InterruptedException;
    
    /**
     * 计算一度人脉
     * @param phoneNumber
     * @return
     * @see [类、类#方法、类#成员]
     */
    boolean calculateFirstDimensionByPhoneNumber(String phoneNumber) throws InterruptedException;
    
    /**
     * 计算一度人脉
     * @param phoneId
     * @return
     * @see [类、类#方法、类#成员]
     */
    boolean calculateFirstDimensionByPhoneId(Long phoneId) throws InterruptedException;
}
