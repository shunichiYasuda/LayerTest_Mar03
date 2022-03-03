module com.GenSci.sample.testLayer {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
    //requires transitive javafx.graphics;

    opens com.GenSci.sample.testLayer to javafx.fxml;
    exports com.GenSci.sample.testLayer;
}
