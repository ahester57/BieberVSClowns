
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.GridPane;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController implements Initializable{

    private World world;
    private final static int GAME_COLS = 40, GAME_ROWS = 40;
    private ArrayList<ArrayList<ImageView>> imgList;
    private final ToggleGroup dirGroup = new ToggleGroup();


    //Beiber stuff
    private int beiberDir;
    private String action;


    @FXML
    private Button newGame;
    @FXML
    private GridPane gameboard;
    @FXML
    private ListView<String> controlList;
    @FXML
    private Button beiberGo;
    @FXML
    private ProgressBar beiberHealth;
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private Label ojLabel;
    @FXML
    private Label posse1;
    @FXML
    private Label posse2;
    @FXML
    private Label posse3;
    @FXML
    private ProgressBar pHealth1;
    @FXML
    private ProgressBar pHealth2;
    @FXML
    private ProgressBar pHealth3;

    @FXML
    private ToggleButton UP;
    @FXML
    private ToggleButton RIGHT;
    @FXML
    private ToggleButton LEFT;
    @FXML
    private ToggleButton DOWN;
    @FXML
    private ToggleButton UP_RIGHT;
    @FXML
    private ToggleButton UP_LEFT;
    @FXML
    private ToggleButton DOWN_RIGHT;
    @FXML
    private ToggleButton DOWN_LEFT;
    @FXML
    private ToggleButton STAY;



    @Override
    public void initialize(URL location, ResourceBundle resources) {


//
//        //Game logic stuff
//
//        initWorld();
//
//        //updateImageList();
//        //Gameboard stuff

        initGameboard();
        initControls();
        System.out.println(gameboard.getChildren());
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if(event.getSource() == newGame) {
            UP.setDisable(false);
            RIGHT.setDisable(false);
            LEFT.setDisable(false);
            DOWN.setDisable(false);
            UP_RIGHT.setDisable(false);
            UP_LEFT.setDisable(false);
            DOWN_LEFT.setDisable(false);
            DOWN_RIGHT.setDisable(false);
            STAY.setDisable(false);
            beiberGo.setDisable(false);
            controlList.setDisable(false);
            initWorld();
            updateGUI();
            //centerNodeInScrollPane(scrollpane, imgList.get(world.getBeiberLoc()[0]).get(world.getBeiberLoc()[1]));
        }

        if(event.getSource() == beiberGo) {
            world.run(beiberDir, action);


            world.clownRun();
            updateGUI();


        }
    }

    private ImageView makeImageView() {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(40);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        return imageView;
    }

    private ImageView makeImageView(Image img) {
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(40);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        return imageView;
    }

    private void initGameboard() {


        for(int i = 0; i < GAME_COLS; i++){
            ColumnConstraints column = new ColumnConstraints(32);
            gameboard.getColumnConstraints().add(column);

        }
        for(int i = 0; i < GAME_ROWS; i++){
            RowConstraints row = new RowConstraints(32);
            gameboard.getRowConstraints().add(row);
        }

        ImageView temp;
        imgList = new ArrayList<>();

        for(int i = 0; i < GAME_COLS; i++){
            imgList.add(new ArrayList<>());

            for(int j = 0; j < GAME_ROWS; j++){
                temp = makeImageView();
                imgList.get(i).add(temp);
                gameboard.add(temp, i, j);
            }

        }



    }

    private void initControls() {

        //Directional controls
        UP.setToggleGroup(dirGroup);
        UP.setText("\u21D1");
        UP.setUserData("up");
        RIGHT.setToggleGroup(dirGroup);
        RIGHT.setText("\u21D2");
        RIGHT.setUserData("right");
        LEFT.setToggleGroup(dirGroup);
        LEFT.setText("\u21D0");
        LEFT.setUserData("left");
        DOWN.setToggleGroup(dirGroup);
        DOWN.setText("\u21D3");
        DOWN.setUserData("down");
        UP_RIGHT.setToggleGroup(dirGroup);
        UP_RIGHT.setText("\u21D7");
        UP_RIGHT.setUserData("upRight");
        UP_LEFT.setToggleGroup(dirGroup);
        UP_LEFT.setText("\u21D6");
        UP_LEFT.setUserData("upLeft");
        DOWN_RIGHT.setToggleGroup(dirGroup);
        DOWN_RIGHT.setText("\u21D8");
        DOWN_RIGHT.setUserData("downRight");
        DOWN_LEFT.setToggleGroup(dirGroup);
        DOWN_LEFT.setText("\u21D9");
        DOWN_LEFT.setUserData("downLeft");
        STAY.setToggleGroup(dirGroup);
        STAY.setText("\u20E0");
        STAY.setUserData("stay");
        STAY.setTooltip(new Tooltip("Wait here"));

        dirGroup.selectedToggleProperty().addListener((ov, toggle, new_toggle) -> {
            try {
                System.out.println(dirGroup.getSelectedToggle().getUserData());
                String dir = (String) dirGroup.getSelectedToggle().getUserData();
                if (dir.equals("up")) {
                    beiberDir = Entity.Dir.UP;
                } else if (dir.equals("right")) {
                    beiberDir = Entity.Dir.RIGHT;
                } else if (dir.equals("left")) {
                    beiberDir = Entity.Dir.LEFT;
                } else if (dir.equals("down")) {
                    beiberDir = Entity.Dir.DOWN;
                } else if (dir.equals("upRight")) {
                    beiberDir = Entity.Dir.UP_RIGHT;
                } else if (dir.equals("upLeft")) {
                    beiberDir = Entity.Dir.UP_LEFT;
                } else if (dir.equals("downRight")) {
                    beiberDir = Entity.Dir.DOWN_RIGHT;
                } else if (dir.equals("downLeft")) {
                    beiberDir = Entity.Dir.DOWN_LEFT;
                } else if (dir.equals("stay")){
                    beiberDir = Entity.Dir.STAY;
                }

                //ADD other directions!!!


            }catch(NullPointerException e){

            }
        });

        RIGHT.setSelected(true);


        //Attack list setup
        //controlList.setCellFactory(list -> new AttackListCell());
        controlList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            action = newValue;
            System.out.println(newValue);
        });

        String actions[] = {"Microphone Attack", "Call Posse", "Drink OJ"};

        for(String str : actions){
            controlList.getItems().add(str);
        }

        controlList.getSelectionModel().select(0);
    }

    private void initWorld() {
        world = new World();

        //Beiber Posse
        int[] loc = getRandomLoc();
        world.addPlayer(new Beiber(loc[0], loc[1], 30), World.BEIBER_TEAM);

        for(int i = 0; i < 3; i++) {
            loc = getPosseLoc();
            world.addPlayer(new Posse(loc[0], loc[1], 30, 1, .02, 1), World.BEIBER_TEAM);
        }


        //Clown Posse

        // ADD OTHER CLOWNS AND GETCLOWNLOC()
        int[] leadClownLoc = getLeadClownLoc();
        world.addPlayer(new LeadClown(leadClownLoc[0], leadClownLoc[1], 10, 1, .02, 0), World.CLOWN_TEAM);


        for(int i = 0; i < 6; i++) {
            loc = getClownLoc(leadClownLoc);
            world.addPlayer(new Clown(loc[0], loc[1], 10, 1, .02, 0), World.CLOWN_TEAM);
        }
    }

    static int[] getRandomLoc() {
        int[] locs = new int[2];
        Random rand = new Random();

        locs[0] = (int)(rand.nextDouble() * GAME_COLS);
        locs[1] = (int)(rand.nextDouble() * GAME_ROWS);

        return locs;
    }

    private int[] getPosseLoc() {
        int[] locs = new int[2];
        int[] beibLoc = world.getBeiberLoc();
        Random rand = new Random();

        int xDispl = (int)(rand.nextDouble() * 4);
        xDispl = (rand.nextDouble() > 0.5) ? xDispl : (xDispl * -1);

        int yDispl = (int)(rand.nextDouble() * 4);
        yDispl = (rand.nextDouble() > 0.5) ? yDispl : (yDispl * -1);


        int x = beibLoc[0] + xDispl;
        if(x < 0 || x > 39){
            locs[0] = beibLoc[0] - xDispl;
        }else {
            locs[0] = x;
        }

        int y = beibLoc[1] + yDispl;
        if(y < 0 || y > 39){
            locs[1] = beibLoc[1] - yDispl;
        }else {
            locs[1] = y;
        }

        if(!(world.getEntity(locs[0], locs[1]) instanceof OccupiableSpace)){
            return getPosseLoc();
        }

        return locs;
    }

    private int[] getLeadClownLoc() {
        int[] locs = new int[2];
        Random rand = new Random();

        locs[0] = (int)(rand.nextDouble() * GAME_COLS);
        locs[1] = (int)(rand.nextDouble() * GAME_ROWS);


        if((Math.abs(locs[0] - world.getBeiberLoc()[0]) < 10) || (Math.abs(locs[1] - world.getBeiberLoc()[1]) < 10)){
            return getLeadClownLoc();
        }

        return locs;
    }

    private int[] getClownLoc(int[] lead) {
        int[] locs = new int[2];
        Random rand = new Random();

        int xDispl = (int)(rand.nextDouble() * 3);
        xDispl = (rand.nextDouble() > 0.5) ? xDispl : (xDispl * -1);

        int yDispl = (int)(rand.nextDouble() * 3);
        yDispl = (rand.nextDouble() > 0.5) ? yDispl : (yDispl * -1);


        int x = lead[0] + xDispl;
        if(x < 0 || x > 39){
            locs[0] = lead[0] - xDispl;
        }else {
            locs[0] = x;
        }

        int y = lead[1] + yDispl;
        if(y < 0 || y > 39){
            locs[1] = lead[1] - yDispl;
        }else {
            locs[1] = y;
        }

        if(!(world.getEntity(locs[0], locs[1]) instanceof OccupiableSpace)){
            return getClownLoc(lead);
        }

        return locs;
    }



    public void updateGUI() {
        ArrayList<ArrayList<Entity>> entities  = world.getPlayers();
        Entity temp;

        //clears board
        for(ArrayList<ImageView> col : imgList) {
            for(ImageView imgV : col){
                imgV.setImage(null);
            }
        }

        //puts in all entities
        for(int i = 0; i < entities.size(); i++){

            for(int j = 0; j < entities.get(i).size(); j++){
                temp = entities.get(i).get(j);
                imgList.get(temp.getX()).get(temp.getY()).setImage(temp.getIcon());



            }
        }

        //sidebar stuff
        beiberHealth.setProgress(world.getBeiberHealth());
        ojLabel.setText(String.valueOf(world.getBeiberOJ()));

        try{
            posse1.setText(entities.get(World.BEIBER_TEAM).get(1).getName());
            pHealth1.setProgress(entities.get(World.BEIBER_TEAM).get(1).getHealth());
        }catch(IndexOutOfBoundsException ex){
            posse1.setText("Dead");
            pHealth1.setProgress(0);
        }
        try{
            posse2.setText(entities.get(World.BEIBER_TEAM).get(2).getName());
            pHealth2.setProgress(entities.get(World.BEIBER_TEAM).get(2).getHealth());
        }catch(IndexOutOfBoundsException ex){
            posse2.setText("Dead");
            pHealth2.setProgress(0);
        }
        try{
            posse3.setText(entities.get(World.BEIBER_TEAM).get(3).getName());
            pHealth3.setProgress(entities.get(World.BEIBER_TEAM).get(3).getHealth());
        }catch(IndexOutOfBoundsException ex){
            posse3.setText("Dead");
            pHealth3.setProgress(0);
        }


    }

    public void centerNodeInScrollPane(ScrollPane scrollPane, Node node) {
        double h = scrollPane.getContent().getBoundsInLocal().getHeight();
        double y = (node.getBoundsInParent().getMaxY() +
                node.getBoundsInParent().getMinY()) / 2.0;
        double v = scrollPane.getViewportBounds().getHeight();
        scrollPane.setVvalue(scrollPane.getVmax() * ((y - 0.5 * v) / (h - v)));
    }



}
