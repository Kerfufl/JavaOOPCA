//import java.awt.Color;
import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*
Class to generate gui, which will facilitate input/ output of data
Provides a welcome/overview message, a field displaying factors, and input to check instance against data set
Not as fleshed out as I'd hoped, but Swing confused me more than it should have
*/
public class GUI extends JFrame implements ActionListener{
    //Holds class for access to data and methods for GUI
    InputScan scan;
    //Holds strings for comparing to InputScan Methods
    String[] temp;
    //Holds long string to display factors
    String facs,pers;
    //Used for displaying solid percentage
    int acc;
    //Used to toggle gui element
    Boolean vis;

    //Definitions for components
    JPanel top,add,desc;

    JTabbedPane tab;
    JTextArea dyk, perc;
    JLabel welcome,descr,input,accuracy;

    JButton submit;

    JComboBox g,p,j,a,b;
    
    //Removes warning of implementing action listener/ gui in general
    static final long serialVersionUID = 0;
    public GUI(String title, InputScan i)
    {
        super(title);
        //Holds inputScan object for array access
        scan = i;

        setLayout(new BorderLayout());
        setSize(900,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Gets dataset accuracy, then rounds the number
        acc = Math.round(i.accuracy*100);
        
        //Welcome message
        top = new JPanel();
        top.setBackground(Color.BLUE);

        //Panel holding text area of factors
        desc = new JPanel();
        desc.setLayout(new BorderLayout());

        //Panel holding combo box dropdowns fo rinput
        add = new JPanel();
        add.setBackground(Color.LIGHT_GRAY);

        //Sets up welcome message
        welcome = new JLabel("Welcome to the Student-Entrepreneur Prediction-inator");
        welcome.setForeground(Color.white);
        descr = new JLabel("This device will predict if a given student will become an entrepreneur");
        descr.setForeground(Color.white);

        //Informs user of the accuracy of the Naive Bayes dataset
        accuracy = new JLabel();
        accuracy.setText(acc+"% Accuracy reported in the dataset");
        accuracy.setHorizontalAlignment(JLabel.CENTER);

        //Text area holding factors. Bulky, as I couldn't succinctly port this from the other class
        dyk = new JTextArea();
        facs = setFacs();
        dyk.setText(facs);
        dyk.setEditable(false);

        perc = new JTextArea();
        pers = setPers();
        perc.setText(pers);
        perc.setEditable(false);


        tab = new JTabbedPane();
        tab.addTab("Factors", dyk);
        tab.addTab("Percentages", perc);

        //Small message to inform user of input instructions
        input = new JLabel("Please enter the requisite information to discover whether the student will become an entrepreneur");
        input.setHorizontalAlignment(JLabel.CENTER);
        //input.setVerticalAlignment();

        //Combo box, linking ot submit button and function
        submit = new JButton("Submit");
        submit.addActionListener(this);
        g = new JComboBox(new String[] {"Male","Female"});
        p = new JComboBox(new String[] {"Yes","No"});
        j = new JComboBox(new String[] {"Yes","No"});
        a = new JComboBox(new String[] {"Urban","Rural"});
        b = new JComboBox(new String[] {"Yes","No"});
        
        //Adding everything to panels and frame
        top.add(welcome);
        top.add(descr);
        add(top, BorderLayout.NORTH);

        //Ensures components start where they should
        desc.add(accuracy, BorderLayout.PAGE_START);
        desc.add(tab);
        desc.add(input, BorderLayout.SOUTH);
        
        add(desc, BorderLayout.CENTER);
        //add.add(pane, BorderLayout.NORTH);
        add.add(g);
        add.add(p);
        add.add(j);
        add.add(a);
        add.add(b);
        add.add(submit);
        //add.setVisible(false);

        
        add(add,BorderLayout.SOUTH);

        setVisible(true);
        //JOptionPane.showMessageDialog(this, scan.bae.getGivenyes());
    }
    //Updates factors when instances are added, for flavour
    public String setFacs()
    {
        return scan.toString();
    }

    public String setPers()
    {
        return scan.toStringPercent();
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==submit) {
            //Test for inputting scan.getFactors() to check if student becomes entrepreneur
            JOptionPane.showMessageDialog(this,scan.bae.answerString(g.getSelectedIndex(), p
            .getSelectedIndex(), j.getSelectedIndex(), a.getSelectedIndex(),
             b.getSelectedIndex()));
            //Connects the indices into a an array, in order to be added to the list of instances
            temp = new String[] {g.getSelectedItem().toString(),p.getSelectedItem().toString(),
            j.getSelectedItem().toString(), a.getSelectedItem().toString(), 
            b.getSelectedItem().toString(), scan.bae.getEntre()};

            scan.addElement(temp);
            
            //Updates factors in text area
            facs = setFacs();
            dyk.setText(facs);

            pers = setPers();
            perc.setText(pers);
        } 
    }
    
}
