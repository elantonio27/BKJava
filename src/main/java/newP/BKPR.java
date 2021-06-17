package newP;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BKPR extends Application implements Initializable {

    @FXML
    private Label lblOutput;
    @FXML
    private Button btntest;
    @FXML
    private ListView<Bestellposition> listBPos;
    @FXML
    private ListView<Mailobject> listMPos;

    private Controller controller;
    public static void main(String[] args) {
        launch(args);
    }

    public Label getLblOutput() {
        return lblOutput;
    }

    public void setLblOutput(Label lblOutput) {
        this.lblOutput = lblOutput;
    }

    public Button getBtntest() {
        return btntest;
    }

    public void setBtntest(Button btntest) {
        this.btntest = btntest;
    }

    public ListView<Bestellposition> getListBPos() {
        return listBPos;
    }

    public void setListBPos(ListView<Bestellposition> listBPos) {
        this.listBPos = listBPos;
    }

    public ListView<Mailobject> getListMPos() {
        return listMPos;
    }

    public void setListMPos(ListView<Mailobject> listMPos) {
        this.listMPos = listMPos;
    }

    @Override
    public void start(Stage primaryStage) throws IOException, MessagingException, InterruptedException {

        Parent root = FXMLLoader.load(getClass().getResource("BKPR.fxml"));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
        controller = new Controller(this);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listBPos = new ListView<>();
        listMPos = new ListView<>();
        lblOutput = new Label();
        btntest = new Button();


    }
}
