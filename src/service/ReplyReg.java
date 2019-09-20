package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import di.M_Action;
import model.CenterDAO;
import model.CenterDTO;

public class ReplyReg implements M_Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		CenterDTO dto = new CenterDTO();
		dto.setGid(Integer.parseInt(request.getParameter("gid")));
		dto.setLev(Integer.parseInt(request.getParameter("lev")));
		dto.setSeq(Integer.parseInt(request.getParameter("seq")));
		dto.setTitle(request.getParameter("title"));
		dto.setPw(request.getParameter("pw"));
		dto.setPname(request.getParameter("pname"));
		dto.setContent(request.getParameter("content"));
		System.out.println(dto);
					
		//DB 저장
		new CenterDAO().reply(dto);
		
		///redirect
		request.setAttribute("msg","작성되었습니다." );
		request.setAttribute("goUrl","Detail?id="+dto.getId()+"&page="+request.getAttribute("page"));
		request.setAttribute("mainUrl", "alert");

	}

}
