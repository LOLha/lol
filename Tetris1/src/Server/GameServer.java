package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {
	public static final int TCP_PORT = 8080;
	public static final int UDP_PORT = 5000;
	public ServerSocket ss =null;
	public List<Client> client = new ArrayList<Client>();
	public static int id =100;
	public GameServer(){
		try {
			ExecutorService executor = Executors.newFixedThreadPool(2);
			executor.execute(new UDPThread());
			executor.execute(new UDPStartThread());
			ss = new ServerSocket(TCP_PORT);
			System.out.println("端口开启");
			while(true){
				Socket socket = ss.accept();
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				dos.writeInt(id++);
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				int udpPort = dis.readInt();
				System.out.println("玩家连接了");
				String ip = socket.getInetAddress().getHostAddress();
				client.add(new Client(ip,udpPort));
				
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args){
		new GameServer();
	}
	private class UDPThread implements Runnable{
		private DatagramSocket ds;		
		public void run(){
			byte[] buf = new byte[1024];
			try {
				ds = new DatagramSocket(UDP_PORT);
			} catch (SocketException e) {
				e.printStackTrace();
			}
			while(true){
				DatagramPacket dp = new DatagramPacket(buf,buf.length);
				try{
					ds.receive(dp);
					
					for(int i=0;i<client.size();i++){
						String ip = client.get(i).getIp();
						int udpPort = client.get(i).getUdpPort();
						dp.setSocketAddress(new InetSocketAddress(ip,udpPort));
						ds.send(dp);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
			}
			
			
			
		}
		
			
		}
	
    private class UDPStartThread implements Runnable{
    	private int number = 0;
    	private DatagramSocket ds = null;
    	public void run(){
    		byte[] buf = new byte[1024];
    		try{
    			ds = new DatagramSocket(UDP_PORT+1);
    		while(ds != null){
    			DatagramPacket dp = new DatagramPacket(buf,buf.length);
    			ds.receive(dp);
    			number++;
    			System.out.println("number"+number);
    			if(number == 2){
    				for(int i=0;i<client.size();i++){
						String ip = client.get(i).getIp();
						int udpPort = client.get(i).getUdpPort();
						dp.setSocketAddress(new InetSocketAddress(ip,udpPort));
						ds.send(dp);
					}
    				
    			}
    		}
    		
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	}
    	
    	
    }
	
	
	}


