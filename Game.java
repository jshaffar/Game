import java.awt.Color;
import javax.swing.JOptionPane;


public class Game
{
	private static int level=1;
	//private Color c;
	private static boolean alive = true;
	//private static boolean beatTheGame=false;
	private static Color[][] c = {{null,null},{Color.white,Color.blue},{Color.green,new Color(22,145,217)},{Color.pink,Color.yellow},{Color.red,Color.white},{Color.blue,Color.orange},{Color.black,Color.white}};
	
	
	public static void main(String[] args)
	{
		//while
		while(alive==true&&level<c.length)
		{
		 int a = JOptionPane.showConfirmDialog(null, "Are you ready for level "+level,null, 0);
			if(a==1)
			{break;}

			AltMaze am = new AltMaze(c[level][0],c[level][1],level);
			//CountDownClockDisplay cdcd=new CountDownClockDisplay(3);
			am.run(c[level][0],c[level][1],level);
			JOptionPane.showInputDialog("what's one+1");
			level++;			
				
		}
	}
}
