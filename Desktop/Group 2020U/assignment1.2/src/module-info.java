module NEW {
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires javafx.media;
	requires javafx.swing;
	requires javafx.swt;
	requires javafx.web;
	requires transitive java.compiler;
	opens WindowTest;
	exports WindowTest;
}