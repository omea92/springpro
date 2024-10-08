package kr.board.entity;

import lombok.Data;

@Data //롬복 제공으로 자동 getter/setter 제공
public class Board {
	private int idx; //번호
	private String title; //제목
	private String content; //내용
	private String writer; //작성자
	private String indate; //작성일
	private int count; //조회수

}
