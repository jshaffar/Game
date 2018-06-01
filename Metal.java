//Jeremy Shaffar 
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Metal
{
	private int value;
	private ImageIcon look = null;
	
	public Metal()
	{
		value=setValue();
		setImage();
	}
	public int setValue()
	{
		Random gen =new Random();
		int n = gen.nextInt(100)+1;
		return n;
	}
	public int getValue()
	{
		return value;
	}
	public ImageIcon setImage()
	{
		
	}
	public ImageIcon getImage()
	{
		return look;
	}
}
