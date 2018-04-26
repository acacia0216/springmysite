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
}
