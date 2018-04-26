package kr.co.bit.service;

import kr.co.bit.dao.BoardDAO;
import kr.co.bit.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardDAO boardDAO;

    public List<BoardVO> listAll() {
        List<BoardVO> list = boardDAO.listAll();
        return list;
    }

    public List<BoardVO> searchkwd(String kwd) {
        List<BoardVO> list = boardDAO.searchkwd(kwd);
        return list;
    }

    public void modify(String no,BoardVO boardVO) {
        boardDAO.boardupdate(no, boardVO);
    }

    public BoardVO searchcontent(String no) {
        BoardVO boardVO = boardDAO.searchContent(no);
        return boardVO;
    }

    public void delete(String no) {
        boardDAO.delete(no);
    }

    public void insert(BoardVO boardVO) {
        System.out.println("insert service 들어옴");
        boardDAO.insert(boardVO);
    }
}
