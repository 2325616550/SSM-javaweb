package service.impl;

import dao.UserDao;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;

/**
 * Created by ShaoJ
 * Date: 2020/7/10
 * Time: 10:59
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    public boolean isUser(String name, String password) {
        String username = dao.isUser(name, password);
        if (username != null)
            return true;
        return false;
    }
/**
* @Author: ShaoJ
* @Description:
* @Date: 16:44 2020/7/12
* @Param: [name]
* @return: 真为可以使用
*/
    @Override

    public boolean checkName(String name) {
        String username=null;
        username = dao.checkName(name);
        if (username != null)
        {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Integer addUser(User user) {
        Integer i = dao.addUser(user);
        return i;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Integer changePhoto(String path, String name) {
        Integer integer = dao.changePhoto(path, name);
        return integer;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Integer changeEmail(String email, String name) {
        Integer i= dao.changeEmail(email,name);
        return i;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Integer changeName(String newName, String name) {
        Integer i= dao.changeName(newName,name);
        return i;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Integer changePassword(String password, String name) {
        Integer i= dao.changePassword(password,name);
        return i;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public User getUser(String name) {
        User user = null;
        user=dao.getUser(name);
        return user;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public String getImgPath(String name) {
        return dao.getImgPath(name);
    }
}
