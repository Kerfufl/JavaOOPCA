//Main 'driver' code for the project

public class Control
{
    public static void main(String[] args)
    {
        InputScan data = new InputScan("MLdata.csv");
        
        
        //data.Printline();
        data.PrintFactors();
        data.PrintPercent();

        //GUI g = new GUI("bruh", data);
    }
}