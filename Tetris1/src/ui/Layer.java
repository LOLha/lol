package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import config.ConfigFactory;
import config.GameConfig;
import dto.GameDto;

public abstract class Layer {
	private static GameConfig gameConfig = ConfigFactory.getGameConfig();
	
	 /**
	  * 边框图片
	  */
      protected static Image image = new ImageIcon("image/window/Window.png").getImage();
      protected static int imgW = image.getWidth(null);
      protected static int imgH = image.getHeight(null);
      protected static int PADDING = gameConfig.getPADDING();
      
      protected static Image image_num = new ImageIcon("image/string/num.png").getImage();
      protected static final int NUM_W = 26;
      protected static final int NUM_H = 36;
      
      protected static int SIZE = gameConfig.getSIZE();
      protected static int ROW_SIZE = gameConfig.getROW_SIZE();
	  protected int x;
	  protected int y;
	  protected int w;
	  protected int h;
	  public GameDto getDto() {
		return dto;
	}
	public void setDto(GameDto dto) {
		this.dto = dto;
	}
	/**
	   * 数据源
	   */
      protected GameDto dto;
      public Layer(int x,int y,int w,int h){
    	  this.x = x;
    	  this.y = y;
    	  this.w = w;
    	  this.h = h;
      }
      abstract protected void paint(Graphics g);
      public void createFrame(Graphics g){
    	g.drawImage(image,x,y,x+SIZE,y+SIZE,0,0,SIZE,SIZE ,null);
  	    g.drawImage(image,x+SIZE,y,x-SIZE+w,y+SIZE,SIZE,0, imgW-SIZE, SIZE, null);
  	    g.drawImage(image, x-SIZE+w, y, x+w, y+SIZE,imgW-SIZE, 0, imgW, SIZE, null);
  	    g.drawImage(image, x, y+SIZE, x+SIZE,y-SIZE+h, 0, SIZE, SIZE, imgH-SIZE, null);
  	    g.drawImage(image, x+SIZE, y+SIZE,x+w-SIZE, y+h-SIZE,SIZE,SIZE,imgW-SIZE, imgH-SIZE,null);
        g.drawImage(image, x+w-SIZE, y+SIZE, x+w, y+h-SIZE, imgW-SIZE, SIZE, imgW, imgH-SIZE,null);
        g.drawImage(image, x, y+h-SIZE, x+SIZE, y+h, 0, imgH-SIZE,SIZE,imgH,null);
  	    g.drawImage(image,x+SIZE,y+h-SIZE,x+w-SIZE,y+h,SIZE,imgH-SIZE,imgW-SIZE,imgH,null);
  	    g.drawImage(image, x+w-SIZE, y+h-SIZE,x+w,y+h,imgW-SIZE, imgH-SIZE,imgW,imgH,null);
      }
      protected void drawNumLeftPad(int x,int y,int num,int maxBit,Graphics g){
  		//把数字取出
  		String strNum = String.valueOf(num);
  		//循环绘制数字右对齐
  		for(int i=0;i<maxBit;i++){
  			//判断是否满足绘制条件
  			if(maxBit - i <=strNum.length()){
  				int idx = i -maxBit + strNum.length();
  				int bit = strNum.charAt(idx) - '0';
  				g.drawImage(image_num, this.x + x+NUM_W *i, this.y +y, 
  						this.x + x + NUM_W*(i+1) ,
  						this.y + y +NUM_H,
  						NUM_W*bit, 0, (bit+1)*NUM_W, NUM_H, null);	
  			}	
  		}
  	}
	
}
