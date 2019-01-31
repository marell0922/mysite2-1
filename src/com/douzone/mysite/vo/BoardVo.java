package com.douzone.mysite.vo;

public class BoardVo {
	private long no;
	private String title;
	private String contents;
	private String write_date;
	private int hit;
	private int g_no;
	private int o_no;
	private int dept;
	private long user_no;
	private String user_name;
	public void setDept(int dept) {
		this.dept=dept;
	}
	public int getDept() {
		return dept;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return contents;
	}
	public void setContent(String content) {
		this.contents = content;
	}
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getG_no() {
		return g_no;
	}
	public void setG_no(int g_no) {
		this.g_no = g_no;
	}
	public int getO_no() {
		return o_no;
	}
	public void setO_no(int o_no) {
		this.o_no = o_no;
	}
	public long getUser_no() {
		return user_no;
	}
	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + contents + ", write_date=" + write_date
				+ ", hit=" + hit + ", g_no=" + g_no + ", o_no=" + o_no + ", user_no=" + user_no + ", user_name="
				+ user_name + "dept"+dept+"]";
	}
	
	
}
