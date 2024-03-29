package FileStreams;

import javax.swing.*;
import java.awt.*;

public class RandProductMaker extends JFrame {
    public static void main(String[] args) {
        RandProductMaker runner = new RandProductMaker();
    }


    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    public RandProductMaker(){
        JPanel mainPnl = new JPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Product Creator");


        add(mainPnl);


        pack();
        setSize((int)(.5*screenSize.width),(int)(.5*screenSize.height));
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
