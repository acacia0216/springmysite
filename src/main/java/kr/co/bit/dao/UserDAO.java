package kr.co.bit.dao;

import kr.co.bit.vo.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class UserDAO {

    @Autowired
    SqlSession sqlSession;
    public int insert(UserVO userVO){
        System.out.println("insert DAO 들어옴");
        return sqlSession.insert("userVO.insert",userVO);
    }

    public UserVO searchId(UserVO userVO) {
        System.out.println("searchId DAO 들어옴");
        return sqlSession.selectOne("userVO.login",userVO);
    }

    public void update(UserVO userVO) {
        System.out.println("UserInfoUpdate DAO 들어옴");
        sqlSession.update("userVO.update",userVO);
    }

    public UserVO nameupdate(UserVO userVO) {
        System.out.println("이름 고치러 DAO 들어옴");
        UserVO userVO1 = sqlSession.selectOne("userVO.nameupdate",userVO);
        return userVO1;
    }
}
