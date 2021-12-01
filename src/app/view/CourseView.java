package app.view;

import app.controller.Course;
import app.controller.CoursesList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

//CourseView: HBox with all the Course details
public class CourseView extends HBox {
	private final int SPACE = 4;
	
	private Button remove = new Button("X");
	
	private Label courseLbl = new Label("Course:");
	private Label weightLbl = new Label("Points:");
	private Label gradeLbl = new Label("Grade:");
	
	private TextField courseTxt = new TextField();
	private TextField weigthTxt = new TextField();
	private TextField gradeTxt = new TextField();
	
	public CourseView(Course course, CoursesList list) {
		setSpace();
		remove.setOnAction(e-> list.remove(new Course(courseTxt.getText(), Double.parseDouble(gradeTxt.getText()), Double.parseDouble(weigthTxt.getText()))));
		drawCourse(course);
	}
	
	public CourseView(CoursesList list) {
		setSpace();
		remove.setOnAction(e-> list.remove(new Course(courseTxt.getText(), Double.parseDouble(gradeTxt.getText()), Double.parseDouble(weigthTxt.getText()))));
	}
	
	private void setSpace() {
		this.setSpacing(SPACE);
		this.setPadding(new Insets(SPACE));
		this.setAlignment(Pos.CENTER);
	}
	
	//Build the HBox
	private void drawCourse(Course course) {
		courseTxt.setText(course.getName());
		courseTxt.setEditable(false);
		
		gradeTxt.setText(Double.toString(course.getGrade()));
		gradeTxt.setEditable(false);
		
		//paint the txtBox if the grade i F or B+
		if(course.getGrade() < 60)
			gradeTxt.setStyle("-fx-background-color: #ff704d");
		else if(course.getGrade() >= 85)
			gradeTxt.setStyle("-fx-background-color: #b3ff99");
		
		weigthTxt.setText(Double.toString(course.getWeight()));
		weigthTxt.setEditable(false);
		
		this.getChildren().addAll(courseLbl, courseTxt, weightLbl, weigthTxt, gradeLbl, gradeTxt, remove);
	}
	
}
