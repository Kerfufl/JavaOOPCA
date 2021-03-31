import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

//Class to read MLdata.csv file, with functions pertaining to such
public class InputScan
{
    File csv;
    Scanner scan;
    private String file;


    private String[] title;
    private String[][] hold = new String[291][6];

    private int total;
    //gender
    private int gm;
    private int gf;

    private float pgm;
    private float pgf;
    //parental business
    private int py;
    private int pn;
    
    private float ppy; 
    private float ppn;
    //part time job
    private int jy;
    private int jn;

    private float pjy; 
    private float pjn;

    //address
    private int au;
    private int ar;

    private float pau; 
    private float par;
    //business studies
    private int by;
    private int bn;

    private float pby; 
    private float pbn;
    //integers tracking number of students who became entrepreneurs
    private int ey;
    private int en;

    private float pey;
    private float pen; 
    //Instantiates the file name
    public InputScan(String file)
    {
        this.file = file;
        title = new String[] {"Gender","Parent/Guardian","Part-time Job","Urban/Rural","Studies Business","Entrepreneur"};

        //To catch FileNotFoundError
        try
        {
            File csv = new File(file);
            Scanner scan = new Scanner(csv);
            
            //Temp array to then transfer to 2D array later
            String[] temp = new String[6];
            
            //Nested loop to split dataset into accessible chunks
            for(int i=0;i<291;i++)
            {
                //Prevents first three lines from being accessed
                if(i>2)
                {
                    temp = scan.nextLine().split(",\\s*");
                    //Checks for parameters, and increments relevant variable
                    if(temp[0].equals("Male"))
                    {
                        gm++;
                    } else if(temp[0].equals("Female"))
                    {
                        gf++;
                    }

                    if(temp[1].equals("Yes"))
                    {
                        py++;
                    } else if(temp[1].equals("No"))
                    {
                        pn++;
                    }

                    if(temp[2].equals("Yes"))
                    {
                        jy++;
                    } else if(temp[2].equals("No"))
                    {
                        jn++;
                    }

                    if(temp[3].equals("Urban"))
                    {
                        au++;
                    } else if(temp[3].equals("Rural"))
                    {
                        ar++;
                    }

                    if(temp[4].equals("Yes"))
                    {
                        by++;
                    } else if(temp[4].equals("No"))
                    {
                        bn++;
                    }

                    if(temp[5].equals("Yes"))
                    {
                        ey++;
                    } else if(temp[5].equals("No"))
                    {
                        en++;
                    }
                    for(int j=0;j<temp.length;j++)
                    {
                        hold[(i-3)][j]= temp[j];
                        //System.out.println(hold[1][1]);
                    }

                    total++;
                } else {
                    //Skips line to get to relevant data
                    scan.nextLine();
                }
                
            }
            //Closes scanner to prevent resource leakage
            scan.close();
            //Calculates percentages for later use
            pey = (float)ey / (float)total;
            pen = (float)en / (float)total;

            pgm = (float)gm / (float)total;
            pgf = (float)gf / (float)total;

            ppy = (float)py / (float)total;
            ppn = (float)pn / (float)total;

            pjy = (float)jy / (float)total;
            pjn = (float)jn / (float)total;

            pau = (float)au / (float)total;
            par = (float)ar / (float)total;

            pby = (float)by / (float)total;
            pbn = (float)bn / (float)total;
            System.out.println(pey + " " + pen);
            
        } catch(FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
    }
    
    //Defunct function to debug file reading, now integrated into constructor 
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
            for(int i=0;i<103;i++)
            {
                //Prevents first three lines from being accessed
                if(i>2)
                {
                    temp = scan.nextLine().split(",\\s*");

                    if(temp[5].equals("Yes"))
                    {
                        ey++;
                    } else if(temp[5].equals("No"))
                    {
                        en++;
                    }
                    for(int j=0;j<temp.length;j++)
                    {
                        hold[(i-3)][j]= temp[j];
                        //System.out.println(hold[1][1]);
                    }

                    total++;
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

    //Debug function for checking array capabilities 
    public void PrintFactors()
    {
        System.out.println("Total: "+total+"  Entrepreneurs: "+ey+"  Not Entrepreneurs: "+en);
        System.out.println("Males: "+gm+"  Females: "+gf);
        System.out.println("Parents owned business: "+py+"  Parents didn't own business: "+pn);
        System.out.println("Has part-time job: "+jy+"  Not Entrepreneurs: "+jn);
        System.out.println("Urban: "+au+"  Rural: "+ar);
        System.out.println("Studies business: "+by+"  Does not study business: "+bn);
        System.out.println("");
        /*
        //Iterates through each 'row'
        for(int i=0;i<100;i++)
        {
            //Prints the factors of a row on a single line
            for(int j=0;j<6;j++)
            {
                System.out.print(hold[i][j]+" ");
            }
            //Prints new line
            System.out.println("");
            
        }
        */
    }

    public void PrintPercent()
    {
        System.out.println("Total: "+total+"  Entrepreneurs: "+pey+"  Not Entrepreneurs: "+pen);
        System.out.println("Males: "+pgm+"  Females: "+pgf);
        System.out.println("Parents owned business: "+ppy+"  Parents didn't own business: "+ppn);
        System.out.println("Has part-time job: "+pjy+"  Not Entrepreneurs: "+pjn);
        System.out.println("Urban: "+pau+"  Rural: "+par);
        System.out.println("Studies business: "+pby+"  Does not study business: "+pbn);
        
    }

    //Get/Sets for private variables

    public String getFile() {
        return this.file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String[] getTitle() {
        return this.title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }


    public String[][] getHold() {
        return this.hold;
    }

    public void setHold(String[][] hold) {
        this.hold = hold;
    }

    public int getEy() {
        return this.ey;
    }

    public void setEy(int ey) {
        this.ey = ey;
    }

    public int getEn() {
        return this.en;
    }

    public void setEn(int en) {
        this.en = en;
    }



}
