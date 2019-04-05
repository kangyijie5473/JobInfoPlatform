package dao;

import entity.User;

import java.util.List;

public interface UserDAO {
    public User queryUserByName(String name);
    public int insertUser(User user);
    public List<User> display();

//    public int deleteUser(long id);
//    public int updateUser(User user);
}
