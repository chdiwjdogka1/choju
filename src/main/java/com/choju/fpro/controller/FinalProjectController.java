package com.choju.fpro.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.activation.CommandMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.choju.fpro.vo.BoardVO;
import com.choju.fpro.dao.BoardDAO;
import com.choju.fpro.service.BoardService;

//-----------------Member 베타테스트 -----------------
import com.choju.fpro.service.MemberService;
import com.choju.fpro.vo.MemberVO;
import com.choju.fpro.vo.PageMaker;

//-------------------여기까지-----------------------

@Controller
public class FinalProjectController {
	private static final Logger logger = LoggerFactory.getLogger(FinalProjectController.class);
	@Autowired
	private BoardService bs;

	private ModelAndView mav;

	// --memberController 베타테스트.-------
	@Autowired
	private MemberService ms;
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	@Autowired
	private HttpSession session;
	@Autowired
	private HttpServletRequest request;
	// ------------------여기까지---------

	// 프로젝트 시작했을때 페이지 지정
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	// freeboard 메뉴 눌렸을때 나오는 화면(글목록)
	/* @RequestMapping(value = "/freeboardForm", method = RequestMethod.GET)
	public ModelAndView boardList() {
		mav = new ModelAndView();
		mav = bs.freeboardForm();
		return mav;
	} */
	
	//글 목록 페이징 처리
	@RequestMapping(value = "/freeboardForm", method = RequestMethod.GET) // /list
	public ModelAndView list(PageMaker pagemaker, Model model) {
		logger.info("START LIST");
		
		mav = new ModelAndView();
		mav = bs.freeboardForm();

		int count = 0;
		pagemaker.setPage(pagemaker.getPage());// 처음 결과 1 아무것도 없는 상태
		count = bs.count(count);//게시글 전체 개수를 가져옴
		System.out.println("count의 값 : " + count);
		// 레코드 총 갯수 구함
		pagemaker.setCount(count);//해당 페이지메이커에개수를 입력해줌
		System.out.println("count의 값 : " + count);
		// 페이지 계산
		System.out.println("컨트롤 " + pagemaker.getPage());
		System.out.println("페이지 메이커 VO start"+pagemaker.getStart());
		System.out.println("페이지 메이커 VO end"+pagemaker.getEnd());
		List<BoardVO> list = bs.getRead(pagemaker);
		System.out.println("list.size의 값은? " + list.size());
		model.addAttribute("result", list);
		model.addAttribute("pageMaker", pagemaker);

		return mav;
	}

	// 글쓰기 화면 호출
	@RequestMapping(value = "/boardwriteForm", method = RequestMethod.GET)
	public String boardwriteForm() {
		return "boardWrite";
	}

	// 글쓰기
	@RequestMapping(value = "/boardwrite", method = RequestMethod.POST)
	public ModelAndView boardWrite(@ModelAttribute BoardVO boardVO, HttpServletResponse response)
			throws IllegalStateException, IOException {
		mav = new ModelAndView();
		MultipartFile Board_File = boardVO.getBoard_File(); // 파일처리
		if (!Board_File.isEmpty()) {
			String fileName = Board_File.getOriginalFilename();
			Board_File.transferTo(new File(
					"C:\\Users\\user\\Documents\\workspace-sts-3.9.5.RELEASE\\choju\\src\\main\\webapp\\WEB-INF\\uploadFile\\"
							+ fileName));
		}
		boardVO.setBoard_FileName(Board_File.getOriginalFilename());
		mav = bs.boardWrite(boardVO, response);
		return mav;
	}

	// 글상세보기
	@RequestMapping(value = "/boardview", method = RequestMethod.GET)
	public ModelAndView boardView(@RequestParam("board_Num") int board_Num) {
		// 조회수 증가 처리
		bs.increaseHit(board_Num);
		mav = new ModelAndView();
		mav = bs.boardView(board_Num);
		return mav;
	}

	// 글수정하기
	@RequestMapping(value = "/boardmodify", method = RequestMethod.GET)
	public ModelAndView boardModify(@RequestParam("board_Num") int board_Num) {
		mav = new ModelAndView();
		mav = bs.boardView(board_Num);
		mav.setViewName("boardModify");
		return mav;
	}

