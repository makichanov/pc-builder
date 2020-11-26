package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class Main extends Application {

    //ссылка на элемент Stage главного окна
    private static Stage primaryStage;

    //загрузка главного окна
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        primaryStage.setTitle("Компоновщик ПК");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //загрузка окна менеджера
    public static void showManager() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("partsManagerDialog.fxml"));
            AnchorPane page = loader.load();

            Stage managerStage = new Stage();
            managerStage.setTitle("Parts Manager");
            managerStage.initModality(Modality.WINDOW_MODAL);
            managerStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            managerStage.setScene(scene);

            PartsManagerController controller = loader.getController();
            controller.setDialogStage(managerStage);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            managerStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //загрузка окна создания
    public static void addPartWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("addPartDialog.fxml"));
            AnchorPane page = loader.load();

            Stage addPartStage = new Stage();
            addPartStage.setTitle("Add Part");
            addPartStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            addPartStage.setScene(scene);

            AddPartController controller = loader.getController();
            controller.setDialogStage(addPartStage);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            addPartStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getPartsFilesPath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void setPartsFilesPath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
            primaryStage.setTitle("Компоновщик ПК - " + file.getName());
        } else {
            prefs.remove("filePath");
            primaryStage.setTitle("Компоновщик ПК");
        }
    }

}
