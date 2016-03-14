package Server;

public class Client {
        private String ip;
        private int udpPort;
        public Client(String ip,int udpPort){
        	this.ip = ip;
        	this.udpPort = udpPort;
        }
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public int getUdpPort() {
			return udpPort;
		}
		public void setUdpPort(int udpPort) {
			this.udpPort = udpPort;
		}
        
	
	
}
