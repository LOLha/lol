package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerNext extends Layer{
	private static Image[] NEXT_ACT;
    static{
	   NEXT_ACT = new Image[7];
	   for(int i=0;i<NEXT_ACT.length;i++){
		   NEXT_ACT[i] = new ImageIcon("image/game/"+i+".png").getImage();
	   }
    }
	public LayerNext(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	public void paint(Graphics g){
		super.createFrame(g);
		int codeType = this.dto.getNext();
		g.drawImage(NEXT_ACT[codeType],this.x +(1<<ROW_SIZE),this.y+(1<<ROW_SIZE),null);
		
	}
}
