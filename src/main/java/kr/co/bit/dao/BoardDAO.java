package kr.co.bit.dao;

import kr.co.bit.database.ConnectionManager;
import kr.co.bit.vo.BoardVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.management.remote.JMXConnectionNotification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BoardDAO {
@Autowired
    SqlSession sqlSession;

    public List<BoardVO> listAll() {
        List<BoardVO> list = sqlSession.selectList("boardVO.listAll");
        return list;
    }

    public List<BoardVO> searchkwd(String kwd) {
        List<BoardVO> list = sqlSession.selectList("boardVO.searchkwd",kwd);
        return list;
    }

    public void boardupdate(String no,BoardVO boardVO) {
        sqlSession.update("boardVO.update",boardVO);
    }

    public BoardVO searchContent(String no) {
        BoardVO boardVO = sqlSession.selectOne("boardVO.searchcontent",no);
        sqlSession.update("boardVO.count",no);
        return boardVO;
    }

    public void delete(String no) {
        sqlSession.delete("boardVO.delete",no);
    }

    public void insert(BoardVO boardVO) {
        System.out.println("insert DAO 들어옴");
        System.out.println(boardVO.toString());
        sqlSession.insert("boardVO.insert",boardVO);
    }
}
