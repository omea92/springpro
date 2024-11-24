package kr.board.entity;

public class Board {
	
	private int idx; //번호
	
	private String title; //제목
	
	private String cotent; //내용
	
	private String writer; //작성자
	
	private String indate; //작성일
	
	private int count; //조회수

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCotent() {
		return cotent;
	}

	public void setCotent(String cotent) {
		this.cotent = cotent;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
