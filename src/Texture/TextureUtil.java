
package Texture;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class TextureUtil {
   
    
    public Textura ringTexture , mapTex  , solarSystemTex_max , curs , shipTex_max ,shipTex_med , shipTex_min ,  bulletTex , explosionTex , shieldTex,phaserTex , textChart , board , phasetEmiter , galaxyTex;
    
    public void loadTextures () {
    
        try {
            ringTexture =new Textura(new FileInputStream(new File("res/ring_texture.png")),6 , 1);
            ringTexture.glInit();
            textChart =new Textura(new FileInputStream(new File("res/textChart.png")),10, 1);
            textChart.glInit();
            mapTex =new Textura(new FileInputStream(new File("res/map_512.png")),1, 1);
            mapTex.glInit();
            galaxyTex =new Textura(new FileInputStream(new File("res/galaxy.png")),1, 1);
            galaxyTex.glInit();
            solarSystemTex_max =new Textura(new FileInputStream(new File("res/solar_system_max.png")),10, 1f);
            solarSystemTex_max.glInit();
            shipTex_max =new Textura(new FileInputStream(new File("res/ship_max_F.png")),10, 1);
            shipTex_max.glInit();
            board =new Textura(new FileInputStream(new File("res/board.png")),5, 1);
            board.glInit();
            bulletTex =new Textura(new FileInputStream(new File("res/bullet_animated.png")),5, 1);
            bulletTex.glInit();
            explosionTex =new Textura(new FileInputStream(new File("res/explsion_animation.png")),10, 1);
            explosionTex.glInit();
            shieldTex =new Textura(new FileInputStream(new File("res/shield.png")),10, 1);
            shieldTex.glInit();
            phaserTex =new Textura(new FileInputStream(new File("res/phaser.png")),10, 1);
            phaserTex.glInit();
            phasetEmiter =new Textura(new FileInputStream(new File("res/emiter.png")),10, 1);
            phasetEmiter.glInit();

        } catch (FileNotFoundException ex) {
                  System.out.println("texture error");
        }
    

    }
    
    
    
    
}
