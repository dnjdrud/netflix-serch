package main.com.dnjdrud.service;

import main.com.dnjdrud.dao.UserDAO;
import main.com.dnjdrud.model.User;

public class AuthService {
    private final UserDAO userDAO = new UserDAO();

    public User login(String username, String password) {
        User user = userDAO.findByUsername(username);

        // 암호화 없이 평문으로 비교
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }
}