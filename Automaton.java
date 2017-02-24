import javafx.application.Application; //Application controls the lifecycle of your application
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext; //The magic pen and paintbrush
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.text.TextAlignment;
import java.util.Arrays;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.application.Platform;
import java.util.Random;
public class Automaton extends Application
{
    private BorderPane bp;
    private Stage primary;
    private GridPane gp;
    private AutoCell[][] grid;
    private AnimationTimer at;
    public Automaton()
    {
        bp = new BorderPane();
        gp = new GridPane();
        grid = new AutoCell[60][60];
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
        at = new AnimationTimer(){
            @Override
            public void handle(long now)
            {
                try
                {
                    Thread.sleep(75);
                }
                catch (Exception ex) {}
                update(grid);
            }
        };
    }
    @Override
    public void start(Stage primary)
    {
        primary.setTitle("Conway's Game of Life");
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
        return (b.getX() == 0 || b.getX() == grid.length-1 || b.getY() == 0 || b.getY() == grid.length-1);
    }
    public void update(AutoCell[][] grid)
    {
        int l = grid.length;
        boolean[][] states = new boolean[l][l];
        for (int i = 0; i< l; i++)
        {
            for (int j = 0; j < l; j++)
            {
                AutoCell curr = grid[i][j];
                if (isEdge(curr, grid))
                {
                    states[i][j] = false;
                }
                else
                {
                    AutoCell[] neighbors = getNeighbors(curr,grid);
                    int count = 0;
                    for (AutoCell cell : neighbors)
                    {
                        if (cell.getState())
                        {
                            count += 1;
                        }
                    }
                    if (!curr.getState())
                    {
                        if (count == 3)
                        {
                            states[i][j] = true;
                        }
                        else{ states[i][j] = false;}
                    }
                    else
                    {
                        if (count < 2 || count > 3)
                        {
                            states[i][j] = false;
                        }
                        else{ states[i][j] = true;}
                    }
                }
            }
        }
        for (int i = 0; i<l; i++)
        {
            for (int j = 0; j < l; j++)
            {
                grid[i][j].setState(states[i][j]);
            }
        }
    }
    public AutoCell[] getNeighbors(AutoCell curr, AutoCell[][] grid)
    {
        AutoCell[] neighbors = new AutoCell[8];
        int currentX = curr.getX();
        int currentY = curr.getY();
        int[] transX = new int[] {-1,0,1,-1,1,-1,0,1};
        int[] transY = new int[] {-1,-1,-1,0,0,1,1,1};
        for (int i = 0; i < 8; i++)
        {
            neighbors[i] = grid[currentX +transX[i]][currentY+transY[i]];
        }
        return neighbors;
    }
}
