package kr.co.bit.service;

import kr.co.bit.dao.BoardDAO;
import kr.co.bit.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    BoardDAO boardDAO;

    public Map listAll(int crtPage) {

        int listCnt = 10;
        int startnum = (crtPage -1) * listCnt;
        int endnum = startnum + listCnt;

        Map map = new HashMap();
        map.put("startnum",startnum);
        map.put("endnum",endnum);

        List<BoardVO> list = boardDAO.listAll(map);
        int totalCount = boardDAO.totalCount();
        int pageBtnCount = 5;
        int endPageBtnNo = (int)(Math.ceil(crtPage / (double)pageBtnCount) * pageBtnCount);
        int startPageBtnNo = endPageBtnNo-(pageBtnCount - 1);

        boolean next = false;
        if(endPageBtnNo * listCnt < totalCount){
            next = true;
        }else{
            endPageBtnNo = (int)(Math.ceil(totalCount / (double)listCnt));
        }

        boolean prev = false;
        if(startPageBtnNo != 1){
            prev = true;
        }

        int last = (int)(Math.ceil(totalCount / (double)listCnt));

        Map bmap = new HashMap();
        bmap.put("list",list);
        bmap.put("crtPage",crtPage);
        bmap.put("totalCount",totalCount);
        bmap.put("pageBtnCount",pageBtnCount);
        bmap.put("endPageBtnNo",endPageBtnNo);
        bmap.put("startPageBtnNo",startPageBtnNo);
        bmap.put("next",next);
        bmap.put("prev",prev);
        bmap.put("last",last);

        return bmap;
    }

    public List<BoardVO> searchkwd(String kwd) {
        List<BoardVO> list = boardDAO.searchkwd(kwd);
        return list;
    }

    public void modify(String no, BoardVO boardVO) {
        boardDAO.boardupdate(no, boardVO);
    }

    @Transactional

    public BoardVO searchcontent(String no) {
        boardDAO.hitcount(no);
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
