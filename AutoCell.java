import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
public class AutoCell extends Button
{
 private boolean alive;
 private final int x;
 private final int y;
 
 public AutoCell(int x, int y)
 {
  this.setOnAction( e->
  {
   if (this.alive == true) {this.alive = false;}
   else {this.alive = true;}
   fixColor();
  });
  this.x = x;
  this.y = y;
  this.alive = false;
  this.setStyle("-fx-background-color:white;-fx-border-color:black;");
 }
 public void fixColor()
 {
  if (this.alive == true) {this.setStyle("-fx-background-color:black;-fx-border-color:black;");}
  else {this.setStyle("-fx-background-color:white;-fx-border-color:black;");}
 }
 public int getX()
 {
  return x;
 }
 public int getY()
 {
  return y;
 }
 public boolean getState()
 {
  return alive;
 }
 public void setState(boolean state)
 {
  this.alive = state;
  fixColor();
 }
}