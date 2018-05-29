/*
   Creates a random maze, then solves it by finding a path from
   the upper left corner to the lower right corner.
        
   BY:  David Eck
        Department of Mathematics and Computer Science
        Hobart and William Smith Colleges
        Geneva, NY   14456
     
        E-mail:  eck@hws.edu     

   NOTE:  YOU CAN DO ANYTHING YOU WANT WITH THIS CODE AND APPLET,
          EXCEPT CLAIM CREDIT FOR THEM (such as by trying to
          copyright the code yourself).
 
   UPDATE HISTORY:
      1998.11.23
      snilsson@nada.kht.se
      Changed this program into an application, adapted it to
      JDK 1.1, and removed some code.

      2000.01.21
      snilsson@nada.kth.se
      Fixed null pointer exception in putSquare.
*/        

import java.awt.*;
import java.awt.event.*;

class AltMaze extends Frame {
    public static void main(String[] args) {
        Frame window = new AltMaze();

        // The program can be terminated by closing the frame.
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        window.setSize(500, 500);
        window.setTitle("Maze");
        MazeCanvas maze = new MazeCanvas();
        window.add(maze);
        window.show();
        maze.run();
    }    
}
