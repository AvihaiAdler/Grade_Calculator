package app.view;

import app.controller.Course;
import app.controller.CoursesList;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

//CoursesListView: contain a list of CourseView - a list of all the courses with graphics
public class CoursesListView extends ScrollPane {

	public CoursesListView(CoursesList list) {
		drawCourses(list);
	}

	// Draw courses from the CoursesList
	public void drawCourses(CoursesList list) {
		VBox courses = new VBox();
		
		// Iterate over the List
		if (list.getGrades() != null)
			for (Course course : list.getGrades())
				courses.getChildren().add(new CourseView(course, list)); // Build CourseView & add it to the list
		courses.setAlignment(Pos.CENTER);

		this.setContent(courses); // Specific for ScrollPane
		this.setFitToWidth(true); // Bind the width & height of ScrollBar to the wrapper window
		this.setFitToHeight(true);
	}

}
