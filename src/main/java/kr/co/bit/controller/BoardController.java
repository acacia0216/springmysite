package kr.co.bit.controller;

import kr.co.bit.service.BoardService;
import kr.co.bit.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @RequestMapping(value = "/boardlist/{crtpage}", method = RequestMethod.GET)//전체 찾아오기
    public String boardlist(Model model,@PathVariable int crtpage){
        System.out.println("boardlist 들어옴");
        Map map = boardService.listAll(crtpage);
        model.addAttribute("map",map);
        return "board/list";
    }

    @RequestMapping(value = "/searchkwd", method = RequestMethod.POST)//검색어 찾아오기
    public String boardsearch(@RequestParam("kwd")String kwd,Model model){
        System.out.println("searchkwd 들어옴");
        model.addAttribute("boardlist",boardService.searchkwd(kwd));
        return "board/list";
    }

    @RequestMapping(value = "/boardmodifyform/{no}", method = RequestMethod.GET)//게시글 수정 폼
    public String boardmodifyform(@PathVariable("no")String no,Model model){
        System.out.println("boardmodifyform 들어옴");
        //번호에 해당하는 게시글 찾아오기
        model.addAttribute("searchcontent",boardService.searchcontent(no));
        return "board/modify";
    }

    @RequestMapping(value = "/boardmodify/{no}", method = RequestMethod.POST)//게시글 수정
    public String boardmodify(@PathVariable("no")String no, BoardVO boardVO){
        System.out.println("boardmodify 들어옴");
        boardService.modify(no,boardVO);
        return "redirect:/boardlist";
    }

    @RequestMapping(value = "/boardview/{no}", method = RequestMethod.GET)//게시글 뷰
    public String boardview(@PathVariable("no")String no,Model model){
        System.out.println("boardview 들어옴");
        model.addAttribute("searchcontent",boardService.searchcontent(no));
        return "board/view";
    }

    @RequestMapping(value = "/boarddelete/{no}", method = RequestMethod.GET)//게시글 삭제
    public String boarddelete(@PathVariable("no")String no){
        System.out.println("boarddelete 들어옴");
        boardService.delete(no);
        return "redirect:/boardlist";
    }
    @RequestMapping(value = "/boardwriteform", method = RequestMethod.GET)
    public String boardwriteform(){
        System.out.println("boardwrite 들어옴");
        return "board/write";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insert(@ModelAttribute BoardVO boardVO){
        System.out.println("insert 들어옴");
        boardService.insert(boardVO);
        return "redirect:/boardlist";
    }
}
