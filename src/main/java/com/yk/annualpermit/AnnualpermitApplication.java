package com.yk.annualpermit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;

import com.yk.annualpermit.config.SmartLocaleResolver;
import com.yk.annualpermit.model.Employee;
import com.yk.annualpermit.model.Leave;
import com.yk.annualpermit.repository.EmployeeRepository;

@SpringBootApplication
public class AnnualpermitApplication {


    @Autowired
    EmployeeRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(AnnualpermitApplication.class, args);
	}

	 @Bean
	    public MessageSource messageSource() {
	        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	        messageSource.setBasenames("classpath:/mes");
	        messageSource.setDefaultEncoding("UTF-8");
	        messageSource.setUseCodeAsDefaultMessage(true);
	        messageSource.setCacheSeconds((int) TimeUnit.HOURS.toSeconds(1));
	        messageSource.setFallbackToSystemLocale(false);
	        return messageSource;
	    }

	    @Bean
	    public LocaleResolver localeResolver() {
	        return new SmartLocaleResolver();
	    }

	    @Bean
	    InitializingBean sendDatabase() {
	        return () -> {

	            Calendar calendarStart = Calendar.getInstance();
	            calendarStart.set(Calendar.YEAR, 2020);
	            calendarStart.set(Calendar.MONTH, 06);
	            calendarStart.set(Calendar.DATE, 10);

	            Employee e=new Employee();
	            e.setAddress("dörtyol");
	            e.setEmail("abc@gmail.com");
	            e.setGender("male");
	            e.setName("at");
	            e.setSurname("ta");
	            e.setPhoneNumber("19071907192");
	            e.setRole("employee");
	            e.setTckn("33333333333");
	            e.setStartDate(calendarStart);


	            Leave leave=new Leave();
	            leave.setStatus(String.valueOf(LeaveStatus.approved));
	            leave.setWorkDay(3);


	            List<Leave> leaveList=new ArrayList<Leave>();
	            leaveList.add(leave);

	            e.setLeaveList(leaveList);

	            repository.save(e);
	        };
	    }
}
