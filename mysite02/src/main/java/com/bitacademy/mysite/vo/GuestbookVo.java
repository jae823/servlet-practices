package com.bitacademy.mysite.vo;

public class GuestbookVo {
	private Long no;
	private String name;
	private String contents;
	private String password;
	private String dt;

	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getDt() {
		return dt;
	}
	
	public void setDt(String dt) {
		this.dt = dt;
	}
	
	
	@Override
	public String toString() {
		return "GuestbookVo [no=" + no + ", name=" + name + ", content=" + contents + ", password=" + password + ", dt="
				+ dt + "]";
	}

}
