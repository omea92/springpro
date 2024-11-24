package kr.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;

@Controller //해당 클래스가 컨트롤러라고 인식
public class BoardController {
	
	//클라이언트가 URL통해 호출하면 핸들러매핑에 의해 컨트롤러가 먼저 받아서 아래 메소드 수행
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		
		Board vo = new Board();
		vo.setIdx(1);
		vo.setTitle("게시판실습");
		vo.setCotent("게시판실습");
		vo.setWriter("테스터");
		vo.setIndate("2024-11-25");
		vo.setCount(0);
		
		List<Board> list = new ArrayList<>();
		list.add(vo);
		list.add(vo);
		list.add(vo);
		
		model.addAttribute("list", list);
		
		return "boardList"; //view resolver의해 JSP 파일 연계(servlet-context.xml 설정 참고)
	}

}
