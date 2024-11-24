package kr.board.mapper;

import java.util.List;

import kr.board.entity.Board;

/*
 * MyBatis SQL 연결 메소드명
 */
public interface BoardMapper {
	public List<Board> getLists(); //게시판 전체 리스트
	
	
}
