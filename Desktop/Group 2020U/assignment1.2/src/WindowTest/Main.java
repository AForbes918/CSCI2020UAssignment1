package WindowTest;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This program will show a circle and its angle as well as have the user
 * be able to drag the points around and the angles will be calculated as the
 * points get moved
 * @author Dennis Alfred de Leon 100659532, Andre Forbes 100669470
 */
public class Main extends Application{
	static double pointRadius = 5.0;							//radius of triangle vertex points
	static double mainRadius = 75.0;							//radius of main circle
	static double mainX = 200.0;								//X coordinate of main circle
	static double mainY = 200.0;								//Y coordinate of main circle
	static double error = 3.0;									//mouse room for dragging points on outer circle
	public void start (Stage stage)  {	
		double coord[][] = new double[3][2];					//double array for coordinates of triangle vertices
		double lineL[] = new double[3];							//double array for the length of triangle sides
		double angles[] = new double[3];						//double array for triangle inner angles
		Text[] angleText = {new Text(),new Text(),new Text()};	//Text array to display the triangle inner angles
		Line lines[] = new Line[3];								//Line array to hold all the lines for sides of the main triangle
		
		Circle circles[] = new Circle[3];						//Circle Array to hold all the triangle vertex points
		Circle main = new Circle(mainX,mainY,mainRadius);		//Creates main circle
		main.setFill(Color.TRANSPARENT);						//Makes circle transparent
		main.setStroke(Color.BLACK);							
		interfaceSetup(coord,circles,lines);					//Calls interfaceSetup
		drawTriangle(coord,lineL,lines,circles);				//Calls drawTriangle
		getAngles(angles,lineL);								//Calls getAngles
		showText(angles,angleText,coord);						//Calls showText
		
		circles[0].setOnMouseDragged(e -> { 					//Checks if point 0 is being moved by the user, then updates accordingly
		      if (circles[0].contains(e.getX(), e.getY())&&isOnLine(e.getX(),e.getY())) {
		        circles[0].setCenterX(e.getX());
		        circles[0].setCenterY(e.getY());
		        coord[0][0]=e.getX();
				coord[0][1]=e.getY();
		        drawTriangle(coord,lineL,lines,circles);		
		        getAngles(angles,lineL);
		        showText(angles,angleText,coord);
		      }
		    });
		circles[1].setOnMouseDragged(e -> { 					//Checks if point 1 is being moved by the user, then updates accordingly
		      if (circles[1].contains(e.getX(), e.getY())&&isOnLine(e.getX(),e.getY())) {
		        circles[1].setCenterX(e.getX());
		        coord[1][0]=e.getX();
				coord[1][1]=e.getY();
		        circles[1].setCenterY(e.getY());
		        drawTriangle(coord,lineL,lines,circles);
		        getAngles(angles,lineL);
		        showText(angles,angleText,coord);
		      }
		    });
		    
		circles[2].setOnMouseDragged(e -> { 					//Checks if point 2 is being moved by the user, then updates accordingly
		      if (circles[2].contains(e.getX(), e.getY())&&isOnLine(e.getX(),e.getY())) {
		        circles[2].setCenterX(e.getX());
		        circles[2].setCenterY(e.getY());
		        coord[2][0]=e.getX();
				coord[2][1]=e.getY();
		        drawTriangle(coord,lineL,lines,circles);
		        getAngles(angles,lineL);
		        showText(angles,angleText,coord);
		      }
		    });
		
		Group group = new Group();								//Creates new group
		group.getChildren().addAll(main);						//Adds all visuals
		group.getChildren().addAll(circles);
		group.getChildren().addAll(lines);
		group.getChildren().addAll(angleText);
		Scene scene = new Scene(group,600,300);					//Creates new scene
		stage.setTitle("Assignment 1.3");						//Sets title
		stage.setScene(scene);									//Sets scene
		stage.show();											//Displays Scene
	}
	
	public static void main(String[] args){
		launch(args);	
	}
	
