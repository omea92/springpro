package kr.board.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller
public class BoardController {
	
	@Autowired
	private BoardMapper mapper;
	
	//스프링 Handler Mapping이 클라이언트의 url을 받아 처리
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		List<Board> list = mapper.getLists();
		
		//view단으로 데이터전달
		model.addAttribute("list", list);
		
		return "boardList"; //view - jsp 파일 경로 일치(servlet-context.xml의 viewResolver 설정 효과 -> /WEB-INF/views)
	}
	
}