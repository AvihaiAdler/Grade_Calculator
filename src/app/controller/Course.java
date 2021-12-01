package app.controller;

import java.io.Serializable;
import java.util.Objects;

// Basic Course contain name, weight and grade
public class Course implements Serializable {
	private static final long serialVersionUID = -4418368345395259467L;
	private String name;
	private double grade;
	private double weight;
	
	public Course(String name, double grade, double weight) {
		setCourse(name);
		setGrade(grade);
		setWeight(weight);
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public String getName() {
		return name;
	}

	public void setCourse(String name) {
		this.name = name;
	}

	public String toString() {
		return "Course: " + name + " Weight: " + weight + " Grade: " + grade;
	}
	
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Course course = (Course) o;
        return Objects.equals(getName(), course.getName());      
    }
	
}
