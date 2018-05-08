package kr.co.bit.controller;

import kr.co.bit.service.UserService;
import kr.co.bit.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/joinform", method = RequestMethod.GET)
    public String joinform() {
        System.out.println("joinform 들어옴");
        return "user/joinform";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(@ModelAttribute UserVO userVO) {
        System.out.println("join 들어옴");
        System.out.println(userVO.toString());
        //DB에 저장하기
        int count = userService.join(userVO);
        System.out.println(count);
        return "main/index";
    }

    @RequestMapping(value = "/loginform", method = RequestMethod.GET)
    public String loginform() {
        System.out.println("loginform 들어옴");
        return "user/loginform";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute UserVO userVO, HttpSession session, Model model) {
        System.out.println("login 들어옴");
        System.out.println(userVO.toString());
        //DB에서 정보확인하고 가져오기
        UserVO authUser = userService.searchId(userVO);
        //세션 생성
        if (authUser != null) {
            session.setAttribute("authUser", authUser);
            System.out.println("로그인 성공");
            System.out.println(authUser.toString());
        } else {
            System.out.println("로그인 실패");
            model.addAttribute("result", "fail");
            return "redirect:/loginform";
        }
        return "main/index";
    }

    @RequestMapping(value = "/modifyform", method = RequestMethod.GET)
    public String modifyform() {
        System.out.println("modifyform 들어옴");
        return "user/modifyform";
    }

    @RequestMapping(value = "/usermodify", method = RequestMethod.POST)
    public String usermodify(UserVO userVO, HttpSession session) {
        System.out.println("usermodify 들어옴");
        System.out.println(userVO.toString());
        if (userVO != null) {
            userService.update(userVO);
            UserVO userVO1 = userService.nameupdate(userVO);
            session.setAttribute("authUser", userVO1);
        }


        return "main/index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        System.out.println("logout 들어옴");
        //세션 자르기
        if (session != null) {
            session.removeAttribute("authUser");
        }
        return "main/index";
    }

    @ResponseBody
    @RequestMapping(value = "/emailcheck", method = RequestMethod.POST)
    public Boolean exists(@RequestParam String email) {
        System.out.println("아작스 이메일 체크 : " + email);
        boolean idcheck = userService.idcheck(email);
        return idcheck;
    }
}
