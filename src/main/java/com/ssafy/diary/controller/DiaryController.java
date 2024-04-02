package com.ssafy.diary.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.diary.dto.DiaryDto;
import com.ssafy.diary.service.DiaryService;
import com.ssafy.diary.service.DiaryServiceImpl;
import com.ssafy.member.model.MemberDto;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.ParameterCheck;

@WebServlet("/diary")
public class DiaryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DiaryService diaryService;
	
	private int pgno;
	private String key;
	private String word;
	private String queryString;
	private String root;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		diaryService = DiaryServiceImpl.getDiaryService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		pgno = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
		key = ParameterCheck.nullToBlank(request.getParameter("key"));
		word = ParameterCheck.nullToBlank(request.getParameter("word"));
		queryString = "pgno="+pgno+"&key="+key+"&word="+word;
		
		String root = request.getContextPath();
		
		String path = "";
		if("list".equals(action)) {
			path = list(request, response);
			forward(request, response, path);
		} else if("view".equals(action)) {
			path = view(request, response);
			forward(request, response, path);
		} else if("mvwrite".equals(action)) {
			System.out.println("hi");
			path = "/diary/diary_write.jsp";
			redirect(request, response, path);
		} else if("write".equals(action)) {
			System.out.println("write");
			path = write(request, response);
			redirect(request, response, path);
		} else if("mvmodify".equals(action)) {
			path = mvModify(request, response);
			forward(request, response, path);
		} else if("modify".equals(action)) {
			path = modify(request, response);
			forward(request, response, path);
		} else if("delete".equals(action)) {
			path = delete(request, response);
			redirect(request, response, path);
		} else {
			redirect(request, response, path);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
	
	public void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+path);
	}
	
	private String list(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if(memberDto!=null) {
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("pgno", pgno);
				map.put("key", key);
				map.put("word", word);
				
				List<DiaryDto> list = diaryService.listDiary(map);
				request.setAttribute("diaries", list);
				
				PageNavigation pageNavigation = diaryService.makePageNavigation(map);
				request.setAttribute("navigation", pageNavigation);
				
//				return "/diary?"+queryString;
				return "/diary/diary_list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				return "/diary/diary_list.jsp";
			}			
		}else
			return "/login/login.jsp";
	}
	
	private String view(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		
		if(memberDto!=null) {			
			int diaryNo = Integer.parseInt(request.getParameter("diaryNo"));
			try {
				DiaryDto diaryDto = diaryService.getDiary(diaryNo);
				diaryService.updateHit(diaryNo);
				request.setAttribute("diary", diaryDto);
				System.out.println(diaryDto);
				return "/diary/diary_view.jsp?"+queryString;
			} catch (Exception e) {
				e.printStackTrace();
				return "/diary?action=list";
			}
		}else
			return "/login/login.jsp";
		
	}
	
	private String write(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		
		if(memberDto != null) {
			DiaryDto diaryDto = new DiaryDto();
			diaryDto.setUserId(memberDto.getUserId());
			diaryDto.setTitle(request.getParameter("title"));
			diaryDto.setColor(request.getParameter("color"));
			diaryDto.setDiaryImg(request.getParameter("imageFile"));
			diaryDto.setContent(request.getParameter("content"));
			System.out.println(diaryDto.toString());
			try {
				diaryService.writeDiary(diaryDto);
				return "/diary?action=list";
			} catch (Exception e) {
				e.printStackTrace();
				// 메인 페이지로 보낼까??
				return "/diary?action=list";
			}
		}else
			return "/login/login.jsp";
		
	}
	
	private String mvModify(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		
		if(memberDto != null) {			
			int diaryNo = Integer.parseInt(request.getParameter("diaryNo"));
			try {
				DiaryDto diaryDto = diaryService.getDiary(diaryNo);
				request.setAttribute("diary", diaryDto);
				return "/diary/diary_modify.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				return "/diary?action=list";
			}	
		}else
			return "/login/login.jsp";
		
	}
	
	private String modify(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		
		if(memberDto != null) {			
			DiaryDto diaryDto = new DiaryDto();
			diaryDto.setDiaryNo(Integer.parseInt(request.getParameter("diaryNo")));
			diaryDto.setTitle(request.getParameter("title"));
			diaryDto.setColor(request.getParameter("color"));
			diaryDto.setDiaryImg(request.getParameter("imageFile"));
			diaryDto.setContent(request.getParameter("content"));
			System.out.println(diaryDto);
			try {
				diaryService.modifyDiary(diaryDto);
				return "/diary?action=view&diaryNo="+diaryDto.getDiaryNo();
			} catch (Exception e) {
				e.printStackTrace();
				return "/diary?action=list";
			}
		}else
			return "/login/login.jsp";
		
	}
	
	private String delete(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		
		if(memberDto != null) {
			int diaryNo = Integer.parseInt(request.getParameter("diaryNo"));
			
			try {
				diaryService.deleteDiary(diaryNo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "/diary?action=list";
		}else
			return "/login/login.jsp";
		
	}
	
}
