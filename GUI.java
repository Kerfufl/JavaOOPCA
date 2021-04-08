//import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;

//Class to generate gui, which will facilitate input/ output of data
public class GUI extends JFrame implements ActionListener{

    InputScan scan;

    JPanel panel;
    JTable table;
    JScrollPane pane;
    JButton button;
    JComboBox g;
    JComboBox p;
    JComboBox j;
    JComboBox a;
    JComboBox b;
    

    static final long serialVersionUID = 0;
    public GUI(String title, InputScan i)
    {
        super(title);
        scan = i;
        setSize(900,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        panel = new JPanel(new FlowLayout());
        
        table = new JTable(scan.getHold(),scan.getTitle());
        
        button = new JButton("Submit");
        button.addActionListener(this);
        g = new JComboBox(new String[] {"Male","Female"});
        p = new JComboBox(new String[] {"Yes","No"});
        j = new JComboBox(new String[] {"Yes","No"});
        a = new JComboBox(new String[] {"Urban","Rural"});
        b = new JComboBox(new String[] {"Yes","No"});
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        
        pane = new JScrollPane(table);
        //panel.add(pane, BorderLayout.NORTH);
        panel.add(g, BorderLayout.NORTH);
        panel.add(p, BorderLayout.NORTH);
        panel.add(j, BorderLayout.NORTH);
        panel.add(a, BorderLayout.NORTH);
        panel.add(b, BorderLayout.NORTH);

        add(panel, BorderLayout.NORTH);

        setVisible(true);
        JOptionPane.showMessageDialog(this, scan.bae.getGivenyes());
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==button)
        {
            JOptionPane.showMessageDialog(this, scan.bae.getGivenyes());
        }
    }
    
}
