package kr.co.bit.dao;

import kr.co.bit.vo.GuestBookVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GuestBookDAO {
    @Autowired
    SqlSession sqlSession;
    public void insert(GuestBookVO guestBookVO) {
        System.out.println("insert DAO 들어옴");
        System.out.println(guestBookVO.toString());
        sqlSession.insert("guestbookVO.insert",guestBookVO);
    }

    public void delete(String no) {
        System.out.println("delete DAO 들어옴");
        sqlSession.delete("guestbookVO.delete",no);
    }

    public List<GuestBookVO> listAll() {
        System.out.println("listAll DAO 들어옴");
        List<GuestBookVO> list = sqlSession.selectList("guestbookVO.listAll");
        return list;
    }

    public List<GuestBookVO> ajaxlistAll(String num) {
        System.out.println("listAll DAO 들어옴");
        List<GuestBookVO> list = sqlSession.selectList("guestbookVO.ajaxlistAll",num);
        return list;
    }

    public GuestBookVO add(GuestBookVO guestBookVO) {
        System.out.println("전 : "+guestBookVO.toString());
        sqlSession.insert("guestbookVO.add",guestBookVO);
        System.out.println("후 : "+guestBookVO.toString());
        return guestBookVO;
    }

    public GuestBookVO addget(String no) {
        return sqlSession.selectOne("guestbookVO.addget",no);
    }

    public void ajaxdelete(Map map) {
        System.out.println("아작딜다오");
        sqlSession.delete("guestbookVO.ajaxdelete",map);
        System.out.println("아작딜다오 리턴없어도 나오냐");
    }

    public GuestBookVO ajaxdelconfirm(String no) {
        return sqlSession.selectOne("guestbookVO.ajaxdelconfirm",no);
    }
}
