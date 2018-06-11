

import java.awt.*;
import java.awt.event.*;

class AltMaze extends Frame {

	private  static Color lava;
	private static Color walk;
	private static int speed;
	
	//make sure baseline<50;
	private final int BASELINE=70;
	private boolean changed;
	private boolean isAlive;
	private int levels;
	
	public AltMaze(Color a,Color b, int level) 
	{

		lava=a;
		walk=b;
		levels=level;
		speed = BASELINE-10*level;
	}

    public void run(Color a,Color b,int i)
    {
    	
        Frame window = new AltMaze(a,b,i);
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        window.setSize(500, 500);
        window.setTitle("Maze Level "+levels);
        
        MazeCanvas maze = new MazeCanvas(lava,walk,speed);
        window.add(maze);
        window.show();
        maze.move();
        
        maze.run();
        
       // isAlive(maze);
        
    }}
	
	
	
	
	
