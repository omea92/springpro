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
	
	@GetMapping("/boardForm.do")
	public String boardForm() {
		return "boardForm";
	}
	
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board vo) { //파라미터 여러개 한번에 수집위해 설정
		mapper.boardInsert(vo);
		
		return "redirect:/boardList.do"; //조회화면으로 보여지게 경로
	}
	
	//상세보기
	@GetMapping("/boardContent.do")
	public String boardContent(@RequestParam("idx") int idx, Model model) {
		Board vo = mapper.boardContent(idx);
		
		mapper.boardCount(idx); //조회수 증가
		
		model.addAttribute("vo", vo);
		
		return "boardContent";
	}
	
	//삭제요청
	@GetMapping("/boardDelete.do/{idx}")
	public String boardDelete(@PathVariable("idx") int idx) {
		mapper.boardDelete(idx);
		
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/boardUpdateForm.do/{idx}")
	public String boardUpdateForm(@PathVariable("idx")int idx, Model model) {
		Board vo = mapper.boardContent(idx);
		model.addAttribute("vo", vo);
		return "boardUpdate";
	}
	
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(Board vo) {
		mapper.boardUpdate(vo); //수정
		return "redirect:/boardList.do";
	}
}