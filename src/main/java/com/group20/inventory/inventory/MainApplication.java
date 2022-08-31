package com.group20.inventory.inventory;

import com.group20.inventory.inventory.utils.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.util.Objects;

public class MainApplication extends Application {
    double winX,winY;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        root.setOnMousePressed(event->{
            winX = event.getX();
            winY = event.getY();
        });

        root.setOnMouseDragged(event->{
            stage.setX(event.getScreenX()-winX);
            stage.setY(event.getScreenY()-winY);
        });

        DBConnection.getConnection();
    }


    public static void main(String[] args) {
//        Category.createObject("Cleaners");
//        int result = Category.deleteObject(2);
//
//        Category category = Category.getObjectByName("Cleaners");
//        Product product = Product.createObject("Rice", 33.3F, 44.4F, 32, category);
//        System.out.println(product);
//        System.out.println(product.getCategory());
//
//        for(Category category : Category.selectAllObjects()){
//            System.out.println(category.toString());
//        }
        launch();
    }
}