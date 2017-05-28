package handlers.data_valid;


import dao.UserDao;
import lombok.SneakyThrows;
import model.User;
import java.time.LocalDate;
import java.util.regex.*;

public class validUser {

private static final String VALID_MAIL="^[a-zA-Z0-9]([.-_]?[a-zA-Z0-9]+)*@[a-zA-Z0-9]+([.-_]?[a-zA-Z0-9]+)*\\.[a-zA-Z]+$";
private static final String VALID_MOBILE_PHONE="^(\\+7|8)[0-9]{10}$";
private static final String VALID_PHONE="[0-9]{5}([0-9]{2})?";

public static String validMessage(User user,UserDao ud){
    StringBuilder str= new StringBuilder();
    if (emptyFields(user))
        str.append("Some fields are empty"+'\n');
    if (userExists(user,ud))
        str.append("User with this data exists"+'\n');
    if (!validEmail(user))
        str.append("Invalid email"+'\n');
    if (!validPhone(user))
        str.append("Invalid phone number"+'\n');
    else if(str.toString().equals(""))
        str.append("OK");
    return str.toString();
}
@SneakyThrows
private static boolean emptyFields(User user) {
    return user.getPassword_hash().equals("") ||
            user.getName().equals("") ||
            user.getLastname().equals("") ||
            user.getFathername().equals("") ||
            user.getEmail().equals("") ||
            user.getTelephone().equals("") ||
            user.getDob().toString().equals(LocalDate.MIN.toString()) ||
            user.getNickname().equals("");
}
private static boolean userExists(User user, UserDao ud) {

    return ud.get_cond("nickname", user.getNickname()) != null ||
           ud.get_cond("email", user.getEmail()) != null;
}
private static boolean validEmail(User user){
    return Pattern.compile(VALID_MAIL).matcher(user.getEmail()).matches();
}
private static boolean validPhone(User user){
    return Pattern.compile(VALID_MOBILE_PHONE).matcher(user.getTelephone()).matches() ||
     Pattern.compile(VALID_PHONE).matcher(user.getTelephone()).matches();
}

}
