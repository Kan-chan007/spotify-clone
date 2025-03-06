import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AppFrame extends JFrame{
    AppFrame(ArrayList<SongModel> songList){
        setTitle("Media Player");
        setSize(500,500);
        setBackground(Color.LIGHT_GRAY);
        setLocationRelativeTo(null);
       AppPanel  aPanel = new AppPanel(songList);

        add(aPanel);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}