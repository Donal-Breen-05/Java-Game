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


    }
}