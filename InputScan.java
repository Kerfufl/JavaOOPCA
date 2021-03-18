import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

//Class to read MLdata file, with functions pertaining to such
public class InputScan
{
    private String file;
    private String[] factors;
    
    //Only need to instantiate the file name
    public InputScan(String file)
    {
        this.file = file;
    }
    //Function to debug file reading 
    public void Printline()
    {
        try
        {
            File csv = new File(file);
            Scanner scan = new Scanner(csv);
            int i=0;
            for(i=0;i<10;i++)
            {
                if(i>3)
                {
                    System.out.println(scan.nextLine());
                } else {
                    scan.nextLine();
                }
            }

            //System.out.println(scan.nextLine());
            
            scan.close();


        } catch(FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
    }

    public String getFile() {
        return this.file;
    }

    public void setFile(String file) {
        this.file = file;
    }


}
