package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App extends Application implements IAnimalMoveObserver{
    private final GridPane gridPane = new GridPane();
    private AbstractWorldMap map;
    private ThreadedSimulationEngine engine;
    int fieldWidth = 40;
    int fieldHeight = 40;

    @Override
    public void init() throws Exception {

        try {
            List<MoveDirection> directions = OptionsParser.parse(getParameters().getRaw().toArray(String[]::new));
            List<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(1, 1), new Vector2d(3, 4)));
            this.map = new GrassField(10);
//            this.map = new RectangularMap(10,10);
            this.engine = new ThreadedSimulationEngine(this.map, positions);
            this.engine.addObserver(this);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox userInterface = setUserInterface();
        VBox appInterface = new VBox(userInterface, this.gridPane);
        Scene scene = new Scene(appInterface,1000,500);
        primaryStage.setScene(scene);
        primaryStage.show();
        showResult();

    }
    //TODO
    //zwierzak czasem znika jak wyjdzie poza mape

    public void drawGrid() {
        this.gridPane.setGridLinesVisible(false);
        this.gridPane.setGridLinesVisible(true);

        Vector2d lowerLeft = this.map.getLowerLeft();
        Vector2d upperRight = this.map.getUpperRight();

        int i = 1;
        for (int x = lowerLeft.x; x <= upperRight.x; x++) {

            int j = 1;
            for (int y = lowerLeft.y; y <= upperRight.y; y++) {
                drawObjectAt(new Vector2d(x,y),i,j);
                j++;
            }
            i++;
        }
    }

    public void drawObjectAt(Vector2d vector,int i,int j){
        AbstractWorldMapElement object = this.map.objectAt(vector);
        GuiElementBox guiElement = new GuiElementBox(object);
        if(object != null) {
            try {
                this.gridPane.add(guiElement.getImage(), i, this.map.getUpperRight().y - this.map.getLowerLeft().y - j + 2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void showResult() {
        drawFirstRowColumn();
        drawGrid();
        setParameters();
    }

    public void setParameters() {
        for(int i = 0; i <= this.map.getUpperRight().y - this.map.getLowerLeft().y + 1; i++){
            this.gridPane.getRowConstraints().add(new RowConstraints(this.fieldHeight));
        }
        for(int i = 0; i <= this.map.getUpperRight().x - this.map.getLowerLeft().x + 1; i++){
            this.gridPane.getColumnConstraints().add(new ColumnConstraints(this.fieldWidth));
        }

    }

    public void drawFirstRowColumn() {
        Vector2d lowerLeft = this.map.getLowerLeft();
        Vector2d upperRight = this.map.getUpperRight();

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
            showResult();});
    }

    public HBox setUserInterface(){
        TextField inputText = new TextField();
        Button startButton = new Button("Start");
        HBox userInterface = new HBox(inputText, startButton);


        startButton.setOnAction(click -> {
            List<MoveDirection> moves = OptionsParser.parse(inputText.getText().split(" "));
            this.engine.setDirections(moves);
            Thread simulationEngineThread = new Thread(this.engine);
            simulationEngineThread.start();

        });

        return userInterface;
    }
}
