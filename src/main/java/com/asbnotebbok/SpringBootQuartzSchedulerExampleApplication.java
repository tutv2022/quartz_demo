package com.asbnotebbok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class SpringBootQuartzSchedulerExampleApplication {

	static void getLastDayOfMonthUsingCalendar(int month) throws ParseException {
		Date d = new SimpleDateFormat("dd/MM/yyyy").parse("01/02/2024");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
		System.out.println(c.getTime());
	}

	public static void main(String[] args) throws ParseException {

		getLastDayOfMonthUsingCalendar(2);

		SpringApplication.run(SpringBootQuartzSchedulerExampleApplication.class, args);
	}
}
