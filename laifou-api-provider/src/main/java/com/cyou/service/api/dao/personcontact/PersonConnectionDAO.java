package com.cyou.service.api.dao.personcontact;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.cyou.service.api.dao.provider.personcontact.PersonConnectionDAOProvider;
import com.cyou.service.api.personcontact.model.PersonConnection;


public interface PersonConnectionDAO
{
    public static final String ALIGNNAME ="alias_person_connection0";

    public static final String VIEW = "alias_person_connection0.`id`, alias_person_connection0.`master_phone_id` as masterPhoneId, alias_person_connection0.`slaver_phone_id` as slaverPhoneId, alias_person_connection0.`create_time` as createTime,alias_person_connection0.`relation_type` as relationType";

    public static final String TABLENAME ="person_connection";

    @Select("select "+VIEW+" from "+TABLENAME+" "+ALIGNNAME+" where   "+ALIGNNAME+".master_phone_id=#{master}")
    public List<PersonConnection> findbyMaster(@Param("master") Long master);
    
    @Select("select "+VIEW+" from "+TABLENAME+" "+ALIGNNAME+" where   "+ALIGNNAME+".master_phone_id=#{master} and "+ALIGNNAME+".slaver_phone_id=#{slaver}")
    public PersonConnection findbyMasterAndSlaver(@Param("master") Long master,@Param("slaver") Long slaver);
    
    @Select("select count(1) from "+TABLENAME+" "+ALIGNNAME+" where   "+ALIGNNAME+".master_phone_id=#{master} and "+ALIGNNAME+".slaver_phone_id=#{slaver}")
    public Integer countbyMasterAndSlaver(@Param("master") Long master,@Param("slaver") Long slaver);
    
    @Insert("insert ignore into "+TABLENAME+"(master_phone_id, slaver_phone_id,create_time,relation_type) values(#{masterPhoneId}, #{slaverPhoneId},#{createTime},#{relationType})")
    public void insert(PersonConnection personConnection);
    
    
    @Select("select "+VIEW+" from "+TABLENAME+" "+ALIGNNAME+" where   "+ALIGNNAME+".master_phone_id=#{master} and "+ALIGNNAME+".relation_type=#{relationType}")
    public List<PersonConnection> findSlaverIdByMasterAndRelationType(@Param("master") Long master,@Param("relationType") Integer relationType);
    
    @Update("update "+TABLENAME+" set `relation_type`=#{relationType} where id=#{id}")
    public void updateRelationType(@Param("relationType") Integer relationType,@Param("id") Long id);
    
    @UpdateProvider(type=PersonConnectionDAOProvider.class,method="updateRelationTypeBatch")
    public void updateRelationTypeBatch(@Param("relationType") Integer relationType,@Param("ids") String ids);
    
    
}
