package main;

import enemy.Zombie ;

import java.io.IOException;

public class EnemySet{

    GamePanel gp ;

    public EnemySet(GamePanel gp){
        this.gp = gp;
    }

    public void setEnemy() throws IOException {

        gp.enemy[0] = new Zombie(gp);
        gp.enemy[0].worldx = gp.tileSize*4;
        gp.enemy[0].worldy = gp.tileSize*4;

        gp.enemy[1] = new Zombie(gp);
        gp.enemy[1].worldx = gp.tileSize*20;
        gp.enemy[1].worldy = gp.tileSize*46;

        gp.enemy[2] = new Zombie(gp);
        gp.enemy[2].worldx = gp.tileSize*8;
        gp.enemy[2].worldy = gp.tileSize*10;

        gp.enemy[3] = new Zombie(gp);
        gp.enemy[3].worldx = gp.tileSize*4;
        gp.enemy[3].worldy = gp.tileSize*20;

        gp.enemy[4] = new Zombie(gp);
        gp.enemy[4].worldx = gp.tileSize*30;
        gp.enemy[4].worldy = gp.tileSize*10;





    }
}