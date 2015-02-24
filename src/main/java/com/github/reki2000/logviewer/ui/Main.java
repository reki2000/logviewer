package com.github.reki2000.logviewer.ui;

import com.github.reki2000.logviewer.model.LineView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    private TableView table = new TableView();
    private HBox taskBar = new HBox();
    private Controller controller = new Controller(this);

    public TableColumn strCol(String f) {
        TableColumn col = new TableColumn(f);
        col.setCellValueFactory(new PropertyValueFactory<LineView,String>(f));
        return col;
    }
    public TableColumn intCol(String f) {
        TableColumn col = new TableColumn(f);
        col.setCellValueFactory(new PropertyValueFactory<LineView,Integer>(f));
        return col;
    }

    @Override
    public void start(Stage stage) throws Exception{

        stage.setTitle("LogViewer");

        table.setEditable(false);
        table.getColumns().addAll(strCol("time"), strCol("server"), strCol("host"), strCol("user"), strCol("uri"), intCol("status"), intCol("elapsed"));
        table.setItems(FXCollections.emptyObservableList());

        Button loadButton = new Button("Load");
        loadButton.setOnAction(e -> controller.onLoadButton());

        HBox topBar = new HBox(loadButton, taskBar);

        Scene scene = new Scene(new BorderPane(table, topBar, null, null, null));
        stage.setScene(scene);

        stage.show();
    }

    TableView getTable() {
        return this.table;
    }

    HBox getTaskBar() {
        return this.taskBar;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
