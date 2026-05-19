package seng201.team0.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import seng201.team0.Adventurer;
import seng201.team0.GameEnvironment;
import seng201.team0.Item;
import seng201.team0.ItemType;

import java.util.Optional;

public class GuiHelper {
    private GuiHelper() {
    }

    public static String money(double value) {
        return String.format("%.0f", value);
    }

    public static void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.showAndWait();
    }



}