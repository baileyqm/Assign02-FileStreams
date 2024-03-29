package FileStreams;

import javax.swing.*;
import java.awt.*;

public class RandProductSearch extends JFrame {
    public static void main(String[] args) {
        RandProductSearch runner = new RandProductSearch();
    }

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    public RandProductSearch(){
        JPanel mainPnl = new JPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Product Search");


        add(mainPnl);


        pack();
        setSize((int)(.5*screenSize.width),(int)(.5*screenSize.height));
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
