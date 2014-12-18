package com.cyou.laifou.house.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.cyou.laifou.house.dao.provider.HouseDAOProvider;
import com.cyou.laifou.house.model.Condition;
import com.cyou.laifou.house.model.House;

public interface HouseDAO {

public static final String ALIGNNAME ="alias_house0";

public static final String VIEW ="alias_house0.`id`, alias_house0.`city_name` as cityName, alias_house0.`house_name` as houseName, alias_house0.`kfs_name` as kfsName, alias_house0.`address`, alias_house0.`price`, alias_house0.`create_time` as createTime,alias_house0.`index_build` as indexBuild";

public static final String TABLENAME ="house";

	/** insert House */
	@Insert("insert into house (`city_name`, `house_name`, `kfs_name`, `address`, `price`, `create_time`) values (#{cityName}, #{houseName}, #{kfsName}, #{address}, #{price}, #{createTime})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id", useCache = false, flushCache = true)
	public void insert(House house);
	
	@SelectProvider(type=HouseDAOProvider.class,method="selectByCondition")
	public List<House> findByCondition(@Param("condition") Condition condition);
	
	@SelectProvider(type=HouseDAOProvider.class,method="countByCondition")
    public int countByCondition(@Param("condition") Condition condition);
	
	@Select("select "+VIEW+" from "+TABLENAME+" "+ALIGNNAME+" where "+ALIGNNAME+".index_build=#{indexBuild}")
	public List<House> findHousesByIndexBuild(@Param("indexBuild")int indexBuild);
	
	@Update("update "+TABLENAME+" set index_build=#{status} where id=#{id}")
	public void updateIndexStatus(@Param("id")Long id,@Param("status")int status);
	
	@Select("select "+VIEW+" from "+TABLENAME+" "+ALIGNNAME+" where "+ALIGNNAME+".id=#{id}")
    public House findById(@Param("id")Long id);
	
	@Select("select "+VIEW+" from "+TABLENAME+" "+ALIGNNAME+" ")
    public List<House> findAll();
	
	@Update("update "+TABLENAME+" set index_build=#{status} ")
    public void updateAllIndexStatus(@Param("status")int status);
	
	@Update("update "+TABLENAME+" set index_build=#{status} where index_build!=#{status} ")
    public void changeIndexStatus(@Param("status")int status);
	
	

}
