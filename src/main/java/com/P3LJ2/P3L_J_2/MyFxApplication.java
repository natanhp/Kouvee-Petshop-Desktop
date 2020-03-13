package com.P3LJ2.P3L_J_2;

import com.P3LJ2.P3L_J_2.controller.MyController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class MyFxApplication extends Application {

    private ConfigurableApplicationContext applicationContext;
    private Parent rootNode;
    private FXMLLoader fxmlLoader;


    @Override
    public void init() throws Exception {
        applicationContext = SpringApplication.run(P3LJ2Application.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(applicationContext::getBean);
    }

//    @Override
//    public void init() throws Exception{
//        String[] args = getParameters().getRaw().toArray(new String[0]);
//
//        this.applicationContext = new SpringApplicationBuilder()
//                .sources(P3LJ2Application.class)
//                .run(args);
//    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        fxmlLoader.setLocation(getClass().getResource("MyFxApplication.fxml"));
        rootNode = fxmlLoader.load();

        primaryStage.setTitle("Test Application");
        Scene scene = new Scene(rootNode, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
//        Parent root = fxWeaver.loadView(MyController.class);
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }
}
