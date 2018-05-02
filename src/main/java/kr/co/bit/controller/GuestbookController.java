package kr.co.bit.controller;

import kr.co.bit.service.GuestbookService;
import kr.co.bit.vo.GuestBookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GuestbookController {

    @Autowired
    GuestbookService guestbookService;

    @RequestMapping(value = "/guestbooklist", method = RequestMethod.GET)
    public String guestbooklist(Model model){
        System.out.println("guestbooklist 들어옴");
        model.addAttribute("guestbooklist",guestbookService.listAll());
        return "guestbook/list";
    }

    @RequestMapping(value = "/guestbookinsert", method = RequestMethod.POST)
    public String guestbookinsert(@ModelAttribute GuestBookVO guestBookVO){
        System.out.println("guestbookinsert 들어옴");
        System.out.println(guestBookVO.toString());
        guestbookService.insert(guestBookVO);
        return "redirect:/guestbooklist";
    }

    @RequestMapping(value = "/guestbookdeleteform/{no}", method = RequestMethod.GET)
    public String guestbookdeleteform(@PathVariable ("no") String no,Model model){
        System.out.println("guestbookdeleteform 들어옴");
        model.addAttribute("no",no);
        return "guestbook/deleteform";
    }

    @RequestMapping(value = "/delete/{no}", method = RequestMethod.POST)
    public String guestbookdelete(@PathVariable("no")String no){
        System.out.println("guestbookdelete 들어옴");
        System.out.println("게시글번호 : "+no);
        guestbookService.delete(no);
        return "redirect:/guestbooklist";
    }

    @RequestMapping(value = "/list_ajax",method = RequestMethod.GET)
    public String list_ajax(){
        System.out.println("list_ajax");
        return "guestbook/list_ajax";
    }
}
