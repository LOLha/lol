package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerStart extends Layer{

	private static Image image_start = new ImageIcon("image/string/start.png").getImage();
	
	private static int start_w = image_start.getWidth(null);
	private static int start_h = image_start.getHeight(null);
	
	public LayerStart(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	
	protected void paint(Graphics g) {
	  super.createFrame(g);
	  g.drawImage(image_start, this.x+((this.w - start_w)>>1), this.y+((this.h - start_h)>>1), null);
	}
 
	
	
}
