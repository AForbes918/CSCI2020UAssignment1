package WindowTest;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This program will be used to display in a window 
 * three random playing cards
 * @author Dennis Alfred de Leon 100659532, Andre Forbes 100669470
 */
public class Main extends Application{
	@Override
	public void start (Stage stage)  {
		String file1, file2, file3;
		ImageView cd1, cd2, cd3;
		int card1, card2,card3;
		
                //Generate a random value for which card to be created
		card1 = (int)(Math.random()*52)+1;
		file1 = card1+".png";
                //Set variable to the designated picture from folder
		cd1 = new ImageView(new Image(getClass().getResourceAsStream("/pics/"+file1)));
		cd1.setFitWidth(100);
		cd1.setPreserveRatio(true);
		
		do {
                    /**
                     * Make sure that the value is not the same as the first card
                     */
			card2 = (int)(Math.random()*52)+1;
		}
		while(card1==card2);
		
		file2 = card2+".png";
                //Set variable to the designated picture from folder
		cd2 = new ImageView(new Image(getClass().getResourceAsStream("/pics/"+file2)));
		cd2.setFitWidth(100);
		cd2.setPreserveRatio(true);
		
		do {
                    /**
                     * Make sure that the value is not the same as the first
                     * and second cards
                     */
			card3 = (int)(Math.random()*52)+1;
		}
		while(card2==card3);
		
		file3 = card3+".png";
                //Set variable to the designated picture from folder
		cd3 = new ImageView(new Image(getClass().getResourceAsStream("/pics/"+file3)));
		cd3.setFitWidth(100);
		cd3.setPreserveRatio(true);
		
		Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        /**
         * Create a new box that will be used to store the 
         */
        HBox box = new HBox();
        box.getChildren().add(cd1);
        box.getChildren().add(cd2);
        box.getChildren().add(cd3);
        root.getChildren().add(box);
        /**
         * Set the title of the window as well input the scene and the box
         * such that they will be shown
         */
        stage.setTitle("Assignment1_1");
        stage.setScene(scene); 
        stage.sizeToScene(); 
        stage.show(); 
	}
	
	public static void main(String[] args){
		launch(args);	
	}
	
}



