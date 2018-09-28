package com.choju.fpro.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.choju.fpro.dao.MemberDAO;
import com.choju.fpro.vo.MemberVO;

@Service
public class MemberService {

	@Autowired // 바로 밑 한줄에만 적용
	private MemberDAO memberDAO;

	private ModelAndView mav;

	private MemberVO memberVO;

	@Autowired
	private BCryptPasswordEncoder passEncoder;

	@Autowired
	private HttpSession session;

	// 회원가입 메소드
	public ModelAndView memberJoin(MemberVO memberVO) {
		mav = new ModelAndView();
		int result = memberDAO.memberJoin(memberVO);
		System.out.println("!!!!!!!!!!!SERVICE memberVO" + memberVO.toString());
		if (result == 0) {
			// 회원가입 실패하면 다시 joinForm으로
			mav.setViewName("joinForm");
		} else {
			// 회원가입 성공하면 loginForm으로 이동
			mav.setViewName("Login");
		}
		return mav;
	}

	// id중복체크
	public void idOverlap(String id, HttpServletResponse response) throws IOException {
		memberVO = new MemberVO();
		memberVO = memberDAO.idOverlap(id);
		if (memberVO == null) {
			response.getWriter().print("1");
		} else {
			response.getWriter().print("0");
		}
	}

	// 로그인처리 메소드
	public ModelAndView memberLogin(MemberVO memberVO, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		mav = new ModelAndView();
		System.out.println("사용자 입력 ID" + memberVO.getId());
		System.out.println("사용자 입력 비번 : " + memberVO.getPassword());
		MemberVO loginMember = memberDAO.memberLogin(memberVO); // DB에서 회원정보 가져오기
		System.out.println("service DB" + loginMember);
		System.out.println("사용자 입력 비번 : " + memberVO.getPassword());
		PrintWriter out = response.getWriter();
		
		// spring-security를 활용한 비밀번호 확인
	/*	if (passEncoder.matches(memberVO.getPassword(), loginMember.getPassword())
				&& memberVO.getId().equals(loginMember.getId())) { // true, false로 날아옴
			session.setAttribute("session_id", memberVO.getId());
			mav.addObject("loginMember", loginMember); // 모델담기
			mav.setViewName("main"); // 뷰담기
			
*/
		if(loginMember.getId().equals(memberVO.getId())&&loginMember.getPassword().equals(memberVO.getPassword()))
		{
			session.setAttribute("session_id", memberVO.getId());
			mav.addObject("loginMember", loginMember); // 모델담기
			mav.setViewName("MemberLogin"); //로그인후 나오는것.
			System.out.println("오류test서비스");
		} else { // 로그인 실패
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		}
		return mav;
	}

	// 회원 리스트
	public ModelAndView memberList() {
		mav = new ModelAndView();
		List<MemberVO> memberList = memberDAO.memberList();

		mav.addObject("memberList", memberList);
		mav.setViewName("memberList"); // 목적지 설정

		return mav;
	}

	// 회원정보보기
	public ModelAndView memberView(String id) {
		mav = new ModelAndView();
		MemberVO viewMember = memberDAO.memberView(id);
		mav.addObject("viewMember", viewMember); // 담고
		mav.setViewName("memberView"); // 목적지로 전달
		return mav;
	}

	// 회원삭제
	public void memberDelete(String id) {
		memberDAO.memberDelete(id);
	}

}
