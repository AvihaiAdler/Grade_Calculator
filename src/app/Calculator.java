package app;

import app.view.CalculatorView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Main program
public class Calculator extends Application {

	private final int WIDTH = 900;
	private final int HEIGHT = 600;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		CalculatorView calculator = new CalculatorView();
		Scene scene = new Scene(calculator, WIDTH, HEIGHT);
		stage.setScene(scene);
		stage.show();
	}

}
