
package text_adventure;


import javafx.scene.image.Image;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Isaac
 */
public class LandMarks {
    
    
    
    
    public static void main(String[] args){
        System.out.println("Working Directory = " +
              System.getProperty("user.dir"));
        
    }
    
    
    public void runLandmark(String entity){
        
        
        switch (entity){
            case " C ":
                Cave();
                break;
            case " H ":
                House();
                break;
            case " O ":
                City();
                break;
            default:
                System.out.println("lmao youll never see this ☻");
        }
        
        
        
    }
    
    public void Cave(){
        Stage stage = new Stage();
        Pane root = new Pane();
        
        Label lab = new Label();
        
        Image img = new Image("cave.png");
        
        ImageView img2 = new ImageView(img);
        img2.setLayoutX(30);
        
        
        
        
        lab.setText("---You are standing by the cave---");
        lab.setLayoutX(300);
        lab.setLayoutY(510);
        
        Button leave = new Button("Leave");
        leave.setLayoutX(40);
        leave.setLayoutY(520);
        leave.setOnAction(e -> {
            stage.close();
        });
        
        
        Button enter = new Button("enter the cave");
        enter.setLayoutX(350);
        enter.setLayoutY(530);
        enter.setOnAction(e -> {
            lab.setText("---You are now in a cave---");
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
    
    
    
    
    
    
    public int answer;
    
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
                Logger.getLogger(text_adventure.LandMarks.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage1.close();
        });
        no.setOnAction(e -> {
            System.out.println("You've chosen Leave");
            answer = 0;
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(text_adventure.LandMarks.class.getName()).log(Level.SEVERE, null, ex);
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
