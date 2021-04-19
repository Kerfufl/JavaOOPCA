//Main 'driver' code for the project
//Some Debug is available, but otherwise, only instantiates classes to drive the program
public class Control
{
    public static void main(String[] args)
    {
        //Instantiates class with file name and percentage of data for accuracy testing
        //Default is 70% of data used for building
        InputScan data = new InputScan("MLdata.csv",.7f);
        
        //Debugging in terminal
        //data.printLine();
        //data.printFactors();
        //data.printHolding();
        //data.printTesting();
        //data.printPercent();
        //System.out.println(data.toString());
        //Inititates gui with title and access to two vital classes
        GUI g = new GUI("Assignment", data);
    }
}