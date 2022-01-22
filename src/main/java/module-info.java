module agh.tw.exam {
    requires javafx.controls;
    requires javafx.fxml;

    opens agh.tw.exam to javafx.fxml;
    exports agh.tw.exam;
}
