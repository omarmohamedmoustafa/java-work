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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    MenuItem _new, open, save, saveAs, exit;
    MenuItem undo, cut, copy, paste, delete,selectAll;
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
        save = new MenuItem("Save");
        saveAs = new MenuItem("Save As");
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

        file.getItems().addAll(_new, open, save, saveAs, new SeparatorMenuItem(),exit);
        edit.getItems().addAll(undo, new SeparatorMenuItem(),cut, copy, paste, delete,new SeparatorMenuItem(),selectAll);
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

        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                openFile(primaryStage);
            }
        });

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                saveFile(primaryStage);
            }
        });

        saveAs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                saveFileAs(primaryStage);
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

        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // Create an Alert dialog
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("About ES45 Notepad");
                alert.setHeaderText("ES45 Notepad - Version 1.0");
                alert.setContentText("Author: Omar Mohamed Mostafa\nThis is a simple notepad application created using JavaFX.\nLicense Expiration date: 1-1-3000");

                // Show the dialog
                alert.showAndWait();
            }
        });

        Scene myS = new Scene(bpane, 500, 200);
        primaryStage.setScene(myS);
        primaryStage.setTitle("ES45 Notepad");
        primaryStage.show();
    }

    private void openFile(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");

        // Set the initial directory to the last used directory
        if (lastUsedDirectory != null) {
            fileChooser.setInitialDirectory(lastUsedDirectory);
        }

        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            // Update the last used directory
            lastUsedDirectory = file.getParentFile();
            currentFile = file; // Set the current file

            try {
                String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
                ta.setText(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile(Stage primaryStage) {
        if (currentFile != null) {
            // Save to the current file
            saveToFile(currentFile);
        } else {
            // If no current file, perform "Save As"
            saveFileAs(primaryStage);
        }
    }

    private void saveFileAs(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");

        // Set the initial directory to the last used directory
        if (lastUsedDirectory != null) {
            fileChooser.setInitialDirectory(lastUsedDirectory);
        }

        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            // Update the last used directory
            lastUsedDirectory = file.getParentFile();
            currentFile = file; // Set the current file
            saveToFile(file);
        }
    }

    private void saveToFile(File file) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(ta.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}