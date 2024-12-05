package kr.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;

@Controller
public class BoardController {
	
	//스프링 Handler Mapping이 클라이언트의 url을 받아 처리
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		Board vo = new Board();
		vo.setIdx(1);
		vo.setTitle("게시판 실습");
		vo.setContent("게시판 실습");
		vo.setWriter("정원준");
		vo.setIndate("2024-12-05");
		vo.setCount(0);
		
		List<Board> list = new ArrayList<>();
		list.add(vo);
		list.add(vo);
		list.add(vo);
		
		//view단으로 데이터전달
		model.addAttribute("list", list);
		
		return "boardList"; //view - jsp 파일 경로 일치(servlet-context.xml의 viewResolver 설정 효과 -> /WEB-INF/views)
	}
	
}