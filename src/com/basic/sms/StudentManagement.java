package com.basic.sms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {
	private static List<Student> students = new ArrayList<>();

	public static void main(String[] args) {
		String fileName = "C:/Users/vijai/eclipse-workspace/StudentManagementSystem/src/com/basic/sms/StudentData.txt";
		readStudentData(fileName);

		Scanner sc = new Scanner(System.in);
		boolean exit = false;

		while (!exit) {
			System.out.println("===========Student Management System =================");
			System.out.println("1. Add Student");
			System.out.println("2. Delete Student");
			System.out.println("3. Edit Student");
			System.out.println("4. Display student by Name(Ascending)");
			System.out.println("5. Display student by GPA(Descending)");
			System.out.println("6. Exit");
			System.out.println("Enter your choice Here : ");

			int choice = sc.nextInt();

			switch (choice) {

			case 1:
				addStudent(sc);
				break;
			case 2:
				removeStudent(sc);
				break;
			case 3:
				updateStudent(sc);
				break;
			case 4:
				displayStudentsByName();
				break;
			case 5:
				displayStudentsByGPA();
				break;
			case 6:
				exit = true;
				System.out.println("Thank you for using the Student Management System.");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				break;

			}
		}
	}

	public static void addStudent(Scanner sc) {
		System.out.println(" ======= Add Student ===========");
		System.out.println("Enter ID : ");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Name : ");
		String name = sc.nextLine();
		System.out.println("Enter GPA : ");
		double gpa = sc.nextDouble();
		sc.nextLine();
		System.out.println("Enter City");
		String city = sc.nextLine();

		Student student = new Student(id, name, gpa, city, "NIT");
		students.add(student);

		System.out.println("Student Details added successfully !!! ");
	}

	public static void readStudentData(String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				int id = Integer.parseInt(data[0].trim());
				String name = data[1].trim();
				double gpa = Double.parseDouble(data[2].trim());
				String city = data[3].trim();
				String university = "NIT";
				Student student = new Student(id, name, gpa, city, university);
				students.add(student);
			}
			System.out.println("Student data loaded successfully.");
		} catch (IOException e) {
			System.out.println("An error occurred while reading the student data:" + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Invalid number format in the student data : " + e.getMessage());
		}

	}

	public static void removeStudent(Scanner sc) {
		System.out.println("========Remove Student=======");
		System.out.println("Enter the Student ID to remove : ");
		int id = sc.nextInt();

		boolean found = false;
		Iterator<Student> iterator = students.iterator();
		while (iterator.hasNext()) {
			Student student = iterator.next();
			if (student.getId() == id) {
				iterator.remove();
				found = true;
				break;
			}
		}
		if (found) {
			System.out.println("Student removed successfully.");
		} else {
			System.out.println("Student not found.");
		}

	}

	public static void updateStudent(Scanner scanner) {
		System.out.println("----- Update Student -----");
		System.out.print("Enter the ID of the student to update: ");
		int id = scanner.nextInt();

		boolean found = false;
		for (Student student : students) {
			if (student.getId() == id) {
				System.out.print("Enter Name: ");
				String name = scanner.next();
				if (!name.isEmpty()) {
					student.setName(name);
				}
				System.out.print("Enter GPA: ");
				String gpaInput = scanner.next();
				if (!gpaInput.isEmpty()) {
					double gpa = Double.parseDouble(gpaInput);
					student.setGpa(gpa);
				}
				System.out.print("Enter City: ");
				String city = scanner.next();
				if (!city.isEmpty()) {
					student.setCity(city);
				}
				found = true;
				break;
			}
		}

		if (found) {
			System.out.println("Student updated successfully.");
		} else {
			System.out.println("Student not found.");
		}
	}

	public static void displayStudentsByName() {
		System.out.println("----- Students by Name (Ascending) -----");
		List<Student> sortedStudents = new ArrayList<>(students);
		Collections.sort(sortedStudents, Comparator.comparing(Student::getName));
		System.out.println("ID\tName\tGPA\tCity\tUniverity");
		System.out.println("******************************************");
		displayStudents(sortedStudents);
	}

	public static void displayStudentsByGPA() {
		System.out.println("----- Students by GPA (Descending) -----");
		List<Student> sortedStudents = new ArrayList<>(students);
		Collections.sort(sortedStudents, Comparator.comparingDouble(Student::getGpa).reversed());
		System.out.println("ID\tName\tGPA\tCity\tUniverity");
		System.out.println("******************************************");
		displayStudents(sortedStudents);
	}

	public static void displayStudents(List<Student> students) {
		for (Student student : students) {
			System.out.println(student);
		}
	}

}
