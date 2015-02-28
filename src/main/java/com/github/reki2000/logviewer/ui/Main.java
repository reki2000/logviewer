package com.github.reki2000.logviewer.ui;

import com.github.reki2000.logviewer.model.LineView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
        col.setCellValueFactory(new PropertyValueFactory<LineView, Integer>(f));
        return col;
    }

    @Override
    public void start(Stage stage) throws Exception{

        stage.setTitle("LogViewer");

        table.setEditable(false);
        table.getColumns().addAll(strCol("id"), strCol("time"), strCol("server"), strCol("host"), strCol("user"), strCol("uri"), intCol("status"), intCol("elapsed"), strCol("referrer"), strCol("ua"));
        table.setItems(FXCollections.emptyObservableList());

        TextField yyyymmdd = new TextField();
        yyyymmdd.setPrefWidth(80.0);
        yyyymmdd.setText("20140228" /* DateTimeFormatter.ofPattern("uuuuMMdd").format(ZonedDateTime.now())*/ );

        TextField keyword = new TextField();
        keyword.setPrefWidth(120.0);

        Button loadButton = new Button("Load");
        loadButton.setOnAction(e -> controller.onLoadButton(yyyymmdd.getText(), keyword.getText()));

        HBox topBar = new HBox(new Label("Date:"), yyyymmdd, new Label("Keyword:"), keyword, loadButton, taskBar);

        Scene scene = new Scene(new BorderPane(table, topBar, null, null, null));
        stage.setScene(scene);

        stage.show();
    }

    TableView getTable() {
        return this.table;
    }

    TaskController taskController = new TaskController(taskBar);
    TaskController getTaskController() {
        return this.taskController;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
