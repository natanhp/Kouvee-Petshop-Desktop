package com.P3LJ2.P3L_J_2;

import com.sun.javafx.util.Logging;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class MyFxApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources(P3LJ2Application.class)
                .run(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(MyController.class);
//        try{
//        throw new ClassNotFoundException();
//        }catch(ClassNotFoundException e)
//        {
//            System.out.printf("Exception handled in class MyFxApplicaation.java, method start");
//            throw e;
//        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }
}
