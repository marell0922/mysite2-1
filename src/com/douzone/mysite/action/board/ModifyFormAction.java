package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		UserVo authUser = null;
		HttpSession session = request.getSession();
		if(session != null) {
			authUser = (UserVo)session.getAttribute("authuser");
		}
		if(authUser == null) {
			WebUtils.redirect(request, response, request.getContextPath());
			return;
		}
		
		Long no= Long.parseLong(request.getParameter("no"));
		
		BoardDao dao=new BoardDao();
		
		BoardVo modifyVo=dao.getVo(no);
		
		request.setAttribute("modifyVo", modifyVo);
		
		WebUtils.forward(request, response, "WEB-INF/views/board/modify.jsp");
	}

}
