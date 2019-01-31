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

public class WriteAction implements Action {

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
		
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		BoardVo vo=new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setUser_no(authUser.getNo());
		
		if(request.getParameter("g_no")!=null) {
			
			int g_no=Integer.parseInt(request.getParameter("g_no"));
			int o_no=Integer.parseInt(request.getParameter("o_no"));
			int dept=Integer.parseInt(request.getParameter("dept"));
			
			vo.setG_no(g_no);
			vo.setO_no(o_no);
			vo.setDept(dept);
		}
		
		BoardDao dao=new BoardDao();
		boolean result=false;
		
		if(request.getParameter("g_no")!=null) {
			result=dao.insert_board(vo);
		}else {
		result=dao.insert(vo);
		}
		
		
		if(result) {
			WebUtils.redirect(request, response, request.getContextPath()+"/board");
		}
		
	}

}
