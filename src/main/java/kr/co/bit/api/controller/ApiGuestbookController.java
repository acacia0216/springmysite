package kr.co.bit.api.controller;

import kr.co.bit.service.GuestbookService;
import kr.co.bit.vo.GuestBookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/guestbook")
public class ApiGuestbookController {

    @Autowired
    GuestbookService guestbookService;

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<GuestBookVO> list(@RequestParam String num) {
        System.out.println("아작리스트게스트북컨트롤러");
        List<GuestBookVO> list = guestbookService.ajaxlistAll(num);
        System.out.println(list.toString());
        return list;
    }

    //    @ResponseBody
//    @RequestMapping(value = "/add",method = RequestMethod.POST)
//    public GuestBookVO add(@RequestBody GuestBookVO guestBookVO){
//        System.out.println("add");
//        System.out.println(guestBookVO.toString());
//        GuestBookVO guestBookVO1 = guestbookService.add(guestBookVO);
//        return guestBookVO1;
//    }
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public GuestBookVO add(@RequestParam String name,@RequestParam String password, @RequestParam String content) {
        System.out.println("add");
        GuestBookVO guestBookVO = new GuestBookVO();
        guestBookVO.setName(name);
        guestBookVO.setPassword(password);
        guestBookVO.setContent(content);
        System.out.println(guestBookVO.toString());
        GuestBookVO guestBookVO1 = guestbookService.add(guestBookVO);
        return guestBookVO1;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Boolean delete(@RequestParam String no, @RequestParam String password) {
        System.out.println("아작딜컨트롤");
        return guestbookService.ajaxdelete(no, password);
    }
}
