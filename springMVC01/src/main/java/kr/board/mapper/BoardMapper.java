package kr.board.mapper;

import java.util.List;

import kr.board.entity.Board;

public interface BoardMapper {
	public List<Board> getLists(); //전체리스트 -> 메소드명과 동일한 SQL Mapper 필요
}
