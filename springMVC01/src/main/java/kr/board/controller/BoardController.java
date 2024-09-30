package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/boardContent.do")
	public String boardContent(int idx, Model model) {
		//조회수 증가
		mapper.boardCount(idx);
		
		//파라미터를 DB로 전달하여 조회결과 반환
		Board vo = mapper.boardContent(idx);
		model.addAttribute("vo", vo); //객체 바인딩은 모델사용
		
		return "boardContent"; //boardContent.jsp
	}
	
	@GetMapping("/boardDelete.do/{idx}")
	public String boardDelete(@PathVariable("idx") int idx) { // ?idx=6
		mapper.boardDelete(idx); //삭제		
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/boardUpdateForm.do/{idx}")
	public String boardUpdate(@PathVariable("idx") int idx, Model model) {
		//수정이니 기존 게시글 내용정보 가져오기
		Board vo = mapper.boardContent(idx);
		
		//vo객체를 넘겨야 하니 Model 사용
		model.addAttribute("vo", vo);
		return "boardUpdate"; //boardUpdate.jsp
	}
	
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(Board vo) { //idx, content, writer 한번에 넘기기위한 vo 객체
		mapper.boardUpdate(vo); //수정처리
		return "redirect:/boardList.do";
	}
}
