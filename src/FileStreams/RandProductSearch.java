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
    private JScrollPane scroller = new JScrollPane(displayArea);
    private String searchStr = "";



    public static void main(String[] args) {
        RandProductSearch runner = new RandProductSearch();
    }

    public RandProductSearch(){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path.toFile())))
        {
            productList = (ArrayList<Product>) in.readObject();
            for(Product k : productList){
                k.setName(k.getName().trim());
                k.setDescription(k.getDescription().trim());
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
        setSize((int)(.65*screenSize.width),(int)(.5*screenSize.height));
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
                displayArea.setText(String.format("%-35s%-75s%-9s","Name","Description","Price") + "\n"+String.format("%-119s","=========================================================================================================================================\n"));
                if(e.getKeyCode() == 8 && !searchStr.isEmpty()){
                    searchStr = searchStr.substring(0,searchStr.length()-1);
                } else if(e.getKeyCode() != 8) {
                    searchStr += e.getKeyChar();
                }
                System.out.println(searchStr);
                for(Product p: productList){
                    if(p.getName().toLowerCase().contains(searchStr.toLowerCase()) && !searchStr.isBlank()){
                        //displayArea.append(p.getName()+"\t"+p.getDescription()+"\t$"+p.getCost()+"\n");
                        displayArea.append(String.format("%-35s%-76s%-9.2f",p.getName(),p.getDescription(),p.getCost())+"\n");
                    }
                }
            }
            });

    }
    private void generateDisplayPnl(){
        displayPnl = new JPanel(new GridLayout(1,1));
        displayArea.setFont(new Font("Courier New", Font.PLAIN, 14));
        displayArea.setEditable(false);
        displayArea.setText(String.format("%-35s%-75s%-9s","Name","Description","Price") + "\n"+String.format("%-119s","=========================================================================================================================================\n"));



        displayPnl.add(scroller);
    }
    private void generateCmdPnl(){
        cmdPnl = new JPanel(new GridLayout(1,1));
        JButton quitBtn = new JButton("Quit");
        cmdPnl.setPreferredSize(new Dimension(100,40));
        quitBtn.setFont(new Font("Helvetica",Font.PLAIN,20));
        quitBtn.addActionListener(e -> System.exit(0));
        cmdPnl.add(quitBtn);
    }

}
