package Handlers;

import Models.User;
import Utils.Parser;

import java.util.HashMap;
import java.util.Map;

public class LoginHandler {

    public static Map<String, Object> login(String username, String password) {

        Map<String, Object> response = new HashMap<>();

        User testUser = UserHandler.getInstance().findByUsername(username);

        if (testUser != null) {
            if (Parser.comparePassword(password, testUser.getPassword())) {
                response.put("result", true);
            } else {
                response.put("result", false);
                response.put("error", "The password is incorrect");
            }
        } else {
            response.put("result", false);
            response.put("error", "User not found");
        }

        return response;
    }

}
