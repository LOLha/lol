package MSG;

import java.io.DataInputStream;
import java.net.DatagramSocket;

public interface Msg {
	int GAME_NEW = 0;
	int GAME_MOVE = 1;
	int GAME_START = 2;
	int GAME_INIT = 3;
	int GAME_LEFT = 4;
	int GAME_RIGHT = 5;
	int GAME_ROUND = 6;
	int GAME_DOWN = 7;
	int GAME_MAP = 8;
	int GAME_PAUSE = 9;
	public void send(DatagramSocket ds,String ip,int udpPort);
	public void parse(DataInputStream dis);

}
