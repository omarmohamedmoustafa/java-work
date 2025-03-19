package com.mycompany.clientside;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PrimaryController implements Initializable {

    @FXML
    private TextField chatArea;
    @FXML
    private Button sendBtn;
    @FXML
    private TextArea historyArea;

    private DataInputStream dis;
    private DataOutputStream dos;

    private StringProperty chatHistory = new SimpleStringProperty("");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        historyArea.textProperty().bind(chatHistory);
    }

    public void setNetworkStreams(DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;

        Thread receiveThread = new Thread(this::receiveMessages);
        receiveThread.setDaemon(true);
        receiveThread.start();
    }

    @FXML
    private void sendMessage() {
        String message = chatArea.getText();
        if (!message.isEmpty()) {
            try {
                dos.writeBytes(message + '\n');
                dos.flush();
                chatArea.clear();
            } catch (IOException e) {
                appendToHistory("Error sending message.\n");
            }
        }
    }

    private void receiveMessages() {
        try {
            while (true) {
                String message = dis.readLine();
                if (message == null) {
                    break;
                }
                appendToHistory("Server: " + message + "\n");
            }
        } catch (IOException e) {
            appendToHistory("Disconnected from server.\n");
        }
    }

    private void appendToHistory(String message) {
        chatHistory.set(chatHistory.get() + message);
    }
}
