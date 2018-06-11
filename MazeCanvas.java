import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import javafx.scene.shape.Circle;

import java.awt.*;


class MazeCanvas extends Canvas implements ActionListener, KeyListener {
    // Description of state of maze. The value of maze[i][j] is
    // one of the constants WALL, PATH, EMPTY, or VISITED. (Value
    // can also be negative, temporarily, inside createMaze().)
    //    A maze is made up of walls and corridors. maze[i][j] is
    // either part of a wall or part of  a corridor.  A cell that
    // is part of a corridor is represented by PATH if it is part
    // of the current path through the maze, by VISITED if it has
    // already been explored without finding a solution, and by
    // EMPTY if it has not yet been explored.
    int[][] maze;
    Rectangle[][] rect;
    private int a =9;;;;;;;;;
    final static int BACKGROUND = 0;
    final static int WALL = 1;
    final static int PATH = 2;
    final static int EMPTY = 3;
    final static int VISITED = 4;
    
   // final static Circle cir;

    // Colors associated with the preceding 5 constants
    Color[] color = {Color.magenta,
                     Color.black,Color.black,
                    // new Color(0, 0, 0),    // red
                     Color.green, // blue
                     Color.black};
    
     
    boolean done;
    // Number of rows and columns of cells in maze, including
    // a wall around edges. Should be odd numbers.
    int rows = 33;
    int columns = 33;

    // Short delay between steps in making and solving maze
    int speedSleep = 5;

    // Graphics context for canvas, created in putSquare()
    Graphics me = null;
    
    // The following fields are set by checkSize()
    int width = -1;   // width of canvas
    int height = -1;  // height of canvas
    int cellWidth;    // width of cell
    int cellHeight;   // height of cell
    int left;         // left edge of maze, allowing for border
    int top;          // top edge of maze, allowing for border
    int speed;
    
    public MazeCanvas(Color a,Color b, int speeds)
    {
    	
    	super();
    	color[0]=a;color[1]=b;color[2]=b;color[3]=a;color[4]=b;
    	setBackground(color[BACKGROUND]);
    	maze= new int[rows][columns];
    	speed=speeds;
    	
    }
    public MazeCanvas() {
        super();
        setBackground(color[BACKGROUND]);
        maze = new int[rows][columns];
       // addCircle();
        /*
        final Circle circle = new Circle(200, 150, 50, Color.magenta);
        cir= new Circle(1,1,cellWidth/2,Color.magenta);*/
       // rect= new Rectangle[rows][columns];
        
		 	/*.setDefaultCloseOperation
	         (JFrame.EXIT_ON_CLOSE);*/
    }
    public boolean getDone()
    	{
    		return done;
    	}
    // Called every time something is about to be drawn to check
    // the canvas size and adjust variables that depend on the size.
    // Returns true if the size has changed.
    boolean checkSize() {
       if (getSize().width != width || getSize().height != height) {
          width  = getSize().width;
          height = getSize().height;
          cellWidth = width / columns;
          cellHeight = height / rows;
          left = (width - cellWidth*columns) / 2;
          top = (height - cellHeight*rows) / 2;
          return true;
       }
       return false;
    }

