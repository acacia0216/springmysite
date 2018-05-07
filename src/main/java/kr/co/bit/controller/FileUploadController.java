package kr.co.bit.controller;

import kr.co.bit.service.FileUploadService;
import kr.co.bit.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @RequestMapping(value = "/upload",method = {RequestMethod.POST, RequestMethod.POST})
    public String upload(@RequestParam("file") MultipartFile multipartFile,@RequestParam String imgtitle,@RequestParam int user_no, Model model){
        System.out.println(multipartFile.toString());
        System.out.println(multipartFile.getOriginalFilename());
        FileVO fileVO = fileUploadService.restore(multipartFile,imgtitle,user_no);
        System.out.println("업로드하고 컨트롤러로 돌아옴 : "+fileVO.toString());
        model.addAttribute("fileVO",fileVO);
        return "fileupload/result";
    }

    @RequestMapping(value = "/imgview", method = RequestMethod.GET)
    public String imgview(Model model){
        System.out.println("뷰 가질러 간다");
        List<FileVO> list = fileUploadService.imgview();
        model.addAttribute("list",list);
        System.out.println("뷰 가지고 왔다 : "+list.size());

        return "fileupload/list";
    }

    @ResponseBody
    @RequestMapping(value = "/imgdel",method = RequestMethod.POST)
    public String imgdel(@RequestParam String no){
        System.out.println(no);

        return fileUploadService.imgdel(no);
    }

    @ResponseBody
    @RequestMapping(value = "/delbutton",method = RequestMethod.POST)
    public boolean delbutton(@RequestParam String no, @RequestParam String user_no){
        return fileUploadService.delbutton(no,user_no);
    }
}
