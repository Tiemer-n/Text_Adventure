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
    public String[][] map = new String[30][30];
    public String[][] LandMarkMap = new String[30][30];
    public int x = 15;
    public int y = 15;
    public int on = 1;
    public BackPack pack = new BackPack(10,30,3);
    
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
            backPack(1);
        });
        
        Pane root = new Pane();

        up.setOnAction((ActionEvent e) -> { 
            if (Check(-1, 0) == 1) {
                MovePlayer(-1, 0);
                CreateMap();
            }
            else if (Check(-1,0) == 2){
                MovePlayer(-1, 0);   
                CreateMap(); 
                land.runLandmark(LandMarkMap[x][y]);
            }

        });
        up.setLayoutX(89);
        up.setLayoutY(25);
        up.setStyle("-fx-font-size:20");
        
  
        down.setOnAction(e -> {
            if( Check(1,0) == 1 ){
                MovePlayer(1, 0);
                CreateMap();  
            }
            else if ( Check(1,0) == 2){
                MovePlayer(1, 0); 
                CreateMap(); 
                land.runLandmark(LandMarkMap[x][y]);
            }
            
        });
        down.setLayoutX(89);
        down.setLayoutY(114);
        down.setStyle("-fx-font-size:20");

        left.setOnAction(e -> {
            if( Check(0,-1) == 1 ){
                MovePlayer(0, -1);
                CreateMap();
            }
            else if ( Check(0,-1) == 2){
                MovePlayer(0, -1); 
                CreateMap(); 
                land.runLandmark(LandMarkMap[x][y]);
            }
            
        });
        left.setLayoutX(43);
        left.setLayoutY(70);
        left.setStyle("-fx-font-size:20");

        right.setOnAction(e -> {
            if( Check(0,1) == 1 ){
                MovePlayer(0, 1);
                CreateMap();
            }
            else if ( Check(0,1) == 2){
                MovePlayer(0, 1); 
                CreateMap(); 
                land.runLandmark(LandMarkMap[x][y]);
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
            map[k][u]=" C ";
            LandMarkMap[k][u] = " C ";
        }
        for (int i = 0; i < 20; i++) {
            int k = rand.nextInt(30);
            int u = rand.nextInt(30);
            map[k][u]=" H ";
            LandMarkMap[k][u] = " H ";
        }
        for (int i = 0; i < 5; i++) {
            int k = rand.nextInt(30);
            int u = rand.nextInt(30);
            map[k][u]=" O ";
            LandMarkMap[k][u] = " O ";
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

    
    public void backPack(int mode){
        
        Stage stage = new Stage();
        Pane root = new VBox();
        Label food = new Label();
        food.setText("Food: " + Integer.toString(pack.getFood()));
        food.setStyle("-fx-font-size:20");
        
        Label water = new Label();
        water.setText("Water: " + Integer.toString(pack.getWater()));
        water.setStyle("-fx-font-size:20");
        
        Label torches = new Label();
        torches.setText("Torches: " + Integer.toString(pack.getTorches()));
        torches.setStyle("-fx-font-size:20");
        
        
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
    
    
    public void CreateMap() {

        
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
        
        System.out.println("ð <-- this is you ");
        System.out.println("C: cave  H: house  O: City");
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

    public void question() {
        Stage stage1 = new Stage();

        Pane root = new Pane();

        Button yes = new Button("Go");
        Button no = new Button("Leave");
        yes.setLayoutX(40);
        yes.setLayoutY(20);

        no.setLayoutX(115);
        no.setLayoutY(20);

        yes.setOnAction(e -> {
            System.out.println("You've chosen Go");
            answer = 1;
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Text_Adventure.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage1.close();
        });
        no.setOnAction(e -> {
            System.out.println("You've chosen Leave");
            answer = 0;
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Text_Adventure.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage1.close();
        });
        Scene scene = new Scene(root, 200, 150);
        root.getChildren().addAll(yes, no);
        stage1.setTitle("question");
        stage1.setScene(scene);
        stage1.setResizable(false);
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.initModality(Modality.APPLICATION_MODAL);
        stage1.showAndWait();

    }

}
