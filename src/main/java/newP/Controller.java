package newP;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;


public class Controller
{
    public BKPR bkpr;
    private RequestHandler rq;
    private MailChecker mc;
    private ArrayList<Bestellposition> positionen= new ArrayList<>();
    private Gson gson;
    //private BKPR bkpr;
    private ObservableList<Bestellposition> dlmPositionen;
    private ObservableList<Mailobject>dlmMails;





    public Controller(BKPR bkpr) throws InterruptedException, IOException, MessagingException
    {

        bkpr = new BKPR();
        this.bkpr = bkpr;
        mc=new MailChecker();
        dlmPositionen = FXCollections.observableArrayList();
        dlmMails=FXCollections.observableArrayList();
        MailChecker mc=new MailChecker();
        rq = new RequestHandler();
        listenLaden();
        //Befüllen der Listen
        //getMails();
        //getBestellungen();
       // Application.launch(BKPR.class, args);

    }

    public void listenLaden() throws IOException, InterruptedException, MessagingException {
        ObservableList<Bestellposition> bpList
                = FXCollections.observableArrayList();
        //Für Bestellpositionen

        ObservableList<Bestellposition> emptyList= FXCollections.observableArrayList();
        Bestellposition leereBPos = new Bestellposition();
        leereBPos.setName("keine Bestellungen verfügbar");
        //Für Mails
        ObservableList<Mailobject> mlList = FXCollections.observableArrayList();
        ObservableList<Mailobject>emptyMList = FXCollections.observableArrayList();
        Mailobject emptyMo = new Mailobject();
        emptyMo.setSender("Keine Mails vorhanden");
        emptyMList.add(emptyMo);
        System.out.println("BPOSumsetzung");
        //BPOS umsetzung    bpList

        bpList= getBestellungen();//gefüllt und nicht null

        // System.out.println(bpList.get(1));//null ?????????????????????????????????????????
        //BP ist gefüllt
        //aber sobald es auf ObservableArray übertragen wird: collecting data
        if(bpList != null)
        {
            //wie kann eine Nullpointerexception auftreten wenn ich !=null abfange?

           // bkpr.getListBPos().setItems(bpList);

        }
        else
        {
            //emptyList.add(leereBPos);
            // listBPos.getItems().setAll(emptyList);
        }
        //Mailobjects umsetzung
        System.out.println("MPosumsetzung");

        mlList = getMails();
        if(mlList!=null)
        {
            System.out.println("test");
            bkpr.getListMPos().getItems().setAll(mlList); //hier gehts schief
            // listMPos.setItems(mlList);
        }
        else
        {
            // listMPos.getItems().setAll(emptyMList);
        }


    }


    public ObservableList<Mailobject> getMails() throws IOException, MessagingException
    {
            //Mails füllen
            dlmMails= mc.getAllMails();
            return dlmMails;
            /*
            if(bkpr.getListBPos()==null)
            {
                System.out.println("Leere Liste");

            }
            else {
                bkpr.getListMPos().setItems(dlmMails);
            }

             */



            /*
            ListView<String> list = new ListView<String>();
ObservableList<String> items =FXCollections.observableArrayList (
    "Single", "Double", "Suite", "Family App");
list.setItems(items);
             */
    }


    public boolean deleteOrder(Bestellposition bp) throws IOException
    {
        Bestellposition obj = new Bestellposition();
        //obj aus Liste lesen
        String url = "http://localhost:1337/sendDB";
        String json = gson.toJson(obj);
        String bestellungJSON = "noch zu füllen";

        rq.makePostRequest(url, bestellungJSON);
        //try to check for success or failure
        return true;
    }

 //  public ObservableList<Bestellposition> getBestellungen() throws IOException, InterruptedException
 public ObservableList<Bestellposition> getBestellungen() throws IOException, InterruptedException
    {

        String url = "http://localhost:1337/sendDB";
        String positionenS = rq.makeGetRequest(url);

        if(positionenS!=null)
        {

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson1 = gsonBuilder.create();

            Bestellposition[] bp = gson1.fromJson(positionenS, Bestellposition[].class);

            for (Bestellposition bpos : bp)//Liste befüllen
            {
                dlmPositionen.add(bpos);
                System.out.println("ID" + bpos.getRid() + " Tisch: " + bpos.getTableno() + " DRINK: " + bpos.getDrink());
            }


            return dlmPositionen; //nicht null
        }
        else
        {
            System.out.println("Leere Positionsliste");
            Bestellposition leererBP = new Bestellposition();
            leererBP.setName("Keine Bestellungen");
            dlmPositionen.clear();
            dlmPositionen.add(leererBP);
            return dlmPositionen;
        }
       //Pos füllen



    }

}
