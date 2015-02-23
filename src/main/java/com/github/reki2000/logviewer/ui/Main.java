package com.github.reki2000.logviewer.ui;

import com.github.reki2000.logviewer.collecter.ParallelCollector;
import com.github.reki2000.logviewer.loader.FileLoader;
import com.github.reki2000.logviewer.loader.LogLoader;
import com.github.reki2000.logviewer.loader.PipeLoader;
import com.github.reki2000.logviewer.parser.SampleLogParser;
import com.github.reki2000.logviewer.model.LineView;
import com.github.reki2000.logviewer.collecter.LogCollector;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main extends Application {

    private TableView table = new TableView();

    public AnchorPane autofit(Node n) {
        final AnchorPane anchor = new AnchorPane(n);
        anchor.setTopAnchor(n, 0.0);
        anchor.setLeftAnchor(n, 0.0);
        anchor.setBottomAnchor(n, 0.0);
        anchor.setRightAnchor(n, 0.0);
        return anchor;
    }

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

        List<LogLoader> loaders = Arrays.asList(
                new PipeLoader("c:\\apps\\git\\bin\\cat.exe c:\\me\\git\\logviewer\\resource\\test.log"),
                new PipeLoader("c:\\apps\\git\\bin\\gzip.exe -dc c:\\me\\git\\logviewer\\resource\\test3.log.gz"),
                new FileLoader("resource\\test2.log"));
        LogCollector collector = new ParallelCollector(loaders, new SampleLogParser());

        ObservableList<LineView> list = FXCollections.observableArrayList(collector.lineViews().collect(Collectors.toList()));
        table.setItems(list);

        stage.setScene(new Scene(autofit(table)));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
