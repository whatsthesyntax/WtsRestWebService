package Util;

/**
 * Created by rim on 17/01/17.
 */
import Dao.UserDao;
import Model.User;
import com.sun.jersey.core.util.Base64;

import java.io.IOException;
import java.util.StringTokenizer;

public class Authentication {

    UserDao dao = new UserDao();
    User loginUser = new User();

    public boolean authenticate(String authCredentials) {

        if (null == authCredentials)
            return false;
        // header value format will be "Basic encodedstring" for Basic
        // authentication. Example "Basic YWRtaW46YWRtaW4="
        final String encodedUserPassword = authCredentials.replaceFirst("[B|b]asic ","");
        String usernameAndPassword = null;
        try {
            byte[] decodedBytes = Base64.decode(
                    encodedUserPassword);
            usernameAndPassword = new String(decodedBytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final StringTokenizer tokenizer = new StringTokenizer(
                usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        //check if user exists
        try{
            User user = dao.findUser(username);
            if(user != null){
                //check if password is correct
                this.loginUser = user;
                return user.getPassword().equals(password);
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
    public User getLoginUser(){
        return this.loginUser;
    }

}
