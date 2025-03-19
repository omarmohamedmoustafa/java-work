package com.mycompany.clientside;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class App extends Application {

    private static Scene scene;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;

    @Override
    public void start(Stage stage) throws IOException {
        // Load the primary FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        Parent root = fxmlLoader.load();
        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();

        connectToServer();

        // Pass the DataInputStream and DataOutputStream to the controller
        PrimaryController controller = fxmlLoader.getController();
        controller.setNetworkStreams(dis, dos);
    }

    private void connectToServer() {
        try {
            socket = new Socket("localhost", 5005);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}