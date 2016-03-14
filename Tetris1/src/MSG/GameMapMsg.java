package MSG;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import dto.GameDto;

public class GameMapMsg implements Msg{
   private GameDto dto;
	public GameMapMsg(GameDto dto){
		this.dto = dto;
	}
	
	public void send(DatagramSocket ds, String ip, int udpPort) {
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
	     DataOutputStream dos = new DataOutputStream(baos);
	     try{
	   	  dos.writeInt(Msg.GAME_MAP);
	   	  dos.writeInt(this.dto.getId());
	   	  boolean[][] map = this.dto.getGameMap();
	   	  for(int i=0;i<10;i++){
	   		  for(int j=0;j<18;j++){
	   			  dos.writeBoolean(map[i][j]);
	   		  }
	   	  }
	   	  
	   	  byte[] buf = baos.toByteArray();
	   	  DatagramPacket dp = new DatagramPacket(buf,buf.length,new InetSocketAddress(ip,udpPort));
	   	  ds.send(dp);
	     }catch(IOException e){
	   	  e.printStackTrace();}
	}

	@Override
	public void parse(DataInputStream dis) {
		try{
			int id = dis.readInt();
			if(id == this.dto.getId())return;
            boolean[][] map = new boolean[10][18];
            for(int i=0;i<10;i++){
            	for(int j=0;j<18;j++){
            		map[i][j] = dis.readBoolean();
            	}
            }
			this.dto.setGameMap1(map);
		}catch(IOException e){
			e.printStackTrace();}	
	}
	

}
