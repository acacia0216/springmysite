package kr.co.bit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        System.out.println("index 들어옴");
        return "main/index";
    }
}
