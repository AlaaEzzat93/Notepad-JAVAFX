/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linepack;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author Alaa Ezzat
 */
public class Notepad_proj extends Application {
    
    
    
        private void saveTextToFile(String content, File file) throws FileNotFoundException {
   
            PrintWriter w;
            w = new PrintWriter(file);
            w.println(content);
            w.close();
       
    }
    
    @Override
    
    public void start(Stage primaryStage) throws FileNotFoundException {
        
        TextArea ta = new TextArea();
        MenuBar menubar = new MenuBar();
        Menu filemenu = new Menu("File");
        Menu editmenu = new Menu("Edit");
        Menu helpmenu = new Menu("Help");
        
        MenuItem newitem = new MenuItem("New");
        newitem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        newitem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                ta.clear();        
            }
            
        });
        //****************//
        MenuItem openitem = new MenuItem("Open");
        openitem.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        openitem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                       
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Resource File");
        fc.getExtensionFilters().addAll(new ExtensionFilter("Text Files","*.txt"));
        File f = fc.showOpenDialog(primaryStage);

            
           FileReader reader_obj = null;
            try {
                reader_obj = new FileReader(f);
                BufferedReader buff_obj = new BufferedReader(reader_obj);
                String str;
                //for(int i=0;i<10;i++){
                str = buff_obj.readLine();
                ta.setText(str);
                
                //} 
                } catch (FileNotFoundException ex) {
                Logger.getLogger(Notepad_proj.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                Logger.getLogger(Notepad_proj.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                try {
                    reader_obj.close();
                } catch (IOException ex) {
                    Logger.getLogger(Notepad_proj.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }
        });

        
        //***************//
        
        MenuItem saveitem = new MenuItem("Save");
        saveitem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        saveitem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
 
            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            
            //Show save file dialog
            File f1 = fileChooser.showSaveDialog(primaryStage);
 
            if (f1 != null) {
                
                           try {
                               saveTextToFile(ta.getText(), f1);
                           } catch (FileNotFoundException ex) {
                               Logger.getLogger(Notepad_proj.class.getName()).log(Level.SEVERE, null, ex);
                           }
            
            }
            }
        });

        //**************//
        MenuItem exititem = new MenuItem("Exit");
        
        //--------------------------------------------------------------//
        //----------------------------------------------------------//
        
        MenuItem undoitem = new MenuItem("Undo");
        undoitem.setAccelerator(KeyCombination.keyCombination("Ctrl+Z"));
        undoitem.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               
                ta.undo();
            }
        });
        //*******************//
        
        MenuItem cutitem = new MenuItem("Cut");
        cutitem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        cutitem.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) {
             
                ta.cut();
            }
        });
        
        //***********************//
        MenuItem copyitem = new MenuItem("Copy");
        copyitem.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
        copyitem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              ta.copy();
            }
        });
        //**********************//
        MenuItem pasteitem = new MenuItem("Paste");
        pasteitem.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));  
        pasteitem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            ta.paste();
            }
        });
        
        //*********************//
        
        MenuItem deleteitem = new MenuItem("Delete");
        deleteitem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              ta.deleteNextChar();
            }
        });
        //********************//
        
        MenuItem selectallitem = new MenuItem("Select All");
        selectallitem.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
        selectallitem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ta.selectAll();
            }
        });
        
        
        //----------------------------------------------------------//
        //-------------------------------------------------------//
        
       
        filemenu.getItems().addAll(newitem,openitem,saveitem,exititem);
        editmenu.getItems().addAll(undoitem,cutitem,copyitem,pasteitem,deleteitem,selectallitem);
        menubar.getMenus().addAll(filemenu,editmenu,helpmenu);
        
        
        BorderPane p = new BorderPane();
        p.setTop(menubar);
        p.setCenter(ta);
        
        Scene scene = new Scene(p, 300, 250);
        
        primaryStage.setTitle("Notepad");
        primaryStage.setScene(scene);
        primaryStage.show();
    

        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