	//Function that sets up first points and lines (random)
	public static void interfaceSetup(double c[][],Circle points[],Line lines[]) {	
		randomPoints(c);
		
		for(int i=0;i<3;i++) {
				points[i] = new Circle();
				points[i].setCenterX(c[i][0]);
				points[i].setCenterY(c[i][1]);
				points[i].setRadius(pointRadius);
		}
		lines[0] = new Line(c[0][0],c[0][1],c[1][0],c[1][1]); //creates line b
		lines[1] = new Line(c[1][0],c[1][1],c[2][0],c[2][1]); //creates line a
		lines[2] = new Line(c[2][0],c[2][1],c[0][0],c[0][1]); //creates line c
		
	}
	
	//Function that sets lines for Triangle
	public static void drawTriangle(double c[][],double lc[],Line lines[],Circle circles[]) {
		lc[0]=Math.sqrt(Math.pow(c[0][0]-c[1][0],2)+Math.pow(c[0][1]-c[1][1],2)); //line length for c
		lc[1]=Math.sqrt(Math.pow(c[1][0]-c[2][0],2)+Math.pow(c[1][1]-c[2][1],2)); //line length for a
		lc[2]=Math.sqrt(Math.pow(c[2][0]-c[0][0],2)+Math.pow(c[2][1]-c[0][1],2)); //line length for b
		
		lines[0].setStartX(circles[0].getCenterX());
	    lines[0].setStartY(circles[0].getCenterY());
	    lines[0].setEndX(circles[1].getCenterX());
	    lines[0].setEndY(circles[1].getCenterY());
	    lines[1].setStartX(circles[0].getCenterX());
	    lines[1].setStartY(circles[0].getCenterY());
	    lines[1].setEndX(circles[2].getCenterX());
	    lines[1].setEndY(circles[2].getCenterY());
	    lines[2].setStartX(circles[1].getCenterX());
	    lines[2].setStartY(circles[1].getCenterY());
	    lines[2].setEndX(circles[2].getCenterX());
	    lines[2].setEndY(circles[2].getCenterY());
		
	}
	
	//Function that checks if mouse is still on circumference of circle (with error)
	public static boolean isOnLine(double x, double y) {
		boolean isOn=false;
		double check = Math.sqrt(Math.pow(mainX-x,2)+Math.pow(mainY-y,2));
		if(check<mainRadius+error&&check>mainRadius-error) {
			isOn=true;
		}
		return isOn;
	}
	
	//Function that gets angles from triangle
	public static void getAngles(double angles[],double l[]) {
		angles[0]=Math.round(Math.toDegrees(Math.acos((l[1] * l[1] - l[2] * l[2] - l[0] * l[0]) / (-2 * l[2] * l[0])))*100.0)/100.0;
		angles[1]=Math.round(Math.toDegrees(Math.acos((l[2] * l[2] - l[1] * l[1] - l[0] * l[0]) / (-2 * l[1] * l[0])))*100.0)/100.0;
		angles[2]=Math.round(Math.toDegrees(Math.acos((l[0] * l[0] - l[2] * l[2] - l[1] * l[1]) / (-2 * l[1] * l[2])))*100.0)/100.0;
	}
	
	//Function that sets text from angle data
	public static void showText(double angles[],Text text[],double c[][])
	{
		for(int i=0;i<3;i++) {
			if(c[i][0]<mainX) {
				text[i].setX(c[i][0]-50);
			}
			else{
				text[i].setX(c[i][0]+10);				
			}
			if(c[i][1]<mainY) {
				text[i].setY(c[i][1]-50);
			}
			else{
				text[i].setY(c[i][1]+10);				
			}
			text[i].setText(Double.toString(angles[i]));
		}
	}
	
	//Function that creates random coordinates for triangle vertices on the circle
	public static void randomPoints(double c[][]) {
		for(int i=0;i<3;i++) {
			double x = Math.random()*(mainRadius);
			int sign = (int)(Math.random()*2)+1;
			if(sign==2) {
				x=0-x;
			}
			c[i][0]=mainX+x;
			double y = Math.sqrt(Math.pow(mainRadius, 2)-Math.pow(x, 2));
			sign = (int)(Math.random()*2)+1;
			if(sign==2) {
				y=0-y;
			}

			c[i][1]=mainY+y;
			System.out.println(c[i][0]);
			System.out.println(c[i][1]);
		}
	}
	
}



