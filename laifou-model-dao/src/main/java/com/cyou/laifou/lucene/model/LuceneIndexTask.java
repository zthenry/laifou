package com.cyou.laifou.lucene.model;

import java.util.*;

/** null (请继承改DAO，而不要直接用该DAO区操作，防止代码需要重新生成的时候覆盖自定义的方法) */
public class LuceneIndexTask {

	/** id */
	protected Long id;

	/** 内容id */
	protected Long objectId;

	/** 招聘1,求职2 */
	protected Integer objectType;

	/** (0：未处理,1：已处理) */
	protected Integer status;

	/** (0：add,1：update，2:delete) */
	protected Integer option;

	/** 任务添加时间 */
	protected Date addTime;

	/** 任务处理时间 */
	protected Date handleTime;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getObjectId() {
		return objectId;
	}
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	public Integer getObjectType() {
		return objectType;
	}
	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getOption() {
		return option;
	}
	public void setOption(Integer option) {
		this.option = option;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
}
