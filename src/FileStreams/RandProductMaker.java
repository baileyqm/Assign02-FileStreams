package FileStreams;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class RandProductMaker extends JFrame {
    private BorderLayout mainLayout = new BorderLayout(0,10);
    private JPanel mainPnl = new JPanel(mainLayout);
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
    private JLabel itemCostLbl;
    private JTextField itemCostFld;

    private JPanel cmdPnl;

    private String newItemID;
    private String newItemName;
    private String newItemDesc;
    private String newItemCost;

    private ArrayList<Product> productList = new ArrayList<>();

    private File workingDirectory = new File(System.getProperty("user.dir"));
    private Path path = Paths.get(workingDirectory.getPath() + "/Src/FileStreams/ProductData.bin");

    public static void main(String[] args) {new RandProductMaker();}

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public RandProductMaker(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Product Creator");


        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path.toFile())))
        {
            productList = (ArrayList<Product>) in.readObject();
            count = productList.size();
        }
        catch (ClassNotFoundException p)
        {
            p.printStackTrace();
        }
        catch (IOException p)
        {
            p.printStackTrace();
        }

        add(mainPnl);

        generateCounterPnl();
        mainPnl.add(counterPnl, BorderLayout.NORTH);

        generateAddItemPnl();
        mainPnl.add(addItemPnl, BorderLayout.CENTER);

        generateCmdPnl();
        mainPnl.add(cmdPnl, BorderLayout.SOUTH);

        pack();
        setSize((int)(.5*screenSize.width),(int)(.2*screenSize.height));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void generateCounterPnl(){
        Border margin = new EmptyBorder(10,10,5,10);
        counterPnl = new JPanel(new GridLayout(1,1));

        counterLbl = new JLabel("Item Counter: " + count);
        counterLbl.setHorizontalAlignment(SwingConstants.CENTER);
        counterPnl.setBorder(margin);
        counterPnl.add(counterLbl);
    }

    private void generateAddItemPnl(){
        Border margin = new EmptyBorder(10,10,5,10);
        addItemPnl = new JPanel(new GridLayout(4,2));
        addItemPnl.setBorder(margin);

        itemNumLbl = new JLabel("Item ID # (6 Digits):");
        itemNumLbl.setHorizontalAlignment(SwingConstants.TRAILING);
        itemNumFld= new JTextField(6);
        itemNumFld.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (itemNumFld.getText().length() >= 6 ) // limit textfield to 3 characters
                    e.consume();
            }
        });

        addItemPnl.add(itemNumLbl);
        addItemPnl.add(itemNumFld);

        itemNameLbl = new JLabel("Item Name (35 Character Max): ");
        itemNameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
        itemNameFld = new JTextField(35);
        itemNameFld.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (itemNameFld.getText().length() >= 35 ) // limit textfield to 3 characters
                    e.consume();
            }
        });
        addItemPnl.add(itemNameLbl);
        addItemPnl.add(itemNameFld);

        itemDescLbl = new JLabel("Item Description (75 Character Max):");
        itemDescLbl.setHorizontalAlignment(SwingConstants.TRAILING);
        itemDescFld = new JTextField(75);
        itemDescFld.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (itemDescFld.getText().length() >= 75) // limit textfield to 3 characters
                    e.consume();
            }
        });
        addItemPnl.add(itemDescLbl);
        addItemPnl.add(itemDescFld);

        itemCostLbl = new JLabel("Cost (9 Character Max):");
        itemCostLbl.setHorizontalAlignment(SwingConstants.TRAILING);
        itemCostFld = new JTextField();
        itemCostFld.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (itemCostFld.getText().length() >= 9) // limit textfield to 3 characters
                    e.consume();
            }
        });
        addItemPnl.add(itemCostLbl);
        addItemPnl.add(itemCostFld);

    }

    private void generateCmdPnl(){
        cmdPnl = new JPanel(new GridLayout(1,3));
        JButton addBtn = new JButton("Add a Record");
        JButton removeBtn = new JButton("Remove ALL Records");
        JButton quitBtn = new JButton("Quit");

        addBtn.addActionListener(e ->{
            if(itemNumFld.getText().length() > 0 && itemNameFld.getText().length() > 0 && itemDescFld.getText().length() > 0 && itemCostFld.getText().length() > 0){
                newItemID = itemNumFld.getText();
                newItemName = itemNameFld.getText();
                newItemDesc = itemDescFld.getText();
                newItemCost =  itemCostFld.getText();


                Product currentProd = new Product(newItemID,newItemName,newItemDesc, Double.valueOf(newItemCost));
                productList.add(currentProd);


                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path.toFile())))
                {
                    out.writeObject(productList);
                }
                catch (IOException k)
                {
                    k.printStackTrace();
                }
                count = productList.size();
                counterLbl.setText("Item Counter: " + count);

                itemNumFld.setText("");
                itemNameFld.setText("");
                itemDescFld.setText("");
                itemCostFld.setText("");
            } else{
                 JOptionPane.showMessageDialog(addItemPnl, "One or more field(s) are blank!", "Uh-Oh! Blank Field Alert", JOptionPane.ERROR_MESSAGE);
            }
        });
        cmdPnl.add(addBtn);

        removeBtn.addActionListener(e -> {
            for(int i = productList.size()-1; i > -1; i--) {
                productList.remove(i);
            }

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path.toFile())))
            {
                out.writeObject(productList);
            }
            catch (IOException k)
            {
                k.printStackTrace();
            }

            count = productList.size();
            counterLbl.setText("Item Counter: " + count);
        });
        cmdPnl.add(removeBtn);

        quitBtn.addActionListener(e -> System.exit(0));
        cmdPnl.add(quitBtn);
    }
}
