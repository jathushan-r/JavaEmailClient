package package1;
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
abstract class FileHandler {
    public static ArrayList < String > readClientList() {
        ArrayList < String > lines = new ArrayList < > ();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("clientList.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("clientList.txt not found!\n" + e);
        }
        return lines;
    }
    public static void writeClientList(Recipient recipient) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("clientList.txt", true));
            writer.write(recipient.getDetails());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("clientList.txt not found!\n" + e);
        }
    }
    public static HashMap < String, ArrayList < Email >> readSentMails() {
        HashMap < String, ArrayList < Email >> sentMails;
        try {
            ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(
                    "sentMails.ser"));
            sentMails = (HashMap < String, ArrayList < Email >> ) objectInput.readObject();
            objectInput.close();
        } catch (IOException | ClassNotFoundException e) {
            sentMails = new HashMap < String, ArrayList < Email >> ();
        }
        return sentMails;
    }
    public static void writeSentMails(HashMap sentMails) {
        try {
            ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream(
                    "sentMails.ser"));
            objectOutput.writeObject(sentMails);
            objectOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
