//Main 'driver' code for the project

import javax.swing.SwingUtilities;

public class Control
{
    public static void main(String[] args)
    {
        InputScan data = new InputScan("MLdata.csv");
        
        
        data.Printline();
        //data.printFactors();
        GUI g = new GUI("bruh", data);
    }
}