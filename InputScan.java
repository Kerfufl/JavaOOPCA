import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class InputScan
{
    private String file;
    

    public InputScan(String file)
    {
        this.file = file;
    }

    public void Printline()
    {
        try
        {
            File csv = new File(file);
            Scanner scan = new Scanner(csv);

            System.out.println(scan.nextLine());
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
