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

public class ModifyAction implements Action {

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
		
		long board_no=Long.parseLong(request.getParameter("no"));
		
		String title=request.getParameter("title");
		String contents=request.getParameter("content");
		
		BoardVo vo=new BoardVo();
		vo.setNo(board_no);
		vo.setTitle(title);
		vo.setContent(contents);
		
		BoardDao dao=new BoardDao();
		boolean result=dao.modify(vo);
		
		if(result) {
			WebUtils.redirect(request, response, request.getContextPath()+"/board?a=view&no="+board_no);
		}else {
			WebUtils.redirect(request, response, request.getContextPath()+"/board?a=view&status=Modifyfail&no="+board_no);
		}
	}

}
