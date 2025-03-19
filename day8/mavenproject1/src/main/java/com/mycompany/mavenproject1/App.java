
package com.mycompany.mavenproject1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.control.SeparatorMenuItem;

/**
 * JavaFX App
 */
public class App extends Application {

    BorderPane bpane;
    MenuBar mbar;
    Menu file;
    Menu edit;
    Menu help;
    MenuItem _new, open, saveLow, saveHigh, openLow, openHigh, exit;
    MenuItem undo, cut, copy, paste, delete, selectAll;
    MenuItem about;

    TextArea ta;

    private File lastUsedDirectory = null;

    private File currentFile = null;

    @Override
    public void init() throws Exception {
        super.init();
        ta = new TextArea();

        _new = new MenuItem("New");
        open = new MenuItem("Open");

        saveLow = new MenuItem("Save Low");
        saveHigh = new MenuItem("Save High");
        openLow = new MenuItem("Open Low");
        openHigh = new MenuItem("Open High");

        exit = new MenuItem("Exit");

        undo = new MenuItem("Undo");
        cut = new MenuItem("Cut");
        copy = new MenuItem("Copy");
        paste = new MenuItem("Paste");
        delete = new MenuItem("Delete");
        selectAll = new MenuItem("Select All");

        about = new MenuItem("About");

        file = new Menu("File");
        edit = new Menu("Edit");
        help = new Menu("Help");

        file.getItems().addAll(_new, saveLow, saveHigh, openHigh, openLow, new SeparatorMenuItem(), exit);
        edit.getItems().addAll(undo, new SeparatorMenuItem(), cut, copy, paste, delete, new SeparatorMenuItem(),
                selectAll);
        help.getItems().add(about);

        mbar = new MenuBar();
        mbar.getMenus().addAll(file, edit, help);
        bpane = new BorderPane();
        bpane.setTop(mbar);
        bpane.setCenter(ta);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        _new.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ta.clear();
                currentFile = null;
            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.close();
            }
        });

        undo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ta.undo();
            }
        });

        cut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ta.cut();
            }
        });

        copy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ta.copy();
            }
        });

        paste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ta.paste();
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ta.clear();
            }
        });
        selectAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ta.selectAll();
            }
        });

        saveHigh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                saveToFileHigh(currentFile);
            }
        });

        saveLow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                saveToFileLOW(currentFile);
            }
        });

        openHigh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                openFileHigh(primaryStage);
            }
        });

        openLow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                openFileLow(primaryStage);
            }
        });

        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // Create an Alert dialog
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("About ES45 Notepad");
                alert.setHeaderText("ES45 Notepad - Version 1.0");
                alert.setContentText(
                        "Author: Omar Mohamed Mostafa\nThis is a simple notepad application created using JavaFX.\nLicense Expiration date: 1-1-3000");
                alert.showAndWait();
            }
        });

        Scene myS = new Scene(bpane, 500, 200);
        primaryStage.setScene(myS);
        primaryStage.setTitle("ES45 Notepad");
        primaryStage.show();
    }

    private void openFileLow(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        if (lastUsedDirectory != null) {
            fileChooser.setInitialDirectory(lastUsedDirectory);
        }
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            try {
                FileInputStream fis = new FileInputStream(file);
                int size = fis.available();
                byte[] b = new byte[size];
                fis.read(b);
                ta.setText(new String(b));
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void openFileHigh(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        if (lastUsedDirectory != null) {
            fileChooser.setInitialDirectory(lastUsedDirectory);
        }
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            try {
                DataInputStream dis = new DataInputStream(new FileInputStream(file));
                String s = dis.readUTF();
                ta.setText(s);
                dis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveToFileLOW(File f) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                byte[] b = ta.getText().getBytes();
                fos.write(b);
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveToFileHigh(File f) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
                dos.writeUTF(ta.getText());
                dos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}