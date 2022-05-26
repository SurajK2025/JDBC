package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.model.StudentDao;
import com.model.Students;

public class Start {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to Students Management App:");
		
		boolean exitApp = false;
		while(!exitApp) {
			System.out.println("1. Add a student");
			System.out.println("2. Delete a student");
			System.out.println("3. View all students");
			System.out.println("4. Exit app");
			
			int choice = Integer.parseInt(br.readLine());
			boolean status = false;
			switch(choice) {
				case 1:
					//add student
					System.out.println("Enter your name:");
					String name = br.readLine();
					
					System.out.println("Enter your number:");
					String number = br.readLine();
					
					System.out.println("Enter your city:");
					String city = br.readLine();
					
					Students st = new Students(name, number, city);
					status = StudentDao.insertStudentToDB(st);
					
					if(status) {
						System.out.println("Student added...");
					}
					else {
						System.out.println("Try again.");
					}
					
					System.out.println(st);
					break;
					
				case 2:
					//delete student
					System.out.println("Enter student id:");
					int id = Integer.parseInt(br.readLine());
					status = StudentDao.deleteStudent(id);
					if(status) {
						System.out.println("Student added...");
					}
					else {
						System.out.println("Try again.");
					}
				case 3:
					//display students
					StudentDao.showAllStudennts();
					break;
				case 4:
					System.out.println("Closing application....");
					exitApp = true;
					break;
				default:
					System.out.println("Not a valid choice.");
					continue;
			}
		}

	}

}
