import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
public class AutoCell extends Button
{
    private boolean alive;
    private final int x;
    private final int y;
    private boolean hasAnt;
    
    public AutoCell(int x, int y)
    {
        this.setOnAction( e->
                         {
            if (this.alive) {this.alive = false;}
            else {this.alive = true;}
            fixColor();
        });
        this.x = x;
        this.y = y;
        this.alive = false;
        this.hasAnt = false;
        this.setStyle("-fx-background-color:white;-fx-border-color:black;");
    }
    public void fixColor()
    {
        if (this.hasAnt) {this.setStyle("-fx-background-color:red;-fx-border-color:black;");}
        else if (alive) {this.setStyle("-fx-background-color:black;-fx-border-color:black;");}
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
    public void switchState()
    {
        if (alive) {alive = false;}
        else {alive = true;}
        fixColor();
    }
    public void setState(boolean state)
    {
        this.alive = state;
        fixColor();
    }
    public void enterAnt()
    {
        this.hasAnt = true;
        fixColor();
    }
    public void exitAnt()
    {
        this.hasAnt = false;
        fixColor();
    }
}
