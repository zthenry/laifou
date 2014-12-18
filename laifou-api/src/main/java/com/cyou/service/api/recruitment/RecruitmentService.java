package com.cyou.service.api.recruitment;

public interface RecruitmentService
{
    /**
     * 发布招聘信息
     * json格式数据传输
     * @param userId
     * @param recruitmentInfo
     * @return 返回生成的数据
     * @see [类、类#方法、类#成员]
     */
    String releaseRecruitment(Long userId,String recruitmentInfo);
}
