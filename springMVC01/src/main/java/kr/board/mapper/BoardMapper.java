package kr.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.board.entity.Board;

@Mapper
public interface BoardMapper { //SqlSessionFactory를 내부적으로 상속받아 구현
	public List<Board> getLists(); //전체리스트 -> 메소드명과 동일한 SQL Mapper 필요
	public void boardInsert(Board vo);
}
