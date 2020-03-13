package com.P3LJ2.P3L_J_2;

import com.P3LJ2.P3L_J_2.model.Employees;
import javafx.application.Application;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.spring.SpringFxWeaver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class P3LJ2Application {


	public static void main(String[] args) {
		SpringApplication.run(P3LJ2Application.class, args);

//		Employees employees = new Employees(1, "Jack Bauer", "NewFound St No.1", "01/01/1980", "111-1111-11", "Admin", "jackbauer", "jackbauer123");
//		employees.getId();
		Application.launch(MyFxApplication.class, args);

	}

	@Bean
	public FxWeaver fxWeaver(ConfigurableApplicationContext applicationContext) {
		// Would also work with javafx-weaver-core only:
		// return new FxWeaver(applicationContext::getBean, applicationContext::close);
		return new SpringFxWeaver(applicationContext); //(2)
	}

}
