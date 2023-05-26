package Util;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class User {
	
	public enum UserType {
		  REGULAR,
		  LECTURER,
		  LECTURER2FA
		}
	
	
	static private User user = null;
	static private User lecturer = null;
	static private User lecturer2FA = null;

	public String password;
	public String email;
	
	private User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public static User getUser(UserType role) throws Exception {
		switch(role) {
		case LECTURER:
			if (lecturer == null) {
				lecturer = initUser("lecturer");
			}
			return User.lecturer;
		
		case REGULAR :
			if (user == null) {
				user = initUser("regular");
			}
			return user;
		
		case LECTURER2FA:
			if (lecturer2FA == null) {
				lecturer2FA = initUser("lecturer2FA");
			}
			return User.lecturer2FA;
		default:
            throw new IllegalArgumentException("Invalid user type: " + role);
		}
	}
	
	private static User initUser(String role) throws Exception{
		JSONParser parser = new JSONParser();
		String email ,password;
        try {
            // Read JSON file
            Object obj = parser.parse(new FileReader("C:\\Users\\aloni\\users.json"));
            JSONObject jsonObject = (JSONObject) obj;

           
            JSONObject userJsonObject = (JSONObject) jsonObject.get(role);
            email = (String) userJsonObject.get("email");
            password = (String) userJsonObject.get("password");
            // Save regularUser data as needed
            return new User(email, password);

        } catch (Exception e) {
            throw new Exception("get credential of user failed.");
        }
        

	}
	

}