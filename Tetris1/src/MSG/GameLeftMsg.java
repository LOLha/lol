package MSG;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import dto.GameDto;

public class GameLeftMsg implements Msg{
 private GameDto dto;
	 public GameLeftMsg(GameDto dto){
	this.dto = dto;
}
	public void send(DatagramSocket ds, String ip, int udpPort) {
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          DataOutputStream dos = new DataOutputStream(baos);
          try{
        	  dos.writeInt(Msg.GAME_LEFT);
        	  dos.writeInt(this.dto.getId());
        	  byte[] buf = baos.toByteArray();
        	  DatagramPacket dp = new DatagramPacket(buf,buf.length,new InetSocketAddress(ip,udpPort));
        	  ds.send(dp);
          }catch(IOException e){
        	  e.printStackTrace();}
          }
			
	public void parse(DataInputStream dis) {
		try{
			int id = dis.readInt();
			if(id == this.dto.getId())return;
			this.dto.getCube1().move(-1, 0, this.dto.getGameMap1());
			
			
		}catch(IOException e){
			e.printStackTrace();}
		}
		
		
	}
	

