/*
 * Scrolling text ticker
 * Author: Joey Cargill
 */
package scrollingpanel;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.util.List;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;

import javax.swing.*;

public class marquee {
    public static String quote = "";

    public static void main(String[] args) {
        FileInputStream fis = null;
        BufferedReader reader = null;

        List<String> quotes = new ArrayList<String>();

        try {
            String workingDir = new File(".").getAbsolutePath();
            fis = new FileInputStream(workingDir + "/ticker.txt");
            reader = new BufferedReader(new InputStreamReader(fis));

            //System.out.println("Reading File line by line using BufferedReader");

            String line = reader.readLine();
            while (line != null && line.length() != 0) {
                System.out.println(line);
                line = reader.readLine();
                if (line != null) {
                    quotes.add(line + "                                                            ");
                }
            }

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {

            }
        }

        for (String tmp : quotes) {
            quote += tmp;
        }
        //static String quote = str;
        MyFrame frame = new MyFrame();
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setBackground(Color.white);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(0, d.height - 35);
        frame.setVisible(true);
    }
}

class MyFrame extends JFrame implements ActionListener {
    private ActionListener listener;
    private Timer t1;

    public MyFrame() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(d.width, 35);
        setTitle("MARQUEE");
        MyPanel panel = new MyPanel();
        add(panel);
        listener = this;
        t1 = new Timer(20, listener);
        t1.start();
    }

    public void actionPerformed(ActionEvent event) {
        repaint();
    }

}

class MyPanel extends JPanel {
    private int x, y;
    private Dimension d;

    public MyPanel() {
        d = Toolkit.getDefaultToolkit().getScreenSize();
        x = d.width - 200;
        y = 25;

    }

    public void paintComponent(Graphics g) {


        Color nxOrg = new Color(0xEF9703);
        Font font = new Font("Arial", Font.BOLD, 22);
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        FontMetrics fm = img.getGraphics().getFontMetrics(font);
        int width = fm.stringWidth(marquee.quote);
        x -= 1;
        if (x < -width)
            x = d.width;
        Graphics2D g2 = (Graphics2D) g;

        g2.setFont(font);
        g2.setPaint(nxOrg);

        g2.drawString(marquee.quote, x, y);


    }
}
