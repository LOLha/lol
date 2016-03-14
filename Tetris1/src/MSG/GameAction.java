package MSG;

import java.io.DataInputStream;

import ui.NetClient;
import dto.GameDto;

public class GameAction {
    
	private GameDto dto;
	
	private NetClient nc;
	
	private Msg msg = null;
	
	private DataInputStream dis;
	public GameAction(GameDto dto,NetClient nc){
		this.dto = dto;
		this.nc = nc;
		
	}
	public void setDis(DataInputStream dis) {
		this.dis = dis;
	}
	
	public void gameNew(){
    	msg = new GameNewMsg(dto,nc);
	 	msg.parse(dis);
	}
	public void gameStart(){
		msg = new GameStartMsg(dto);
  		msg.parse(dis);
	}
	public void gameInit(){
	    msg = new GameInitMsg(dto);
		msg.parse(dis);
	}
	public void gameLeft(){
		 msg = new GameLeftMsg(dto);
 		msg.parse(dis);
	}
	public void gameRight(){
		msg = new GameRightMsg(dto);
		msg.parse(dis);
	}
	public void gameRound(){
		msg = new GameRoundMsg(dto);
		msg.parse(dis);
	}
	public void gameMap(){
		msg = new GameMapMsg(dto);
		msg.parse(dis);
	}
	public void gamePause(){
		msg = new GamePauseMsg(dto);
		msg.parse(dis);
	}
	
	
}
