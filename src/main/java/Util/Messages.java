package Util;

public class Messages {
	
	/////// LOGIN ////////
	public static String wrongPassword = "Incorrect email or password";
	public static String wrongEmail = "Incorrect email or password";
	public static String wrong2FACode = "Code is incorrect";
	public static String expired2FACode = "2FA Code expired";
	
	
	///// FEADBACK //////
	public static String getFeedbacksSelected(int feedbacks) {
		if (feedbacks == 1)
			return "1 item selected";
		return Integer. toString(feedbacks) + " items selected";
	}

	public static String submitFeedback = "Feedback submitted successfully";

}
