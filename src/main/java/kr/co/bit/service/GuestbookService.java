package kr.co.bit.service;

import kr.co.bit.dao.GuestBookDAO;
import kr.co.bit.vo.GuestBookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GuestbookService {

    @Autowired
    GuestBookDAO guestBookDAO;

    public List<GuestBookVO> listAll() {
        System.out.println("listAll service 들어옴");
        List<GuestBookVO> list = guestBookDAO.listAll();
        return list;
    }

    public void insert(GuestBookVO guestBookVO){
        System.out.println("insert service 들어옴");
        System.out.println(guestBookVO.toString());
        guestBookDAO.insert(guestBookVO);
    }

    public void delete(String no){
        System.out.println("delete service 들어옴");
        guestBookDAO.delete(no);
    }

    public GuestBookVO add(GuestBookVO guestBookVO){
        GuestBookVO guestBookVO1 = guestBookDAO.add(guestBookVO);
        String no = guestBookVO1.getNo();
        GuestBookVO guestBookVO2 = guestBookDAO.addget(no);
        return guestBookVO2;
    }

    public boolean ajaxdelete(String no, String password) {
        Map map = new HashMap();
        map.put("no",no);
        map.put("password",password);
        System.out.println("아작딜서비스");
        System.out.println(no);
        System.out.println(password);
        guestBookDAO.ajaxdelete(map);
        GuestBookVO guestBookVO = guestBookDAO.ajaxdelconfirm(no);
        boolean flag = false;
        if(guestBookVO == null){
            flag = true;
        }
        System.out.println(flag);
        return flag;
    }
    public List<GuestBookVO> ajaxlistAll(String num) {
        System.out.println("ajaxlistAll service 들어옴");
        List<GuestBookVO> list = guestBookDAO.ajaxlistAll(num);
        return list;
    }
}
