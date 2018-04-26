package kr.co.bit.dao;

import kr.co.bit.database.ConnectionManager;
import kr.co.bit.vo.GuestBookVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

}
