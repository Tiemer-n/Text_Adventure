/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text_adventure;


import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Isaac
 */
public class Text_Adventure extends Application {

    public int answer;
    public static String[][] map = new String[30][30];
    public static String[][] LandMarkMap = new String[30][30];
    public static int x = 15;
    public static int y = 15;
    public int on = 1;
    public static BackPack pack = new BackPack(10,30,3);
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        
        LandMarks land = new LandMarks();
        
        
        
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                map[i][j] = "   ";
                LandMarkMap[i][j]="   ";
            }
        }
        //  à á â ã ä å
        
        CreateLandmarks();
        LandMarkMap[x][y]=" A ";
        map[x][y] = " ð ";
        CreateMap();

        Button up = new Button("↑");
        Button down = new Button("↓");
        Button left = new Button("←");
        Button right = new Button("→");
        Button backpack = new Button("BackPack");
        
        //-----------------------------
        backpack.setLayoutY(180);
        backpack.setLayoutX(75);
        backpack.setOnAction(e -> {
            backPack(0);
        });
        
        Pane root = new Pane();

        up.setOnAction((ActionEvent e) -> { 
            if (Check(-1, 0) == 1) {
                MovePlayer(-1, 0);
                pack.setWater(pack.getWater()-1);
                CreateMap();
            }
            else if (Check(-1,0) == 2){
                MovePlayer(-1, 0);   
                CreateMap(); 
                pack.setWater(pack.getWater()-1);
                runLandmark(LandMarkMap[x][y]);
            }

        });
        up.setLayoutX(89);
        up.setLayoutY(25);
        up.setStyle("-fx-font-size:20");
        
  
        down.setOnAction(e -> {
            if( Check(1,0) == 1 ){
                MovePlayer(1, 0);
                pack.setWater(pack.getWater()-1);
                CreateMap();  
            }
            else if ( Check(1,0) == 2){
                MovePlayer(1, 0);
                pack.setWater(pack.getWater()-1);
                CreateMap();
                runLandmark(LandMarkMap[x][y]);
            }
            
        });
        down.setLayoutX(89);
        down.setLayoutY(114);
        down.setStyle("-fx-font-size:20");

        left.setOnAction(e -> {
            if( Check(0,-1) == 1 ){
                MovePlayer(0, -1);
                pack.setWater(pack.getWater()-1);
                CreateMap();
            }
            else if ( Check(0,-1) == 2){
                MovePlayer(0, -1); 
                CreateMap(); 
                pack.setWater(pack.getWater()-1);
                runLandmark(LandMarkMap[x][y]);
            }
            
        });
        left.setLayoutX(43);
        left.setLayoutY(70);
        left.setStyle("-fx-font-size:20");

        right.setOnAction(e -> {
            if( Check(0,1) == 1 ){
                MovePlayer(0, 1);
                pack.setWater(pack.getWater()-1);
                CreateMap();
            }
            else if ( Check(0,1) == 2){
                MovePlayer(0, 1);
                pack.setWater(pack.getWater()-1);
                CreateMap();
                runLandmark(LandMarkMap[x][y]);
            }
            
        });
        right.setLayoutX(127);
        right.setLayoutY(70);
        right.setStyle("-fx-font-size:20");

        root.getChildren().addAll(up, down, left, right,backpack);

        Scene scene = new Scene(root, 200, 200);

        primaryStage.setTitle("Arrows");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }

    public void CreateLandmarks(){
        Random rand = new Random();
        
        for (int i = 0; i < 50; i++) {
            int k = rand.nextInt(30);
            int u = rand.nextInt(30);
            map[k][u]="|C|";
            LandMarkMap[k][u] = "|C|";
        }
        for (int i = 0; i < 20; i++) {
            int k = rand.nextInt(30);
            int u = rand.nextInt(30);
            map[k][u]="|H|";
            LandMarkMap[k][u] = "|H|";
        }
        for (int i = 0; i < 5; i++) {
            int k = rand.nextInt(30);
            int u = rand.nextInt(30);
            map[k][u]="|O|";
            LandMarkMap[k][u] = "|O|";
        }
    }
    
    
    
    public int Check(int ex, int why) {
        if ((x + ex) < 0 || (x + ex) > 29 || (y + why) < 0 || (y + why) > 29) {
            return -1;
        }else if (!"   ".equals(LandMarkMap[x+ex][y+why])){
            return 2;
        }else {
            return 1;
        }
        
        
    }
    

    public void MovePlayer(int ex, int why) {
        map[x][y] = LandMarkMap[x][y];

        x += ex;
        y += why;

        
        if(on == 7){
            on = 1;
        }
        
        switch (on) {
            case 1:
                map[x][y]=" ò ";
                on++;
                break;
            case 2:
                map[x][y]=" ó ";
                on++;
                break;
            case 3:
                map[x][y]=" ô ";
                on++;
                break;
            case 4:
                map[x][y]=" õ ";
                on++;
                break;
            case 5:
                map[x][y]=" ö ";
                on++;
                break;
            case 6:
                map[x][y]=" ð ";
                on++;
                break;
            default:
                break;
        }
        
    }

    
    public static void backPack(int mode){
        
        Stage stage = new Stage();
        Pane root = new VBox();
        Label food = new Label();
        food.setText("Food: " + Integer.toString(pack.getFood()));
        food.setStyle("-fx-font-size:15");
        
        Label water = new Label();
        water.setText("Water: " + Integer.toString(pack.getWater()));
        water.setStyle("-fx-font-size:15");
        
        Label torches = new Label();
        torches.setText("Torches: " + Integer.toString(pack.getTorches()));
        torches.setStyle("-fx-font-size:15");
        
        
        Button btn = new Button("Close");
        btn.setLayoutY(20);
        btn.setOnAction(e -> {
            stage.close();
        });
        
        if(mode == 1){
            food.setText("Food: " + Integer.toString(pack.getFood()));
            torches.setText("Torches: " + Integer.toString(pack.getTorches()));
            water.setText("Water: " + Integer.toString(pack.getWater()));
        }else if (mode == 0){
            root.getChildren().addAll(btn,food,water,torches);
            Scene scene = new Scene(root,100,150);
            stage.setTitle("BackPack");
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.show();
        }
    }
    
    
    public static void CreateMap() {
        String Food = Integer.toString(pack.getFood());
        String Water = Integer.toString(pack.getWater());
        String Torches = Integer.toString(pack.getTorches());
        
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
        
        System.out.println("ð <-- this is you ");
        System.out.println("A: Home C: cave  H: house  O: City");
        System.out.println("");
        System.out.println("BackPack: ");
        System.out.println("Food: " + Food);
        System.out.println("Water: " + Water);
        System.out.println("Torches: " + Torches);
        System.out.println("");
        for (int i = 0; i < 30; i++) {
            System.out.print("___");
        }
        System.out.println("");
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30 ; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println(" |");
        }

        map[x][y] = "[@]";

    }
    
     public void runLandmark(String entity){
        
        
        switch (entity){
            case "|C|":
                Cave();
                break;
            case "|H|":
                House();
                break;
            case "|O|":
                City();
                break;
            case" A ":
                Home();
                break;
            default:
                System.out.println("lmao youll never see this ☻");
        }
        
        
        
    }
    
    public void Home(){
        
        Stage stage = new Stage();
        Pane root = new Pane();
        
        Label lab = new Label();
        
        Image img = new Image("house.jpg");
        
        ImageView img2 = new ImageView(img);
        img2.setLayoutX(30);
        img2.setFitHeight(488);
        img2.setFitWidth(671);
        lab.setText("---You are in your home---");
        lab.setLayoutX(300);
        lab.setLayoutY(510);
        
        Button leave = new Button("Embark");
        leave.setLayoutX(40);
        leave.setLayoutY(520);
        leave.setOnAction(e -> {
            stage.close();
        });
        
        
        Button enter = new Button("Re-Stock");
        enter.setLayoutX(350);
        enter.setLayoutY(530);
        enter.setOnAction(e -> {
            if(pack.food != 10 ){
                System.out.println("+" + (10-pack.food) + " food");
                pack.food = 10;
            }else{
                System.out.println("+0 food");
            }
            if (pack.water != 10){
                System.out.println("+" + (30-pack.water) + " water");
                pack.water = 30;
            }else{
                System.out.println("+0 water");
            }
            backPack(1);
            CreateMap();
            enter.setDisable(true);
        });
        
        
        Scene scene = new Scene(root, 700, 700);
        root.getChildren().addAll(leave,lab,enter,img2);
        stage.setTitle("Cave");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        System.out.println("You have left Your Home");
        
        
        
    }
    
    
    public void Cave(){
        Stage stage = new Stage();
        Pane root = new Pane();
        
        Label lab = new Label();
        
        Image img = new Image("cave.png");
        
        ImageView img2 = new ImageView(img);
        img2.setLayoutX(30);
        
        
        Button collect = new Button ("Collect +1 meat");
        Button enter = new Button("enter the cave");
        
        lab.setText("---You are standing by the cave---");
        lab.setLayoutX(300);
        lab.setLayoutY(510);
        
        Button leave = new Button("Leave");
        leave.setLayoutX(40);
        leave.setLayoutY(520);
        leave.setOnAction(e -> {
            stage.close();
        });
        
        enter.setLayoutX(350);
        enter.setLayoutY(530);
        enter.setOnAction(e -> {
            lab.setText("---You are now in a cave---");
            root.getChildren().add(collect);
            root.getChildren().remove(enter);
        });
        
        collect.setLayoutX(350);
        collect.setLayoutY(530);
        collect.setOnAction(e ->{ 
            pack.food++;
            collect.setDisable(true);
        });
        
        
        
        
        Scene scene = new Scene(root, 700, 700);
        root.getChildren().addAll(leave,lab,enter,img2);
        stage.setTitle("Cave");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        System.out.println("You have left the Cave.");
        
    }
    
    public void House(){
        Stage stage = new Stage();
        Pane root = new Pane();
        
        Label lab = new Label();
        
        Image img = new Image("house.png");
        ImageView img2 = new ImageView(img);
        
        img2.setFitHeight(488);
        img2.setFitWidth(671);
        img2.setLayoutX(20);
        
        lab.setText("---You are standing by the house---");
        lab.setLayoutX(300);
        lab.setLayoutY(510);
        
        Button leave = new Button("Leave");
        leave.setLayoutX(40);
        leave.setLayoutY(520);
        leave.setOnAction(e -> {
            stage.close();
        });
        
        
        Button enter = new Button("enter the house");
        enter.setLayoutX(350);
        enter.setLayoutY(530);
        enter.setOnAction(e -> {
            lab.setText("---You are now in the house---");
            enter.setDisable(true);
        });
        
        
        Scene scene = new Scene(root, 700, 700);
        root.getChildren().addAll(leave,lab,enter,img2);
        stage.setTitle("House");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        System.out.println("You have left the House.");
    }
    
    public void City(){
        Stage stage = new Stage();
        Pane root = new Pane();
        
        Label lab = new Label();
        
        Image img = new Image("city.jpg");
        
        ImageView img2 = new ImageView(img);
        img2.setLayoutX(30);
        img2.setFitHeight(488);
        img2.setFitWidth(671);
        
        
        
        lab.setText("---You are standing by the entrance---");
        lab.setLayoutX(300);
        lab.setLayoutY(510);
        
        Button leave = new Button("Leave");
        leave.setLayoutX(40);
        leave.setLayoutY(520);
        leave.setOnAction(e -> {
            stage.close();
        });
        
        
        Button enter = new Button("enter the city");
        enter.setLayoutX(350);
        enter.setLayoutY(530);
        enter.setOnAction(e -> {
            lab.setText("---You are now in the city---");
            enter.setDisable(true);
        });
        
        
        Scene scene = new Scene(root, 700, 700);
        root.getChildren().addAll(leave,lab,enter,img2);
        stage.setTitle("City");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        System.out.println("You have left the City.");
    }
    
}


