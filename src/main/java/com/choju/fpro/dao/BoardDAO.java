package com.choju.fpro.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.choju.fpro.vo.BoardVO;
import com.choju.fpro.vo.PageMaker;
/*import com.choju.fpro.vo.PageVO;*/


@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<BoardVO> freeboardForm() {
		return sqlSession.selectList("Board.freeboardForm");
	}

	public int boardWrite(BoardVO boardVO) {
		return sqlSession.insert("Board.boardwrite", boardVO);
	}
	// 조회수 증가처리
	public void increaseHit(int board_Num) {
		sqlSession.update("Board.increaseHit", board_Num);
	}

	// 글 상세보기
	public BoardVO boardView(int board_Num) {
		return sqlSession.selectOne("Board.boardview", board_Num);
	}

	// 글 수정하기
	public void boardModify(BoardVO boardVO) {
		sqlSession.selectOne("Board.boardmodify", boardVO);
	}

	// 글 수정 업데이트
	public void boardModify1(BoardVO boardVO) {
		sqlSession.update("Board.boardmodify1", boardVO);
	}

	// 글 삭제하기
	public void boardDelete(int board_Num) {
		sqlSession.delete("Board.boarddelete", board_Num);
	}
	
	public int getListCount(int count) {
		return sqlSession.selectOne("Board.getListCount", count);
	}
	
	public List<BoardVO> getRead(PageMaker pagemaker) {
		System.out.println("DAO pageMaker"+pagemaker.toString());
		
		return sqlSession.selectList("Board.getRead", pagemaker);
	}

}
