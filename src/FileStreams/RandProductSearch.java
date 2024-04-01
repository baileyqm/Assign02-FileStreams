package FileStreams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class RandProductSearch extends JFrame {
    private ArrayList<Product> productList = new ArrayList<>();
    private File workingDirectory = new File(System.getProperty("user.dir"));
    private Path path = Paths.get(workingDirectory.getPath() + "/Src/FileStreams/ProductData.bin");
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JPanel searchPnl, displayPnl, cmdPnl;
    private JLabel searchLbl;
    private JTextField searchFld;
    private JTextArea displayArea = new JTextArea();



    public static void main(String[] args) {
        RandProductSearch runner = new RandProductSearch();
    }

    public RandProductSearch(){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path.toFile())))
        {
            productList = (ArrayList<Product>) in.readObject();
            for(Product k : productList){
                System.out.println(k);
            }
        }
        catch (ClassNotFoundException p)
        {
            p.printStackTrace();
        }
        catch (IOException p)
        {
            p.printStackTrace();
        }

        JPanel mainPnl = new JPanel(new BorderLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Product Search");

        add(mainPnl);

        generateSearchPnl();
        mainPnl.add(searchPnl, BorderLayout.NORTH);

        generateDisplayPnl();
        mainPnl.add(displayPnl);

        generateCmdPnl();
        mainPnl.add(cmdPnl, BorderLayout.SOUTH);

        pack();
        setSize((int)(.35*screenSize.width),(int)(.5*screenSize.height));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void generateSearchPnl(){
        searchPnl = new JPanel();
        searchLbl = new JLabel("Search:");
        searchLbl.setHorizontalAlignment(SwingConstants.CENTER);

        searchPnl.add(searchLbl);

        searchFld = new JTextField(15);
        searchPnl.add(searchFld);
        searchPnl.setPreferredSize(new Dimension(100,45));
        searchFld.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                displayArea.setText("Item\t\tDesc\t\t\t\tPrice\n" + "===========================================================\n");
                System.out.println(e.getKeyChar());
                for(Product p: productList){
                    if(p.getName( ).toLowerCase().contains(searchFld.getText().toLowerCase())){
                        displayArea.append(p.getName()+"\t\t"+p.getDescription()+"\t\t\t$"+p.getCost()+"\n");
                    }
                }
            }
            });

    }
    private void generateDisplayPnl(){
        displayPnl = new JPanel(new GridLayout(1,1));
        JTextArea displayArea = new JTextArea(20,20);
        displayArea.setFont(new Font("Courier New", Font.PLAIN, 18));
        displayArea.setText("Item\t\tDesc\t\t\t\tPrice\n" + "===========================================================\n");


        JScrollPane scroller = new JScrollPane(displayArea);

        displayPnl.add(scroller);
    }
    private void generateCmdPnl(){
        cmdPnl = new JPanel(new GridLayout(1,1));
        JButton quitBtn = new JButton("Quit");
        cmdPnl.setPreferredSize(new Dimension(100,25));
        quitBtn.setFont(new Font("Helvetica",Font.PLAIN,20));
        quitBtn.addActionListener(e -> System.exit(0));
        cmdPnl.add(quitBtn);
    }

}
