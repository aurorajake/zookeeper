package com.mydemo.zookeeper.model.user;

import com.mydemo.zookeeper.model.base.ModelBase;

/**
 * 用户表
 * @author Administrator
 *
 */
public class User extends ModelBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3154604542403276946L;

	private int id;
    private int state;
    private String nickName;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
    
    
}
