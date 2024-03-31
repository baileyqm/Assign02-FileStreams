package FileStreams;

import javax.swing.*;
import java.awt.*;
import java.io.File;
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
        setSize((int)(.25*screenSize.width),(int)(.5*screenSize.height));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void generateSearchPnl(){
        searchPnl = new JPanel();
        searchLbl = new JLabel("Search: ");
        searchLbl.setHorizontalAlignment(SwingConstants.CENTER);

        searchPnl.add(searchLbl);

        searchFld = new JTextField(15);
        searchPnl.add(searchFld);
        searchPnl.setPreferredSize(new Dimension(100,100));

    }
    private void generateDisplayPnl(){
        displayPnl = new JPanel(new GridLayout(1,1));
    }
    private void generateCmdPnl(){
        cmdPnl = new JPanel(new GridLayout(1,1));
        JButton quitBtn = new JButton("Quit");
        cmdPnl.setPreferredSize(new Dimension(100,50));
        quitBtn.setFont(new Font("Helvetica",Font.PLAIN,20));
        quitBtn.addActionListener(e -> System.exit(0));
        cmdPnl.add(quitBtn);
    }

}
