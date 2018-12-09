
package com.mangy.game;

import com.mangy.bullet.Bullet;
import com.mangy.cube.Cube;
import com.mangy.entity.Entity;
import com.mangy.player.Player;
import java.util.ArrayList;


public class GamePlayer {

	ArrayList<Player>  listaPlayer   =  new  ArrayList<Player>();
	ArrayList<Bullet>  listaBullets  =  new  ArrayList<Bullet>();        
        ArrayList<Cube>    listaCubes    =  new  ArrayList<Cube>();        
        ArrayList<Entity>  listaEntity   =  new  ArrayList<Entity>();        
        
    public GamePlayer() {} 

    public ArrayList<Player> getListaPlayer() {
       synchronized(this)
       {
           return listaPlayer;
       }
    }
    public ArrayList<Bullet> getListaBullets() {
       synchronized(this)
       {
           return listaBullets;
       }
    }

    public ArrayList<Cube> getListaCubes() {
      synchronized(this)
      {
        return listaCubes;
      }
    }

    public ArrayList<Entity> getListaEntity() {
      synchronized(this)
      {
        return listaEntity;
      }
    }

}
