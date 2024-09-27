package kr.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;

@Controller
public class BoardController {
	
	//DB에서 리스트 조회결과를 가져오기
	//url요청 처리, Handler Mapping
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		
		Board vo = new Board();
		vo.setIdx(1);
		vo.setTitle("게시판실습");
		vo.setContent("게시판실습");
		vo.setWriter("박매일");
		vo.setIndate("2024-09-27");
		vo.setCount(0);
		
		List<Board> list = new ArrayList<>();
		list.add(vo);
		list.add(vo);
		list.add(vo);
		
		//view단으로 전달위한 객체
		model.addAttribute("list", list);
		
		//view jsp파일로 포워딩
		//WEB-INF/views 매핑
		//설정은 servlet-context.xml 참고
		return "boardList";
	}
}
