

package com.mangy.server;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class UDP_Server {

	public static void main(String[] args) {
		UDP server = new UDP();
		    server.start();
	}
}



 class UDP extends Thread {

	private DatagramSocket socket;
	ArrayList<ClientInformation> listaPlayer = new ArrayList<ClientInformation>();

	public UDP() {
		try {
                        System.out.println("SERVER START");
			this.socket = new DatagramSocket(1331);

		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
                    
                        String buffer ="                                                                                                                                   ";
			byte[] data = buffer.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length);

			try {
                                socket.receive(packet);

			} catch (IOException e) {
				e.printStackTrace();
			}

			String message = new String(packet.getData()).trim();
			if (message.startsWith("CONNECT")) {                             
				String[] string = message.split("/");
				listaPlayer.add(new ClientInformation(string[1].trim(), packet.getAddress(), packet.getPort()));
                                sendOnlainPlayersToAll(packet);
				printPlayerList();
			}
                        if (message.startsWith("DISCONECT")) {
                             broadcastMessage(message, packet);
                        }
                        if(message.startsWith("MOVE")) {
                            broadcastMessage(message, packet);
                        }
                        if(message.startsWith("FIRE")) {
                            broadcastMessage(message, packet);
                        }
                        if(message.startsWith("DESABLE_ANIMATION")) {
                           broadcastMessage(message, packet);
                        }
                        if(message.startsWith("ENABLE_ANIMATION")) {
                           broadcastMessage(message, packet);
                        }                        
		}
	}
	public void sendOnlainPlayersToAll(DatagramPacket packet) {
		 String players = getPlayersOnlain();
		 broadcastMessage(players , packet);  
	}
    public String getPlayersOnlain() {
    	       String  onlainPlayers = ""; 
    	               onlainPlayers += "PLAYERS_IN";
    	           	for (ClientInformation info : listaPlayer) {
    	           	   onlainPlayers +="/";
    	           	   onlainPlayers +=info.name;
    	           	   onlainPlayers +="/";
    	           	   onlainPlayers +="0";
    	           	   onlainPlayers +="/";
    	           	   onlainPlayers +="0";
    	           	   onlainPlayers +="/";
    	           	   onlainPlayers +="0";
    	           	   onlainPlayers +="/";
    	           	   onlainPlayers +="0";    	           	   
    	           	}
    	 return  onlainPlayers;
    }
	public void broadcastMessage(String message, DatagramPacket packet) {
		for (ClientInformation info : listaPlayer) {
                    sendData(message.getBytes(), info.inetAddress, info.port);
		}
	}

	public void sendData(byte[] data, InetAddress ipAddress, int port) {

		DatagramPacket packet = new DatagramPacket(data, data.length,ipAddress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printPlayerList() {
		System.out.println("________________PLAYER LIST_______________________________________________ "+listaPlayer.size());
		for (ClientInformation info : listaPlayer) {
			System.out.println("NUME: " + info.name+"    IP: "+info.inetAddress.getHostAddress()+"    PORT: "+info.port);               
                }
          System.out.println("\n");
	}

}

class ClientInformation {
    
	String name;
	InetAddress inetAddress;
	int port;

	public ClientInformation(String name, InetAddress inetAddress, int port) {
		this.name = name;
		this.inetAddress = inetAddress;
		this.port = port;
	}
}
