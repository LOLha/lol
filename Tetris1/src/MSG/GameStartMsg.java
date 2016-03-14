package MSG;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import ui.NetClient;
import dto.GameDto;

public class GameStartMsg implements Msg{
   private GameDto dto;
   private NetClient nc;
   public GameStartMsg(GameDto dto){
	   this.dto = dto;
   }
   public GameStartMsg(GameDto dto,NetClient nc){
	   this.dto = dto;
	   this.nc = nc;
   }
   
	public void send(DatagramSocket ds, String ip, int udpPort) {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         DataOutputStream dos = new DataOutputStream(baos);
         try{
        	 dos.writeInt(Msg.GAME_START);
        	 dos.writeInt(this.dto.getId());
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
			System.out.println("接收到开始包"+id);
			this.dto.setStart(true);
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
	}
	

}
