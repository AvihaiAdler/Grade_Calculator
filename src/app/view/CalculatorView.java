package app.view;

import java.io.IOException;
import java.text.DecimalFormat;

import app.controller.Course;
import app.controller.CoursesList;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

//CalculatorView:  contain the SCrollBar & the interactive Buttons/TextFields
public class CalculatorView extends BorderPane {

	private final int SPACE = 3;
	private Button saveBtn = new Button("Save");
	private Button addBtn = new Button("+");

	private Label courseLbl = new Label("Course:");
	private Label weightLbl = new Label("Points:");
	private Label gradeLbl = new Label("Grade:");

	private TextField courseTxt = new TextField();
	private TextField weigthTxt = new TextField();
	private TextField gradeTxt = new TextField();
	private TextField averageTxt = new TextField();

	private CoursesListView coursesList;

	private CoursesList list;

	public CalculatorView() {
		averageTxt.setEditable(false);
		list = new CoursesList();

		drawCourses();
		drawBottomPane();

		// Listener - check if the list change
		list.getGrades().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable observable) {
				// if change detected: update courses & average
				drawCourses();
				setAverage();
			}
		});

		addBtn.setOnAction(e -> {
			if (checkGradeInput() && checkWeightInput()) { // check correct input
				// Build new Course & add it to the list
				Course c = new Course(courseTxt.getText(), Double.parseDouble(gradeTxt.getText()),
						Double.parseDouble(weigthTxt.getText()));
				list.addCourse(c);
				clearFields();
			}			
		});

		saveBtn.setOnAction(e -> {
			try {
				list.writeAll();
				clearFields();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	private void clearFields() {
		courseTxt.clear();
		gradeTxt.clear();
		weigthTxt.clear();
	}

	// Check weight input
	private boolean checkWeightInput() throws NumberFormatException {
		boolean weightFlag = false;
		double weight = Double.parseDouble(weigthTxt.getText());
		if (weight > 0 && weight <= 10)
			weightFlag = true;

		return weightFlag;
	}

	// Check grade input
	private boolean checkGradeInput() throws NumberFormatException {
		boolean gradeFlag = false;
		double grade = Double.parseDouble(gradeTxt.getText());
		if (grade >= 0 && grade <= 100)
			gradeFlag = true;

		return gradeFlag;
	}

	// Draw courses from the CoursesList
	public void drawCourses() {
		// Build list of all Courses & add it to the main window
		coursesList = new CoursesListView(list);
		this.setCenter(coursesList);
	}

	// Draw the interactive Buttons/TextFields
	public void drawBottomPane() {

		VBox bottom = new VBox();
		HBox fields = new HBox();
		HBox average = new HBox();

		if (list.getAverage() > 0)
			setAverage();

		fields.getChildren().addAll(courseLbl, courseTxt, weightLbl, weigthTxt, gradeLbl, gradeTxt, addBtn);
		fields.setAlignment(Pos.CENTER);
		fields.setSpacing(SPACE);
		fields.setPadding(new Insets(SPACE*10, SPACE, SPACE, SPACE));

		average.getChildren().addAll(new Label("Average:"), averageTxt, saveBtn);
		average.setAlignment(Pos.CENTER);
		setSpace(average);
		average.setSpacing(SPACE);

		bottom.getChildren().addAll(fields, average);
		this.setBottom(bottom);
	}

	// Set average
	public void setAverage(/*double d*/) {
		// view of only 1 digit after the decimal point
		averageTxt.setText(new DecimalFormat("###.#").format(list.getAverage()));
		//averageTxt.setText(new DecimalFormat("###.#").format(d));
	}

	private void setSpace(Pane pane) {
		pane.setPadding(new Insets(SPACE));
	}

}
