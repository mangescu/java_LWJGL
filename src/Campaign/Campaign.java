
package Campaign;

import Player.Player;
import Util.RandomFloat;
import java.util.ArrayList;


public class Campaign {
  
    int max_ship_size;
    Player player;
    Color color = new Color();
    RandomFloat rand = new RandomFloat();


    public void setupMultiplayer(ArrayList<Player>   playerList , int playerNumber, int minRadius , int maxRadius , int race_index)
    {

        if(playerNumber !=0) {
             max_ship_size = (900 / playerNumber);
        }
        int minDefenceColonizeCount = 100;
        int maximumColonizePlanet   = 100;
            
        
        for(int i =0 ; i < playerNumber ; i++) {
            //________PLAYER_0______________________________//    
                    player  =  new Player();


                    player.auto_colonizePlanet = true;
                    player.auto_addShip = true;
                    player.auto_deploySquad = true;
                    
                    if(i==race_index) {
                        player.auto_deploySquad = false;
                       // player.auto_colonizePlanet = false;

                    }

              player.start_position_x = rand.genConstrainedIntNumber(minRadius, maxRadius);
              player.start_position_y = rand.genConstrainedIntNumber(minRadius, maxRadius);




                    if(i == race_index) {
                        //  player.start_position_x = -2;
                        //  player.start_position_y = -2;
                    }
                    if(i == 1) {
                       //  player.start_position_x = 0;
                       //  player.start_position_y = 1;
                    }


                    player.ring_index =color.gameRingIndex.get(i);
                    player.selectyRing = color.gameShipSelectRing.get(i);
                    player.cercle_index = color.gameCercleIndex.get(i);
                    player.index = i;
                    player.maxShipCount = max_ship_size;


                    player.faction = i+1;
                    if(i == race_index) {
                        player.renderPlayerElements = true;
                    }
                  
                    player.ship_tex_index = color.gameshipIndex.get(i);
                    player.boarder_tex_index = color.gameBordetIndex.get(i);
                    player.segmented_boarder_tex_index = color.gamesegmentedBordetIndex.get(i);
                    player.bullet_tex_index_type_1 = color.gameBulletIndex_1.get(i);
                    player.bullet_tex_index_type_2 = color.gameBulletIndex_2.get(i);
                    player.explosion_tex_index = color.gameExplosionIndex.get(i);
                    player.shield_tex_index = color.gameShieldIndex.get(i);
                    player.phaser_tex_index = color.gamePhaserIndex.get(i);
                    player.phaserEmiter_tex_index = color.gameEmiterIndex.get(i);
                    
                    player.minDefenceColonizeCount = minDefenceColonizeCount;       
                    player.maximumColonizePlanet = maximumColonizePlanet;

                    playerList.add(player);
            //_____________________________________________//          
        
        } 
 
    }
    
}
class Color {

ArrayList<Integer>   gameshipIndex      =  new ArrayList<Integer>();
ArrayList<Integer>   gameBulletIndex_1  =  new ArrayList<Integer>();
ArrayList<Integer>   gameBulletIndex_2  =  new ArrayList<Integer>();
ArrayList<Integer>   gameRingIndex      =  new ArrayList<Integer>();
ArrayList<Integer>   gameCercleIndex    =  new ArrayList<Integer>();
ArrayList<Integer>   gamePhaserIndex    =  new ArrayList<Integer>();
ArrayList<Integer>   gameShieldIndex    =  new ArrayList<Integer>();
ArrayList<Integer>   gameBordetIndex    =  new ArrayList<Integer>();
ArrayList<Integer>   gamesegmentedBordetIndex    =  new ArrayList<Integer>();
ArrayList<Integer>   gameExplosionIndex =  new ArrayList<Integer>();
ArrayList<Integer>   gameEmiterIndex =  new ArrayList<Integer>();
ArrayList<Integer>   gameShipSelectRing =  new ArrayList<Integer>();

    public int  shipTRows      = 10;
    public int  explosionTRows = 10;
    public int  phaserTRows    = 10;
    public int  shieldTRows    = 10;
    public int  emiterTRows    = 10;

