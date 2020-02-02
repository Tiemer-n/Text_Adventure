
package text_adventure;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JFrame;

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
    public static int MaxWater = 10;
    public static int MaxFood = 10;
    public static BackPack pack = new BackPack(MaxFood,MaxWater,0,0,0,0,0,0,0);
    
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        
            
        
        CreateLandmarks();
        map[x][y] = " ð ";
        CreateMap();

        Button up = new Button("↑");
        Button down = new Button("↓");
        Button left = new Button("←");
        Button right = new Button("→");
        
        //-----------------------------
        
        
        Pane root = new Pane();

        up.setOnAction((ActionEvent e) -> { 
            if (Check(-1, 0) == 1) {
                MovePlayer(-1, 0);
                pack.setWater(pack.getWater()-1);
                CreateMap();
                CheckHealth();
            }
            else if (Check(-1,0) == 2){
                MovePlayer(-1, 0);   
                CreateMap(); 
                CheckHealth();
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
                CheckHealth();
            }
            else if ( Check(1,0) == 2){
                MovePlayer(1, 0);
                pack.setWater(pack.getWater()-1);
                CreateMap();
                CheckHealth();
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
                CheckHealth();
            }
            else if ( Check(0,-1) == 2){
                MovePlayer(0, -1); 
                CreateMap(); 
                CheckHealth();
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
                CheckHealth();
            }
            else if ( Check(0,1) == 2){
                MovePlayer(0, 1);
                pack.setWater(pack.getWater()-1);
                CreateMap();
                CheckHealth();
                runLandmark(LandMarkMap[x][y]);
            }
            
        });
        right.setLayoutX(127);
        right.setLayoutY(70);
        right.setStyle("-fx-font-size:20");

        root.getChildren().addAll(up, down, left, right);

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
        
        JFrame menu = new Menu();
        menu.setSize(600, 500);
        menu.show();
        
        
        launch(args);
    }
    
    
    public void CheckHealth(){
        if(pack.water == 0){
            System.out.println("You've ran out of water");
            System.out.println("It becomes impossible to go on");
        }else if (pack.water <= -1){
            System.out.println("");
            System.out.println("You ran out of water and have died of thirst");
            System.out.println("");
            System.out.println("GAME OVER");
            System.exit(0);
        }   
    }

    public void CreateLandmarks() throws FileNotFoundException{
        Random rand = new Random();
        String Pick= " ";
        int picktemp = rand.nextInt(3);
        switch (picktemp) {
            case 0:
                Pick = "template1.txt";
                break;
            case 1:
                Pick = "template2.txt";
                break;
            case 2:
                Pick = "template3.txt";
                break;
            default:
                break;
        }
        try{
            File dirl = new File("res/"+ Pick);
            Scanner read = new Scanner(dirl);
            for (int i = 0; i < 30; i++) {
                String line = read.nextLine();
                String item= null;
                for (int j = 0; j < 30; j++) {
                    switch (line.charAt(j)) {
                        case '#':
                            item = "   ";
                            break;
                        case 'C':
                            item = "|C|";
                            break;
                        case 'H':
                            item = "|H|";
                            break;
                        case 'A':
                            item = " A ";
                            break;
                        case 'O':
                            item = "|O|";
                            break;
                        default:
                            break;
                        }
                    map[i][j]=item;
                    LandMarkMap[i][j]=item;
                }
                
            }
            //---
        }catch(Exception e){
            System.out.println("Error: " + e);
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

    
   
    
    
    public static void CreateMap() {
        String Food = Integer.toString(pack.getFood());
        String Water = Integer.toString(pack.getWater());
        String Iron = Integer.toString(pack.getIron());
        String Steel = Integer.toString(pack.getSteel());
        String woodenClub = Integer.toString(pack.getWoodenClub());
        String Sword = Integer.toString(pack.getSword());
        String Knife = Integer.toString(pack.getKnife());
        String Leather = Integer.toString(pack.getLeather());
        String Wood = Integer.toString(pack.getWood());
        
        
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
        
        System.out.println("ð <-- this is you ");
        System.out.println("A: Home C: cave  H: house  O: City");
        System.out.println("");
        System.out.println("BackPack: ");
        System.out.println("Food: " + Food);
        System.out.println("Water: " + Water);
        System.out.println("Leather: " + Leather);
        System.out.println("Wood: " + Wood);
        System.out.println("Iron: " + Iron);
        System.out.println("Steel: " + Steel);
        if("1".equals(Sword)){
            System.out.println("Weapon: Sword");
        }else if ("1".equals(Knife)){
            System.out.println("Weapon: Knife");
        }else if ("1".equals(woodenClub)){
            System.out.println("Weapon: WoodenClub");
        }
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
        //checking where the landmark is on the map
        int type =0;
        
        //dont try to judge this was the only way i could carefully do it without making mistakes
        
        //type 1
        if(x >= 10 && x <= 20 && y >= 10 && y <= 20){
            type = 1;
        }
        //type 2 
        if ( (x > 5 && x < 10) && (y > 5  && y < 25) ){
            type = 2;
        }
        if( x > 20 && x < 25 && (y > 5  && y < 25)){
            type = 2;
        }
        if(y > 5 && y < 10 && (x > 5 && x < 25) ){
            type =2;
        }
        if(y > 20 && y < 25 && (x > 5 && x < 25) ){
            type =2;
        }
        //type 2 
        
        //type 3
        if(x >= 0 && x <= 5 && y >= 0 && y < 30 ){
            type = 3;
        }        
        if(x >= 25 && x < 30 && y >= 0 && y < 30){
            type = 3;
        }        
        if (y >= 0 && y <= 5 && x >= 0 && x < 30 ){
            type = 3;
        }        
        if(y >= 25 & y < 30 && x >= 0 && x < 30){
            type =3;
        }
        //type 3

        switch (entity){
            case "|C|":
                Cave(type);
                break;
            case "|H|":
                House(type);
                break;
            case "|O|":
                City();
                break;
            case" A ":
                Home();
                break;
            case " C ":
                System.out.println("This cave has already been explored");
                break;
            case " H ":
                System.out.println("This house has already been explored");
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
        img2.setLayoutX(15);
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
            int checkw = 0;
            int checkf = 0;
            int f = pack.food;
            int w = pack.water;
            if(pack.food < MaxFood ){   
                pack.food = MaxFood;
                checkf++;
            }
            if (pack.water < MaxWater){   
                pack.water = MaxWater;
                checkw++;
            }
            CreateMap();
            if(checkf>0){
              System.out.println("+" + (MaxFood-f) + " food");  
            }else{
              System.out.println("+0 food");  
            }
            
            if(checkw>0){
                System.out.println("+" + (MaxWater-w) + " water");
            }else{
               System.out.println("+0 water"); 
            }
            
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
    
    
    public void Cave(int type){
        System.out.println("Type: " + type);
        Random rand = new Random();
        
        Stage stage = new Stage();
        Pane root = new Pane();
        Label lab1 = new Label("---You are standing by the cave---");
        lab1.setLayoutX(300);
        lab1.setLayoutY(510);

        Label lab2 = new Label();
        lab2.setLayoutX(300);
        lab2.setLayoutY(530);

        Label lab3 = new Label();
        lab3.setLayoutX(300);
        lab3.setLayoutY(550);


        Image img = new Image("cave.png");
        ImageView img2 = new ImageView(img);
        img2.setLayoutX(30);
        Button enter = new Button("enter the cave");

        Button leave = new Button("Leave");
        leave.setLayoutX(40);
        leave.setLayoutY(520);
        leave.setOnAction(e -> {
            stage.close();
        }); 
        enter.setLayoutX(100);
        enter.setLayoutY(520);
        //first layer --------------------------------------------------------------------------------------------------------
        enter.setOnAction(e -> {
            final int Leather = rand.nextInt(3); 
            final int wood = rand.nextInt(3); 
            final int Iron = rand.nextInt(3);
            final int Steel = rand.nextInt(3);
            root.getChildren().removeAll(leave,enter);
            lab1.setText("---You are now in a cave---");
            GenerateAttack(101,type); //30% cahnce

            if(Leather > 0 || wood > 0){
                Button pickUp = new Button("Pick up Items"); pickUp.setLayoutX(300); pickUp.setLayoutY(570);
                root.getChildren().add(pickUp);
                pickUp.setOnAction(a -> {
                    switch (type){
                        case 1:
                            pack.leather += Leather ;
                            pack.wood += wood;
                            lab3.setText("you picked up "+Leather+" leather and "+wood+" wood");
                            break;
                        case 3:
                            pack.iron += Iron ;
                            pack.steel += Steel;
                            lab3.setText("you picked up "+Iron+" iron and "+Steel+" steel");
                            break;
                        case 2:
                            pack.leather += Leather ;
                            pack.iron += Iron;
                            lab3.setText("you picked up "+Leather+" leather and "+Iron+" iron");
                    }
                    root.getChildren().remove(pickUp);
                });
                lab2.setText("You search around and find valued items");

            }else{
                lab2.setText("you couldnt find anything in the cave");
            }
            Button enterFurther = new Button ("EnterFurther"); enterFurther.setLayoutX(300); enterFurther.setLayoutY(600);
            //second layer ---------------------------------------------------------------------------------------------------
            enterFurther.setOnAction(a -> {
                
                
                final int Leather2 = rand.nextInt(2);
                final int wood2 = rand.nextInt(2);
                final int Iron2 = rand.nextInt(3);
                final int Steel2 = rand.nextInt(3);
                root.getChildren().removeAll(enterFurther,leave);
                lab1.setText("You go even deeper into the cave");
                GenerateAttack(101,type);
                
                if(Leather > 0 || wood > 0){
                    Button pickUp2 = new Button("Pick up Items"); pickUp2.setLayoutX(300); pickUp2.setLayoutY(570);
                    root.getChildren().add(pickUp2);
                    pickUp2.setOnAction(i -> {
                        switch (type){
                        case 1:
                            pack.leather += Leather2 ;
                            pack.wood += wood2;
                            lab3.setText("you picked up "+Leather2+" leather and "+wood2+" wood");
                            break;
                        case 3:
                            pack.iron += Iron2 ;
                            pack.steel += Steel2;
                            lab3.setText("you picked up "+Iron2+" iron and "+Steel2+" steel");
                            break;
                        case 2:
                            pack.leather += Leather2 ;
                            pack.iron += Iron2;
                            lab3.setText("you picked up "+Leather2+" leather and "+Iron2+" iron");
                    }
                        
                        root.getChildren().remove(pickUp2);
                    });
                }
                
                switch (type){
                    case 1:
                        lab2.setText("you come across an abandond camp");
                        break;
                    case 2:
                        lab2.setText("you see a pile of dead miners on the floor");
                        break;
                    case 3:
                        lab2.setText("you can see the remains of what used to be a massive spiders nest");
                        break;
                }
                lab3.setText("You search around for valued items");
                
                Button FinalVenture = new Button("Enter Deeper"); FinalVenture.setLayoutX(300); FinalVenture.setLayoutY(600);
                

                // final layer ----------------------------------------------------------------
                FinalVenture.setOnAction(i -> {
                    
                    root.getChildren().remove(FinalVenture);
                    GenerateAttack(101,type);
                    
                    lab1.setText("Youve reached the end of the cave");
                    lab2.setText("You find Valuable goods ");
                    lab3.setText("");
                    Button pickUp3 = new Button("Pick up Items"); pickUp3.setLayoutX(300); pickUp3.setLayoutY(570);
                    root.getChildren().add(pickUp3);
                    pickUp3.setOnAction(u -> {
                        switch (type){
                        case 1:
                            pack.leather += 4 ;
                            pack.wood += 4;
                            lab3.setText("you picked up 4 leather and 4 wood");
                            break;
                        case 3:
                            pack.iron += 4;
                            pack.steel += 4;
                            lab3.setText("you picked up 4 iron and 4 steel");
                            break;
                        case 2:
                            pack.leather += 4 ;
                            pack.iron += 4;
                            lab3.setText("you picked up 4 leather and 4 iron");
                    }
                        
                        root.getChildren().remove(pickUp3);
                    });
                    
                    Button FinalLeave = new Button ("leave"); 
                    FinalLeave.setLayoutX(40);
                    FinalLeave.setLayoutY(520);
                    FinalLeave.setOnAction(u -> {
                       LandMarkMap[x][y] = " C ";
                        System.out.println("You have completed this cave +3 water");
                        pack.water += 3;
                       stage.close();
                    });
                    root.getChildren().add(FinalLeave);
                    
                });
                //final layer --------------------------------------------------------------
                root.getChildren().add(FinalVenture);
            });
            //second layer -----------------------------------------
            root.getChildren().add(enterFurther);
        }); 
        //first layer --------------------------------------------------------


        Scene scene = new Scene(root, 700, 700);
        root.getChildren().addAll(leave,lab1,lab2,lab3,enter,img2);
        stage.setTitle("Cave");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        System.out.println("You have left the Cave.");
        
        
    }
    
    public void House(int type){
        System.out.println("Type: " + type);
        
        
        switch (type){
            case 1:
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
                break;
            case 2:
                
                
                break;
                
            case 3:
                
                
                break;
            
            
            
        }

    }
    
    public void City(){
        System.out.println("Type: 3");
        
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
    
    
    public void GenerateAttack(int chance, int difficulty)  {
        // the number is the paramater that the number out of 100 needs to meet
        // e.g. chance = 2 means that the number needs to be 2 or lower (or 2% chance or getting attacked)
        // in order for the attacksequence to run 
        
        
        Random rand = new Random();
        int isAttack = rand.nextInt(100);
        isAttack++;
        if(isAttack <= chance){
            AttackSequence(difficulty);
        }
    }
    
    public void AttackSequence(int difficulty){
       // 0 = type 1 wilderness
       // 1 = type 1 cave or house  
       // 2 = type 2 cave or house 
       // 3 = type 2 wilderness
       // 4 = type 3 cave or house
       // 5 = type 3 wilderness
       // 6 = City 
       
       
        System.out.println("You've been attacked and you won by default because i havent finished this yet");
  
    }
    
}


