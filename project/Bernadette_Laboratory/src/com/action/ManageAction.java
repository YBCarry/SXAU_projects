package com.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dao.GonggaoDao;
import com.dao.LabDao;
import com.dao.LiuyanDao;
import com.dao.UserDao;
import com.dao.YuyueDao;
import com.model.Gonggao;
import com.model.Lab;
import com.model.Liuyan;
import com.model.User;
import com.model.Yuyue;
import com.opensymphony.xwork2.ActionSupport;
import com.util.Pager;
import com.util.Util;


public class ManageAction extends ActionSupport {

	private static final long serialVersionUID = -4304509122548259589L;

	private String url = "./";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	// 获取请求对象
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return request;
	}

	// 获取响应对象
	public HttpServletResponse getResponse() {
		HttpServletResponse response = ServletActionContext.getResponse();
		return response;
	}

	// 获取session对象
	public HttpSession getSession() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		return session;
	}

	// 获取输出对象
	public PrintWriter getPrintWriter() {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return writer;
	}
	
	
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	//登入请求
	public String login() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter  writer = this.getPrintWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String  role = request.getParameter("role");
		User user = userDao.selectBean(" where username = '" + username
				+ "' and password= '" + password + "' and role= "+role +" and deletestatus=0 ");
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			this.setUrl("index.jsp");
			return "redirect";
		} else {
			writer.print("<script language=javascript>alert('用户名或者密码错误');window.location.href='login.jsp';</script>");
		}
		return null;
	}
	
	//用户退出
	public String loginout() {
		HttpServletRequest request = this.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		this.setUrl("login.jsp");
		return SUCCESS;
	}
	
	//跳转到修改密码页面
	public String changepwd() {
		this.setUrl("password.jsp");
		return SUCCESS;
	}
	
	//修改密码操作
	public void changepwd2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter  writer = this.getPrintWriter();
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		User bean = userDao.selectBean(" where username= '"+u.getUsername()+"' and password= '"+password1+"' and deletestatus=0");
		if(bean!=null){
			bean.setPassword(password2);
			userDao.updateBean(bean);
			writer.print("<script language=javascript>alert('修改成功');window.location.href='method!changepwd';</script>");
		}else{
			writer.print("<script language=javascript>alert('原密码错误');window.location.href='method!changepwd';</script>");
		}
	}
	
	//学生列表
	public String userlist() {
		HttpServletRequest request = this.getRequest();

		//控制左边菜单栏的展示或者闭合
		String menu = request.getParameter("menu");

		request.setAttribute("menu",menu );
		
		
		String username = request.getParameter("username");
		
		String xingming = request.getParameter("xingming");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (xingming != null && !"".equals(xingming)) {

			sb.append("xingming like '%" + xingming + "%'");
			sb.append(" and ");
			request.setAttribute("xingming", xingming);
		}
		
		if (username != null && !"".equals(username)) {

			sb.append("username like '%" + username + "%'");
			sb.append(" and ");
			request.setAttribute("username", username);
		}
		
	
		sb.append("   deletestatus=0 and role=1 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!userlist?menu="+menu, "共有" + total + "条记录"));
		request.setAttribute("url", "method!userlist?menu="+menu);
		request.setAttribute("url2", "method!user");
		request.setAttribute("title", "学生管理");
		this.setUrl("user/userlist.jsp");
		return SUCCESS;

	}
	
	//跳转到添加学生页面
	public String useradd() {
		HttpServletRequest request = this.getRequest();
		String menu = request.getParameter("menu");
		request.setAttribute("menu",menu );
		
		request.setAttribute("url", "method!useradd2?menu="+menu);
		request.setAttribute("title", "添加新学生");
		this.setUrl("user/useradd.jsp");
		return SUCCESS;
	}
	
	//添加学生操作
	public void useradd2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter  writer = this.getPrintWriter();
			
		String username = request.getParameter("username");
		String banji = request.getParameter("banji");
		String dianhua = request.getParameter("dianhua");
		String xingming = request.getParameter("xingming");
	
		User bean = userDao.selectBean(" where deletestatus=0 and username='"+username+"' ");
		if(bean==null){
			bean = new User();
			bean.setBanji(banji);
			bean.setCreatetime(Util.getTime());
			bean.setDianhua(dianhua);
			bean.setPassword("111111");
			bean.setUsername(username);
			bean.setXingming(xingming);
			bean.setRole(1);
			userDao.insertBean(bean);
			
			String menu = request.getParameter("menu");
			writer.print("<script language=javascript>alert('操作成功');window.location.href='method!userlist?menu="+menu+"';</script>");
			
		}else{
			String menu = request.getParameter("menu");
			writer.print("<script language=javascript>alert('操作失败，该用户名已经存在');window.location.href='method!userlist?menu="+menu+"';</script>");
		}
	
	}
	
	//跳转到更新学生页面
	public String userupdate() {
		HttpServletRequest request = this.getRequest();
		String menu = request.getParameter("menu");
		request.setAttribute("menu",menu );
		
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!userupdate2?id="+bean.getId()+"&menu="+menu);
		request.setAttribute("title", "学生修改");
		this.setUrl("user/userupdate.jsp");
		return SUCCESS;
	}
	
	//更新学生操作
	public void userupdate2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter  writer = this.getPrintWriter();
		
		String banji = request.getParameter("banji");
		String dianhua = request.getParameter("dianhua");
		String xingming = request.getParameter("xingming");
		
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		bean.setBanji(banji);
		bean.setDianhua(dianhua);
		bean.setXingming(xingming);
		
		userDao.updateBean(bean);
		
		String menu = request.getParameter("menu");
		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!userlist?menu="+menu+"';</script>");
	}
	
	//删除学生操作
	public void userdelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter  writer = this.getPrintWriter();
		
		User bean = userDao.selectBean(" where id= "+ request.getParameter("id"));
		bean.setDeletestatus(1);
		userDao.updateBean(bean);

		String menu = request.getParameter("menu");
		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!userlist?menu="+menu+"';</script>");
	}
	
	//跳转到查看学生页面
	public String userupdate3() {
		HttpServletRequest request = this.getRequest();
		User bean = userDao.selectBean(" where id= "+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "学生查看");
		String menu = request.getParameter("menu");
		request.setAttribute("menu",menu );
		this.setUrl("user/userupdate3.jsp");
		return SUCCESS;
	}
	
	
	private LabDao labDao;

	public LabDao getLabDao() {
		return labDao;
	}

	public void setLabDao(LabDao labDao) {
		this.labDao = labDao;
	}
	
	//实验室列表
	public String lablist() {
		HttpServletRequest request = this.getRequest();

		//控制左边菜单栏的展示或者闭合
		String menu = request.getParameter("menu");

		request.setAttribute("menu",menu );
		
		
		String mingchen = request.getParameter("mingchen");
		
				
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (mingchen != null && !"".equals(mingchen)) {

			sb.append("mingchen like '%" + mingchen + "%'");
			sb.append(" and ");
			request.setAttribute("mingchen", mingchen);
		}

		sb.append("   deletestatus=0 and fenlei='教师实验室' order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = labDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", labDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!lablist?menu="+menu, "共有" + total + "条记录"));
		request.setAttribute("url", "method!lablist?menu="+menu);
		request.setAttribute("url2", "method!lab");
		request.setAttribute("title", "教师实验室管理");
		this.setUrl("lab/lablist.jsp");
		return SUCCESS;

	}
	
	//跳转到添加实验室页面
	public String labadd() {
		HttpServletRequest request = this.getRequest();
		String menu = request.getParameter("menu");
		request.setAttribute("menu",menu );
		
		request.setAttribute("url", "method!labadd2?menu="+menu);
		request.setAttribute("title", "添加新实验室");
		this.setUrl("lab/labadd.jsp");
		return SUCCESS;
	}
	

	//添加实验室操作
	public void labadd2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter  writer = this.getPrintWriter();
		
		String fzr = request.getParameter("fzr");
		String leixing = request.getParameter("leixing");
		String dianhua = request.getParameter("dianhua");
		String jianjie = request.getParameter("jianjie");
		String mingchen = request.getParameter("mingchen");
		
		Lab bean = new Lab();
		bean.setDianhua(dianhua);
		bean.setFzr(fzr);
		bean.setJianjie(jianjie);
		bean.setLeixing(leixing);
		bean.setMingchen(mingchen);
		bean.setFenlei("教师实验室");
		
		
		labDao.insertBean(bean);
		
		String menu = request.getParameter("menu");
		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!lablist?menu="+menu+"';</script>");
		
	}

	//跳转到更新实验室页面
	public String labupdate() {
		HttpServletRequest request = this.getRequest();
		String menu = request.getParameter("menu");
		request.setAttribute("menu",menu );
		
		Lab bean = labDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!labupdate2?id="+bean.getId()+"&menu="+menu);
		request.setAttribute("title", "实验室修改");
		this.setUrl("lab/labupdate.jsp");
		return SUCCESS;
	}
	
	//更新实验室操作
	public void labupdate2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter  writer = this.getPrintWriter();
		
		String fzr = request.getParameter("fzr");
		String leixing = request.getParameter("leixing");
		String dianhua = request.getParameter("dianhua");
		String jianjie = request.getParameter("jianjie");
		String mingchen = request.getParameter("mingchen");

		
		Lab bean = labDao.selectBean(" where id= "+ request.getParameter("id"));
		
		bean.setDianhua(dianhua);
		bean.setFzr(fzr);
		bean.setJianjie(jianjie);
		bean.setLeixing(leixing);
		bean.setMingchen(mingchen);
		
		labDao.updateBean(bean);
		
		String menu = request.getParameter("menu");
		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!lablist?menu="+menu+"';</script>");
	}
	
	//删除实验室操作
	public void labdelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter  writer = this.getPrintWriter();
		
		Lab bean = labDao.selectBean(" where id= "+ request.getParameter("id"));
		bean.setDeletestatus(1);
		labDao.updateBean(bean);
	
		String menu = request.getParameter("menu");
		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!lablist?menu="+menu+"';</script>");
	}
	
	//跳转到查看实验室页面
	public String labupdate3() {
		HttpServletRequest request = this.getRequest();
		Lab bean = labDao.selectBean(" where id= "+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "实验室查看");
		String menu = request.getParameter("menu");
		request.setAttribute("menu",menu );
		this.setUrl("lab/labupdate3.jsp");
		return SUCCESS;
	}
	
	//实验室列表
	public String lablist3() {
		HttpServletRequest request = this.getRequest();

		//控制左边菜单栏的展示或者闭合
		String menu = request.getParameter("menu");

		request.setAttribute("menu",menu );
		
		
		String mingchen = request.getParameter("mingchen");
		
				
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (mingchen != null && !"".equals(mingchen)) {

			sb.append("mingchen like '%" + mingchen + "%'");
			sb.append(" and ");
			request.setAttribute("mingchen", mingchen);
		}

		sb.append("   deletestatus=0 and fenlei='学生实验室' order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = labDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", labDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!lablist3?menu="+menu, "共有" + total + "条记录"));
		request.setAttribute("url", "method!lablist3?menu="+menu);
		request.setAttribute("url2", "method!lab");
		request.setAttribute("title", "学生实验室管理");
		this.setUrl("lab/lablist3.jsp");
		return SUCCESS;

	}
	
	//跳转到添加实验室页面
	public String labadd10() {
		HttpServletRequest request = this.getRequest();
		String menu = request.getParameter("menu");
		request.setAttribute("menu",menu );
		
		request.setAttribute("url", "method!labadd20?menu="+menu);
		request.setAttribute("title", "添加新实验室");
		this.setUrl("lab/labadd10.jsp");
		return SUCCESS;
	}
	
	//添加实验室操作
	public void labadd20() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter  writer = this.getPrintWriter();
		
		String fzr = request.getParameter("fzr");
		String leixing = request.getParameter("leixing");
		String dianhua = request.getParameter("dianhua");
		String jianjie = request.getParameter("jianjie");
		String mingchen = request.getParameter("mingchen");
		String renshu = request.getParameter("renshu");
		
		Lab bean = new Lab();
		bean.setDianhua(dianhua);
		bean.setFzr(fzr);
		bean.setJianjie(jianjie);
		bean.setLeixing(leixing);
		bean.setMingchen(mingchen);
		bean.setFenlei("学生实验室");
		bean.setRenshu(Integer.parseInt(renshu));
		
		labDao.insertBean(bean);
		
		String menu = request.getParameter("menu");
		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!lablist3?menu="+menu+"';</script>");	
		
	}
	
	//跳转到更新实验室页面
	public String labupdate10() {
		HttpServletRequest request = this.getRequest();
		String menu = request.getParameter("menu");
		request.setAttribute("menu",menu );
		
		Lab bean = labDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!labupdate20?id="+bean.getId()+"&menu="+menu);
		request.setAttribute("title", "实验室修改");
		this.setUrl("lab/labupdate10.jsp");
		return SUCCESS;
	}
	
	//更新实验室操作
	public void labupdate20() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter  writer = this.getPrintWriter();
		
		String fzr = request.getParameter("fzr");
		String leixing = request.getParameter("leixing");
		String dianhua = request.getParameter("dianhua");
		String jianjie = request.getParameter("jianjie");
		String mingchen = request.getParameter("mingchen");
		String renshu = request.getParameter("renshu");
		
		Lab bean = labDao.selectBean(" where id= "+ request.getParameter("id"));
		
		bean.setDianhua(dianhua);
		bean.setFzr(fzr);
		bean.setJianjie(jianjie);
		bean.setLeixing(leixing);
		bean.setMingchen(mingchen);
		bean.setRenshu(Integer.parseInt(renshu));
		
		labDao.updateBean(bean);
		
		String menu = request.getParameter("menu");
		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!lablist3?menu="+menu+"';</script>");
	}
	
	
	//删除实验室操作
	public void labdelete10() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter  writer = this.getPrintWriter();
		
		Lab bean = labDao.selectBean(" where id= "+ request.getParameter("id"));
		bean.setDeletestatus(1);
		labDao.updateBean(bean);
	
		String menu = request.getParameter("menu");
		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!lablist3?menu="+menu+"';</script>");
	}
	
	//跳转到查看实验室页面
	public String labupdate30() {
		HttpServletRequest request = this.getRequest();
		Lab bean = labDao.selectBean(" where id= "+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "实验室查看");
		String menu = request.getParameter("menu");
		request.setAttribute("menu",menu );
		this.setUrl("lab/labupdate30.jsp");
		return SUCCESS;
	}
	
	
	private GonggaoDao gonggaoDao;

	public GonggaoDao getGonggaoDao() {
		return gonggaoDao;
	}

	public void setGonggaoDao(GonggaoDao gonggaoDao) {
		this.gonggaoDao = gonggaoDao;
	}
	

	//公告列表
	public String gonggaolist() {
		HttpServletRequest request = this.getRequest();

		//控制左边菜单栏的展示或者闭合
		String menu = request.getParameter("menu");

		request.setAttribute("menu",menu );
		
		
		String gtitle = request.getParameter("gtitle");
		
				
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (gtitle != null && !"".equals(gtitle)) {

			sb.append("gtitle like '%" + gtitle + "%'");
			sb.append(" and ");
			request.setAttribute("gtitle", gtitle);
		}	
	
		sb.append("   deletestatus=0 order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = gonggaoDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", gonggaoDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!gonggaolist?menu="+menu, "共有" + total + "条记录"));
		request.setAttribute("url", "method!gonggaolist?menu="+menu);
		request.setAttribute("url2", "method!gonggao");
		request.setAttribute("title", "公告管理");
		this.setUrl("gonggao/gonggaolist.jsp");
		return SUCCESS;

	}
	
	//跳转到添加公告页面
	public String gonggaoadd() {
		HttpServletRequest request = this.getRequest();
		String menu = request.getParameter("menu");
		request.setAttribute("menu",menu );
		
		request.setAttribute("url", "method!gonggaoadd2?menu="+menu);
		request.setAttribute("title", "添加新公告");
		this.setUrl("gonggao/gonggaoadd.jsp");
		return SUCCESS;
	}

	//添加公告操作
	public void gonggaoadd2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter  writer = this.getPrintWriter();
		
		String gtitle = request.getParameter("gtitle");
		String neirong = request.getParameter("neirong");
		
		Gonggao bean = new Gonggao();
		bean.setGtitle(gtitle);
		bean.setNeirong(neirong);
		bean.setShijian(Util.getTime());
		
		gonggaoDao.insertBean(bean);
		
		String menu = request.getParameter("menu");
		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!gonggaolist?menu="+menu+"';</script>");
		
	}
	
	//跳转到更新公告页面
	public String gonggaoupdate() {
		HttpServletRequest request = this.getRequest();
		String menu = request.getParameter("menu");
		request.setAttribute("menu",menu );
		
		Gonggao bean = gonggaoDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!gonggaoupdate2?id="+bean.getId()+"&menu="+menu);
		request.setAttribute("title", "公告修改");
		this.setUrl("gonggao/gonggaoupdate.jsp");
		return SUCCESS;
	}
	
	//更新公告操作
	public void gonggaoupdate2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter  writer = this.getPrintWriter();
		
		String gtitle = request.getParameter("gtitle");
		String neirong = request.getParameter("neirong");
	
		Gonggao bean = gonggaoDao.selectBean(" where id= "+ request.getParameter("id"));
		
		bean.setGtitle(gtitle);
		bean.setNeirong(neirong);
		
		gonggaoDao.updateBean(bean);
		
		String menu = request.getParameter("menu");
		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!gonggaolist?menu="+menu+"';</script>");
	}
		
	//删除公告操作
	public void gonggaodelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter  writer = this.getPrintWriter();
		
		Gonggao bean = gonggaoDao.selectBean(" where id= "+ request.getParameter("id"));
		bean.setDeletestatus(1);
		gonggaoDao.updateBean(bean);

		String menu = request.getParameter("menu");
		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!gonggaolist?menu="+menu+"';</script>");
	}
	
	//跳转到查看公告页面
	public String gonggaoupdate3() {
		HttpServletRequest request = this.getRequest();
		Gonggao bean = gonggaoDao.selectBean(" where id= "+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "公告查看");
		String menu = request.getParameter("menu");
		request.setAttribute("menu",menu );
		this.setUrl("gonggao/gonggaoupdate3.jsp");
		return SUCCESS;
	}
	
	
	//公告列表
	public String gonggaolist2() {
		HttpServletRequest request = this.getRequest();

		//控制左边菜单栏的展示或者闭合
		String menu = request.getParameter("menu");

		request.setAttribute("menu",menu );
		
		
		String gtitle = request.getParameter("gtitle");
		
				
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (gtitle != null && !"".equals(gtitle)) {

			sb.append("gtitle like '%" + gtitle + "%'");
			sb.append(" and ");
			request.setAttribute("gtitle", gtitle);
		}
	
		sb.append("   deletestatus=0 order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = gonggaoDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", gonggaoDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!gonggaolist2?menu="+menu, "共有" + total + "条记录"));
		request.setAttribute("url", "method!gonggaolist2?menu="+menu);
		request.setAttribute("url2", "method!gonggao");
		request.setAttribute("title", "公告查询");
		this.setUrl("gonggao/gonggaolist2.jsp");
		return SUCCESS;

	}
		
	//实验室列表
	public String lablist2() {
		HttpServletRequest request = this.getRequest();

		//控制左边菜单栏的展示或者闭合
		String menu = request.getParameter("menu");

		request.setAttribute("menu",menu );
		
		
		String mingchen = request.getParameter("mingchen");
		
				
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (mingchen != null && !"".equals(mingchen)) {

			sb.append("mingchen like '%" + mingchen + "%'");
			sb.append(" and ");
			request.setAttribute("mingchen", mingchen);
		}		
	
		sb.append("   deletestatus=0  and fenlei ='教师实验室' order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = labDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", labDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!lablist2?menu="+menu, "共有" + total + "条记录"));
		request.setAttribute("url", "method!lablist2?menu="+menu);
		request.setAttribute("url2", "method!lab");
		request.setAttribute("title", "实验室查询");
		this.setUrl("lab/lablist2.jsp");
		return SUCCESS;

	}
	
	
	private YuyueDao yuyueDao;

	public YuyueDao getYuyueDao() {
		return yuyueDao;
	}

	public void setYuyueDao(YuyueDao yuyueDao) {
		this.yuyueDao = yuyueDao;
	}
	
	
	//预约列表
	public String yuyuelist() {
		HttpServletRequest request = this.getRequest();

		//控制左边菜单栏的展示或者闭合
		String menu = request.getParameter("menu");

		request.setAttribute("menu",menu );
		
		
		String mingchen = request.getParameter("mingchen");
		
				
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (mingchen != null && !"".equals(mingchen)) {

			sb.append(" lab.mingchen like '%" + mingchen + "%'");
			sb.append(" and ");
			request.setAttribute("mingchen", mingchen);
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
	
		sb.append("   deletestatus=0 and user.id="+user.getId()+" order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = yuyueDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", yuyueDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!yuyuelist?menu="+menu, "共有" + total + "条记录"));
		request.setAttribute("url", "method!yuyuelist?menu="+menu);
		request.setAttribute("url2", "method!yuyue");
		request.setAttribute("title", "预约管理");
		this.setUrl("yuyue/yuyuelist.jsp");
		return SUCCESS;

	}
		
	//跳转到预约页面
	public String yuyueadd() {
		HttpServletRequest request = this.getRequest();
		String menu = request.getParameter("menu");
		String labid = request.getParameter("labid");
		request.setAttribute("menu",menu );
		
		request.setAttribute("url", "method!yuyueadd2?menu="+menu+"&labid="+labid);
		request.setAttribute("title", "预约实验室");
		this.setUrl("yuyue/yuyueadd.jsp");
		return SUCCESS;
	}
	
	//预约实验室操作
	public void yuyueadd2() throws Exception {
		HttpServletRequest request = this.getRequest();
		PrintWriter  writer = this.getPrintWriter();
		String menu = request.getParameter("menu");
		
		String jieshu = request.getParameter("jieshu");//结束使用时间
		String labid = request.getParameter("labid");
		String shiyong = request.getParameter("shiyong");//开始使用时间
		String shuoming = request.getParameter("shuoming");
		
		long t1 = Util.parseTime(shiyong).getTime();//开始使用时间
		
		long t2 = Util.parseTime(jieshu).getTime();//结束使用时间
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if(t1>=t2){
			
			writer.print("<script language=javascript>alert('操作失败，开始使用时间必须小于结束使用时间');window.location.href='method!yuyueadd?menu="+menu+"&labid="+labid+"';</script>");
			return ;
		}
		
		long now = new Date().getTime();
		
		if(t1<=now){
			writer.print("<script language=javascript>alert('操作失败，开始使用时间必须大于当前时间');window.location.href='method!yuyueadd?menu="+menu+"&labid="+labid+"';</script>");
			return ;
		}
		
		//先取出所有的审核中且没过期的实验室
		List<Yuyue> list2 = yuyueDao.selectBeanList(0, 9999," where deletestatus=0 and lab.id="+labid+" and shenhe='审核中' and j1>"+now+" and user.id=  "+user.getId());
		
		int flag = 0;
		for(Yuyue bean:list2){
			if(bean.getS1()>=t1&&bean.getS1()<=t2&&bean.getJ1()>=t2){
				flag = 1;
				break;
			}
			
			if(bean.getS1()<=t1&&bean.getJ1()>=t1&&bean.getJ1()<=t2){
				flag = 1;
				break;
			}
			
			
			if(bean.getS1()<=t1&&bean.getJ1()>=t2){
				flag = 1;
				break;
			}
			
			if(bean.getS1()>=t1&&bean.getJ1()<=t2){
				flag = 1;
				break;
			}
			
		}
		
		if(flag==1){
			writer.print("<script language=javascript>alert('操作失败，在该时间段内您已提交实验室预约，正在审核中');window.location.href='method!yuyueadd?menu="+menu+"&labid="+labid+"';</script>");
			return ;
		}
		
		
		//判断该时间段有没有成功预约的实验室
		
		//先取出所有的审核通过且没过期的实验室
		List<Yuyue> list = yuyueDao.selectBeanList(0, 9999," where deletestatus=0 and lab.id="+labid+" and shenhe='审核通过' and j1>"+now+"   ");
		
		flag = 0;
		for(Yuyue bean:list){
			if(bean.getS1()>=t1&&bean.getS1()<=t2&&bean.getJ1()>=t2){
				flag = 1;
				break;
			}
			
			if(bean.getS1()<=t1&&bean.getJ1()>=t1&&bean.getJ1()<=t2){
				flag = 1;
				break;
			}
			
			
			if(bean.getS1()<=t1&&bean.getJ1()>=t2){
				flag = 1;
				break;
			}
			
			if(bean.getS1()>=t1&&bean.getJ1()<=t2){
				flag = 1;
				break;
			}
		}
		
		if(flag==1){
			writer.print("<script language=javascript>alert('操作失败，在该时间段内该实验室已被预订');window.location.href='method!yuyueadd?menu="+menu+"&labid="+labid+"';</script>");
			return ;
		}
		
		Yuyue bean = new Yuyue();
		
		bean.setJieshu(jieshu);
		bean.setLab(labDao.selectBean(" where id= "+labid));
		bean.setShenhe("审核中");
		bean.setShijian(Util.getTime());
		bean.setShiyong(shiyong);
		bean.setShuoming(shuoming);
		bean.setUser(user);
		bean.setJ1(t2);
		bean.setS1(t1);		
		
		yuyueDao.insertBean(bean);
				
		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!yuyuelist?menu="+menu+"';</script>");
		
		
		
	}
	
	//删除预约操作
	public void yuyuedelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter  writer = this.getPrintWriter();
		
		Yuyue bean = yuyueDao.selectBean(" where id= "+ request.getParameter("id"));
		bean.setDeletestatus(1);
		yuyueDao.updateBean(bean);
	
		

		String menu = request.getParameter("menu");
		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!yuyuelist?menu="+menu+"';</script>");
	}
	
	
	//跳转到查看预约页面
	public String yuyueupdate3() {
		HttpServletRequest request = this.getRequest();
		Yuyue bean = yuyueDao.selectBean(" where id= "+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "预约查看");
		String menu = request.getParameter("menu");
		request.setAttribute("menu",menu );
		this.setUrl("yuyue/yuyueupdate3.jsp");
		return SUCCESS;
	}
	
	//预约列表
	public String yuyuelist2() {
		HttpServletRequest request = this.getRequest();

		//控制左边菜单栏的展示或者闭合
		String menu = request.getParameter("menu");

		request.setAttribute("menu",menu );
		
		
		String mingchen = request.getParameter("mingchen");
		
		String xingming = request.getParameter("xingming");
		
				
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (mingchen != null && !"".equals(mingchen)) {

			sb.append(" lab.mingchen like '%" + mingchen + "%'");
			sb.append(" and ");
			request.setAttribute("mingchen", mingchen);
		}
		
		if (xingming != null && !"".equals(xingming)) {

			sb.append(" user.xingming like '%" + xingming + "%'");
			sb.append(" and ");
			request.setAttribute("xingming", xingming);
		}
		
	
		sb.append("   deletestatus=0 and shenhe='审核中'  and  lab.fenlei='教师实验室' order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = yuyueDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", yuyueDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!yuyuelist2?menu="+menu, "共有" + total + "条记录"));
		request.setAttribute("url", "method!yuyuelist2?menu="+menu);
		request.setAttribute("url2", "method!yuyue");
		request.setAttribute("title", "预约管理");
		this.setUrl("yuyue/yuyuelist2.jsp");
		return SUCCESS;

	}
	
	

		//跳转到审核预约页面
		public String yuyueupdate() {
			HttpServletRequest request = this.getRequest();
			String menu = request.getParameter("menu");
			request.setAttribute("menu",menu );
			
			Yuyue bean = yuyueDao.selectBean(" where id= "
					+ request.getParameter("id"));
			request.setAttribute("bean", bean);
			request.setAttribute("url", "method!yuyueupdate2?id="+bean.getId()+"&menu="+menu);
			request.setAttribute("title", "审核预约");
			this.setUrl("yuyue/yuyueupdate.jsp");
			return SUCCESS;
		}
		
		//审核预约操作
		public void yuyueupdate2() throws IOException {
			HttpServletRequest request = this.getRequest();
			PrintWriter  writer = this.getPrintWriter();
			String menu = request.getParameter("menu");
			
			String shenhe = request.getParameter("shenhe");
			String fankui = request.getParameter("fankui");

			
			Yuyue bean = yuyueDao.selectBean(" where id= "+ request.getParameter("id"));
			
			
			long t1 = bean.getS1();//开始使用时间
			
			long t2 = bean.getJ1();//结束使用时间
			
			
			
			long now = new Date().getTime();
			
			if(t1<=now){
				bean.setShenhe("审核不通过");
				bean.setFankui("审核不通过，开始使用时间必须大于当前时间");
				
				yuyueDao.updateBean(bean);
				
				writer.print("<script language=javascript>alert('审核不通过，开始使用时间必须大于当前时间');window.location.href='method!yuyuelist2?menu="+menu+"';</script>");
				return ;
			}
			
			//判断该时间段有没有成功预约的实验室
			
			//先取出所有的审核通过且没过期的实验室
			List<Yuyue> list = yuyueDao.selectBeanList(0, 9999," where deletestatus=0 and lab.id="+bean.getLab().getId()+" and shenhe='审核通过' and j1>"+now+"   ");
			
			int flag = 0;
			for(Yuyue bean2:list){
				if(bean2.getS1()>=t1&&bean2.getS1()<=t2&&bean2.getJ1()>=t2){
					flag = 1;
					break;
				}
				
				if(bean2.getS1()<=t1&&bean2.getJ1()>=t1&&bean2.getJ1()<=t2){
					flag = 1;
					break;
				}
				
				
				if(bean2.getS1()<=t1&&bean2.getJ1()>=t2){
					flag = 1;
					break;
				}
				
				if(bean2.getS1()>=t1&&bean2.getJ1()<=t2){
					flag = 1;
					break;
				}
			}
			
			if(flag==1){
				bean.setShenhe("审核不通过");
				bean.setFankui("审核不通过，在该时间段内该实验室已被预订");
				
				yuyueDao.updateBean(bean);
				
				writer.print("<script language=javascript>alert('操作失败，在该时间段内该实验室已被预订');window.location.href='method!yuyuelist2?menu="+menu+"';</script>");
				return ;
			}
			
			bean.setShenhe(shenhe);
			bean.setFankui(fankui);
			
			yuyueDao.updateBean(bean);
			
			
			writer.print("<script language=javascript>alert('操作成功');window.location.href='method!yuyuelist2?menu="+menu+"';</script>");
		}
		
		
		//预约列表
		public String yuyuelist3() {
			HttpServletRequest request = this.getRequest();

			//控制左边菜单栏的展示或者闭合
			String menu = request.getParameter("menu");

			request.setAttribute("menu",menu );
			
			
			String mingchen = request.getParameter("mingchen");
			
			String xingming = request.getParameter("xingming");
			
					
			StringBuffer sb = new StringBuffer();
			sb.append(" where ");

			if (mingchen != null && !"".equals(mingchen)) {

				sb.append(" lab.mingchen like '%" + mingchen + "%'");
				sb.append(" and ");
				request.setAttribute("mingchen", mingchen);
			}
			
			if (xingming != null && !"".equals(xingming)) {

				sb.append(" user.xingming like '%" + xingming + "%'");
				sb.append(" and ");
				request.setAttribute("xingming", xingming);
			}
			
		
			sb.append("   deletestatus=0 and  lab.fenlei='教师实验室' order by id desc ");
			String where = sb.toString();


			int currentpage = 1;
			int pagesize = 10;
			if (request.getParameter("pagenum") != null) {
				currentpage = Integer.parseInt(request.getParameter("pagenum"));
			}
			int total = yuyueDao.selectBeanCount(where.replaceAll("order by id desc", ""));
			request.setAttribute("list", yuyueDao.selectBeanList((currentpage - 1)
					* pagesize, pagesize, where));
			request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
					currentpage, "method!yuyuelist3?menu="+menu, "共有" + total + "条记录"));
			request.setAttribute("url", "method!yuyuelist3?menu="+menu);
			request.setAttribute("url2", "method!yuyue");
			request.setAttribute("title", "教师预约记录查询");
			this.setUrl("yuyue/yuyuelist3.jsp");
			return SUCCESS;

		}
	
		
		private LiuyanDao liuyanDao;

		public LiuyanDao getLiuyanDao() {
			return liuyanDao;
		}

		public void setLiuyanDao(LiuyanDao liuyanDao) {
			this.liuyanDao = liuyanDao;
		}
		
		//留言列表
		public String liuyanlist() {
			HttpServletRequest request = this.getRequest();

			//控制左边菜单栏的展示或者闭合
			String menu = request.getParameter("menu");

			request.setAttribute("menu",menu );
			
			
			String ltitle = request.getParameter("ltitle");
			
					
			StringBuffer sb = new StringBuffer();
			sb.append(" where ");

			if (ltitle != null && !"".equals(ltitle)) {

				sb.append("ltitle like '%" + ltitle + "%'");
				sb.append(" and ");
				request.setAttribute("ltitle", ltitle);
			}
			
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");

			sb.append("   deletestatus=0 and user.id="+user.getId()+" order by id desc ");
			String where = sb.toString();


			int currentpage = 1;
			int pagesize = 10;
			if (request.getParameter("pagenum") != null) {
				currentpage = Integer.parseInt(request.getParameter("pagenum"));
			}
			int total = liuyanDao.selectBeanCount(where.replaceAll("order by id desc", ""));
			request.setAttribute("list", liuyanDao.selectBeanList((currentpage - 1)
					* pagesize, pagesize, where));
			request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
					currentpage, "method!liuyanlist?menu="+menu, "共有" + total + "条记录"));
			request.setAttribute("url", "method!liuyanlist?menu="+menu);
			request.setAttribute("url2", "method!liuyan");
			request.setAttribute("title", "留言管理");
			this.setUrl("liuyan/liuyanlist.jsp");
			return SUCCESS;

		}
		
		//跳转到留言页面
		public String liuyanadd() {
			HttpServletRequest request = this.getRequest();
			String menu = request.getParameter("menu");
			request.setAttribute("menu",menu );
			
			request.setAttribute("url", "method!liuyanadd2?menu="+menu);
			request.setAttribute("title", "留言");
			this.setUrl("liuyan/liuyanadd.jsp");
			return SUCCESS;
		}
		

		//留言操作
		public void liuyanadd2() throws IOException {
			HttpServletRequest request = this.getRequest();
			PrintWriter  writer = this.getPrintWriter();
			
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			String ltitle = request.getParameter("ltitle");
			String neirong = request.getParameter("neirong");
			
			
			Liuyan bean = new Liuyan();
			bean.setLtitle(ltitle);
			bean.setNeirong(neirong);
			bean.setShijian(Util.getTime());
			bean.setUser(user);
			bean.setZhuangtai("未回复");
			
			liuyanDao.insertBean(bean);
			
			String menu = request.getParameter("menu");
			writer.print("<script language=javascript>alert('操作成功');window.location.href='method!liuyanlist?menu="+menu+"';</script>");		
			
		}
	
		
		
		//删除留言操作
		public void liuyandelete() throws IOException {
			HttpServletRequest request = this.getRequest();
			PrintWriter  writer = this.getPrintWriter();
			
			Liuyan bean = liuyanDao.selectBean(" where id= "+ request.getParameter("id"));
			bean.setDeletestatus(1);
			liuyanDao.updateBean(bean);
		
			

			String menu = request.getParameter("menu");
			writer.print("<script language=javascript>alert('操作成功');window.location.href='method!liuyanlist?menu="+menu+"';</script>");
		}
		
		//跳转到查看留言页面
		public String liuyanupdate3() {
			HttpServletRequest request = this.getRequest();
			Liuyan bean = liuyanDao.selectBean(" where id= "+ request.getParameter("id"));
			request.setAttribute("bean", bean);
			request.setAttribute("title", "留言查看");
			String menu = request.getParameter("menu");
			request.setAttribute("menu",menu );
			this.setUrl("liuyan/liuyanupdate3.jsp");
			return SUCCESS;
		}
		
		//留言列表
		public String liuyanlist2() {
			HttpServletRequest request = this.getRequest();

			//控制左边菜单栏的展示或者闭合
			String menu = request.getParameter("menu");

			request.setAttribute("menu",menu );
			
			
			String ltitle = request.getParameter("ltitle");
			
					
			StringBuffer sb = new StringBuffer();
			sb.append(" where ");

			if (ltitle != null && !"".equals(ltitle)) {

				sb.append("ltitle like '%" + ltitle + "%'");
				sb.append(" and ");
				request.setAttribute("ltitle", ltitle);
			}
			
			sb.append("   deletestatus=0  order by id desc ");
			String where = sb.toString();


			int currentpage = 1;
			int pagesize = 10;
			if (request.getParameter("pagenum") != null) {
				currentpage = Integer.parseInt(request.getParameter("pagenum"));
			}
			int total = liuyanDao.selectBeanCount(where.replaceAll("order by id desc", ""));
			request.setAttribute("list", liuyanDao.selectBeanList((currentpage - 1)
					* pagesize, pagesize, where));
			request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
					currentpage, "method!liuyanlist2?menu="+menu, "共有" + total + "条记录"));
			request.setAttribute("url", "method!liuyanlist2?menu="+menu);
			request.setAttribute("url2", "method!liuyan");
			request.setAttribute("title", "留言管理");
			this.setUrl("liuyan/liuyanlist2.jsp");
			return SUCCESS;

		}
			
		//跳转到更新留言页面
		public String liuyanupdate() {
			HttpServletRequest request = this.getRequest();
			String menu = request.getParameter("menu");
			request.setAttribute("menu",menu );
			
			Liuyan bean = liuyanDao.selectBean(" where id= "
					+ request.getParameter("id"));
			request.setAttribute("bean", bean);
			request.setAttribute("url", "method!liuyanupdate2?id="+bean.getId()+"&menu="+menu);
			request.setAttribute("title", "留言修改");
			this.setUrl("liuyan/liuyanupdate.jsp");
			return SUCCESS;
		}
		
		//更新留言操作
		public void liuyanupdate2() throws IOException {
			HttpServletRequest request = this.getRequest();
			PrintWriter  writer = this.getPrintWriter();
			
			String huifu = request.getParameter("huifu");
			
			
			Liuyan bean = liuyanDao.selectBean(" where id= "+ request.getParameter("id"));
			
			bean.setHuifu(huifu);
			bean.setZhuangtai("已回复");
			
			liuyanDao.updateBean(bean);
			
			String menu = request.getParameter("menu");
			writer.print("<script language=javascript>alert('操作成功');window.location.href='method!liuyanlist2?menu="+menu+"';</script>");
		}
		
		
		//删除留言操作
		public void liuyandelete2() throws IOException {
			HttpServletRequest request = this.getRequest();
			PrintWriter  writer = this.getPrintWriter();
			
			Liuyan bean = liuyanDao.selectBean(" where id= "+ request.getParameter("id"));
			bean.setDeletestatus(1);
			liuyanDao.updateBean(bean);
		
			String menu = request.getParameter("menu");
			writer.print("<script language=javascript>alert('操作成功');window.location.href='method!liuyanlist2?menu="+menu+"';</script>");
		}
		
		
		//实验室列表
		public String lablist4() {
			HttpServletRequest request = this.getRequest();

			//控制左边菜单栏的展示或者闭合
			String menu = request.getParameter("menu");

			request.setAttribute("menu",menu );
			
			
			String mingchen = request.getParameter("mingchen");
			
					
			StringBuffer sb = new StringBuffer();
			sb.append(" where ");

			if (mingchen != null && !"".equals(mingchen)) {

				sb.append("mingchen like '%" + mingchen + "%'");
				sb.append(" and ");
				request.setAttribute("mingchen", mingchen);
			}
					
			sb.append("   deletestatus=0  and fenlei ='学生实验室' order by id desc ");
			String where = sb.toString();

			int currentpage = 1;
			int pagesize = 10;
			if (request.getParameter("pagenum") != null) {
				currentpage = Integer.parseInt(request.getParameter("pagenum"));
			}
			int total = labDao.selectBeanCount(where.replaceAll("order by id desc", ""));
			request.setAttribute("list", labDao.selectBeanList((currentpage - 1)
					* pagesize, pagesize, where));
			request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
					currentpage, "method!lablist4?menu="+menu, "共有" + total + "条记录"));
			request.setAttribute("url", "method!lablist4?menu="+menu);
			request.setAttribute("url2", "method!lab");
			request.setAttribute("title", "实验室查询");
			this.setUrl("lab/lablist4.jsp");
			return SUCCESS;

		}
		
		//预约列表
		public String yuyuelist10() {
			HttpServletRequest request = this.getRequest();

			//控制左边菜单栏的展示或者闭合
			String menu = request.getParameter("menu");

			request.setAttribute("menu",menu );
			
			
			String mingchen = request.getParameter("mingchen");
			
					
			StringBuffer sb = new StringBuffer();
			sb.append(" where ");

			if (mingchen != null && !"".equals(mingchen)) {

				sb.append(" lab.mingchen like '%" + mingchen + "%'");
				sb.append(" and ");
				request.setAttribute("mingchen", mingchen);
			}
			
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			
		
			sb.append("   deletestatus=0 and user.id="+user.getId()+" order by id desc ");
			String where = sb.toString();


			int currentpage = 1;
			int pagesize = 10;
			if (request.getParameter("pagenum") != null) {
				currentpage = Integer.parseInt(request.getParameter("pagenum"));
			}
			int total = yuyueDao.selectBeanCount(where.replaceAll("order by id desc", ""));
			request.setAttribute("list", yuyueDao.selectBeanList((currentpage - 1)
					* pagesize, pagesize, where));
			request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
					currentpage, "method!yuyuelist10?menu="+menu, "共有" + total + "条记录"));
			request.setAttribute("url", "method!yuyuelist10?menu="+menu);
			request.setAttribute("url2", "method!yuyue");
			request.setAttribute("title", "预约管理");
			this.setUrl("yuyue/yuyuelist10.jsp");
			return SUCCESS;

		}
		
		
		//跳转到预约页面
		public String yuyueadd10() {
			HttpServletRequest request = this.getRequest();
			String menu = request.getParameter("menu");
			String labid = request.getParameter("labid");
			request.setAttribute("menu",menu );
			
			request.setAttribute("url", "method!yuyueadd20?menu="+menu+"&labid="+labid);
			request.setAttribute("title", "预约实验室");
			this.setUrl("yuyue/yuyueadd10.jsp");
			return SUCCESS;
		}
		

		//预约实验室操作
		public void yuyueadd20() throws Exception {
			HttpServletRequest request = this.getRequest();
			PrintWriter  writer = this.getPrintWriter();
			String menu = request.getParameter("menu");
			
			String sjd = request.getParameter("sjd");//
			String labid = request.getParameter("labid");
			String riqi = request.getParameter("riqi");//
			String shuoming = request.getParameter("shuoming");
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			
			String time = "";
			if("早上8点半到10点".equals(sjd)){
				time= riqi+" 08:00";
			}
			if("早上10点半到12点".equals(sjd)){
				time= riqi+" 10:00";
			}
			if("下午2点到3点半".equals(sjd)){
				time= riqi+" 14:00";
			}
			if("下午4点到5点半".equals(sjd)){
				time= riqi+" 16:00";
			}
			
			long t1 = Util.parseTime(time).getTime();
			
			
			
			long now = new Date().getTime();
			
			if(t1<=now){
				writer.print("<script language=javascript>alert('操作失败，开始使用时间必须大于当前时间');window.location.href='method!yuyuelist10?menu="+menu+"';</script>");
				return ;
			}
			
			Yuyue yuyue = yuyueDao.selectBean(" where deletestatus=0 and user.id="+user.getId()+" and shenhe='审核中' and riqi='"+riqi+"' and sjd='"+sjd+"' ");
			if(yuyue!=null){
				writer.print("<script language=javascript>alert('在该时间你已经提交审核，不可重复提交');window.location.href='method!yuyuelist10?menu="+menu+"';</script>");
				return;
			}
			
			Lab lab = labDao.selectBean(" where id= "+labid);
					
			List<Yuyue> list = yuyueDao.selectBeanList(0, 9999, " where deletestatus=0  and shenhe='审核通过' and riqi='"+riqi+"' and sjd='"+sjd+"' ");
			
			if(list.size()>=lab.getRenshu()){
				writer.print("<script language=javascript>alert('操作失败，在该时间超过实验室限制人数');window.location.href='method!yuyuelist10?menu="+menu+"';</script>");
				return;
			}
						
			Yuyue bean = new Yuyue();
			bean.setLab(lab);
			bean.setShenhe("审核中");
			bean.setShijian(Util.getTime());
			bean.setShuoming(shuoming);
			bean.setUser(user);
			bean.setSjd(sjd);
			bean.setRiqi(riqi);
						
			yuyueDao.insertBean(bean);
			
			
			writer.print("<script language=javascript>alert('操作成功');window.location.href='method!yuyuelist10?menu="+menu+"';</script>");			
			
		}
		
		
		//删除预约操作
		public void yuyuedelete10() throws IOException {
			HttpServletRequest request = this.getRequest();
			PrintWriter  writer = this.getPrintWriter();
			
			Yuyue bean = yuyueDao.selectBean(" where id= "+ request.getParameter("id"));
			bean.setDeletestatus(1);
			yuyueDao.updateBean(bean);
		
			String menu = request.getParameter("menu");
			writer.print("<script language=javascript>alert('操作成功');window.location.href='method!yuyuelist10?menu="+menu+"';</script>");
		}
		
		
		//跳转到查看预约页面
		public String yuyueupdate30() {
			HttpServletRequest request = this.getRequest();
			Yuyue bean = yuyueDao.selectBean(" where id= "+ request.getParameter("id"));
			request.setAttribute("bean", bean);
			request.setAttribute("title", "预约查看");
			String menu = request.getParameter("menu");
			request.setAttribute("menu",menu );
			this.setUrl("yuyue/yuyueupdate30.jsp");
			return SUCCESS;
		}
		
		//预约列表
		public String yuyuelist20() {
			HttpServletRequest request = this.getRequest();

			//控制左边菜单栏的展示或者闭合
			String menu = request.getParameter("menu");

			request.setAttribute("menu",menu );
			
			
			String mingchen = request.getParameter("mingchen");
			
			String xingming = request.getParameter("xingming");
			
					
			StringBuffer sb = new StringBuffer();
			sb.append(" where ");

			if (mingchen != null && !"".equals(mingchen)) {

				sb.append(" lab.mingchen like '%" + mingchen + "%'");
				sb.append(" and ");
				request.setAttribute("mingchen", mingchen);
			}
			
			if (xingming != null && !"".equals(xingming)) {

				sb.append(" user.xingming like '%" + xingming + "%'");
				sb.append(" and ");
				request.setAttribute("xingming", xingming);
			}
			
		
			sb.append("   deletestatus=0 and shenhe='审核中'  and  lab.fenlei='学生实验室' order by id desc ");
			String where = sb.toString();


			int currentpage = 1;
			int pagesize = 10;
			if (request.getParameter("pagenum") != null) {
				currentpage = Integer.parseInt(request.getParameter("pagenum"));
			}
			int total = yuyueDao.selectBeanCount(where.replaceAll("order by id desc", ""));
			request.setAttribute("list", yuyueDao.selectBeanList((currentpage - 1)
					* pagesize, pagesize, where));
			request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
					currentpage, "method!yuyuelist20?menu="+menu, "共有" + total + "条记录"));
			request.setAttribute("url", "method!yuyuelist20?menu="+menu);
			request.setAttribute("url2", "method!yuyue");
			request.setAttribute("title", "预约管理");
			this.setUrl("yuyue/yuyuelist20.jsp");
			return SUCCESS;

		}
		
		

			//跳转到审核预约页面
			public String yuyueupdate10() {
				HttpServletRequest request = this.getRequest();
				String menu = request.getParameter("menu");
				request.setAttribute("menu",menu );
				
				Yuyue bean = yuyueDao.selectBean(" where id= "
						+ request.getParameter("id"));
				request.setAttribute("bean", bean);
				request.setAttribute("url", "method!yuyueupdate20?id="+bean.getId()+"&menu="+menu);
				request.setAttribute("title", "审核预约");
				this.setUrl("yuyue/yuyueupdate10.jsp");
				return SUCCESS;
			}
			
			//审核预约操作
			public void yuyueupdate20() throws IOException {
				HttpServletRequest request = this.getRequest();
				PrintWriter  writer = this.getPrintWriter();
				String menu = request.getParameter("menu");
				
				String shenhe = request.getParameter("shenhe");
				String fankui = request.getParameter("fankui");
				
				Yuyue bean = yuyueDao.selectBean(" where id= "+ request.getParameter("id"));			
				
				Lab lab = labDao.selectBean(" where id= "+bean.getLab().getId());
				
				
				List<Yuyue> list = yuyueDao.selectBeanList(0, 9999, " where deletestatus=0  and shenhe='审核通过' and riqi='"+bean.getRiqi()+"' and sjd='"+bean.getSjd()+"' ");
				
				if(list.size()>=lab.getRenshu()){
					bean.setShenhe("审核不通过");
					bean.setFankui("审核不通过，在该时间超过实验室限制人数");
					
					yuyueDao.updateBean(bean);
					
					writer.print("<script language=javascript>alert('审核不通过，在该时间超过实验室限制人数');window.location.href='method!yuyuelist10?menu="+menu+"';</script>");
					return;
				}
				
				bean.setShenhe(shenhe);
				bean.setFankui(fankui);
				
				yuyueDao.updateBean(bean);
				
				
				writer.print("<script language=javascript>alert('操作成功');window.location.href='method!yuyuelist20?menu="+menu+"';</script>");
			}
			
			
			//预约列表
			public String yuyuelist30() {
				HttpServletRequest request = this.getRequest();

				//控制左边菜单栏的展示或者闭合
				String menu = request.getParameter("menu");

				request.setAttribute("menu",menu );
						
				String mingchen = request.getParameter("mingchen");
				
				String xingming = request.getParameter("xingming");
				
						
				StringBuffer sb = new StringBuffer();
				sb.append(" where ");

				if (mingchen != null && !"".equals(mingchen)) {

					sb.append(" lab.mingchen like '%" + mingchen + "%'");
					sb.append(" and ");
					request.setAttribute("mingchen", mingchen);
				}
				
				if (xingming != null && !"".equals(xingming)) {

					sb.append(" user.xingming like '%" + xingming + "%'");
					sb.append(" and ");
					request.setAttribute("xingming", xingming);
				}
				
			
				sb.append("   deletestatus=0 and  lab.fenlei='学生实验室' order by id desc ");
				String where = sb.toString();


				int currentpage = 1;
				int pagesize = 10;
				if (request.getParameter("pagenum") != null) {
					currentpage = Integer.parseInt(request.getParameter("pagenum"));
				}
				int total = yuyueDao.selectBeanCount(where.replaceAll("order by id desc", ""));
				request.setAttribute("list", yuyueDao.selectBeanList((currentpage - 1)
						* pagesize, pagesize, where));
				request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
						currentpage, "method!yuyuelist30?menu="+menu, "共有" + total + "条记录"));
				request.setAttribute("url", "method!yuyuelist30?menu="+menu);
				request.setAttribute("url2", "method!yuyue");
				request.setAttribute("title", "学生预约记录查询");
				this.setUrl("yuyue/yuyuelist30.jsp");
				return SUCCESS;

			}
			
			
			
			//教师列表
			public String userlist2() {
				HttpServletRequest request = this.getRequest();

				//控制左边菜单栏的展示或者闭合
				String menu = request.getParameter("menu");

				request.setAttribute("menu",menu );
				
				
				String username = request.getParameter("username");
				
				String xingming = request.getParameter("xingming");
				
				StringBuffer sb = new StringBuffer();
				sb.append(" where ");

				if (xingming != null && !"".equals(xingming)) {

					sb.append("xingming like '%" + xingming + "%'");
					sb.append(" and ");
					request.setAttribute("xingming", xingming);
				}
				
				if (username != null && !"".equals(username)) {

					sb.append("username like '%" + username + "%'");
					sb.append(" and ");
					request.setAttribute("username", username);
				}
				
			
				sb.append("   deletestatus=0 and role=2 order by id desc ");
				String where = sb.toString();


				int currentpage = 1;
				int pagesize = 10;
				if (request.getParameter("pagenum") != null) {
					currentpage = Integer.parseInt(request.getParameter("pagenum"));
				}
				int total = userDao.selectBeanCount(where.replaceAll("order by id desc", ""));
				request.setAttribute("list", userDao.selectBeanList((currentpage - 1)
						* pagesize, pagesize, where));
				request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
						currentpage, "method!userlist2?menu="+menu, "共有" + total + "条记录"));
				request.setAttribute("url", "method!userlist2?menu="+menu);
				request.setAttribute("url2", "method!user");
				request.setAttribute("title", "教师管理");
				this.setUrl("user/userlist2.jsp");
				return SUCCESS;

			}
			
			//跳转到添加教师页面
			public String useradd10() {
				HttpServletRequest request = this.getRequest();
				String menu = request.getParameter("menu");
				request.setAttribute("menu",menu );
				
				request.setAttribute("url", "method!useradd20?menu="+menu);
				request.setAttribute("title", "添加新教师");
				this.setUrl("user/useradd10.jsp");
				return SUCCESS;
			}
			
			//添加教师操作
			public void useradd20() throws IOException {
				HttpServletRequest request = this.getRequest();
				PrintWriter  writer = this.getPrintWriter();
				
				String username = request.getParameter("username");
				String xueyuan = request.getParameter("xueyuan");
				String dianhua = request.getParameter("dianhua");
				String xingming = request.getParameter("xingming");
						
				User bean = userDao.selectBean(" where deletestatus=0 and username='"+username+"' ");
				if(bean==null){
					bean = new User();
					bean.setXueyuan(xueyuan);
					bean.setCreatetime(Util.getTime());
					bean.setDianhua(dianhua);
					bean.setPassword("111111");
					bean.setUsername(username);
					bean.setXingming(xingming);
					bean.setRole(2);
					userDao.insertBean(bean);
					
					String menu = request.getParameter("menu");
					writer.print("<script language=javascript>alert('操作成功');window.location.href='method!userlist2?menu="+menu+"';</script>");
					
				}else{
					String menu = request.getParameter("menu");
					writer.print("<script language=javascript>alert('操作失败，该用户名已经存在');window.location.href='method!userlist2?menu="+menu+"';</script>");
				}
						
			}
			
			//跳转到更新教师页面
			public String userupdate10() {
				HttpServletRequest request = this.getRequest();
				String menu = request.getParameter("menu");
				request.setAttribute("menu",menu );
				
				User bean = userDao.selectBean(" where id= "
						+ request.getParameter("id"));
				request.setAttribute("bean", bean);
				request.setAttribute("url", "method!userupdate20?id="+bean.getId()+"&menu="+menu);
				request.setAttribute("title", "教师修改");
				this.setUrl("user/userupdate10.jsp");
				return SUCCESS;
			}
			
			//更新教师操作
			public void userupdate20() throws IOException {
				HttpServletRequest request = this.getRequest();
				PrintWriter  writer = this.getPrintWriter();
				
				String xueyuan = request.getParameter("xueyuan");
				String dianhua = request.getParameter("dianhua");
				String xingming = request.getParameter("xingming");

				
				User bean = userDao.selectBean(" where id= "
						+ request.getParameter("id"));
				
				bean.setXueyuan(xueyuan);
				bean.setDianhua(dianhua);
				bean.setXingming(xingming);
				
				userDao.updateBean(bean);
				
				String menu = request.getParameter("menu");
				writer.print("<script language=javascript>alert('操作成功');window.location.href='method!userlist2?menu="+menu+"';</script>");
			}
			
			
			//删除教师操作
			public void userdelete10() throws IOException {
				HttpServletRequest request = this.getRequest();
				PrintWriter  writer = this.getPrintWriter();
				
				User bean = userDao.selectBean(" where id= "+ request.getParameter("id"));
				bean.setDeletestatus(1);
				userDao.updateBean(bean);
			
				

				String menu = request.getParameter("menu");
				writer.print("<script language=javascript>alert('操作成功');window.location.href='method!userlist2?menu="+menu+"';</script>");
			}
			
			//跳转到查看教师页面
			public String userupdate30() {
				HttpServletRequest request = this.getRequest();
				User bean = userDao.selectBean(" where id= "+ request.getParameter("id"));
				request.setAttribute("bean", bean);
				request.setAttribute("title", "教师查看");
				String menu = request.getParameter("menu");
				request.setAttribute("menu",menu );
				this.setUrl("user/userupdate30.jsp");
				return SUCCESS;
			}
		
}
