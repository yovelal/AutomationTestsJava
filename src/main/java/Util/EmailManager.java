package Util;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

public class EmailManager {
    private String email;
    private String password;

    public EmailManager(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String readAndDeleteLastEmail() throws MessagingException, IOException {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");

        Session session = Session.getInstance(props);
        Store store = session.getStore();
        store.connect("imap-mail.outlook.com", 993, email, password);

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);

        Message[] messages = inbox.getMessages();
        if (messages.length == 0) {
            return "No emails found.";
        }

        Message lastMessage = messages[messages.length - 1];
        String content = extractMessageContent(lastMessage);

        // Mark the last message as deleted
        lastMessage.setFlag(Flags.Flag.DELETED, true);

        // Expunge the deleted message
        inbox.expunge();
        
        // Close the folder and store
        inbox.close(true);
        store.close();

        return content;
    }

    private String extractMessageContent(Part part) throws MessagingException, IOException {
        if (part.isMimeType("text/plain") || part.isMimeType("text/html")) {
            return part.getContent().toString();
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int count = multipart.getCount();
            for (int i = 0; i < count; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                if (bodyPart.isMimeType("text/plain") || bodyPart.isMimeType("text/html")) {
                    return bodyPart.getContent().toString();
                }
            }
        }
        return "No content found.";
    }

    public static void main(String[] args) throws IOException {
        String email = "automationlecturer@outlook.co.il";
        String password = "12345678Ya!";

        try {
            EmailManager emailManager = new EmailManager(email, password);
            System.out.println("Last email: " + emailManager.readAndDeleteLastEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}