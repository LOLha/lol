package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class LayerPlayer1 extends Layer{
	 private static Image image_rect = new ImageIcon("image/game/rect.png").getImage();
	    private static Image image_shadow = new ImageIcon("image/game/shodow.png").getImage();
		public LayerPlayer1(int x, int y, int w, int h) {
			super(x, y, w, h);
		}
		public void paint(Graphics g){
			super.createFrame(g);
			Point[] actPoints = this.dto.getCube().getActPoint();
			this.drawShadow(true,actPoints,g);
			int typeCode = this.dto.getCube().getTypeCode();
			for(int i= 0;i<actPoints.length;i++){
				Point p = actPoints[i];
				int xPoint = p.x;
				int yPoint = p.y;
				g.drawImage(image_rect,this.x+(xPoint<<ROW_SIZE)+SIZE,
						this.y+(yPoint<<ROW_SIZE)+SIZE,
						this.x+(xPoint<<ROW_SIZE)+(1<<ROW_SIZE)+SIZE,
						this.y +(yPoint<<ROW_SIZE)+(1<<ROW_SIZE)+SIZE, 
						(typeCode<<ROW_SIZE)+(1<<ROW_SIZE),
						0,(typeCode << ROW_SIZE) + (2<< ROW_SIZE), 
						1 << ROW_SIZE, null);
			}
	        boolean[][] map =this.dto.getGameMap();
			for(int x=0;x<map.length;x++)
				for(int y=0;y<map[x].length;y++){
					if(map[x][y]){
						g.drawImage(image_rect,this.x+(x<<ROW_SIZE)+SIZE,
								this.y+(y<<ROW_SIZE)+SIZE,
								this.x+(x<<ROW_SIZE)+(1<<ROW_SIZE)+SIZE,
								this.y +(y<<ROW_SIZE)+(1<<ROW_SIZE)+SIZE, 
								0,0,1<< ROW_SIZE, 
								1 << ROW_SIZE, null);
					}
					
					
				}
			
			
		}
		private void drawShadow(boolean b, Point[] actPoints,Graphics g) {
		  if(b){
			  int leftX = 9;
			  int rightX = 0;
			  for(Point p:actPoints){
				  leftX = p.x <leftX ?p.x :leftX;
				  rightX = p.x> rightX?p.x:rightX;
			  }
			  g.drawImage(image_shadow,this.x + SIZE+(leftX<<ROW_SIZE),
					  this.y+SIZE,
					  (rightX-leftX+1)<<ROW_SIZE, 
					  this.h-(SIZE<<1), null);
		  }
			
		}
}
