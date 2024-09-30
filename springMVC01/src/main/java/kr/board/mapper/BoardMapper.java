package kr.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import kr.board.entity.Board;

@Mapper
public interface BoardMapper { //SqlSessionFactory를 내부적으로 상속받아 구현
	public List<Board> getLists(); //전체리스트 -> 메소드명과 동일한 SQL Mapper 필요
	public void boardInsert(Board vo);
	public Board boardContent(int idx); //구현체는 implements SqlSessionFactory에 구현되어있다
	public void boardDelete(int idx);
	public void boardUpdate(Board vo);
	
	//어노테이션에 쿼리 작성하여 사용 가능
	@Update("update myboard set count=count+1 where idx=#{idx}")
	public void boardCount(int idx);
}
