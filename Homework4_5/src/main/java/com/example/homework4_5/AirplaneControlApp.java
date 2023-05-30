package com.example.homework4_5;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AirplaneControlApp extends Application {

    private static final double AIRPLANE_WIDTH = 150;
    private static final double AIRPLANE_HEIGHT = 60;
    private static final double SCREEN_WIDTH = 800;
    private static final double SCREEN_HEIGHT = 600;
    private static final double INITIAL_POSITION_Y = SCREEN_HEIGHT / 2;
    private static final Duration TRANSITION_DURATION = Duration.seconds(1);

    private Polygon airplane;
    private TranslateTransition translateTransition;

    @Override
    public void start(Stage primaryStage) {
        airplane = createAirplane();
        translateTransition = createTranslateTransition();

        Button flyButton = new Button("Летать");
        flyButton.setPrefWidth(100);
        flyButton.setLayoutX(SCREEN_WIDTH / 2 - flyButton.getPrefWidth() - 10);
        flyButton.setLayoutY(SCREEN_HEIGHT - 50);
        flyButton.setOnAction(event -> handleFlyButtonClicked());

        Button stopButton = new Button("Стоп");
        stopButton.setPrefWidth(100);
        stopButton.setLayoutX(SCREEN_WIDTH / 2 + 10);
        stopButton.setLayoutY(SCREEN_HEIGHT - 50);
        stopButton.setOnAction(event -> handleStopButtonClicked());

        Pane root = new Pane(airplane, flyButton, stopButton);
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Polygon createAirplane() {
        double x = SCREEN_WIDTH / 2;
        double y = INITIAL_POSITION_Y;

        Polygon airplane = new Polygon(
                x, y - AIRPLANE_HEIGHT / 2,
                x - AIRPLANE_WIDTH / 2, y + AIRPLANE_HEIGHT / 2,
                x, y + AIRPLANE_HEIGHT / 2,
                x + AIRPLANE_WIDTH / 2, y + AIRPLANE_HEIGHT / 2
        );
        airplane.setFill(Color.BLACK);
        return airplane;
    }

    private TranslateTransition createTranslateTransition() {
        TranslateTransition translateTransition = new TranslateTransition(TRANSITION_DURATION, airplane);
        translateTransition.setByY(-200);
        translateTransition.setCycleCount(Animation.INDEFINITE);
        translateTransition.setAutoReverse(true);
        return translateTransition;
    }

    private void handleFlyButtonClicked() {
        if (translateTransition.getStatus() == Animation.Status.STOPPED) {
            translateTransition.play();
        }
    }

    private void handleStopButtonClicked() {
        translateTransition.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
