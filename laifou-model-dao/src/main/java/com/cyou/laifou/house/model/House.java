package com.cyou.laifou.house.model;

import java.util.*;

/** null (请继承改DAO，而不要直接用该DAO区操作，防止代码需要重新生成的时候覆盖自定义的方法) */
public class House {

	/** id */
	protected Long id;

	/** cityName */
	protected String cityName;

	/** houseName */
	protected String houseName;

	/** kfsName */
	protected String kfsName;

	/** address */
	protected String address;

	/** price */
	protected Integer price;

	/** createTime */
	protected Date createTime;
	
	protected Integer indexBuild;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public String getKfsName() {
		return kfsName;
	}
	public void setKfsName(String kfsName) {
		this.kfsName = kfsName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    /**
     * @return 返回 indexBuild
     */
    public Integer getIndexBuild()
    {
        return indexBuild;
    }
    /**
     * @param 对indexBuild进行赋值
     */
    public void setIndexBuild(Integer indexBuild)
    {
        this.indexBuild = indexBuild;
    }
	
	
}
