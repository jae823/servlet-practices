package com.bitacademy.mysite.vo;

public class BoardVo {
	private Long no;
	private String title;
	private String content;
	private String name;
	private int hit;
	private String reg_date;
	private int group_no;
	private int order_no;
	private int depth;
	
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getGroup_no() {
		return group_no;
	}
	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", name=" + name + ", hit=" + hit + ", reg_date=" + reg_date
				+ ", group_no=" + group_no + ", order_no=" + order_no + ", depth=" + depth + "]";
	}
	
}
