package Service;

import java.awt.Point;
import java.util.Random;

import ui.JPanelGame;
import MSG.GameInitMsg;
import MSG.GameLeftMsg;
import MSG.GameMapMsg;
import MSG.GamePauseMsg;
import MSG.GameRightMsg;
import MSG.GameRoundMsg;
import MSG.GameStartMsg;
import dto.GameDto;
import entity.Cube;

public class GameService {
	private GameDto gameDto;
	private JPanelGame jpanel;
	private Random ran = new Random();
  public GameService(GameDto dto){
	  this.gameDto = dto;  
	  Cube cube = new Cube(ran.nextInt(7));
	  this.gameDto.setCube(cube);
	 
  }
  public void keyUp(){
	  this.gameDto.getCube().round(this.gameDto.getGameMap());
	  GameRoundMsg msg = new GameRoundMsg(this.gameDto);
	  jpanel.getNc().send(msg);
  }
  public void keyLeft(){
	  this.gameDto.getCube().move(-1, 0, this.gameDto.getGameMap());
	  GameLeftMsg msg = new GameLeftMsg(this.gameDto);
	  jpanel.getNc().send(msg);
  }
  public void keyRight(){
	  this.gameDto.getCube().move(1, 0, this.gameDto.getGameMap());
	  GameRightMsg msg = new GameRightMsg(this.gameDto);
	  jpanel.getNc().send(msg);
  }
  public void keyDown(){
	  this.gameDto.getCube1().move(0, 1, this.gameDto.getGameMap1());
	  if(this.gameDto.getCube().move(0,1,this.gameDto.getGameMap())){
		  
		  
		  return;}
	
 	 boolean[][] map = this.gameDto.getGameMap();    
 	
 	 
 	 Point[] points = this.gameDto.getCube().getActPoint();
 	 for(int i=0;i<points.length;i++){
 		 if(map[points[i].x][points[i].y]) {
 			 this.gameDto.setStart(false);
 			GamePauseMsg msg = new GamePauseMsg(this.gameDto);
 			jpanel.getNc().send(msg);
 			 return;
 		 } 
 		 map[points[i].x][points[i].y] = true;
 	 }
 	 this.gameDto.getCube().isRemove(map,points,this.gameDto);
 	  GameMapMsg msg = new GameMapMsg(this.gameDto);
	  jpanel.getNc().send(msg);
 	 this.gameDto.getCube().init(this.gameDto.getNext());
 	 /**map = this.gameDto.getGameMap();    	 
 	  points = this.gameDto.getCube().getActPoint();
 	  if(map[points[3].x][points[3].y]){
 	  }**/
 	 this.gameDto.setNext(ran.nextInt(7));
 	 GameInitMsg msg1 = new GameInitMsg(this.gameDto);
	 jpanel.getNc().send(msg1);
  }
  
  
  private boolean checkGameOver(boolean[][] map) {
	  int count = 0;
	for(int i=0;i<map.length;i++){
		count = 0;
		for(int j=0;j<map[i].length;j++){
			if(map[i][j]){
				count++;
			}
			if(count==18) return true;
		}
		
	}
	return false;
	
}
public void setJpanel(JPanelGame jpanel){
	  this.jpanel = jpanel;
  }
  public void sendStartMsg() {
	  GameStartMsg msg = new GameStartMsg(this.gameDto);
	  jpanel.getNc().sendStartMsg(msg);
}
  public void gamePause(){
	  this.gameDto.setStart(!this.gameDto.isStart());
	  GamePauseMsg msg = new GamePauseMsg(this.gameDto);
	  jpanel.getNc().send(msg);
	  
  }
  
  
}
