package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		  long no = Long.parseLong(request.getParameter("no"));
	      
		  BoardDao dao=new BoardDao();
		  dao.upHit(no);
		  
		  BoardVo vo = dao.getVo(no);
	      request.setAttribute("viewVo", vo);
	      
	      WebUtils.forward(request, response, "/WEB-INF/views/board/view.jsp");
	}

}
