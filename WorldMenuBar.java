package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class WorldMenuBar extends MenuBar {

    private MenuHandler handler;

    private Menu fileMenu;

    private Menu speedMenu;

    private Menu runMenu;

    private Menu store;

    private Menu infoMenu;

    private MenuItem startItem;

    private MenuItem pauseItem;

    private MenuItem resetItem;

    private MenuItem saveItem;

    private MenuItem loadItem;

    private MenuItem quitItem;

    private MenuItem workAntItem;

    private MenuItem digAntItem;

    private MenuItem infoItem;

    private MenuItem timeItem;

    private MenuItem foodItem;

    private MenuItem oneSpeedItem;

    private MenuItem twoSpeedItem;

    private MenuItem threeSpeedItem;

    private MenuItem fourSpeedItem;


    private AntFarm farm;

    private WorldPane pane;




    public WorldMenuBar(WorldPane worldPane)
    {

        handler = new MenuHandler();

        pane = worldPane;

        farm = pane.getFarm();

        fileMenu = new Menu("File");

        store = new Menu("Store");

        runMenu = new Menu("Run");

        speedMenu = new Menu("Speeds");

        infoMenu = new Menu("Info");

        resetItem = new MenuItem("Reset");

        resetItem.setOnAction(handler);


        startItem = new MenuItem("Start Simulation");

        startItem.setOnAction(handler);


        pauseItem = new MenuItem("Pause");

        pauseItem.setOnAction(handler);


        saveItem = new MenuItem("Save File");

        saveItem.setOnAction(handler);


        loadItem = new MenuItem("Open File");

        loadItem.setOnAction(handler);


        quitItem = new MenuItem("Exit");

        quitItem.setOnAction(handler);

        workAntItem = new MenuItem("Working Ant");

        workAntItem.setOnAction(handler);

        digAntItem = new MenuItem("Digging Ant");

        digAntItem.setOnAction(handler);

        foodItem = new MenuItem("Food");

        foodItem.setOnAction(handler);

        infoItem = new MenuItem("Information");

        infoItem.setOnAction(handler);

        timeItem = new MenuItem("Time");

        timeItem.setOnAction(handler);

        oneSpeedItem = new MenuItem(".5X Speed");

        oneSpeedItem.setOnAction(handler);


        twoSpeedItem = new MenuItem("1X Speed");

        twoSpeedItem.setOnAction(handler);


        threeSpeedItem = new MenuItem("2X Speed");

        threeSpeedItem.setOnAction(handler);


        fourSpeedItem = new MenuItem("4X Speed");

        fourSpeedItem.setOnAction(handler);


        fileMenu.getItems().addAll(saveItem, loadItem, quitItem);

        runMenu.getItems().addAll(startItem, pauseItem, resetItem);

        store.getItems().addAll(workAntItem, digAntItem,foodItem);

        speedMenu.getItems().addAll(oneSpeedItem, twoSpeedItem, threeSpeedItem, fourSpeedItem);

        infoMenu.getItems().addAll(infoItem,timeItem);
        getMenus().addAll(fileMenu, runMenu, speedMenu, store,infoMenu);


    }

    private class MenuHandler implements EventHandler<ActionEvent>
    {
        private boolean isRunning = false;

        public void handle(ActionEvent event) {
            if(event.getSource() == quitItem)
            {
                System.exit(0);
            }
            if(event.getSource() == saveItem)
            {
                if (isRunning) {
                    pane.stopSimulation();
                    isRunning = false;
                }
                FileChooser chooser = new FileChooser();
                File status = chooser.showSaveDialog(null);
                if(status != null) {
                    pane.getFarm().save(pane.getFarm().getScreen(), status);
                }
            }

            if(event.getSource() == startItem)
            {
                if (!isRunning) {
                    pane.runSimulation();
                    isRunning = true;
                }


            }
            if(event.getSource() == resetItem )
            {
                if (isRunning)
                    pane.stopSimulation();

                pane.resetWorld();
                isRunning = false;
            }

            if(event.getSource() == pauseItem)
            {
                if (isRunning)
                    pane.stopSimulation();

                isRunning = false;
            }

            if(event.getSource() == loadItem)
            {
                if (isRunning) {
                    pane.stopSimulation();
                }
                isRunning = false;
                FileChooser chooser = new FileChooser();
                File status = chooser.showOpenDialog(null);
                if(status != null)
                {
                    farm.load(status, farm.getScreen(), pane.getAnts());
                    pane.update();
                }
            }
            if(event.getSource() == workAntItem)
            {
                Ant[] ants = pane.getAnts();
                int numberOfAnts = pane.getNumberOfAnts();

                Queen ant = (Queen) ants[0];
                if (ant.getNestCenterX() != -1) {
                    if(pane.getFoodObtained()>=10) {
                    ants[numberOfAnts] = new WorkingAnt(ant.getNestCenterX() - 1, ant.getNestCenterY());
                    pane.setNumberOfAnts(++numberOfAnts);
                        pane.setFoodObtained(pane.getFoodObtained() - 10);
                    }
                    if(pane.getFoodObtained()<9) {

                        Stage popupwindow2=new Stage();
                        popupwindow2.initModality(Modality.APPLICATION_MODAL);
                        Label label= new Label("Mine more food"+"\n"+ "current source: " + pane.getFoodObtained());


                        Button button1= new Button("Close");
                        button1.setOnAction(e -> popupwindow2.close());


                        VBox layout= new VBox(10);
                        layout.getChildren().addAll(label, button1);

                        layout.setAlignment(Pos.CENTER);

                        Scene scene1= new Scene(layout, 300, 250);
                        popupwindow2.setScene(scene1);
                        popupwindow2.showAndWait();


                    }

                }
            }

            if(event.getSource() == digAntItem)
            {   Behavior behavior = new Behavior();
                Ant[] ants = pane.getAnts();
                int numberOfAnts = pane.getNumberOfAnts();

                Queen ant = (Queen) ants[0];
                if (ant.getNestCenterX() != -1) {
                    if(pane.getFoodObtained()>=2) {
                        ants[numberOfAnts] = new DiggingAnt(ant.getNestCenterX() - 1, ant.getNestCenterY());
                        pane.setNumberOfAnts(++numberOfAnts);

                        pane.setFoodObtained(pane.getFoodObtained() - 2);
                    }
                    if(pane.getFoodObtained()<1) {

                    Stage popupwindow1=new Stage();
                    popupwindow1.initModality(Modality.APPLICATION_MODAL);
                        Label label= new Label("Mine more food"+"\n"+ "current source: " + pane.getFoodObtained());


                    Button button1= new Button("Close");
                    button1.setOnAction(e -> popupwindow1.close());


                    VBox layout= new VBox(10);
                    layout.getChildren().addAll(label, button1);

                    layout.setAlignment(Pos.CENTER);

                    Scene scene1= new Scene(layout, 300, 250);
                    popupwindow1.setScene(scene1);
                    popupwindow1.showAndWait();


                }
                }

            }
            if(event.getSource() == foodItem)
            {
                Label l = new Label("no text input");
                TextInputDialog td = new TextInputDialog("Enter here");
                td.setHeaderText("How many food do you wnat?");
                td.showAndWait();

                l.setText(td.getEditor().getText());
               Behavior behavior = new Behavior();
               String tem =td.getEditor().getText();
               int numberOfFood = Integer.parseInt(tem);
               behavior.foodGenerator(farm.getScreen());



            }
            if(event.getSource() == infoItem)
            {
                Stage popupwindow=new Stage();
                popupwindow.initModality(Modality.APPLICATION_MODAL);
                popupwindow.setTitle("Current Ant Farm Info");
                String numOfFood = Integer.toString(farm.foodCounter());
                String numOfWorkingant = Integer.toString(farm.WorkingantCounter(farm.getScreen()));
                String numOfDiggingant = Integer.toString(farm.DiggingAntCounter(farm.getScreen()));
                String numOfQueen = Integer.toString(farm.queenCounter(farm.getScreen()));
                String numOfFoodObtained = Integer.toString(pane.getFoodObtained());

               // Label label1= new Label("Source: "+numOfFood);
                Label label1= new Label("Food Obtained: "+numOfFoodObtained);

                Label label2= new Label("Queen: "+numOfQueen);
                Label label3= new Label("DiggingAnt: "+numOfDiggingant);
                Label label4= new Label("WorkingAnt: "+numOfWorkingant);


                Button button1= new Button("Close");
                button1.setOnAction(e -> popupwindow.close());


                VBox layout= new VBox(10);
                layout.getChildren().addAll(label1,label2,label3,label4, button1);

                layout.setAlignment(Pos.CENTER);

                Scene scene1= new Scene(layout, 300, 250);
                popupwindow.setScene(scene1);
                popupwindow.showAndWait();

            }

            if(event.getSource() == timeItem){





            }



            if(event.getSource() == oneSpeedItem)
            {
                if (isRunning) {
                    pane.stopSimulation();

                    pane.getFarm().setPlayspeed(1000);

                    pane.runSimulation();

                }
                else
                    pane.getFarm().setPlayspeed(1000);
            }

            if(event.getSource() == twoSpeedItem)
            {

                if (isRunning) {

                    pane.stopSimulation();
                    pane.getFarm().setPlayspeed(500);
                    pane.runSimulation();

                }
                else
                    pane.getFarm().setPlayspeed(500);

            }

            if(event.getSource() == threeSpeedItem)
            {

                if (isRunning) {

                    pane.stopSimulation();
                    pane.getFarm().setPlayspeed(250);
                    pane.runSimulation();

                }

                else
                    pane.getFarm().setPlayspeed(250);
            }

            if(event.getSource() == fourSpeedItem)

            {
                if (isRunning) {

                    pane.stopSimulation();
                    pane.getFarm().setPlayspeed(100);
                    pane.runSimulation();
                }

                else
                    pane.getFarm().setPlayspeed(100);
            }
        }
    }
}