	// 글수정업데이트
	@RequestMapping(value = "/boardmodify1", method = RequestMethod.POST)
	public String boardModify1(@ModelAttribute BoardVO boardVO) throws IllegalStateException, IOException {
		// 글 수정 시 파일 수정 업데이트
		MultipartFile Board_File = boardVO.getBoard_File(); // 파일처리
		if (!Board_File.isEmpty()) {
			String fileName = Board_File.getOriginalFilename();
			Board_File.transferTo(new File(
					"C:\\Users\\user\\Documents\\workspace-sts-3.9.5.RELEASE\\choju\\src\\main\\webapp\\WEB-INF\\uploadFile\\"
							+ fileName));
		}
		boardVO.setBoard_FileName(Board_File.getOriginalFilename());
		bs.boardModify1(boardVO);
		return "redirect:/freeboardForm";
	}

	// 첨부파일 다운로드
	@RequestMapping(value = "/boardFileDown", method = RequestMethod.GET)
	public void fileDown(@RequestParam("board_FileName") String board_FileName, HttpServletResponse response)
			throws Exception {
		// 무조건 팝업창 뜨게 하는!
		response.setContentType("application/octet-stream");
		String Orgname = new String(board_FileName.getBytes("UTF-8"), "iso-8859-1");
		// 파일명 지정(스펠링 중요)
		response.setHeader("Content-Disposition", "attachment;filename=\"" + Orgname + "\"");
		OutputStream os = response.getOutputStream();
		String path = "C:\\Users\\user\\Documents\\workspace-sts-3.9.5.RELEASE\\choju\\src\\main\\webapp\\WEB-INF\\uploadFile\\";
		FileInputStream fis = new FileInputStream(path + File.separator + board_FileName);
		int n = 0;
		byte[] b = new byte[512];
		while ((n = fis.read(b)) != -1) {
			os.write(b, 0, n);
		}
		fis.close();
		os.close();
	}

	// 글삭제하기
	@RequestMapping(value = "/boarddelete", method = RequestMethod.GET)
	public String boardDelete(@RequestParam("board_Num") int board_Num) {
		bs.boardDelete(board_Num);
		return "redirect:/freeboardForm";
	}
	
	

	// ----------------------Member 베타테스트 --------------------------------
	// 회원가입 처리
	@RequestMapping(value = "/memberJoin", method = RequestMethod.POST)
	public ModelAndView memberJoin(@ModelAttribute MemberVO memberVO) {
		mav = new ModelAndView();
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!MemberVO" + memberVO.toString());
		mav = ms.memberJoin(memberVO);
		return mav;
	}

	// 아이디 중복확인
	@RequestMapping(value = "idOverlap", method = RequestMethod.POST)
	public void idOverlap(HttpServletResponse response, @RequestParam("id") String id) throws IOException {
		ms.idOverlap(id, response);
	}

	// 로그인 페이지로 이동
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String Login() {
		return "Login";
	}

	// 회원가입 페이지로 이동
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String Join() {
		return "Join";
	}

	// 로그인 처리
	@RequestMapping(value = "/memberlogin", method = RequestMethod.POST)
	public ModelAndView memberlogin(@ModelAttribute MemberVO memberVO, HttpServletResponse response)
			throws IOException { // memberlogin? memberLogin?

		// user ID 및 비밀번호
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		// 로그인 처리
		memberVO.setId(id);
		memberVO.setPassword(password);
		mav = new ModelAndView();
		mav = ms.memberLogin(memberVO, response);
		System.out.println("오류test컨트롤러" + request.getRequestURL());
		System.out.println("오류test컨트롤러전체경로" + request.getContextPath());
		return mav;
	}

	// 로그아웃 처리
	@RequestMapping(value = "/logout", method = RequestMethod.GET) // 링크방식은 GET
	public String memberLogout() {
		session.invalidate();
		return "loginForm";
	}

	// 회원목록보기
	@RequestMapping(value = "/memberList", method = RequestMethod.GET)
	public ModelAndView memberList(HttpSession session) {
		mav = new ModelAndView();
		String loginMember = (String) session.getAttribute("session_id");
		if (loginMember == null || !loginMember.equals("admin")) {
			mav.setViewName("loginForm");
		} else {
			mav = ms.memberList();
		}
		return mav;
	}

	// 회원 정보 보기
	@RequestMapping(value = "/memberView", method = RequestMethod.GET)
	public ModelAndView memberView(@RequestParam("id") String id) {
		mav = new ModelAndView();
		mav = ms.memberView(id);
		return mav;
	}

	// 회원삭제하기
	@RequestMapping(value = "/memberDelete", method = RequestMethod.GET)
	public String memberDelete(@RequestParam("id") String id) {
		ms.memberDelete(id);
		return "redirect:/memberList";
	}

	// ----------------------------여기까지----------------------
}
