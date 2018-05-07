package kr.co.bit.dao;

import kr.co.bit.vo.FileVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class FileUploadDAO {

    @Autowired
    SqlSession sqlSession;

    public void fileinput(FileVO fileVO) {
        System.out.println("인풋다오 들어왔냐");
        sqlSession.insert("fileVO.insert",fileVO);
    }

    public List<FileVO> listAll() {
        return sqlSession.selectList("fileVO.listAll");
    }

    public int imgdel(String no) {
        return sqlSession.delete("fileVO.delete",no);
    }

    public FileVO delbutton(String no) {
        return sqlSession.selectOne("fileVO.delbutton",no);
    }
}
