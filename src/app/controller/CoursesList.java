package app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//CoursesList contain observable list of Course
public class CoursesList implements Serializable {
	private static final long serialVersionUID = 395265740478413664L;
	private ArrayList<Course> grades = new ArrayList<>(); //regular arrayList for i/o
	private ObservableList<Course> observableGrades;
	
	File file = new File("grades.bin");
	
	//i/o
	private ObjectOutputStream output;
	private ObjectInputStream input;
	
	public CoursesList() {
		try {
			grades = readAll();
			observableGrades = FXCollections.observableArrayList(grades);	//convert arrayList to observableList			
		} catch (Exception e) {
			observableGrades = FXCollections.observableArrayList();
		}	
	}
	
	public ObservableList<Course> getGrades() {
		return observableGrades;
	}

	public float getAverage() {
		return (float) calAverage();
	}

	//Calculate weighted average
	public double calAverage() {
		double numerator = 0, denumarator = 0;
		for(Course course : observableGrades) {
			numerator += course.getGrade() * course.getWeight();	
			denumarator += course.getWeight();
		}		
		return numerator / denumarator;
	}
	
	//Remove course
	public void remove(Course course) {
		if(observableGrades.contains(course)) 
			observableGrades.remove(course);
	}
	
	//Read grades list
	public ArrayList<Course> readAll() throws FileNotFoundException, IOException, ClassNotFoundException{
		ArrayList<Course> tmp = new ArrayList<>();
		input = new ObjectInputStream(new FileInputStream(file));
		tmp = (ArrayList<Course>) input.readObject();
		input.close();
		return tmp;
	}
	
	//Write grades list
	public void writeAll() throws IOException {
		grades = new ArrayList<>();
		
		for (Course course : observableGrades) 
			grades.add(course);
		
		if(!file.exists())
			file.createNewFile();
		output = new ObjectOutputStream(new FileOutputStream(file));
		output.writeObject(grades);
		output.close();
	}
	
	public void addCourse(Course course) {
		if(!observableGrades.contains(course)) 
			observableGrades.add(course);		
	}
}
