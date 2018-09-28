package com.choju.fpro.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.choju.fpro.dao.BoardDAO;
import com.choju.fpro.vo.BoardVO;
import com.choju.fpro.vo.PageMaker;
/*import com.choju.fpro.vo.PageVO;*/

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO bdao;
	
	private ModelAndView mav;

	//글 목록
	public ModelAndView freeboardForm() {
		mav = new ModelAndView();
		List<BoardVO> freeboardForm = new ArrayList<BoardVO>();
		freeboardForm = bdao.freeboardForm();
		
		mav.addObject("FreeBoardlist", freeboardForm);
		mav.setViewName("freeboardForm");
		return mav;
	}
	
	public int count(int count) {
		return bdao.getListCount(count);
	}

	public List<BoardVO> getRead(PageMaker pagemaker) {
		System.out.println("서비스 pageMaker"+pagemaker.toString());
		return bdao.getRead(pagemaker);
	}
	
	//글쓰기
	public ModelAndView boardWrite(BoardVO boardVO,HttpServletResponse response) throws IOException {
		mav=new ModelAndView();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		int result = bdao.boardWrite(boardVO);
		if(result==0) {
			//글쓰기 실패시
			out.println("<script>");
			out.println("alert('다시 작성해주시기 바랍니다.');");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		} else {
			//글쓰기 성공시
			out.println("<script>");
			out.println("alert('글쓰기에 성공했습니다.');");
			out.println("window.location.href='freeboardForm';");
			out.println("</script>");
			out.close();
			mav.setViewName("redirect:/freeboardForm");
		}
		
		return mav;
	}

	//조회수 증가 처리
	public void increaseHit(int board_Num) {
		bdao.increaseHit(board_Num);
	}
	
	//글 상세보기
	public ModelAndView boardView(int board_Num) {
		mav = new ModelAndView();
		BoardVO boardview = bdao.boardView(board_Num);
		mav.setViewName("boardView");
		mav.addObject("boardview", boardview);
		return mav;
	}
	
	//글 수정 업데이트
	public void boardModify1(BoardVO boardVO) {
		bdao.boardModify1(boardVO);
	}

	//글 삭제하기
	public void boardDelete(int board_Num) {
		bdao.boardDelete(board_Num);
	}

	
}
