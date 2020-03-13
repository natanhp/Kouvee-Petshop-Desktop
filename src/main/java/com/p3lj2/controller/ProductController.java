package com.p3lj2.controller;

import com.p3lj2.dao.ProductDao;
import com.p3lj2.model.ProductModel;
import com.p3lj2.util.DatabaseInstance;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    @FXML
    private ListView<ProductModel> productListView;

    private ProductDao productDao;

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productDao = new ProductDao();
        ObservableList<ProductModel> productObservableList = FXCollections.observableList(productDao.getAllProduct());

//        productListView.setCellFactory(new Callback<ListView<ProductModel>, ListCell<ProductModel>>() {
//            @Override
//            public ListCell<ProductModel> call(ListView<ProductModel> param) {
//                return new ListCell<ProductModel>() {
//                    @Override
//                    protected void updateItem(ProductModel item, boolean empty) {
//                        super.updateItem(item, empty);
//                        setText(item.getProductName());
//                    }
//                };
//            }
//        });
        productListView.setItems(productObservableList);
    }
}
