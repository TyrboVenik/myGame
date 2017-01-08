package game.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by venik on 08.01.17.
 */
public class GameCanvas extends JPanel {

    private BufferedImage screen;

    public GameCanvas(){
        screen = new BufferedImage(GameFrame.size.width, GameFrame.size.height, BufferedImage.TYPE_INT_ARGB);
        setSize(GameFrame.size);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void render(){
        Graphics2D graphics2D = (Graphics2D)screen.getGraphics();
        graphics2D.drawRect(0,0,100,300);
        getGraphics().drawImage(screen,0,0,null);
    }
}
