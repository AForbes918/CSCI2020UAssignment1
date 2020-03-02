package WindowTest;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * This program will be used to display and calculate an investment based upon
 * the amount invested, the years and the annual interest rate
 * @author Dennis Alfred de Leon 100659532, Andre Forbes 100669470
 */
public class Main extends Application{
        /**
         * Setup the variable for window so that they will display the 
         * various labels to aid the user for input and create boxes and a
         * button so the user is able to interact with the program
         */
	Label label1 = new Label("Investment Amount");
	Label label2 = new Label("Years:");
	Label label3 = new Label("Annual Interest Rate:");
	Label label4 = new Label("Future Value:");

	TextField iA = new TextField();
	TextField years = new TextField();
	TextField aIR = new TextField();
	Button reg = new Button("Calculate");
	TextArea dataFld = new TextArea();
		
	public void start(Stage stage) throws Exception {
		
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(5,5,5,5));
		pane.setHgap(20);
		pane.setVgap(10);
		iA.setPrefWidth(100);
		years.setPrefWidth(100);
		aIR.setPrefWidth(100);
		dataFld.setPrefWidth(100);
		/**
                 * Setup the window so that it will display properly and in
                 * an organized way with the description of input/output on left 
                 * and the user entered information.
                 */
		pane.add(label1,0,1);
		pane.add(iA,1,1);
		pane.add(label2,0,2);
		pane.add(years,1,2);
		pane.add(label3,0,3);
		pane.add(aIR,1,3);
		pane.add(label4,0,4);
		pane.add(reg,1,5);
		
                /**
                 * Setup the action for the button
                 */
                reg.setOnAction(e -> calculateData(pane));
        
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setWidth(300);
		stage.setHeight(300);
		stage.setTitle("Assignment 1.2");
		stage.show();		
	}
	/**
         * This method will be used to calculate the Future value based off of
         * the user's inputs
         * @param pane 
         */
	private void calculateData(GridPane pane) {
		double calc;
		calc = 1+(Double.parseDouble(aIR.getText())/1200);
		calc =  Math.pow(calc,Double.parseDouble(years.getText())*12);
		calc*= Double.parseDouble(iA.getText());
		dataFld.setText(Double.toString(Math.round(calc*100.0)/100.0));
		dataFld.setDisable(true);
		pane.add(dataFld, 1, 4);	
	}
	public static void main(String passes[])
	{
		launch(passes);	
	}
	
}
