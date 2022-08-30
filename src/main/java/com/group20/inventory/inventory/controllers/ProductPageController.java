package com.group20.inventory.inventory.controllers;

import com.group20.inventory.inventory.models.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductPageController implements Initializable {
    @FXML
    public TableView tableView;

    @FXML
    protected void buttonClicked(ActionEvent event) {
        System.out.println("Clicked");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableView();
    }

    private void initTableView() {
        TableColumn<Product, String> column1 =
                new TableColumn<>("Name");

        column1.setCellValueFactory(
                new PropertyValueFactory<>("name"));


        TableColumn<String, String> column2 =
                new TableColumn<>("Price");

        column2.setCellValueFactory(
                new PropertyValueFactory<>("price"));


        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);

        tableView.getItems().add(
                new Product("John", 444));
        tableView.getItems().add(
                new Product("Jane", 44));
    }
}
