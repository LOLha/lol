package entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ui.JPanelGame;
import MSG.GameMapMsg;
import config.ConfigFactory;
import config.GameConfig;
import config.SystemConfig;
import dto.GameDto;

public class Cube {
	/**
	 * 获得游戏配置
	 */
	 private static GameConfig gameConfig = ConfigFactory.getGameConfig();
	 /**
	  * 获得系统配置
	  */
	 private static SystemConfig systemConfig = gameConfig.getSystemConfig();
	 private Point[] actPoint;
	  private static final int X_MIN = systemConfig.getxMin(); ;
	  private static final int X_MAX = systemConfig.getxMax();
	  private static final int Y_MIN = systemConfig.getyMin();
	  private static final int Y_MAX = systemConfig.getyMax();
	  
	  private static List<Point[]> TYPE_CONFIG;
	  static{
		  TYPE_CONFIG = systemConfig.getTypeConfig();
	  }
	  private int typeCode;
	  
	 
	public Cube(int actCode){
		 
		  this.init(actCode);
	  }
	
	
	  public void init(int actCode){
		  this.typeCode = actCode;
		  Point[] p = TYPE_CONFIG.get(actCode);
		  actPoint = new Point[p.length];
		  for(int i =0 ;i< p.length;i++){
			  actPoint[i] = new Point(p[i].x,p[i].y);
		  }
		  
	  }

	public void setActPoint(Point[] actPoint) {
		this.actPoint = actPoint;
	}

	public Point[] getActPoint() {
		return actPoint;
	}
	/**
	 * 方块移动
	 */
	  public boolean move(int moveX,int moveY,boolean[][] map){
		  
		  for(int i=0;i<actPoint.length;i++){
			  Point p = actPoint[i];
			  int newX = p.x + moveX;
			  int newY = p.y + moveY;
			  if(isOver(newX,newY,map)){
				  return false;
			  }
		  }
		  for(int i=0;i<actPoint.length;i++){
			  Point p = actPoint[i];
			  p.x += moveX;
			  p.y += moveY;
		  }
		  return true;
	  }
 
 
	  /**
	   * 旋转方法
	   */
	  
	  public void round(boolean[][] map){
		  if(this.typeCode == 4){return;}
		  for(int i=1;i<actPoint.length;i++){
			  int newX = actPoint[0].y + actPoint[0].x - actPoint[i].y;
			  int newY = actPoint[0].y - actPoint[0].x + actPoint[i].x;
			  if(isOver(newX,newY, map)){
			   return;
			  }
		  }
		  for(int i=1;i<actPoint.length;i++){
			  int newX = actPoint[0].y + actPoint[0].x - actPoint[i].y;
			  int newY = actPoint[0].y - actPoint[0].x + actPoint[i].x;
			  actPoint[i].x = newX;
			  actPoint[i].y = newY;
		  }
		 
	  }
	  /**
	   * 是否越界或方块重叠
	   * @param newX
	   * @param newY
	   * @param map
	   * @return
	   */
	  private boolean isOver(int newX,int newY,boolean[][] map){
		  return newX< X_MIN||newX>X_MAX||newY<Y_MIN||newY>Y_MAX||map[newX][newY];
	  }
	 /**
	  * 判定是否可以消行
	  * @param map
	  * @param points
	  */
	  public void isRemove(boolean[][] map,Point[] points,GameDto dto){
	      int count =0;
	      List<Integer> list = new ArrayList<Integer>(points.length);
	      for(int i=0;i<points.length;i++){
	    	  list.add(points[i].y);
	      }
	      Collections.sort(list);
		  for(int i=0;i<list.size();i++){
	    	  count =0;
	    	  for(int j=0;j<10;j++){
	    	    	 if(map[j][list.get(i)])  count++; 
	    	     }
	    	  if(count==10) {
	    	    	removeCube(list.get(i),map);
	    	    	copyCube(list.get(i),map);
	    	    	dto.setRemoveLine(dto.getRemoveLine()+1);
	    	    	dto.setNowSocre(dto.getNowSocre()+10);
	    	    	if(dto.getRemoveLine()%1==0){
	    	    		dto.setLevel(dto.getLevel()+1);
	    	    	}
	    	    }
	      }  
		  
	  }
	  
	  
	  
	  public int getTypeCode() {
			return typeCode;
		}
	 public void setTypeCode(int typeCode) {
			this.typeCode = typeCode;
		}
	 /**
	  * 将一整行消除
	  * @param PointY
	  * @param map
	  */
	 private void removeCube(int PointY,boolean[][] map){
		 for(int i=0;i<10;i++){
				map[i][PointY] = false;	
			}
	 }
	 /**
	  * 将消除的行复制为上面行数的状态
	  * @param PointY
	  * @param map
	  */
	 private void copyCube(int PointY,boolean[][] map){
		 for(int j=PointY;j>0;j--)	
		 		for(int i=0;i<10;i++){
		 			map[i][j] = map[i][j-1];
		 		}
		 for(int i=0;i<10;i++){
		 		map[i][0]=false;
		 	}
	 }

}
