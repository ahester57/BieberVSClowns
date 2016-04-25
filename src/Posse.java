import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Austin on 4/19/2016.
 */
public class Posse extends Entity{

    Posse(int x, int y, double aP, double h, double hR, double e) {
        super("", new Image("imgs/yachtyPosse.png"), x, y, aP, 1, hR, .8, 0);
        setName(makeName());
    }

    void microphoneAttack(ArrayList<Entity> enemies){
        int distance;
        for(Entity clown : enemies){
            distance = clown.getDistanceFrom(getX(), getY());
            if(distance < 3){
                switch(distance){
                    case 1:
                        System.out.println(this.getName() + "  says, \"" + " fuck that clown here.\"");
                        clown.subHealth(0.5);
                        //attack 50
                        break;
                    case 2:
                        System.out.println(this.getName() + "  says, \"" + " fuck that clown over there.\n");
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

    private String makeName(){
        Random rand = new Random();
        String[] names = {"Yachty", "Boat", "Rd", "Darnell", "Uzi", "Donnie"};
        return names[(int)(rand.nextDouble() * names.length)];
    }
}
