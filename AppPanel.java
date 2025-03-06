import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AppPanel extends JPanel {
    Timer timer;

    JButton playPauseButton;
    JButton nextSongButton;
    JButton previousSongButton;

    BufferedImage songPhoto;

    List<SongModel> songList;
    int currentSongIndex = 0;

    AppPanel(List<SongModel> songList)
    {
        this.songList = songList;
        setSize(500, 500);

        previousSongButton = new JButton("Previous");
        playPauseButton = new JButton("Play/Pause");
        nextSongButton = new JButton("Next");


        add(previousSongButton);
        add(playPauseButton);
        add(nextSongButton);


        loadPhoto();
        appLoop();
        setFocusable(true);

        playPauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSong();
            }
        });

        nextSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextSong();
            }
        });

         previousSongButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 previousSong();
             }
         });
    }

    void loadPhoto() {
        try {
            songPhoto = ImageIO.read(new URL(songList.get(currentSongIndex).songImageUrl));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void appLoop() {
        timer = new Timer(80, (abc) -> {
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics pen) {
        super.paintComponent(pen);
        pen.setColor(Color.BLACK);
        pen.setFont(new Font("Arial", Font.BOLD, 25));
        pen.drawString(songList.get(currentSongIndex).songName, 120, 330);
        pen.drawString(songList.get(currentSongIndex).singerName, 120, 380);
        pen.drawImage(songPhoto, 120, 100, 200, 200, null);
    }
    void nextSong(){
        currentSongIndex = (currentSongIndex + 1) % songList.size();
        loadPhoto();
    }

    void previousSong(){
        currentSongIndex = (currentSongIndex -1 + songList.size()) % songList.size();
     loadPhoto();
    }


    void playSong(){

//        Platform.startup(()->{});
        String songUrl = songList.get(currentSongIndex).songUrl;
        String songName = songList.get(currentSongIndex).songName;
        System.out.println("Playing " + songName);
        if (!songUrl.equals("No Url Found")) {
            Platform.runLater(() -> {
                Media media = new Media(songUrl);
                MediaPlayer mediaPlayer = new MediaPlayer(media);
              mediaPlayer.play();
           });
       } else {
            System.out.println("No URL found for this song.");
        }

    }
}