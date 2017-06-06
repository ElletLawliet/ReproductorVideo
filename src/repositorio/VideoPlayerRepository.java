/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import dominio.VideoFile;
import enumerators.PlayerRepeatStatus;
import gui.VideoPlayer;
import java.awt.Dimension;
import java.io.File;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import javax.swing.JSlider;


/**
 *
 * @author serbr
 */
public class VideoPlayerRepository{
    private Scene scene;
    private MediaPlayer player;
    private MediaView mediaView =  new MediaView();
    private Media media;
    private Dimension panelDimension;
    private VBox vbox;
    private Slider timeSlider;
    private Timeline slideIn = new Timeline();
    private Timeline slideOut = new Timeline();
    private PlayerRepeatStatus playerRepeatStatus = PlayerRepeatStatus.ALL;
    private List<VideoFile> playList;
    private int currentSongIndex;

    
    public void setCurrentSongIndex(int index){
        this.currentSongIndex = index;
    }
    
    public int getCurrentSongIndex(){
        return this.currentSongIndex;
    }
    
    public void setPlayList(List<VideoFile> playList){
        this.playList = playList;
    }
    
    public void setDimension(Dimension panelDimension){
        this.panelDimension = panelDimension;
    }
    
    public MediaPlayer getPlayer(){
        return this.player;
    }
    
    public MediaView getMediaView(){
        return this.mediaView;
    }
    
    public Media getMedia(){
        return this.media;
    }
    
    public void setPlayerRepeatStatus(PlayerRepeatStatus playerRepeatStatus){
        this.playerRepeatStatus = playerRepeatStatus;
    }
    
    public PlayerRepeatStatus getPlayerRepeatStatus(){
        return this.playerRepeatStatus;
    }
   
    
    public Scene startScene(VideoFile videoFile) {
        Group root = new Group();
        media = new Media(videoFile.getFile().toURI().toString());
        player = new MediaPlayer(media);
        mediaView = new MediaView(player);
        mediaView.setFitWidth(panelDimension.getWidth());
        mediaView.setFitHeight(panelDimension.getHeight());
        mediaView.setPreserveRatio(true);
        timeSlider = new Slider();
        vbox = new VBox();
        vbox.getChildren().add(timeSlider);
        root.getChildren().add(mediaView);
        vboxProperties();
        root.getChildren().add(vbox);
        scene = new Scene(root,panelDimension.getWidth(),panelDimension.getHeight());
        backgroundProperties();
        sliderProperty();
        playerTimeProperty();
        playerEndProperty();
        timeSliderClicProperty();
        playerStopProperty();
        bindProperty();
        slidesProperties();
        slideOutAction(root);
        slideInAction(root);
        return scene;
    }
    
      
    
    
    public void backgroundProperties(){
        Image image = new Image("/resources/blackMejorado.jpg");
        ImagePattern pattern = new ImagePattern(image);
        scene.setFill(pattern);
    }
    
    
    public void playerEndProperty(){
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                if(playerRepeatStatus == PlayerRepeatStatus.ONE){
                    player.stop();
                    player.play();
                }
                else{
                    if(playerRepeatStatus == PlayerRepeatStatus.ALL){
                        player.stop();
                        if(currentSongIndex == playList.size()-1){
                            setSongOnPlayer(playList.get(0));
                            VideoPlayer.playList.setSelectedIndex(0);
                            currentSongIndex = 0;
                            VideoPlayer.currentSongIndex = 0;
                        }
                        else{
                            setSongOnPlayer(playList.get(currentSongIndex +1));
                            VideoPlayer.playList.setSelectedIndex(currentSongIndex + 1);
                            currentSongIndex++;
                            VideoPlayer.currentSongIndex++;
                        }
                        player.play();
                    }
                }
            }
        });
        
    }
    
    
    public void vboxProperties(){
        vbox.setMinSize(panelDimension.getWidth(),100);
        vbox.setTranslateY(panelDimension.getHeight() - 25);
    }
    
    public void sliderProperties(){
        player.setOnReady(new Runnable() {
            @Override
            public void run() {
                timeSlider.setMin(0.0);
                timeSlider.setValue(0.0);
                timeSlider.setMax(player.getTotalDuration().toSeconds());
                timeSlider.setMinWidth(1);
                timeSlider.setMajorTickUnit(timeSlider.getMax() / 5);
                timeSlider.setShowTickLabels(true);
                timeSlider.setShowTickMarks(true);
                timeSlider.setSnapToTicks(true);
            }
        });
    }
    
    public void sliderProperty(){
        timeSlider.setMin(0.0);
        timeSlider.setValue(0.0);
        timeSlider.setMax(player.getTotalDuration().toSeconds());
        timeSlider.setMinWidth(1);
        timeSlider.setMajorTickUnit(timeSlider.getMax() / 5);
        timeSlider.setShowTickLabels(true);
        timeSlider.setShowTickMarks(true);
        timeSlider.setSnapToTicks(true);
    }
    
    public void slideInAction(Group root){
        root.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                slideIn.play();
            }
        });
    }
    
    public void slideOutAction(Group root){
        root.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                slideOut.play();
            }
        });
    }
    
    public void slidesProperties(){
        slideIn.getKeyFrames().addAll(
                new KeyFrame(new Duration(0),
                        new KeyValue(vbox.translateYProperty(),scene.getHeight()),
                        new KeyValue(vbox.opacityProperty(),0.0)
                ),
                new KeyFrame(new Duration(300),
                        new KeyValue(vbox.translateYProperty(),scene.getHeight()-25),
                        new KeyValue(vbox.opacityProperty(),0.9)
                )
        );
        slideOut.getKeyFrames().addAll(
                new KeyFrame(new Duration(0),
                        new KeyValue(vbox.translateYProperty(),scene.getHeight()-25),
                        new KeyValue(vbox.opacityProperty(),0.9)
                ),
                new KeyFrame(new Duration(300),
                        new KeyValue(vbox.translateYProperty(),scene.getHeight()),
                        new KeyValue(vbox.opacityProperty(),0.0)
                )
        );     
    }
    
    public void playerTimeProperty(){
        player.currentTimeProperty().addListener(new ChangeListener<Duration>(){
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration duration, Duration current) {
                timeSlider.setValue(current.toSeconds());
            }
        });
    }
    
    
    public void timeSliderClicProperty(){
        timeSlider.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                player.seek(Duration.seconds(timeSlider.getValue()));
            }
        });
    }
    
    public void playerStopProperty(){
        player.setOnStopped(new Runnable(){
            @Override
            public void run() {
               
            }
            
        });      
    }
    
    public void bindProperty(){
        DoubleProperty mvw = mediaView.fitWidthProperty();
        DoubleProperty mvh = mediaView.fitHeightProperty();
        mvw.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        mvh.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
        mediaView.setPreserveRatio(true);
    }
   
    public Scene setSongOnPlayer(VideoFile videoFile){
        media = new Media(videoFile.getFile().toURI().toString());
        player = null;
        player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        sliderProperties();
        timeSliderClicProperty();
        playerTimeProperty();
        playerEndProperty();
        return scene;
    }
    
    public void setImageBackGround(){
        Image image = new Image("/resources/redSona.gif");
        ImagePattern pattern = new ImagePattern(image);
        scene.setFill(pattern);
    }
    
    
    
    
}
