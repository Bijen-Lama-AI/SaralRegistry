/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.*;

/**
 *
 * @author bijen
 */
public class BackgroundPanel extends JPanel {
    private Image backgroundImage;
    private ScaleMode scaleMode;
    
    public enum ScaleMode {
        FILL, FIT, TILE, CENTER
    }
    
    public BackgroundPanel(String imagePath, ScaleMode scaleMode) {
        this.scaleMode = scaleMode;
        setOpaque(true);
        try {
            this.backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public BackgroundPanel(String imagePath) {
        this(imagePath, ScaleMode.FILL);
    }
    
    public BackgroundPanel() {
        this.scaleMode = ScaleMode.FILL;
        setOpaque(true);
    }
    
    public void setBackgroundImage(String imagePath) {
        try {
            this.backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setScaleMode(ScaleMode scaleMode) {
        this.scaleMode = scaleMode;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (backgroundImage == null) {
            return;
        }
        
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int imgWidth = backgroundImage.getWidth(this);
        int imgHeight = backgroundImage.getHeight(this);
        
        switch (scaleMode) {
            case FILL:
                g2d.drawImage(backgroundImage, 0, 0, panelWidth, panelHeight, this);
                break;
                
            case FIT:
                double scaleX = (double) panelWidth / imgWidth;
                double scaleY = (double) panelHeight / imgHeight;
                double scale = Math.min(scaleX, scaleY);
                
                int scaledWidth = (int) (imgWidth * scale);
                int scaledHeight = (int) (imgHeight * scale);
                int x = (panelWidth - scaledWidth) / 2;
                int y = (panelHeight - scaledHeight) / 2;
                
                g2d.drawImage(backgroundImage, x, y, scaledWidth, scaledHeight, this);
                break;
            
            case TILE:
                for (int i = 0; i < panelWidth; i += imgWidth) {
                    for (int j = 0; j < panelHeight; j += imgHeight) {
                        g2d.drawImage(backgroundImage, i, j, this);
                    }
                }
                break;
                
            case CENTER:
                int centerX = (panelWidth - imgWidth) / 2;
                int centerY = (panelHeight - imgHeight) / 2;
                g2d.drawImage(backgroundImage, centerX, centerY, this);
                break;
        }
    }
}
