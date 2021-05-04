package br.com.veloe.arquivo.config;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebViewConfig extends Application {
    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();

        WebView view = new WebView();
        WebEngine engine = view.getEngine();
        engine.load(getClass().getResource("/index.html").toExternalForm());
        root.getChildren().add(view);
        
        Scene scene = new Scene(root,1000,650);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String [] args){
        Application.launch(args);
    }
}
