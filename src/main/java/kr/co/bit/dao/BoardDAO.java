package kr.co.bit.dao;

import kr.co.bit.vo.BoardVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardDAO {
    @Autowired
    SqlSession sqlSession;

    public List<BoardVO> listAll(Map map) {
        List<BoardVO> list = sqlSession.selectList("boardVO.listAll",map);
        return list;
    }

    public int totalCount(){
        return sqlSession.selectOne("boardVO.totalCount");
    }

    public List<BoardVO> searchkwd(String kwd) {
        List<BoardVO> list = sqlSession.selectList("boardVO.searchkwd", kwd);
        return list;
    }

    public void boardupdate(String no, BoardVO boardVO) {
        sqlSession.update("boardVO.update", boardVO);
    }

    public BoardVO searchContent(String no) {
        BoardVO boardVO = sqlSession.selectOne("boardVO.searchcontent", no);

        return boardVO;
    }

    public void hitcount(String no) {
        sqlSession.update("boardVO.count", no);
    }

    public void delete(String no) {
        sqlSession.delete("boardVO.delete", no);
    }

    public void insert(BoardVO boardVO) {
        System.out.println("insert DAO 들어옴");
        System.out.println(boardVO.toString());
        sqlSession.insert("boardVO.insert", boardVO);
    }
}
