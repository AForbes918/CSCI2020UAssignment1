package WindowTest;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * This program will be used to create a histogram of a text file by counting
 * how many times a letter is present in that document
 * @author Dennis Alfred de Leon 100659532, Andre Forbes 100669470
 */

public class Main extends Application{
    /**
     * Create labels, buttons and textfield
     */
	Label label = new Label("Filename");
	Button reg = new Button("View");
	TextField text = new TextField();
	static int character[] = new int[26];
	public void start (Stage stage)  {
		StackPane pane = new StackPane();
		CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart barChart = new BarChart(xAxis,yAxis);
		XYChart.Series set = new XYChart.Series();

		for(int i=0;i<26;i++) {
			character[i]=0;
			set.getData().add(new XYChart.Data((Character.toString((char)(i+65))), character[i]));
		}
		barChart.getData().add(set);
		VBox vBox = new VBox(barChart);
		HBox hBox = new HBox(label);
		hBox.getChildren().add(text);
		hBox.getChildren().add(reg);
		vBox.getChildren().add(hBox);
        StackPane root = new StackPane();
        root.getChildren().add(vBox);
        /**
         * Setup an action for when the user clicks the view button
         */
        reg.setOnAction(e -> {
			try {
				enterFile(text.getText(),set);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
        
        Scene scene = new Scene(root, 800, 450);
        stage.setScene(scene);
        stage.show();
 		
		
	}
	
	public static void main(String[] args){
		launch(args);	
	}
	/**
         * This will be used to have the user entered file name and append 
         * how many time a letter has appeared in the file
         */
	public static void enterFile(String fileName, XYChart.Series set) throws IOException {
		int ch;
		try {
			BufferedReader r = new BufferedReader(new FileReader(fileName));
			for(int i=0;i<26;i++) {
				character[i]=0;
			}
                        /**
                         * This loop will be used to read the file as well as
                         * if the letter is a capital or a lowercase letter, it 
                         * will be entered as the exact same
                         */
			while((ch=r.read())!=-1) {
				if(ch<91&&ch>54) {
					System.out.println((char)ch);
					character[ch-65]++;
				}
				else if(ch<123&&ch>96) {
					character[ch-97]++;
				}
				else {
					
				}
			}
                        /**
                         * Add the designated letters to the each data point
                         * on the graph
                         * */
                        
			for(int i=0;i<26;i++) {
				set.getData().add(new XYChart.Data((Character.toString((char)(i+65))), character[i]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}



