package kr.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import kr.board.entity.Board;

@Mapper
public interface BoardMapper {
	List<Board> getLists(); //전체조회
	public Board boardContent(int idx); //상세보기
	public void boardInsert(Board vo); //등록
	public void boardDelete(int idx); //삭제
	public void boardUpdate(Board vo); //수정
	
	@Update("update myboard set count = count + 1 where idx = #{idx}")
	public void boardCount(int idx); //조회수
}