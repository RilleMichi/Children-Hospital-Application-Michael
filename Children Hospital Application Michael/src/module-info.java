module childrenHospitalAppMichael {

	exports version5.models;
	exports version2.models;
	exports version3.models;
	exports version4.models;
	exports version7.exception;
	exports version3.exception;
	exports version5.validator;
	exports version7.validator;
	exports version6.exception;
	exports version2.validator;
	exports version4.validator;
	exports version4.exception;
	exports version2.exception;
	exports version6.validator;
	exports version6;
	exports version4;
	exports version5;
	exports version2;
	exports version3;
	exports version1;
	exports version7.models;
	exports version6.models;
	exports version5.exception;
	exports version3.validator;
	
	requires jdk.compiler;
	
	// JavaFX modules (other modules are loaded transitively)
	requires javafx.controls;
	requires transitive javafx.graphics;
	requires javafx.fxml;
	
	// for a clean setup, add gson library to modulepath
	requires com.google.gson;
	
	// package containing your JavaFX application
	exports version7 to javafx.graphics;
	exports topic08 to javafx.graphics;
	
	opens version7.models to com.google.gson;
	opens version7 to javafx.fxml;
	opens topic08 to javafx.fxml;
}