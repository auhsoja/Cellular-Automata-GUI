import javafx.application.Application; //Application controls the lifecycle of your application
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext; //The magic pen and paintbrush
import java.util.ArrayList;
import java.io.File;
import java.util.Random;
import javafx.animation.AnimationTimer;
import java.util.Map;
import java.util.HashMap;
import javafx.scene.input.KeyCode;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import java.util.Arrays;
import javafx.scene.paint.Color;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.application.Platform;
import java.util.Random;
public class TheAnt extends Application
{
    private BorderPane bp;
    private Stage primary;
    private GridPane gp;
    private AutoCell[][] grid;
    private AnimationTimer at;
    private int[] ant;
    
    private Map<Character,ArrayList<Integer>> dirControl;
    private Map<Character,Character> rTurn;
    private Map<Character,Character> lTurn;    
    private char dir;
    
    public TheAnt()
    {
        bp = new BorderPane();
        gp = new GridPane();
        grid = new AutoCell[60][60];
        dir = 'r';
        
        //Setting up the movement stuff
        dirControl = new HashMap<Character,ArrayList<Integer>>();
        rTurn = new HashMap<Character,Character>();
        lTurn = new HashMap<Character,Character>();
        dirControl.put('l', new ArrayList<Integer>(Arrays.asList(-1,0)));
        dirControl.put('r', new ArrayList<Integer>(Arrays.asList(1,0)));
        dirControl.put('d', new ArrayList<Integer>(Arrays.asList(0,1)));
        dirControl.put('u', new ArrayList<Integer>(Arrays.asList(0,-1)));
        
        rTurn.put('r','d');
        rTurn.put('d','l');
        rTurn.put('l','u');
        rTurn.put('u','r');
        lTurn.put('r','u');
        lTurn.put('u','l');
        lTurn.put('l','d');
        lTurn.put('d','r');
        
        for (int i =0; i<60; i++)
        {
            for (int j = 0; j<60; j++)
            {
                AutoCell cell = new AutoCell(j,i);
                cell.setMinSize(10,10);
                cell.setMaxSize(10,10);
                grid[j][i] = cell;
                gp.add(cell,j,i);
            }
        }
        ant = new int[2];
        ant[0] = 25;
        ant[1] = 25;
        grid[ant[0]][ant[1]].enterAnt();
        at = new AnimationTimer(){
            @Override
            public void handle(long now)
            {
                try
                {
                    Thread.sleep(2);
                }
                catch (Exception ex) {}
                update(grid);
            }
        };
    }
    @Override
    public void start(Stage primary)
    {
        primary.setTitle("Langton's Ant");
        Scene s = new Scene(bp,550,550);
        bp.setCenter(gp);
        //Menu Stuff
        MenuBar mbar = new MenuBar();
        bp.setTop(mbar);
        Menu commands = new Menu("Commands");
        mbar.getMenus().addAll(commands);
        MenuItem startItem = new MenuItem("Start");
        MenuItem randomizeItem = new MenuItem("Randomize Grid");
        MenuItem stopItem = new MenuItem("Stop");
        MenuItem clearItem = new MenuItem("Clear");
        MenuItem quitItem = new MenuItem("Quit");
        startItem.setOnAction( e-> {at.start();});
        randomizeItem.setOnAction( e-> {
            int l = grid.length;
            Random gen = new Random();
            for(int i = 0; i<l; i++)
            {
                for(int j = 0; j<l; j++)
                {
                    int rand = Math.abs(gen.nextInt()%2);
                    grid[i][j].setState(rand==1);
                }
            }
        });            
        stopItem.setOnAction( e-> {at.stop();});
        clearItem.setOnAction( e-> {
            int l = grid.length;
            for(int i = 0; i<l; i++)
            {
                for(int j = 0; j<l; j++)
                {
                    grid[i][j].setState(false);
                }
            }
        });
        quitItem.setOnAction( e-> Platform.exit());
        commands.getItems().addAll(startItem,randomizeItem,stopItem,clearItem,quitItem);
        primary.setScene(s);
        primary.show();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
    public boolean isEdge(AutoCell b, AutoCell[][] grid)
    {
        if (b.getX() == 0 || b.getX() == grid.length-1 || b.getY() == 0 || b.getY() == grid.length-1) {return true;}
        return false;
    }
    public void update(AutoCell[][] grid)
    {
        if (grid[ant[0]][ant[1]].getState()) { dir = rTurn.get(dir);}
        else {dir = lTurn.get(dir);}
        move(dir);
    }
    public void move(char dir)
    {
        int xMod = dirControl.get(dir).get(0);
        int yMod = dirControl.get(dir).get(1);
        int nextX = ant[0] + xMod;
        int nextY = ant[1] + yMod;
        grid[ant[0]][ant[1]].exitAnt();
        grid[ant[0]][ant[1]].switchState();
        ant[0] = nextX;
        ant[1] = nextY;
        grid[nextX][nextY].enterAnt();
    }
}
