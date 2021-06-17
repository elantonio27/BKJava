package newP;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javax.mail.*;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.*;

public class MailChecker
{
    private Properties props;
    private MailChecker MailUtils;

    public ObservableList<Mailobject> getAllMails() throws MessagingException, IOException {
/*
        Session session = MailUtils.getGMailSession(
                JOptionPane.showInputDialog( "user" ),
                JOptionPane.showInputDialog( "pass" ) );

 */
       ObservableList<Mailobject> dlmMails= new ObservableList<Mailobject>() {
           @Override
           public void addListener(ListChangeListener<? super Mailobject> listChangeListener) {

           }

           @Override
           public void removeListener(ListChangeListener<? super Mailobject> listChangeListener) {

           }

           @Override
           public boolean addAll(Mailobject... mailobjects) {
               return false;
           }

           @Override
           public boolean setAll(Mailobject... mailobjects) {
               return false;
           }

           @Override
           public boolean setAll(Collection<? extends Mailobject> collection) {
               return false;
           }

           @Override
           public boolean removeAll(Mailobject... mailobjects) {
               return false;
           }

           @Override
           public boolean retainAll(Mailobject... mailobjects) {
               return false;
           }

           @Override
           public void remove(int i, int i1) {

           }

           @Override
           public int size() {
               return 0;
           }

           @Override
           public boolean isEmpty() {
               return false;
           }

           @Override
           public boolean contains(Object o) {
               return false;
           }

           @Override
           public Iterator<Mailobject> iterator() {
               return null;
           }

           @Override
           public Object[] toArray() {
               return new Object[0];
           }

           @Override
           public <T> T[] toArray(T[] ts) {
               return null;
           }

           @Override
           public boolean add(Mailobject mailobject) {
               return false;
           }

           @Override
           public boolean remove(Object o) {
               return false;
           }

           @Override
           public boolean containsAll(Collection<?> collection) {
               return false;
           }

           @Override
           public boolean addAll(Collection<? extends Mailobject> collection) {
               return false;
           }

           @Override
           public boolean addAll(int i, Collection<? extends Mailobject> collection) {
               return false;
           }

           @Override
           public boolean removeAll(Collection<?> collection) {
               return false;
           }

           @Override
           public boolean retainAll(Collection<?> collection) {
               return false;
           }

           @Override
           public void clear() {

           }

           @Override
           public Mailobject get(int i) {
               return null;
           }

           @Override
           public Mailobject set(int i, Mailobject mailobject) {
               return null;
           }

           @Override
           public void add(int i, Mailobject mailobject) {

           }

           @Override
           public Mailobject remove(int i) {
               return null;
           }

           @Override
           public int indexOf(Object o) {
               return 0;
           }

           @Override
           public int lastIndexOf(Object o) {
               return 0;
           }

           @Override
           public ListIterator<Mailobject> listIterator() {
               return null;
           }

           @Override
           public ListIterator<Mailobject> listIterator(int i) {
               return null;
           }

           @Override
           public List<Mailobject> subList(int i, int i1) {
               return null;
           }

           @Override
           public void addListener(InvalidationListener invalidationListener) {

           }

           @Override
           public void removeListener(InvalidationListener invalidationListener) {

           }
       };
       Session session = MailUtils.getGMailSession("testbarkeeper@gmail.com","Jackcarver12");
       Folder inbox = MailUtils.openPop3InboxReadOnly( session );
       MailUtils.printAllTextPlainMessages( inbox );

        if(inbox.getMessages()!=null)
        {
            for (Message m : inbox.getMessages())
            {
                Mailobject mo = new Mailobject();
                mo.setSubject(m.getSubject());
                mo.setDate(m.getSentDate());
                mo.setSender(Arrays.toString(m.getFrom()));
                mo.setSenderA(m.getFrom());
                dlmMails.add(mo);
            }
        }
        else
        {
            System.out.println("Sie haben keine Mails");
        }
       MailUtils.printAllTextPlainMessages(inbox);
       MailUtils.closeInbox( inbox );
        return dlmMails;
    }

