package com.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//预约
@Entity
@Table(name="t_Yuyue")
public class Yuyue {

	@Id
	@GeneratedValue
	private int id;
	
	private int deletestatus;//表示是否删除的状态，0表示未删除，1表示删除
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;//预约的用户，外键
	
	@ManyToOne
	@JoinColumn(name="labid")
	private Lab lab;//预约的实验室，外键
	
	private String shiyong;//开始使用时间
	
	private String jieshu;//结束使用时间
	
	private long s1;//
	
	private long j1;//
	
	private String shijian;//预约时间
	
	private String shenhe;//审核
	
	@Column(name="shuoming", columnDefinition="TEXT")
	private String shuoming;//预约说明
	
	@Column(name="fankui", columnDefinition="TEXT")
	private String fankui;//审核反馈
	
	private String sjd;//学生使用时间段
	
	private String riqi;//使用日期

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeletestatus() {
		return deletestatus;
	}

	public void setDeletestatus(int deletestatus) {
		this.deletestatus = deletestatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Lab getLab() {
		return lab;
	}

	public void setLab(Lab lab) {
		this.lab = lab;
	}

	public String getShiyong() {
		return shiyong;
	}

	public void setShiyong(String shiyong) {
		this.shiyong = shiyong;
	}

	public String getJieshu() {
		return jieshu;
	}

	public void setJieshu(String jieshu) {
		this.jieshu = jieshu;
	}

	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	public String getShenhe() {
		return shenhe;
	}

	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}

	public String getShuoming() {
		return shuoming;
	}

	public void setShuoming(String shuoming) {
		this.shuoming = shuoming;
	}

	public String getFankui() {
		return fankui;
	}

	public void setFankui(String fankui) {
		this.fankui = fankui;
	}

	public long getS1() {
		return s1;
	}

	public void setS1(long s1) {
		this.s1 = s1;
	}

	public long getJ1() {
		return j1;
	}

	public void setJ1(long j1) {
		this.j1 = j1;
	}

	public String getSjd() {
		return sjd;
	}

	public void setSjd(String sjd) {
		this.sjd = sjd;
	}

	public String getRiqi() {
		return riqi;
	}

	public void setRiqi(String riqi) {
		this.riqi = riqi;
	}
	

}
