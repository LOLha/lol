package Main;

import ui.JFrameGame;
import ui.JPanelGame;
import Control.GameControl;
import Control.PlayerControl;
import Service.GameService;
import dto.GameDto;

public class Main {
 public static void main(String[] args){
	 GameDto dto = new GameDto();
	 GameService gameService = new GameService(dto);
	 JPanelGame jpanel = new JPanelGame(dto);
	 GameControl gameControl = new GameControl(jpanel,gameService);
	 PlayerControl playerControl = new PlayerControl(gameControl);
	 jpanel.setControl(playerControl);
	 gameService.setJpanel(jpanel);
	  new JFrameGame(jpanel);

 }
}
