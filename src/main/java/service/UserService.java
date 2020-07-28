package service;

import domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ShaoJ
 * Date: 2020/7/10
 * Time: 10:59
 */
public interface UserService {
    boolean isUser(String name,String password);
    boolean checkName(String name);
    Integer addUser(User user);
    Integer changePhoto(String path,String name);
    Integer changeEmail(String email,String name);
    Integer changeName(String newName,String name);
    Integer changePassword(String password,String name);
    User getUser(String name);
    String getImgPath(String name);
}
