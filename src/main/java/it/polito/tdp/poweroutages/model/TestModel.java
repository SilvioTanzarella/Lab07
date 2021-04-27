package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		//System.out.println(model.getNercList());
		
		/*
		LocalDateTime yesterday = LocalDateTime.of(2021,4,26,0,0,0);
		LocalDateTime today = LocalDateTime.of(2021, 4,27,0,0,0);
		Blackout b = new Blackout(1,yesterday,today);
		System.out.println(b.differenceBetweenDaysInHours());
		*/
		
		Nerc nerc = new Nerc(1,"ciao");
		System.out.println(model.getAllBlackoutsByNerc(nerc));
		
		
		

	}

}
