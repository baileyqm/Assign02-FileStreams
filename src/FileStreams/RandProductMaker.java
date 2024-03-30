package FileStreams;

import javax.swing.*;
import java.awt.*;

public class RandProductMaker extends JFrame {
    private JPanel mainPnl = new JPanel(new BorderLayout());
    private JPanel counterPnl;
    private JLabel counterLbl;
    private int count = 0;

    private JPanel addItemPnl;
    private JLabel itemNumLbl;
    private JTextField itemNumFld;
    private JLabel itemNameLbl;
    private JTextField itemNameFld;
    private JLabel itemDescLbl;
    private JTextField itemDescFld;

    public static void main(String[] args) {
        RandProductMaker runner = new RandProductMaker();
    }

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public RandProductMaker(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Product Creator");


        add(mainPnl);

        generateCounterPnl();
        mainPnl.add(counterPnl, BorderLayout.NORTH);

        generateAddItemPnl();
        mainPnl.add(addItemPnl, BorderLayout.CENTER);


        pack();
        setSize((int)(.5*screenSize.width),(int)(.5*screenSize.height));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void generateCounterPnl(){
        counterPnl = new JPanel(new GridLayout(1,1));
        counterLbl = new JLabel("Item Counter: " + count);

        counterPnl.add(counterLbl);
    }

    private void generateAddItemPnl(){
        addItemPnl = new JPanel(new GridLayout(3,2));

        itemNumLbl = new JLabel("Item ID #:");
        itemNumFld = new JTextField(6);
        addItemPnl.add(itemNumLbl);
        addItemPnl.add(itemNumFld);

        itemNameLbl = new JLabel("Item Name: ");
        itemNameFld = new JTextField(35);
        addItemPnl.add(itemNameLbl);
        addItemPnl.add(itemNameFld);

        itemDescLbl = new JLabel("Item Description:");
        itemDescFld = new JTextField(75);

    }
}
