package kr.co.bit.api.controller;

import kr.co.bit.service.GuestbookService;
import kr.co.bit.vo.GuestBookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/api/guestbook")
public class ApiGuestbookController {

    @Autowired
    GuestbookService guestbookService;

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<GuestBookVO> list(){
        System.out.println("아작리스트게스트북컨트롤러");
        List<GuestBookVO> list = guestbookService.listAll();
        System.out.println(list.toString());
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public GuestBookVO add(@RequestParam String name, @RequestParam String password, @RequestParam String content){
        System.out.println("add");
        System.out.println(name+password+content);
        GuestBookVO guestBookVO = new GuestBookVO();
        guestBookVO.setName(name);
        guestBookVO.setPassword(password);
        guestBookVO.setContent(content);
        GuestBookVO guestBookVO1 = guestbookService.add(guestBookVO);
        return guestBookVO1;
    }

    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String delete(@RequestParam String no,@RequestParam String password){
        System.out.println("아작딜컨트롤");
        guestbookService.ajaxdelete(no,password);
        System.out.println(no);
        return no;
    }
}
