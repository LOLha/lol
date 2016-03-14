package MSG;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import ui.NetClient;
import dto.GameDto;
import entity.Cube;

public class GameNewMsg implements Msg{
  private GameDto dto;
  private NetClient nc;
  public GameNewMsg(GameDto dto){
	  this.dto  =dto;
  }
  public GameNewMsg(GameDto dto,NetClient nc){
	  this.nc = nc;
	  this.dto = dto;
  }
  public void send(DatagramSocket ds, String IP, int udpPort){
	  ByteArrayOutputStream bos = new ByteArrayOutputStream();
      DataOutputStream dos = new DataOutputStream(bos);
      try{
    	   dos.writeInt(Msg.GAME_NEW);
           dos.writeInt(this.dto.getId());
           dos.writeInt(this.dto.getCube().getTypeCode());
          
      
      }catch(Exception e){
      	e.printStackTrace();
      }
      byte[] buf = bos.toByteArray();
      try {
			DatagramPacket dp = new DatagramPacket(buf,buf.length,new InetSocketAddress(IP,udpPort));
			ds.send(dp);
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
  }
public void parse(DataInputStream dis){
	try{
	  int id = dis.readInt();
	 if(id == this.dto.getId()||this.dto.isPlayerOnline())return;	 
	int actType = dis.readInt();	
	this.dto.setCube1(new Cube(actType));
	GameNewMsg msg = new GameNewMsg(this.dto);
	
	this.nc.send(msg);
	this.dto.setPlayerOnline(true);
	}catch(IOException e){
		e.printStackTrace();
	}	
}
}
