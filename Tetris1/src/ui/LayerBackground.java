package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerBackground extends Layer{
  private static Image image_background = new ImageIcon("image/background/light.jpg").getImage();
	public LayerBackground(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	public void paint(Graphics g){
    g.drawImage(image_background, 0, 0, 1170,756,null);//TODO
	}
	
	

}
