import javafx.scene.image.Image;
import sun.corba.EncapsInputStreamFactory;

import java.util.ArrayList;

/**
 * Created by Austin on 4/17/2016.
 */
public class Beiber extends Entity implements DrinksOJ{

    private int ojLeft;

    Beiber() {
        super("Beiber", new Image("imgs/bieberConfused.gif"));
        ojLeft = 3;
    }

    Beiber(int x, int y, double aP) {
        super("Beiber", new Image("imgs/bieberConfused.gif"), x, y, aP, .5, .1, 1, 0);
        ojLeft = 3;
    }

    void microphoneAttack(ArrayList<Entity> enemies){
        int distance;
        for(Entity clown : enemies){
            distance = clown.getDistanceFrom(getX(), getY());
            if(distance < 3){
                switch(distance){
                    case 1:
                        System.out.println("oh shit get me outta here");
                        clown.subHealth(0.5);
                        //attack 50
                        break;
                    case 2:
                        System.out.println("the clown is too close");
                        clown.subHealth(0.1);
                        //attack 10
                        break;
                    default:
                        //he's here!!!
                }
            }else{
                //missed
            }
        }

    }

    void callPosse(ArrayList<Entity> squad){
        int[][] locs = {{getX(), getY()-1},{getX()-1, getY()},{getX()+1, getY()}};
        int locIndex = 0;

        for(Entity dude : squad){
            dude.setxLoc(locs[locIndex][0]);
            dude.setyLoc(locs[locIndex][1]);
            locIndex++;
        }
    }

    @Override
    public void drinkOJ() {
        boolean flag;
        if(ojLeft>0) {
            System.out.println("yum");
            addHealth(getHealth() * 0.2);
            ojLeft--;
        }else{
            System.out.println("no oj left");
        }
    }

    @Override
    public int getOJLeft() {
        return ojLeft;
    }
}
