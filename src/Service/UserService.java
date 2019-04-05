package Service;

import dao.UserDAO;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public User queryUser(String name) {
        User user = userDAO.queryUserByName(name);
        return user;
    }
    public int insertUser(User user) {
        return userDAO.insertUser(user);
    }
    public List<User> display() {
        return userDAO.display();
    }

}
