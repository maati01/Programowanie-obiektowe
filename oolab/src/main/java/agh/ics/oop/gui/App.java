package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App extends Application implements IAnimalMoveObserver{
    GridPane gridPane = new GridPane();
    AbstractWorldMap map;
    SimulationEngine engine;
    Thread simulationEngineThread;
    int fieldWidth = 40;
    int fieldHeight = 40;

    @Override
    public void init() throws Exception {

        try {
            List<MoveDirection> directions = OptionsParser.parse(getParameters().getRaw().toArray(String[]::new));
            List<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(1, 1), new Vector2d(3, 4)));
            this.map = new GrassField(10);
            this.engine = new SimulationEngine(directions, map, positions);
            this.simulationEngineThread = new Thread(this.engine);

            this.engine.addObserver(this);
            //this.engine.run();
            simulationEngineThread.start();
            //showResult(map);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
        //showResult(this.map);

    }

    public void drawGrid(AbstractWorldMap map) {
        this.gridPane.setGridLinesVisible(false);
        this.gridPane.setGridLinesVisible(true);

        Vector2d lowerLeft = map.getLowerLeft();
        Vector2d upperRight = map.getUpperRight();

        int i = 1;
        for (int x = lowerLeft.x; x <= upperRight.x; x++) {

            int j = 1;
            for (int y = lowerLeft.y; y <= upperRight.y; y++) {
                drawObjectAt(map,new Vector2d(x,y),i,j);
                j++;
            }
            i++;
        }
    }

    public void drawObjectAt(AbstractWorldMap map,Vector2d vector,int i,int j){
        AbstractWorldMapElement object = map.objectAt(vector);
        GuiElementBox guiElement = new GuiElementBox(object);
        if(object == null){
            this.gridPane.add(new Label(""),i,j);
        }
        else {
            try {
                this.gridPane.add(guiElement.getImage(),i,map.getUpperRight().y - map.getLowerLeft().y - j + 2);
                GridPane.setHalignment(guiElement.getImage(), HPos.CENTER);
                GridPane.setValignment(guiElement.getImage(), VPos.CENTER);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
    }

    public void showResult(AbstractWorldMap map) {
        drawFirstRowColumn(map);
        drawGrid(map);
        setParameters(map);
    }

    public void setParameters(AbstractWorldMap map) {
        for(int i = 0; i <= map.getUpperRight().y - map.getLowerLeft().y + 1; i++){
            this.gridPane.getRowConstraints().add(new RowConstraints(this.fieldHeight));
        }
        for(int i = 0; i <= map.getUpperRight().x - map.getLowerLeft().x + 1; i++){
            this.gridPane.getColumnConstraints().add(new ColumnConstraints(this.fieldWidth));
        }

    }

    public void drawFirstRowColumn(AbstractWorldMap map) {
        Vector2d lowerLeft = map.getLowerLeft();
        Vector2d upperRight = map.getUpperRight();

        Label xy = new Label("y/x");
        this.gridPane.add(xy, 0, 0);
        GridPane.setHalignment(xy, HPos.CENTER);

        int i = 1;
        for (int y = lowerLeft.y; y <= upperRight.y; y++) {
            Label label = new Label(String.format("%d", upperRight.y - i + 1));
            this.gridPane.add(label, 0, i);
            GridPane.setHalignment(label, HPos.CENTER);
            i++;
        }

        i = 1;
        for (int x = lowerLeft.x; x <= upperRight.x; x++) {
            Label label = new Label(String.format("%d", x));
            this.gridPane.add(label, i, 0);
            GridPane.setHalignment(label, HPos.CENTER);
            i++;
        }
    }

    @Override
    public void animalMove() {
        Platform.runLater(() -> {
            this.gridPane.getChildren().clear();
            this.gridPane.getRowConstraints().clear();
            this.gridPane.getColumnConstraints().clear();
            showResult(this.map);});
    }

}
