//Main 'driver' code for the project

public class Control
{
    public static void main(String[] args)
    {
        InputScan data = new InputScan("MLdata.csv");
        //data.printLine();
        //data.printFactors();
        data.printPercent();
        
        //System.out.println(data.bae.calculateYes());
        //System.out.println(data.bae.calculateNo());

        //GUI g = new GUI("bruh", data);
    }
}