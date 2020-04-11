package main.model;

import javafx.scene.image.ImageView;

public class ProductImage {

    private ImageView imageView;

    public ProductImage(ImageView imageView) {
        this.imageView = imageView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
