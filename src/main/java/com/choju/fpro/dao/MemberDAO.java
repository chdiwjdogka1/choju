package com.choju.fpro.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.choju.fpro.vo.MemberVO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int memberJoin(MemberVO memberVO) {
		return sqlSession.insert("Member.memberJoin", memberVO);
	}

	public MemberVO idOverlap(String id) {
		return sqlSession.selectOne("Member.idOverlap", id);
	}

	public MemberVO memberLogin(MemberVO memberVO) {
		return sqlSession.selectOne("Member.memberLogin", memberVO);
	}

	public List<MemberVO> memberList() {
		return sqlSession.selectList("Member.memberList");
	}

	public MemberVO memberView(String id) {
		return sqlSession.selectOne("Member.memberView", id);
	}

	public void memberDelete(String id) {
		sqlSession.delete("Member.memberDelete", id);
	}
	
}