    public Color () {

    //__PLAYER_0__________________________________//    
        gameshipIndex.add(shipTRows * 1);
        gameBordetIndex.add(1);
        gamesegmentedBordetIndex.add(11);
        gameBulletIndex_1.add(1);
        gameBulletIndex_2.add(6);
        gameExplosionIndex.add(explosionTRows * 0);
        gameEmiterIndex.add(emiterTRows * 1);
        gameShieldIndex.add(shieldTRows * 1);
        gamePhaserIndex.add(phaserTRows * 1);
        gameRingIndex.add(0);
        gameShipSelectRing.add(9);
        gameCercleIndex.add(12);
     //__________________________________________//

        //__PLAYER_1__________________________________//
        gameshipIndex.add(shipTRows * 0);
        gameBordetIndex.add(0);
        gamesegmentedBordetIndex.add(10);
        gameBulletIndex_1.add(0);
        gameBulletIndex_2.add(5);
        gameExplosionIndex.add(explosionTRows * 1);
        gameEmiterIndex.add(emiterTRows * 0);
        gameShieldIndex.add(shieldTRows * 0);
        gamePhaserIndex.add(phaserTRows * 0);
        gameRingIndex.add(1);
        gameShipSelectRing.add(10);
        gameCercleIndex.add(13);
     //__________________________________________//     

    //__PLAYER_2__________________________________//    
        gameshipIndex.add(shipTRows * 2);
        gameBordetIndex.add(2);
        gamesegmentedBordetIndex.add(12);
        gameBulletIndex_1.add(2);
        gameBulletIndex_2.add(7);
        gameExplosionIndex.add(explosionTRows * 2);
        gameEmiterIndex.add(emiterTRows * 2);
        gameShieldIndex.add(shieldTRows * 2);
        gamePhaserIndex.add(phaserTRows * 2);
        gameRingIndex.add(2);
        gameShipSelectRing.add(11);
        gameCercleIndex.add(14);
     //__________________________________________// 

    //__PLAYER_3__________________________________//    
        gameshipIndex.add(shipTRows * 3);
        gameBordetIndex.add(3);
        gamesegmentedBordetIndex.add(13);
        gameBulletIndex_1.add(3);
        gameBulletIndex_2.add(8);
        gameExplosionIndex.add(explosionTRows * 3);
        gameEmiterIndex.add(emiterTRows * 3);
        gameShieldIndex.add(shieldTRows * 3);
        gamePhaserIndex.add(phaserTRows * 3);
        gameRingIndex.add(3);
        gameShipSelectRing.add(12);
        gameCercleIndex.add(15);
     //__________________________________________//   

    //__PLAYER_4__________________________________//    
        gameshipIndex.add(shipTRows * 4);
        gameBordetIndex.add(4);
        gamesegmentedBordetIndex.add(14);
        gameBulletIndex_1.add(4);
        gameBulletIndex_2.add(10);
        gameExplosionIndex.add(explosionTRows * 4);
        gameEmiterIndex.add(emiterTRows * 4);
        gameShieldIndex.add(shieldTRows * 4);
        gamePhaserIndex.add(phaserTRows * 4);
        gameRingIndex.add(4);
        gameShipSelectRing.add(13);
        gameCercleIndex.add(16);
     //__________________________________________//

    //__PLAYER_5__________________________________//    
        gameshipIndex.add(shipTRows * 5);
        gameBordetIndex.add(5);
        gamesegmentedBordetIndex.add(15);
        gameBulletIndex_1.add(9);
        gameBulletIndex_2.add(11);
        gameExplosionIndex.add(explosionTRows * 5);
        gameEmiterIndex.add(emiterTRows * 5);
        gameShieldIndex.add(shieldTRows * 5);
        gamePhaserIndex.add(phaserTRows * 5);
        gameRingIndex.add(5);
        gameShipSelectRing.add(14);
        gameCercleIndex.add(17);
     //__________________________________________//  

    //__PLAYER_6__________________________________//    
        gameshipIndex.add(shipTRows * 6);
        gameBordetIndex.add(6);
        gamesegmentedBordetIndex.add(16);
        gameBulletIndex_1.add(14);
        gameBulletIndex_2.add(12);
        gameExplosionIndex.add(explosionTRows * 6);
        gameEmiterIndex.add(emiterTRows * 6);
        gameShieldIndex.add(shieldTRows * 6);
        gamePhaserIndex.add(phaserTRows * 6);
        gameRingIndex.add(6);
        gameShipSelectRing.add(15);
        gameCercleIndex.add(18);
     //__________________________________________//      

    //__PLAYER_7___________________________________//    
        gameshipIndex.add(shipTRows * 7);
        gameBordetIndex.add(7);
        gamesegmentedBordetIndex.add(17);
        gameBulletIndex_1.add(19);
        gameBulletIndex_2.add(13);
        gameExplosionIndex.add(explosionTRows * 7);
        gameEmiterIndex.add(emiterTRows * 7);
        gameShieldIndex.add(shieldTRows * 7);
        gamePhaserIndex.add(phaserTRows * 7);
        gameRingIndex.add(7);
        gameShipSelectRing.add(16);
        gameCercleIndex.add(19);
     //__________________________________________//      

    //__PLAYER_8___________________________________//    
        gameshipIndex.add(shipTRows * 8);
        gameBordetIndex.add(8);
        gamesegmentedBordetIndex.add(18);
        gameBulletIndex_1.add(24);
        gameBulletIndex_2.add(15);
        gameExplosionIndex.add(explosionTRows * 8);
        gameEmiterIndex.add(emiterTRows * 8);
        gameShieldIndex.add(shieldTRows * 8);
        gamePhaserIndex.add(phaserTRows * 8);
        gameRingIndex.add(8);
        gameShipSelectRing.add(17);
        gameCercleIndex.add(20);
     //__________________________________________//      
     
    }




}