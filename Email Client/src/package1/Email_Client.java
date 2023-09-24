package package1;
// Index number: 200240M
// import libraries
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;
import java.io.Serializable;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;

public class Email_Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RecipientFactory recFact = new RecipientFactory();
        ArrayList < Recipient > recipients = new ArrayList < > ();
        // Deserialize the HashMap in sentMails.ser
        EmailHandler.start();
        System.out.println("Sending birthday wish emails.....");
        ArrayList<String> fileLines = FileHandler.readClientList();
        // add the recipients in clientList.txt to recipients
        for (String fileLine: fileLines) {
            String[] line = fileLine.split("[: ,]+");
            Recipient recipient = recFact.makeRecipient(line);
            if (recipient != null)
                recipients.add(recipient);
        }
        // send the bithday wish to the recipients who have birthday today
        for (Recipient recipient: recipients) {
            if (recipient instanceof BDWishable bDRecipient) {
                LocalDate dateObj = LocalDate.now();
                String date = dateObj.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                if (bDRecipient.isBirthday(date)) {
                    EmailHandler.sendMail(((Recipient) bDRecipient).getEmail(), "Birthday Wish",
                            bDRecipient.getWish());
                }
            }
        }
        // Deserialize the HashMap in sentMails.ser
        EmailHandler.close();
        System.out.println("Finished sending birthday wish emails.");
        boolean isNext = true;

        while (isNext) {
            EmailHandler.start();
            System.out.println("""
                    Enter option type:\s
                    1 - Adding a new recipient
                    2 - Sending an email
                    3 - Printing out all the recipients who have birthdays
                    4 - Printing out details of all the emails sent
                    5 - Printing out the number of recipient objects in the application
                    -1 - exit the application""");
            System.out.print("Enter the option: "); int option = 0;
            try {
                option = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Input type mismatch " + e);
            }
            sc.nextLine();
            switch (option) {
                case 1:
                    // input format - Official: nimal,nimal@gmail.com,ceo
                    // Use a single input to get all the details of a recipient
                    // code to add a new recipient
                    // store details in clientList.txt file
                    // Hint: use methods for reading and writing files
                    System.out.println("Enter the details of the recipient");
                    String input = sc.nextLine();
                    String[] details = input.split("[: ,]+");
                    Recipient recipient = recFact.makeRecipient(details);
                    if (recipient != null) {
                        recipients.add(recipient);
                        FileHandler.writeClientList(recipient);
                    }
                    break;
                case 2:
                    // input format - email, subject, content
                    // code to send an email
                    System.out.println("Enter the details to send an email(<Email>, <Subject>, <Body>)");
                    input = sc.nextLine();
                    details = input.split("[ ,]+");
                    try {
                        EmailHandler.sendMail(details[0], details[1], details[2]);
                    } catch (ArrayIndexOutOfBoundsException error) {
                        System.out.println("Error: Some fields are missing!");
                    }
                    break;
                case 3:
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print recipients who have birthdays on the given date
                    System.out.print("Enter the date (yyyy/MM/dd): ");
                    input = sc.nextLine().trim();
                    for (Recipient r: recipients) {
                        if (r instanceof BDWishable && ((BDWishable) r).isBirthday(input)) {


                            System.out.println("Name: " + r.getName());
                        }
                    }
                    break;
                case 4:
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print the details of all the emails sent on the input date
                    System.out.print("Enter the date (yyyy/MM/dd): ");
                    String dateInput = sc.nextLine();
                    ArrayList < Email > emails = EmailHandler.retrieve(dateInput);
                    if (emails == null) {
                        System.out.println("No email found!");
                    } else {
                        for (Email email: emails) {
                            System.out.println(email.getMailDetails());
                        }
                    }
                    break;
                case 5:
                    // code to print the number of recipient objects in the application
                    System.out.println("Number of recipients: " + Recipient.getCount());
                    break;
                case -1:
                    isNext = false;
                    break;
                default:
                    System.out.println("Not an option!");
                    continue;
            }
            EmailHandler.close();
        }
    }
}