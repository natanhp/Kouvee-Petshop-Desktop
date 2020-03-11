package com.P3LJ2.P3L_J_2;

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
//		SpringApplication.run(P3LJ2Application.class, args);
		Application.launch(MyFxApplication.class, args);

	}

	@Bean
	public FxWeaver fxWeaver(ConfigurableApplicationContext applicationContext) {
		// Would also work with javafx-weaver-core only:
		// return new FxWeaver(applicationContext::getBean, applicationContext::close);
		return new SpringFxWeaver(applicationContext); //(2)
	}

}
