package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller
public class BoardController {
	
	//의존성주입 : 스프링컨테이너가 BoardMappe 객체 찾아서 생성
	@Autowired
	private BoardMapper mapper;
	
	//DB에서 리스트 조회결과를 가져오기
	//url요청 처리, Handler Mapping
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		
//		Board vo = new Board();
//		vo.setIdx(1);
//		vo.setTitle("게시판실습");
//		vo.setContent("게시판실습");
//		vo.setWriter("박매일");
//		vo.setIndate("2024-09-27");
//		vo.setCount(0);
//		
//		List<Board> list = new ArrayList<>();
//		list.add(vo);
//		list.add(vo);
//		list.add(vo);
		
		//SqlSessionFactory를 구현받은 BoardMapper 인터페이스 메소드를 가져와서 SQL 호출
		List<Board> list = mapper.getLists();
		
		//view단으로 전달위한 객체
		model.addAttribute("list", list);
		
		//view jsp파일로 포워딩
		//WEB-INF/views 매핑
		//설정은 servlet-context.xml 참고
		return "boardList";
	}
	
	@GetMapping("/boardForm.do") //href 값과 일치시켜 URL 경로통해 boardForm.jsp 화면 보여주기
	public String boardForm() {
		return "boardForm";
	}
	
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board vo) { //글쓰기의 title, content, writer 담기위한 파라미터
		mapper.boardInsert(vo);
		return "redirect:/boardList.do"; //저장 후 다시 목록으로 돌아가야하니 redirect
	}
}
