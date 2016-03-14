package ui;


import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerPoint extends Layer {
	private static final int POINT_BIT = 5;
	/*
	 * 窗口标题(分数)
	 */
	private static final Image IMG_POINT = new ImageIcon(
			"image/string/point.png").getImage();
	/*
	 * 窗口标题(消行)
	 */
	private static final Image IMG_REMOVE = new ImageIcon(
			"image/string/rmline.png").getImage();
	/*
	 * 距型槽
	 */
	private static final Image IMG_RECT = new ImageIcon("image/window/rect.png").getImage();
    
	private static final int RECT_H = IMG_RECT.getHeight(null);
	private static int REMOVE_LINE_H;
    
	private static int RECT_W = IMG_RECT.getWidth(null);
	private static int POINT_Y = PADDING;

	private static int comX;

	private static int expY;
	
	private static int expW;
    
	private static final int level_up = 20;
	public LayerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		this.comX = this.w - 26 * POINT_BIT - PADDING;
		this.POINT_Y = PADDING;
		this.REMOVE_LINE_H = IMG_POINT.getHeight(null) + PADDING ;
		this.expY = this.REMOVE_LINE_H +IMG_REMOVE.getHeight(null)+PADDING;
	}

	public void paint(Graphics g) {
		super.createFrame(g);
		int score = this.dto.getNowSocre();
		g.drawImage(IMG_POINT, this.x + PADDING, this.y + POINT_Y, null);
		this.drawNumLeftPad(comX, POINT_Y,score, POINT_BIT, g);
		
	}
	
}
