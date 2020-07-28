package dao;

import domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ShaoJ
 * Date: 2020/7/10
 * Time: 10:31
 */
public interface UserDao {
    String isUser(@Param("name") String name, @Param("password") String password);
    String checkName(String name);
    Integer addUser(User user);
    Integer changePhoto(@Param("path") String path,@Param("name")String name);
    Integer changeEmail(@Param("email") String email,@Param("name")String name);
    Integer changeName(@Param("newName") String newName,@Param("name")String name);
    Integer changePassword(@Param("password") String password,@Param("name")String name);
    User getUser(String name);
    String getImgPath(String name);
}
