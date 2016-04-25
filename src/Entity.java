import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * Created by Austin on 4/14/2016.
 */
// MAKE ABSTRACT CLASS
abstract class Entity implements Move {

    private int xLoc, yLoc;  //x is row, y is column for gridpane
    private String name;
    //private char symbol;
    private Image icon;

    private int team;
    private Entity prevEnt;
    private double attackPower;
    //private int attackDir;
    private double health, healthRegen;
    private double emotion;

    Entity() {
        name = "";
        //icon = new Image("imgs/tiger.jpg");

    }

    Entity(String n, Image img) {
        name = n;
        icon = img;

    }

    Entity(String n, Image img, int x, int y, double aP, double h, double hR, double e, int t) {
        name = n;
        icon = img;
        xLoc = x;
        yLoc = y;
        attackPower = aP;
        health = h;
        healthRegen = hR;
        emotion = e;
        team = t;
    }



    public void move(int dir) {

        switch (dir) {
            case Dir.STAY:
                break;
            case Dir.LEFT:
                xLoc--;
                break;
            case Dir.UP_LEFT:
                yLoc--;
                xLoc--;
                break;
            case Dir.UP:
                yLoc--;
                break;
            case Dir.UP_RIGHT:
                yLoc--;
                xLoc++;
                break;
            case Dir.RIGHT:
                xLoc++;
                break;
            case Dir.DOWN_RIGHT:
                yLoc++;
                xLoc++;
                break;
            case Dir.DOWN:
                yLoc++;
                break;
            case Dir.DOWN_LEFT:
                yLoc++;
                xLoc--;
                break;
            default:
                xLoc--;

        }


    }

    void addHealth(double val){
        health += val;
    }

    void subHealth(double val){
        health -= val;
    }

    void setName(String n) {
        name = n;
    }

    void setPrevEnt(Entity e) {
        prevEnt = e;
    }

    void setxLoc(int x) {
        xLoc = x;
    }

    void setyLoc(int y) {
        yLoc = y;
    }

    void setIcon(Image i) {
        icon = i;
    }

    void setTeam(int t) {
        team = t;
    }

    String getName() {
        return name;
    }

    Entity getPrevEnt() {
        return prevEnt;
    }

    int getX() {
        return xLoc;
    }

    int getY() {
        return yLoc;
    }

    double getHealth() { return health; }

    int getTeam() {
        return team;
    }

    Image getIcon() {
        return icon;
    }

    int getDistanceFrom(int x, int y) {
        double distance = Math.hypot(xLoc-x, yLoc-y);
        return (int)distance;
    }

    static class Dir {
        final static int UP = 90;
        final static int RIGHT = 180;
        final static int LEFT = 0;
        final static int DOWN = 270;
        final static int UP_RIGHT = 135;
        final static int UP_LEFT = 45;
        final static int DOWN_RIGHT = 225;
        final static int DOWN_LEFT = 315;
        final static int STAY = -1;
    }

    @Override
    public String toString() {
        return name;
    }
}