    public static Session getGMailSession( String user, String pass )
    {
        final Properties props = new Properties();

        // Zum Empfangen
        props.setProperty( "mail.pop3.host", "pop.gmail.com" );
        props.setProperty( "mail.pop3.user", user );
        props.setProperty( "mail.pop3.password", pass );
        props.setProperty( "mail.pop3.port", "995" );
        props.setProperty( "mail.pop3.auth", "true" );
        props.setProperty( "mail.pop3.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory" );

        // Zum Senden
        props.setProperty( "mail.smtp.host", "smtp.gmail.com" );
        props.setProperty( "mail.smtp.auth", "true" );
        props.setProperty( "mail.smtp.port", "465" );
        props.setProperty( "mail.smtp.socketFactory.port", "465" );
        props.setProperty( "mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory" );
        props.setProperty( "mail.smtp.socketFactory.fallback", "false" );

        return Session.getInstance( props, new Authenticator() {
            @Override protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication( props.getProperty( "mail.pop3.user" ),
                        props.getProperty( "mail.pop3.password" ) );
            }
        } );
//    session.setDebug( true );
    }
    // ...


    public static Folder openPop3InboxReadOnly( Session session )
            throws MessagingException
    {
        Store store = session.getStore( "pop3" );
        store.connect();

        Folder folder = store.getFolder( "INBOX" );
        folder.open( Folder.READ_ONLY );

        return folder;
    }
    public static void closeInbox( Folder folder ) throws MessagingException
    {
        folder.close( false );
        folder.getStore().close();
    }
    public static   void printAllTextPlainMessages( Folder folder )
            throws MessagingException, IOException
    {
        for ( Message m : folder.getMessages() )
        {
            System.out.println( "\nNachricht:" );
            System.out.println( "Von: " + Arrays.toString(m.getFrom()) );
            System.out.println( "Betreff: " + m.getSubject() );
            System.out.println( "Gesendet am: " + m.getSentDate() );
            System.out.println( "Content-Type: " +
                    new ContentType( m.getContentType() ) );

            if ( m.isMimeType( "text/plain" ) )
                System.out.println( m.getContent() );
        }
    }


    public static void sendGMX() throws MessagingException
    {
        String sender = "bkpr@gmx.de";
        String password = "my.password";
        String receiver = "my-receiver@gmail.com";

        Properties properties = new Properties();

        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", "mail.gmx.net");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.user", sender);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.starttls.enable", "true");

        Session mailSession = Session.getInstance(properties, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(properties.getProperty("mail.smtp.user"),
                        properties.getProperty("mail.smtp.password"));
            }
        });

        Message message = new MimeMessage(mailSession);
        InternetAddress addressTo = new InternetAddress(receiver);
        message.setRecipient(Message.RecipientType.TO, addressTo);
        message.setFrom(new InternetAddress(sender));
        message.setSubject("The subject");
        message.setContent("This is the message content", "text/plain");
        Transport.send(message);
    }

}
/*
 props = new Properties();
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.ssl.enable", "true");

        Session session = Session.getDefaultInstance(props, null);
        Store store = session.getStore("imap");
        store.connect("imap.gmx.de", "bkpr2@gmx.de", "testbarkeeper");
        System.out.println(store);

        Folder inbox = store.getFolder("Inbox");
        inbox.open(Folder.READ_ONLY);
        Message messages[] = inbox.getMessages();
        System.out.println("test");
        for(Message message:messages) {
            System.out.println(message.getSubject().toString()); // com.sun.mail.imap.IMAPInputStream@cec0c5
        }
 */
/*
 public static void check(String host, String storeType, String user, String password)
    {
        try {

            //create properties field

            Properties props;
            props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "mail.gmx.net");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.from", "bkpr@gmx.net");
            props.put("username", "bkpr@gmx.at");
            props.put("password", "tomtom57518");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.debug", "true");
            Session emailSession = Session.getDefaultInstance(props);

            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");

            store.connect(host, user, password);

            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);

            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());

            }

            //close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 */