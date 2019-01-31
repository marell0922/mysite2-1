package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		  long no = Long.parseLong(request.getParameter("no"));
	      int currentPage = Integer.parseInt((request.getParameter("currentPage")==null)?"1":request.getParameter("currentPage"));
	      
	      boolean result = new BoardDao().delete(no);
	      
	      if(result) {
	      WebUtils.redirect(request, response, request.getContextPath() + "/board?currentPage=" + currentPage);
	      }

	}

}
