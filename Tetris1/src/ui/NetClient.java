package ui;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import MSG.GameAction;
import MSG.GameNewMsg;
import MSG.Msg;
import Server.GameServer;
import dto.GameDto;

public class NetClient {
	
   private GameDto dto;
	private int udpPort;
	private DatagramSocket ds;
	GameNewMsg msg;
	public NetClient(GameDto dto,int udpPort){
		this.dto  = dto;
		this.udpPort = udpPort;
		try{
			ds=  new DatagramSocket(udpPort);
		}catch(IOException e){
			e.printStackTrace();
		}
		 msg = new GameNewMsg(this.dto,this);
		send(msg);
		new Thread(new listenThread()).start();
	}
	public void send(Msg msg){
		msg.send(ds, "127.0.0.1", GameServer.UDP_PORT);
	}
	public void sendStartMsg(Msg msg){
		msg.send(ds, "127.0.0.1", GameServer.UDP_PORT+1);
	}
	
	private class listenThread implements Runnable{
		GameAction gameAction = new GameAction(NetClient.this.dto,NetClient.this);
		byte[] buf = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buf,buf.length);
		private Map<Integer,String> gameActionMap;
		public listenThread(){
			gameActionMap = new ConcurrentHashMap<Integer,String>();
			gameActionMap.put(Msg.GAME_DOWN, "gameDown");
			gameActionMap.put(Msg.GAME_NEW, "gameNew");
			gameActionMap.put(Msg.GAME_START, "gameStart");
			gameActionMap.put(Msg.GAME_INIT, "gameInit");
			gameActionMap.put(Msg.GAME_LEFT, "gameLeft");
			gameActionMap.put(Msg.GAME_ROUND, "gameRound");
			gameActionMap.put(Msg.GAME_PAUSE, "gamePause");
			gameActionMap.put(Msg.GAME_MAP,"gameMap");
			gameActionMap.put(Msg.GAME_RIGHT, "gameRight");
		}
		
		public void run(){
			while(true){
				try {
					ds.receive(dp);
					parse(dp);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		private void parse(DatagramPacket dp2) {
            ByteArrayInputStream bais = new ByteArrayInputStream(buf,0,dp2.getLength());
            DataInputStream dis = new DataInputStream(bais);
            try{
            	Msg msg = null;
            	int type = dis.readInt();
            	gameAction.setDis(dis);
            	Class c = gameAction.getClass();
            	String methodName = gameActionMap.get(type);
            	Method method = c.getMethod(methodName);
            	method.invoke(gameAction);
            	
            }catch(Exception e){
            	e.printStackTrace();
            }
			
		}
	}



}
			
		

	

