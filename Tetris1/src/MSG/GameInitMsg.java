package MSG;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import dto.GameDto;
import entity.Cube;

public class GameInitMsg implements Msg{
	
	private GameDto dto;
	public GameInitMsg(GameDto dto){
		this.dto = dto;
	}
	@Override
	public void send(DatagramSocket ds, String ip, int udpPort) {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         DataOutputStream dos = new DataOutputStream(baos);
         try{
        	 dos.writeInt(Msg.GAME_INIT);
        	 dos.writeInt(this.dto.getId());
        	 dos.writeInt(this.dto.getCube().getTypeCode());
        	
             byte[] buf = baos.toByteArray();
             DatagramPacket dp = new DatagramPacket(buf,buf.length,new InetSocketAddress(ip,udpPort));
             ds.send(dp);
             
         }catch(IOException e){
           e.printStackTrace();
         }
         }
		
		
	
	@Override
	public void parse(DataInputStream dis) {
              try{
            	  int id = dis.readInt();
            	  if( id == this.dto.getId()) return;
            	  int type = dis.readInt();
            	 
            	  this.dto.setCube1(new Cube(type));
            	
              }catch(IOException e){
            	  e.printStackTrace();
              }
		
		
		
	}

}
