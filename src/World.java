
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Austin on 4/14//2016.
 *
 * This is the world. The world contains a grid
 * containing entities. These entities move around.
 *
 * Each team gets a turn to move, and the world attempts
 * to choose an entity that is able to attack. If it
 * cannot find an attacker, then an entity is chosen at
 * random.
 *
 */
class World {

    private int generations;
    private int rows, cols;


    private Random rand = new Random();

    private ArrayList<ArrayList<Entity>> grid;

    private Entity beiber;
    private ArrayList<ArrayList<Entity>> teams;
    final static int BEIBER_TEAM = 0;
    final static int CLOWN_TEAM = 1;

    private static int cycles = 0;

    World(){

        rows = 40;
        cols = 40;


        generations = 1000;


        makeGrid();
    }

    World(int gen, int r, int c){
        generations = gen;
        cols = c;
        rows = r;


        makeGrid();
    }

    //draws original GUI
    void draw(){


    }

    //Updates labels
    private void update(){

    }

    void run(int direction, String action){

        ArrayList<Entity> squad = new ArrayList<>();
        for(Entity e : teams.get(BEIBER_TEAM)){
            if(!(e instanceof Beiber)){
                squad.add(e);
            }
        }

        //Beiber
        if(action.equals("Microphone Attack")){
            ((Beiber)(beiber)).microphoneAttack(teams.get(CLOWN_TEAM));
            for(Entity e : squad){

                ((Posse)(e)).microphoneAttack(teams.get(CLOWN_TEAM));
            }
        }else if(action.equals("Call Posse")){
            ((Beiber)(beiber)).callPosse(squad);
        }else if(action.equals("Drink OJ")){
            ((Beiber)(beiber)).drinkOJ();

        }
        move(beiber, direction);

        //Beiber squad
        for(Entity e : squad){

            move(e, direction);
        }



        checkDead();

    }


    //put back in run, find better way to sleep
    void clownRun(){
        //Clown squad, clownKills sets how many clowns need
        //to be  cloned.
        int clownKills = 0;
        for(Entity e : teams.get(CLOWN_TEAM)){
            if(clownMove(e, getClownDirection(e))){
                clownKills++;
            }
        }


        int loc[];
        for(int i = 0; i < clownKills; i++){
            loc = GameController.getRandomLoc();
            addPlayer(new Clown(loc[0], loc[1], 10, 1, .02, 0), CLOWN_TEAM);
        }

        checkDead();
        cycles++;

    }


    private void move(Entity e, int direction){
        int oldX = e.getX(), oldY = e.getY();
        Entity oldPrev = e.getPrevEnt();

        e.move(direction);

        if(((e.getX() < 0 || e.getX() > 39) || (e.getY() < 0 || e.getY() > 39)) || !(getEntity(e.getX(), e.getY()) instanceof OccupiableSpace)){
            put(e, oldX, oldY);
            return;
        }

        insert(e, e.getX(), e.getY());
        insert(oldPrev, oldX, oldY);
    }

    //returns whether or not they killed a posse member
    private boolean clownMove(Entity e, int direction){
        int oldX = e.getX(), oldY = e.getY();
        Entity oldPrev = e.getPrevEnt();

        boolean flag = false;

        e.move(direction);

        if(((e.getX() < 0 || e.getX() > 39) || (e.getY() < 0 || e.getY() > 39))){
            put(e, oldX, oldY);
            return flag;
        }

        if(!(getEntity(e.getX(), e.getY()) instanceof OccupiableSpace)){
            if(getEntity(e.getX(), e.getY()) instanceof Posse){
                teams.get(BEIBER_TEAM).remove(getEntity(e.getX(), e.getY()));
                flag = true;
            }else{
                put(e, oldX, oldY);
                return flag;
            }
        }

        insert(e, e.getX(), e.getY());
        insert(oldPrev, oldX, oldY);
        return flag;
    }

    private int getClownDirection(Entity clown) {

        int direction;

        if(clown != null) {
            int[] beiberLoc = getBeiberLoc();
            int clownX = clown.getX(), clownY = clown.getY();

            if (clownX > beiberLoc[0]) {
                if (clownY > beiberLoc[1]) {
                    direction = Entity.Dir.UP_LEFT;
                } else if (clownY < beiberLoc[1]) {
                    direction = Entity.Dir.DOWN_LEFT;
                } else {
                    direction = Entity.Dir.LEFT;
                }
            } else if (clownX < beiberLoc[0]) {
                if (clownY > beiberLoc[1]) {
                    direction = Entity.Dir.UP_RIGHT;
                } else if (clownY < beiberLoc[1]) {
                    direction = Entity.Dir.DOWN_RIGHT;
                } else {
                    direction = Entity.Dir.RIGHT;
                }
            } else {
                if (clownY > beiberLoc[1]) {
                    direction = Entity.Dir.UP;
                } else if (clownY < beiberLoc[1]) {
                    direction = Entity.Dir.DOWN;
                } else {
                    direction = Entity.Dir.RIGHT;
                }
            }
        }else{
            direction = Entity.Dir.DOWN;
        }

        return direction;
    }

    void checkDead(){
        //puts in all entities'
        Entity temp;
        for(int i = 0; i < teams.size(); i++){

            for(int j = 0; j < teams.get(i).size(); j++){
                temp = teams.get(i).get(j);

                if(temp.getHealth() <= 0){
                    teams.get(i).remove(temp);
                    grid.get(temp.getX()).remove(temp.getY());
                    grid.get(temp.getX()).add(temp.getY(), temp.getPrevEnt());
                }


            }
        }
    }

    void addPlayer(Entity e, int team){

        if(e instanceof Beiber){
            beiber = e;
        }

        teams.get(team).add(e);
        int x = e.getX(), y = e.getY();

        e.setPrevEnt(getEntity(x, y));
        grid.get(x).remove(y);
        grid.get(x).add(y, e);
    }

    private void insert(Entity e, int x, int y){


        e.setPrevEnt(getEntity(x, y));


        grid.get(x).remove(y);
        grid.get(x).add(y, e);
    }

    private void put(Entity e, int x, int y){
        e.setxLoc(x);
        e.setyLoc(y);
    }



    int[] getBeiberLoc(){
        int[] locs = new int[2];
        locs[0] = beiber.getX();
        locs[1] = beiber.getY();
        return locs;
    }

    double getBeiberHealth(){
        return beiber.getHealth();
    }

    int getBeiberOJ(){
        return ((Beiber)(beiber)).getOJLeft();
    }

    private void makeGrid(){

        teams = new ArrayList<>();
        grid = new ArrayList<>();

        teams.add(new ArrayList<>());
        teams.add(new ArrayList<>());

        for(int i = 0; i < cols; i++) {

            grid.add(new ArrayList<>());

            for (int j = 0; j < rows; j++) {

                grid.get(i).add(new OccupiableSpace());

            }

        }

    }

     Entity getEntity(int col, int row){
        return grid.get(col).get(row);
    }

//    private char[][] getCharGrid(){
//        char[][] field = new char[rows][cols];
//
//        for(int x = 0; x < rows; x++){
//
//            for(int y = 0; y < cols; y++){
//                field[x][y] = getEntity(y,x).getSymbol();
//            }
//        }
//
//        return field;
//    }


    // For gamecontroller access
    ArrayList<ArrayList<Entity>> getPlayers() {
        return teams;
    }



    int getRows(){
        return rows;
    }

    int getCols(){
        return cols;
    }
}
