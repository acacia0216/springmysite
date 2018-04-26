package kr.co.bit.service;

import kr.co.bit.dao.GuestBookDAO;
import kr.co.bit.vo.GuestBookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
