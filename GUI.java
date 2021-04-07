//import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
//import javax.swing.JButton;

//Class to generate gui, which will facilitate input/ output of data
public class GUI extends JFrame{

    JPanel panel;
    JTable table;
    JScrollPane pane;
    JComboBox drop;

    static final long serialVersionUID = 0;
    public GUI(String title, InputScan i)
    {
        super(title);
        setSize(900,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        panel = new JPanel(new FlowLayout());
        
        table = new JTable(i.getHold(),i.getTitle());
        
        drop = new JComboBox(new String[] {"bruh","unbruh"});
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        
        pane = new JScrollPane(table);
        //panel.add(pane, BorderLayout.NORTH);
        panel.add(drop, BorderLayout.NORTH);

        add(panel, BorderLayout.NORTH);

        setVisible(true);
    }
    
}
