package com.ssafy.member.controller;

import java.io.IOException;
import java.util.StringTokenizer;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.member.model.service.MemberServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService;

	public void init() {
		memberService = MemberServiceImpl.getMemberService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String path = "";
		if("showlogin".equals(action)) {
			String msg = (String) request.getAttribute("msg");
			path="/login/login.jsp";
			
			if(msg != null) {
				System.out.println(msg);
				request.setAttribute("msg", msg);
				forward(request, response, path);
			}else {				
				redirect(request, response, path);	
			}
		}else if("login".equals(action)) {
			path=login(request, response);
			forward(request, response, path);
		}else if("logout".equals(action)) {
			path=logout(request, response);
			redirect(request, response, path);
		}else if("mvjoin".equals(action)) {
			path = "/login/register.jsp";
			redirect(request, response, path);
		}else if("join".equals(action)) {
			path = join(request, response);
			forward(request, response, path);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}
	private String join(HttpServletRequest request, HttpServletResponse response) {
		try {
			String userId = request.getParameter("userId");
			String userPwd = request.getParameter("userPwd");
			String userName = request.getParameter("userName");
			String email = request.getParameter("email");
			StringTokenizer st = new StringTokenizer(email, "@");
			String emailId = st.nextToken();
			String emailDomain = st.nextToken();
			
			MemberDto memberDto = new MemberDto(userId, userName, userPwd, emailId, emailDomain); 
			memberService.joinMember(memberDto);
			
			request.setAttribute("msg", "회원가입 성공!!\n" + userName + "님, 로그인해주세요!!");
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "error!! 동일 아이디가 존재합니다.");
			return "index.jsp";
		}
	}

	private String login(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		System.out.println(userId);
		System.out.println(userPwd);
		
		try {
			MemberDto memberDto = memberService.loginMember(userId, userPwd);
			if (memberDto != null) {
//				session 설정
				HttpSession session = request.getSession();
				session.setAttribute("userinfo", memberDto);

//				cookie 설정
				String idsave = request.getParameter("saveid");
				if ("ok".equals(idsave)) { // 아이디 저장을 체크 했다면.
					Cookie cookie = new Cookie("ssafy_id", userId);
					cookie.setPath(request.getContextPath());
//					크롬의 경우 400일이 최대
//					https://developer.chrome.com/blog/cookie-max-age-expires/
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40); // 40년간 저장.
					response.addCookie(cookie);
				} else { // 아이디 저장을 해제 했다면.
					Cookie cookies[] = request.getCookies();
					if (cookies != null) {
						for (Cookie cookie : cookies) {
							if ("ssafy_id".equals(cookie.getName())) {
								cookie.setMaxAge(0);
								response.addCookie(cookie);
								break;
							}
						}
					}
				}
				return "/index.jsp";
			} else {
				request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요.");
				return "/user?action=showlogin";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	private String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("userinfo");
		session.invalidate();
		return "";
	}

}
