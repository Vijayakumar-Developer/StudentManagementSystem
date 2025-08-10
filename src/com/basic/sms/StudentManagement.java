package com.basic.sms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {
	private static List<Student> students = new ArrayList<>();

	public static void main(String[] args) {
		String fileName = "StudentData.txt";
		readStudentData(fileName);
		
		Scanner sc = new Scanner(System.in);
		boolean exit = false ;
		
		while(!exit) {
			System.out.println("===========Student Management System =================");
			System.out.println("1. Add Student");
			System.out.println("2. Delete Student");
			System.out.println("3. Edit Student");
			System.out.println("4. Display student by Name(Ascending)");
			System.out.println("5. Display student by GPA(Descending)");
			System.out.println("6. Exit");
			System.out.println("Enter your choice Here : ");
			
			int choice = sc.nextInt();
			
			switch(choice) {
			
			case 1 :
				addStudent(sc);
				break;
			
			}
		}
	}
	
	public static void addStudent(Scanner sc){
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
		
		Student student = new Student(id,name,gpa,city,"NIT");
		students.add(student);
		
		System.out.println("Student Details added successfully !!! ");
	}

	public static void readStudentData(String fileName) {
		try(BufferedReader br = new BufferedReader(new FileReader (fileName))){
			String line;
			
			while((line = br.readLine()) != null) {
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
		}catch (IOException e) {
			System.out.println("An error occurred while reading the student data:" + e.getMessage());
		}catch(NumberFormatException e) {
			System.out.println("Invalid number format in the student data : " +e.getMessage());
		}

	}

}
