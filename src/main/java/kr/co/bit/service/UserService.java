package kr.co.bit.service;

import kr.co.bit.dao.UserDAO;
import kr.co.bit.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public int join(UserVO userVO){
        System.out.println(userVO.toString());
        return userDAO.insert(userVO);
    }

    public UserVO searchId(UserVO userVO) {
        return userDAO.searchId(userVO);
    }

    public void update(UserVO userVO) {
        userDAO.update(userVO);
    }

    public UserVO nameupdate(UserVO userVO) {
        return userDAO.nameupdate(userVO);
    }

    public boolean idcheck(String email) {
        String bool = userDAO.idcheck(email);
        boolean idcheck = false;
        if(bool == null) {
            System.out.println("아이디 사용 가능");
            idcheck = true;
        }else{
            System.out.println("해당 아이디가 이미 존재함");
        }
        return idcheck;
    }
}
