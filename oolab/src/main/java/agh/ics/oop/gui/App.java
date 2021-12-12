package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App extends Application {
    GridPane gridPane = new GridPane();
    Scene scene = new Scene(gridPane);
    int fieldWidth = 30;
    int fieldHeight = 30;

    @Override
    public void init() throws Exception {

        try {
            List<MoveDirection> directions = OptionsParser.parse(getParameters().getRaw().toArray(String[]::new));
            List<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(1, 1), new Vector2d(3, 4)));
            AbstractWorldMap map = new GrassField(3);
            SimulationEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            showResult(map);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void drawGrid(AbstractWorldMap map) {
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

        if(object == null){
            this.gridPane.add(new Label(""),i,j);
        }
        else {
            Label label = new Label(object.toString());
            this.gridPane.add(label,i,map.getUpperRight().y - map.getLowerLeft().y - j + 2);
            GridPane.setHalignment(label, HPos.CENTER);

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

        System.out.println(lowerLeft);
        System.out.println(upperRight);
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
}
