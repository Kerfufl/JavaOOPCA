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

    String[] temp;

    JPanel add;
    JPanel desc;
    JPanel bruh;
    JTable table;
    JScrollPane pane;

    JButton reveal;
    JButton submit;

    JComboBox g;
    JComboBox p;
    JComboBox j;
    JComboBox a;
    JComboBox b;
    //Used to toggle gui element
    Boolean vis;

    static final long serialVersionUID = 0;
    public GUI(String title, InputScan i)
    {
        super(title);
        //Holds inputScan object for array access
        scan = i;
        setSize(900,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        vis= false;

        desc = new JPanel(new FlowLayout());

        add = new JPanel(new FlowLayout());
        
        //table = new JTable(scan.getHold(),scan.getTitle());
        reveal = new JButton("reveal");
        reveal.addActionListener(this);

        submit = new JButton("Submit");
        submit.addActionListener(this);
        g = new JComboBox(new String[] {"Male","Female"});
        p = new JComboBox(new String[] {"Yes","No"});
        j = new JComboBox(new String[] {"Yes","No"});
        a = new JComboBox(new String[] {"Urban","Rural"});
        b = new JComboBox(new String[] {"Yes","No"});
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        desc.add(reveal);
        add(desc, BorderLayout.NORTH);
        
        pane = new JScrollPane(table);
        //add.add(pane, BorderLayout.NORTH);
        add.add(g, BorderLayout.NORTH);
        add.add(p, BorderLayout.NORTH);
        add.add(j, BorderLayout.NORTH);
        add.add(a, BorderLayout.NORTH);
        add.add(b, BorderLayout.NORTH);
        add.add(submit,BorderLayout.NORTH);
        add.setVisible(false);

        
        add(add, BorderLayout.SOUTH);

        setVisible(true);
        //JOptionPane.showMessageDialog(this, scan.bae.getGivenyes());
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==submit)
        {
            //Test for inputting factors to check if student becomes entrepreneur
            JOptionPane.showMessageDialog(this,scan.bae.answer(g.getSelectedIndex(), p
            .getSelectedIndex(), j.getSelectedIndex(), a.getSelectedIndex(),
             b.getSelectedIndex()));

            temp = new String[] {g.getSelectedItem().toString(),p.getSelectedItem().toString(),
            j.getSelectedItem().toString(), a.getSelectedItem().toString(), 
            b.getSelectedItem().toString(), scan.bae.getEntre()};

            scan.addElement(temp);

            //scan.printHold();
        } else if(e.getSource()==reveal)
        {
            vis = !vis;
            add.setVisible(vis);
        }
    }
    
}
