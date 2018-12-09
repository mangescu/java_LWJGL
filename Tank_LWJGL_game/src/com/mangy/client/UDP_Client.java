

package com.mangy.client;


import com.mangy.bullet.Bullet;
import com.mangy.game.GamePlayer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.mangy.game.Main;
import com.mangy.player.Player;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.examples.Game;




public class UDP_Client  extends Thread {
	
	private InetAddress     ipAddress;
	private DatagramSocket  socket;
	
	public ArrayList<PlayersData>  listaPlayers  =  new  ArrayList<PlayersData>();
	public ArrayList<BulletData>   listaBullets  =  new  ArrayList<BulletData>();
        
	Main main;
        public String localName ;
	
	public  UDP_Client(Main main , String ipAddress , String localName)  {
		this.main = main;
		try {
				this.socket = new DatagramSocket();
				this.ipAddress = InetAddress.getByName(ipAddress);
                                this.localName  =  localName;
				
		} catch(SocketException | UnknownHostException Ex) {
			Ex.printStackTrace();
		}
	}
	
	public void run() {		
		while(true) {
			
                        String buffer ="                                                                                                                                   ";
			byte[] data = buffer.getBytes();
			DatagramPacket packet = new DatagramPacket(data , data.length);
			
			try {
				socket.receive(packet);
					
			    } catch (IOException e) 
			             {
			    			e.printStackTrace();
			             }

			String message = new String(packet.getData()).trim();
			
			if(message.startsWith("PLAYERS_IN")) {
			    String[] string = message.split("/");    
			    listaPlayers.clear();
                            for (int i = 1; i < string.length; i = i + 5) {
                                if (!string[i].equals(localName)) {
                                    listaPlayers.add(new PlayersData(string[i], Float.valueOf(string[i + 1]), Float.valueOf(string[i + 2]), Float.valueOf(string[i + 3]), Float.valueOf(string[i + 4])));
                                }
                            }
			}
                        if(message.startsWith("DISCONECT")) {
                              for(Player player : main.gamePlayers.getListaPlayer()) {
                                   Main.gamePlayers.getListaPlayer().remove(player);
                              }                      
                        }
                        if(message.startsWith("MOVE")) {
                          String[] string = message.split("/");
                          for(Player  player :  main.gamePlayers.getListaPlayer()) {
                              if(player.name.equals(string[1])) {
                                  player.X = Float.valueOf(string[2]);
                                  player.Y = Float.valueOf(string[3]);
                                  player.Z = Float.valueOf(string[4]);
                                  player.angle = Float.valueOf(string[5]);
                              }
                          }
                        }
                        if(message.startsWith("FIRE")) {
                          String[] string = message.split("/");    
                          listaBullets.add(new BulletData(string[1], Float.valueOf(string[2]), Float.valueOf(string[3]) , Float.valueOf(string[4]), Float.valueOf(string[5]), string[6]));
                          Main.addBuller = true;
                        }
                        if(message.startsWith("DESABLE_ANIMATION")) {
                           String[] string = message.split("/"); 
                           for(Player player : main.gamePlayers.getListaPlayer()) {
                               if(player.name.equals(string[1])) {
                                   player.dinamicRender = false;
                               }
                           }
                        }
                        if(message.startsWith("ENABLE_ANIMATION")) {
                           String[] string = message.split("/"); 
                           for(Player player : main.gamePlayers.getListaPlayer()) {
                               if(player.name.equals(string[1])) {
                                   player.dinamicRender = true;
                               }
                           }
                        }                        
		}
	}
	
	public void sendData(byte[] data) {
		
		DatagramPacket packet = new DatagramPacket(data, data.length , ipAddress , 1331);
		try {
			 socket.send(packet);
			 
		    } catch (IOException e)
		          {
			      	e.printStackTrace();
		          }
	}
	public void printPlayerList() {

                System.out.println("________________CLIENT PLAYER LIST_______________________________________________ ");
		for (PlayersData player : listaPlayers) {
			System.out.println("NUME: " + player.nume+"  X = "+player.X+"  Y ="+player.Y+"  Z = "+player.Z+"  angle = "+player.angle);
		}

	}

}

