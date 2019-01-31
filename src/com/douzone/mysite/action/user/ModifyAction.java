package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.UserDao;
import com.douzone.mysite.vo.UserVo;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String pw=request.getParameter("password");
		String gender=request.getParameter("gender");
		
		long no=Long.parseLong(request.getParameter("no"));
		
		UserVo vo=new UserVo();
		vo.setNo(no);
		vo.setName(name);
		vo.setPassword(pw);
		vo.setGender(gender);
		UserDao dao=new UserDao();
		
		boolean result=dao.modify(vo);
		
		if(result) {
			WebUtils.redirect(request, response, request.getContextPath()+"/user");
		}
	}

}