    // Draws the entire maze.
    public void paint(Graphics g) {
        checkSize();
        
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) {
                if (maze[i][j] < 0)
                    g.setColor(color[EMPTY]);
                else
                    g.setColor(color[maze[i][j]]);
                g.fillRect(j*cellWidth + left, i*cellHeight + top,
                           cellWidth, cellHeight);
            }
       // g.fillOval(5,6,1,1);
    }
    
    // Draw one cell of the maze, to the graphics context "me".
    synchronized void putSquare(int row, int col, int colorNum) {
        if (checkSize() || me == null) {
          if (me != null)
              me.dispose(); // Get rid of old graphics context
          me = getGraphics();
        }
        me.setColor(color[colorNum]);
        me.fillRect(col*cellWidth + left, row*cellHeight + top,
                    cellWidth, cellHeight);
        try { Thread.sleep(speedSleep); }
        catch (InterruptedException e) { }
    }
    
    synchronized void putSquare(int row, int col, Color c) {
        if (checkSize() || me == null) {
          if (me != null)
              me.dispose(); // Get rid of old graphics context
          me = getGraphics();
        }
        me.setColor(c);
        me.fillRect(col*cellWidth + left, row*cellHeight + top,
                    cellWidth, cellHeight);
        try { Thread.sleep(speedSleep); }
        catch (InterruptedException e) { }
    }
    
    synchronized void removeSquare(int row, int col, int colornum) {
        if (checkSize() || me == null) {
          if (me != null)
              me.dispose(); // Get rid of old graphics context
          me = getGraphics();
        }
        me.setColor(color[4]);
        me.fillRect(col*cellWidth + left, row*cellHeight + top,
                    cellWidth, cellHeight);
        try { Thread.sleep(speedSleep); }
        catch (InterruptedException e) { }
    }
 
    // Make a maze and then solve it.
    public void run() {
       makeMaze();
     //  addBox(1,1,Color.magenta);
       speedSleep=speed;
       
       solveMaze(1,1);
       for(int f =0;f<=5;f++)
       {}
       //loseGraphics(Color.black,Color.white);}
    }
    


    // Create a random maze. The strategy is to start with a
    // agrid of disconnnected "rooms" separated by walls and
    // then look at each of the separating walls, in a random
    // order. If tearing down a wall would not create a loop in
    // the maze, then tear it down. Otherwise, leave it in place.
    void makeMaze() {
        int emptyCt = 0; // number of rooms
        int wallCt = 0;  // number of walls

        // Position of walls between rooms
        int walls = (rows/2 - 1)*columns + rows*(columns/2 - 1);
        int[] wallrow = new int[walls];
        int[] wallcol = new int[walls];

        // Start with everything being a wall.
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                maze[i][j] = WALL;

        // Make a grid of empty rooms.
        for (int i = 1; i < rows - 1; i += 2)
            for (int j = 1; j < columns - 1; j += 2) {
                emptyCt++;
                // Each room is represented by a
                // different negative number
                maze[i][j] = -emptyCt;
                // Record info about wall below this room.
                if (i < rows - 2) {
                    wallrow[wallCt] = i + 1;
                    wallcol[wallCt] = j;
                    wallCt++;
                }
                // Record info about wall to right of this room.
                if (j < columns - 2) {
                    wallrow[wallCt] = i;
                    wallcol[wallCt] = j + 1;
                    wallCt++;
                }
             }
        repaint();
        for (int i = wallCt - 2; i > 0; i--) {
            // Choose a wall randomly and maybe tear it down.
            // Never choose the first or last wall. In this way the
            // maze will start and end at the end of a corridor.
            int r = 1 + (int) (Math.random() * (i + 1));
            tearDown(wallrow[r], wallcol[r]);
            wallrow[r] = wallrow[i];
            wallcol[r] = wallcol[i];
        }
        // Replace negative values in maze[][] with EMPTY.
        for (int i = 1; i < rows - 1; i++)
           for (int j = 1; j < columns - 1; j++)
              if (maze[i][j] < 0)
                  maze[i][j] = EMPTY;
    }


    void tearDown(int row, int col) {
        if (row%2 == 1 && maze[row][col-1] != maze[row][col+1]) {
   
            fill(row, col-1, maze[row][col-1], maze[row][col+1]);
            maze[row][col] = maze[row][col+1];
            putSquare(row, col, EMPTY);
        } else if (row%2 == 0 && maze[row-1][col] != maze[row+1][col]) {

            fill(row-1, col, maze[row-1][col], maze[row+1][col]);
            maze[row][col] = maze[row+1][col];
            putSquare(row, col, EMPTY);
        }
    }

 
    void fill(int row, int col, int replace, int replaceWith) {
        if (maze[row][col] == replace) {
            maze[row][col] = replaceWith;
            fill(row+1, col, replace, replaceWith);
            fill(row-1, col, replace, replaceWith);
            fill(row, col+1, replace, replaceWith);
            fill(row, col-1, replace, replaceWith);
        }
    }



    
    boolean solveMaze(int row, int col) {
    	if(maze[row][col]==PATH);
    		putSquare(row,col,PATH);
        if (maze[row][col] == EMPTY) { 
        
            maze[row][col] = PATH;
            putSquare(row, col, PATH);
            

           
            if (row == rows-2 && col == columns-2)
            {	done=true;
                return true; }

            if (solveMaze(row-1, col) ||
                solveMaze(row, col-1) ||
                solveMaze(row+1, col) ||
                solveMaze(row, col+1))
                return true;

            // Maze can't be solved from this cell,
            // so backtract out of the cell.
            maze[row][col] = VISITED;
            putSquare(row, col, VISITED);
        }
        return false;
    }
    
    
    boolean solveMaze(int row, int col,int dir) {//dir1=north,dir2=east,3=south,4=west
    	
        if (maze[row][col] == EMPTY) { 
   
            maze[row][col] = PATH;
            putSquare(row, col, PATH);
            
            	
            

            
            if (row == rows-2 && col == columns-2)
                return true;

            if (solveMaze(row-1, col) ||
                solveMaze(row, col-1) ||
                solveMaze(row+1, col) ||
                solveMaze(row, col+1))
                return true;

           
            maze[row][col] = VISITED;
            putSquare(row, col, VISITED);
        }
        return false;
    }
    
    public void move()
    {
    	
    }
    
    public void loseGraphics(Color c,Color d) 
    {
    	speedSleep=0;
    	for(int f =0;f<=10;f++)
    	{
	    	for(int i =0;i<rows;i++)
	    	{
	    		for(int j =0;j<columns;j++)
	    		{
	    			if(maze[i][j]==PATH||maze[i][j]==VISITED||maze[i][j]==EMPTY)
	    			{
	    				putSquare(i,j,c);
	    			}
	    			else
	    			{
	    				putSquare(i,j,d);
	    			}
	    			
	    		}
	    	}
			try        
			{
			    Thread.sleep(1000);
			} 
			catch(InterruptedException ex) 
			{
			    Thread.currentThread().interrupt();
			}
	    	for(int i =0;i<rows;i++)
	    	{
	    		for(int j =0;j<columns;j++)
	    		{
	    			if(maze[i][j]==PATH||maze[i][j]==VISITED||maze[i][j]==EMPTY)
	    			{
	    				putSquare(i,j,d);
	    			}
	    			else
	    			{
	    				putSquare(i,j,c);
	    			}
	    			
	    		}
	    	}
			try        
			{
			    Thread.sleep(1000);
			} 
			catch(InterruptedException ex) 
			{
			    Thread.currentThread().interrupt();
			}
			
    	}
    		
    	
    }
    
    
	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
    	if(c==KeyEvent.VK_LEFT)
    		a=1;
    	else if(c==KeyEvent.VK_RIGHT)
    		a=2;
    	else if(c==KeyEvent.VK_UP)
    		a=3;
    	else
    		a=4;
    	System.out.print(a);
		// TODO Auto-generated method stub
		
	}
	
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
    
    
}
