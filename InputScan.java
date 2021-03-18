import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

//Class to read MLdata.csv file, with functions pertaining to such
public class InputScan
{
    File csv;
    Scanner scan;
    private String file;
    String[][] hold = new String[10][10];
    //Only need to instantiate the file name
    public InputScan(String file)
    {
        this.file = file;
    }
    //Function to debug file reading 
    public void Printline()
    {
        //To catch FileNotFoundError
        try
        {
            File csv = new File(file);
            Scanner scan = new Scanner(csv);
            
            //Temp array to then transfer to 2D array later
            String[] temp = new String[6];
            
            //Nested loop to split dataset into accessible chunks
            for(int i=0;i<10;i++)
            {
                //Prevents first three lines from being accessed
                if(i>2)
                {
                    temp = scan.nextLine().split(",\\s*");
                    for(int j=0;j<temp.length;j++)
                    {
                        hold[(i-3)][j]= temp[j];
                        //System.out.println(hold[1][1]);
                    }
                } else {
                    //Skips line to get to relevant data
                    scan.nextLine();
                }
                
            }
            //Closes scanner to prevent resource leakage
            scan.close();
            
        } catch(FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
    }

    //Debug function for checking arraylist capabilities 
    public void printFactors()
    {
        for(int i=0;i<7;i++)
        {
            for(int j=0;j<6;j++)
            {
                System.out.print(hold[i][j]+" ");
            }
            System.out.println("");
        }
    }

    public String getFile() {
        return this.file;
    }

    public void setFile(String file) {
        this.file = file;
    }


}
