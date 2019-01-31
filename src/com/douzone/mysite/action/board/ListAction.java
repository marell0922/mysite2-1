package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String kwd=(request.getParameter("kwd")==null)?"":request.getParameter("kwd");
		
		String pageNum=(request.getParameter("currentPage")==null)?"1":request.getParameter("currentPage");
		int currentPage=(Integer.parseInt(pageNum)<0)?1:Integer.parseInt(pageNum);
		
		BoardDao dao=new BoardDao();
		
		List<BoardVo> list=dao.getList(kwd, currentPage);
		
		int totalCount=dao.getCount();
		int maxPage=(totalCount%10==0)?totalCount/10:totalCount/10+1;
		int prevPage=(currentPage-2<=0)?1:(currentPage<=3)?1:(maxPage<=5)?1:(maxPage<currentPage+2)?maxPage-4:currentPage-2;
		int postPage=(currentPage+2>=maxPage)?maxPage:(currentPage<=3)?(maxPage<=5)?maxPage:5: currentPage+2;
		
		System.out.println("kwd : "+kwd);
		System.out.println("currentPage : "+currentPage);
		System.out.println("totalCount : "+totalCount);
		System.out.println("maxPage : "+maxPage);
		System.out.println("prevPage : "+prevPage);
		System.out.println("postPage : "+postPage);
		System.out.println("list : "+list);
		
		
		request.setAttribute("kwd", kwd);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("prevPage", prevPage);
		request.setAttribute("postPage", postPage);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("kwd", kwd);
		request.setAttribute("list", list);
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp");	
		
	
	}

}
