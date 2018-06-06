import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Robber 
{
	public static void main(String args[]) {
		  JFrame frame = new JFrame();
		  ImageIcon icon = new ImageIcon("bank.png");
		  JLabel label = new JLabel(icon);
		  frame.add(label);
		  frame.setDefaultCloseOperation
		         (JFrame.EXIT_ON_CLOSE);
		  frame.pack();
		  frame.setVisible(true);}

}
