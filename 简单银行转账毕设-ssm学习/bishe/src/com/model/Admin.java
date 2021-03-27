package com.model;

public class Admin {

	private Integer aid;
	private String admin_name;
	private String admin_pwd;
	private String old_admin_pwd;
	
	public String getOld_admin_pwd() {
		return old_admin_pwd;
	}
	public void setOld_admin_pwd(String old_admin_pwd) {
		this.old_admin_pwd = old_admin_pwd;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_pwd() {
		return admin_pwd;
	}
	public void setAdmin_pwd(String admin_pwd) {
		this.admin_pwd = admin_pwd;
	}
	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", admin_name=" + admin_name + ", admin_pwd=" + admin_pwd + "]";
	}
	
}
