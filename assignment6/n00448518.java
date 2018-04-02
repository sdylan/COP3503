/* This program is just a means of testing certain learning outcomes of the basic JavaFX
 * framework. In this program many different panes are created to house certain graphical
 * setups. Then they are displayed in a standalone, resizable window. The graphics displayed
 * include a set of four pinwheels in a 2x2 grid, a rudimentary drawing of a hangman game, and
 * a clock face that shows a random time with my name printed in the center.
 *
 *    Created by: Samuel Schwartz
 *    Last Update: Thursday, 11/10/2017 9:37 PM
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.Bindings;
import java.util.Random;

public class n00448518 extends Application{
   @Override
   public void start(Stage primaryStage){  
   
      //Create a GridPane to display the 4 fans
      GridPane fanGrid = new GridPane();
      
      //Set padding outside and inside the grid area
      fanGrid.setPadding(new Insets(10)); 
      fanGrid.setHgap(5); 
      fanGrid.setVgap(5);
      
      //Fill each grid section with a FanPane (defined below in separate class)
      fanGrid.add(new FanPane(), 0, 0);
      fanGrid.add(new FanPane(), 1, 0);
      fanGrid.add(new FanPane(), 0, 1);
      fanGrid.add(new FanPane(), 1, 1);
      
      //Create a BorderPane to hold a ClockPane and its Label
      BorderPane clockHoldPane = new BorderPane();
      clockHoldPane.setPadding(new Insets(10));
      
      //Create a new  ClockPane(defined below in separate class)
      ClockPane clock = new ClockPane();
      
      //From the ClockPane determine the time displayed to print to the Label
      //and create Label
      String timeString = "\t\t"+clock.getHour() + ":" + clock.getMinute(); 
      Label randomTime = new Label(timeString);
      
      //Place the ClockPane and Label in their respective sides of the BorderPane
      clockHoldPane.setCenter(clock);
      clockHoldPane.setBottom(randomTime);
      BorderPane.setAlignment(randomTime, Pos.TOP_CENTER); //and align Label
      
      
      //Create a HBox to house the three separate Panes required
      HBox allThree = new HBox();
      allThree.setPadding(new Insets(10));
      allThree.setSpacing(5);
      
      //Place the three Panes side by side in the HBox. This line also creates a 
      //new HangingManPane in the process(defined below in separate class.
      allThree.getChildren().addAll(fanGrid, new HangingManPane(), clockHoldPane);
      
      //This block creates a StackPane and places the HBox inside the StackPane in order
      //to give it a property that will scale the graphics if the window is resized.
      //The HBox dimensions are locked in to the full size of the StackPane.
      allThree.setMinSize(1100, 400);
      allThree.setMaxSize(1100, 400);
      StackPane scalingNode = new StackPane(allThree);
      scalingNode.setStyle("-fx-background-color: WHITE;");      
      //A binding object is created so that the scale will be determined by either the height or width
      //depending on which has a relatedly smaller scale for the scalingNode object created above.
      NumberBinding maxScale = Bindings.min(scalingNode.widthProperty().divide(1100),
                                      scalingNode.heightProperty().divide(400));
      allThree.scaleXProperty().bind(maxScale);  //Bound to the same size of the StackPane's current scaling
      allThree.scaleYProperty().bind(maxScale);  //the HBox will adjust accordingly.
      
      //Create the scene and place the scalingNode in the scene and setting the appropriate size.
      Scene scene = new Scene(scalingNode, 1100, 400);
      primaryStage.setTitle("Samuel Schwartz - Assignment 6"); //Set the window title.
      primaryStage.setScene(scene);                            //Place the scene on the stage.
      primaryStage.show();                                     //Display the window/stage.
   }

   public static void main(String[] args) {
      launch(args);
   }
}

class FanPane extends Pane {
  private Circle circle = new Circle(80,80,80);
  private Arc arc1 = new Arc(80,80,65,65,30,30);
  private Arc arc2 = new Arc(80,80,65,65,120,30);
  private Arc arc3 = new Arc(80,80,65,65,210,30);
  private Arc arc4 = new Arc(80,80,65,65,300,30);

   public FanPane(){
      circle.setStroke(Color.BLACK);
      circle.setFill(Color.WHITE);
      arc1.setFill(Color.BLACK);
      arc1.setType(ArcType.ROUND);
      arc2.setFill(Color.BLACK);
      arc2.setType(ArcType.ROUND);
      arc3.setFill(Color.BLACK);
      arc3.setType(ArcType.ROUND);
      arc4.setFill(Color.BLACK);
      arc4.setType(ArcType.ROUND);
      getChildren().addAll(circle,arc1,arc2,arc3,arc4);
   }
}

class HangingManPane extends Pane {
  private Arc arc1 = new Arc(100, 375, 75, 50, 0, 180);
  private Line line1 = new Line(100, 50, 100 ,325);
  private Line line2 = new Line(100, 50, 250, 50);
  private Line line3 = new Line(250, 50, 250, 225);
  private Line line4 = new Line(250, 225, 225, 275);
  private Line line5 = new Line(250, 225, 275, 275);
  private Line line6 = new Line(250, 155, 225, 190);
  private Line line7 = new Line(250, 155, 275, 190);
  private Line line8 = new Line(240, 120, 245, 125);
  private Circle circle = new Circle(250, 130, 25);

  public HangingManPane() {
    arc1.setFill(Color.TRANSPARENT);
    arc1.setType(ArcType.OPEN);
    arc1.setStroke(Color.BLACK);
    circle.setStroke(Color.BLACK);
    circle.setFill(Color.WHITE);
    getChildren().addAll(arc1, line1, line2, line3, line4, line5, line6, line7, circle);
  }
}

class ClockPane extends Pane {
  private int hour;
  private int minute;
  private double w = 350;
  private double h = 350;
  Random rand = new Random();

  public ClockPane(){
    setRandomTime();
  }

  public ClockPane(int hour, int minute){
    this.hour = hour;
    this.minute = minute;
    paintClock();
  }

  public int getHour(){
    return hour;
  }

  public String getMinute() {
    if (this.minute <10)
      return "0" + minute;
    return String.valueOf(minute);
  }

  public void setMinute(int minute) {
    this.minute = minute;
    paintClock();
  }

  public void setHour(int hour) {
    this.hour = hour;
  }

  public double getW() {
    return w;
  }

  public void setW(double w) {
    this.w = w;
    paintClock();
  }

  public double getH() {
    return h;
  }

  public void setH(double h) {
    this.h = h;
    paintClock();
  }

  public void setRandomTime() {
    this.hour = rand.nextInt(12)+ 1;
    this.minute = rand.nextInt(60);

    paintClock();
  }

  protected void paintClock() {
    double clockRadius = Math.min(w,h) * 0.8 * 0.5;
    double centerX = w/2;
    double centerY = h/2;

    Circle circle = new Circle(centerX, centerY, clockRadius);
    circle.setFill(Color.WHITE);
    circle.setStroke(Color.BLACK);
    Text t1 = new Text(centerX - 5, centerY - clockRadius + 14, "12");
    Text t2 = new Text(centerX - clockRadius + 3, centerY + 5, "9");
    Text t3 = new Text(centerX + clockRadius - 10, centerY + 3, "3");
    Text t4 = new Text(centerX - 3, centerY + clockRadius - 3, "6");
    Text myName = new Text(centerX - 40, centerY + 5, "Samuel Schwartz");

    double mLength = clockRadius * 0.65;
    double xMinute = centerX + mLength * Math.sin(minute * (2 * Math.PI / 60));
    double minuteY = centerY - mLength * Math.cos(minute * (2 * Math.PI / 60));
    Line mLine = new Line(centerX, centerY, xMinute, minuteY);
    mLine.setStroke(Color.BLUE);

    double hLength = clockRadius * 0.5;
    double hourX = centerX + hLength * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
    double hourY = centerY - hLength * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
    Line hLine = new Line(centerX, centerY, hourX, hourY);
    hLine.setStroke(Color.GREEN);

    getChildren().clear();
    getChildren().addAll(circle, t1, t2, t3, t4, mLine, hLine, myName);
  }
}