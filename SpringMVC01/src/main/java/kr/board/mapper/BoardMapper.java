package kr.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.board.entity.Board;

@Mapper
public interface BoardMapper {
	List<Board> getLists(); //전체조회
	public Board boardContent(int idx); //상세보기
	public void boardInsert(Board vo); //등록
	
}