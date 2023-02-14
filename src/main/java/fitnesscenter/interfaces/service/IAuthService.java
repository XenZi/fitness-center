package fitnesscenter.interfaces.service;

import javax.servlet.http.HttpSession;

public interface IAuthService {
    public void login(String username, String password, HttpSession session);
}
