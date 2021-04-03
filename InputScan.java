import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

//Class to read MLdata.csv file, with functions pertaining to such
public class InputScan
{
    File csv;
    Scanner scan;
    private String file;

    //Holds headings for table in gui
    private String[] title;

    //Stores data from csv
    private String[][] hold = new String[291][6];

    /*Stores factors for
        0: Total
        1: Entrepreneur==yes
        2: Entrepreneur==no
        [12][3], stores 1/0s together for easier access
        eg [1-2][0] stores genders overall etc
    */
    private int[][] factors;
    //Similar to above but for percentages
    private float[][] percent;

    private int total;
    
    
    //Differentiates between entrepreneurs and not-entrepreneurs
    private int yn;
    //Instantiates the file name
    public InputScan(String file)
    {
        this.file = file;
        title = new String[] {"Gender","Parent/Guardian","Part-time Job","Urban/Rural","Studies Business","Entrepreneur"};

        factors = new int[12][3];
        percent = new float[12][3];

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
                        factors[0][0]++;
                    } else if(temp[0].equals("Female"))
                    {
                        factors[1][0]++;
                    }

                    if(temp[1].equals("Yes"))
                    {
                        factors[2][0]++;
                    } else if(temp[1].equals("No"))
                    {
                        factors[3][0]++;
                    }

                    if(temp[2].equals("Yes"))
                    {
                        factors[4][0]++;
                    } else if(temp[2].equals("No"))
                    {
                        factors[5][0]++;
                    }

                    if(temp[3].equals("Urban"))
                    {
                        factors[6][0]++;
                    } else if(temp[3].equals("Rural"))
                    {
                        factors[7][0]++;
                    }

                    if(temp[4].equals("Yes"))
                    {
                        
                        factors[8][0]++;
                    } else if(temp[4].equals("No"))
                    {
                        factors[9][0]++;
                    }

                    if(temp[5].equals("Yes"))
                    {
                        factors[10][0]++;
                        //Sets function to increment if 
                        yn = 1;
                    } else if(temp[5].equals("No"))
                    {
                        factors[11][0]++;

                        yn = 2;
                    }
                    for(int j=0;j<temp.length;j++)
                    {
                        hold[(i-3)][j]= temp[j];
                    }
                    //Increments factors whether student is entrepreneur or not
                    given(temp,yn);
                    //Increments total number of students
                    total++;
                } else {
                    //Skips line to get to relevant data
                    scan.nextLine();
                }
                
            }
            //Closes scanner to prevent resource leakage
            scan.close();
            //Calculates percentages for later use
            /*
            pgm = (float)factors[0][0] / (float)total;
            pgf = (float)factors[1][0] / (float)total;

            ppy = (float)factors[2][0] / (float)total;
            ppn = (float)factors[3][0] / (float)total;

            pjy = (float)factors[4][0] / (float)total;
            pjn = (float)factors[5][0] / (float)total;

            pau = (float)factors[6][0] / (float)total;
            par = (float)factors[7][0] / (float)total;

            pby = (float)factors[8][0] / (float)total;
            pbn = (float)factors[9][0] / (float)total;

            pey = (float)factors[10][0] / (float)total;
            pen = (float)factors[11][0] / (float)total;
            System.out.println(pey + " " + pen);
            */
            //More succinct loop for calculating probabilities
            for (int i=0;i<12;i++)
            {
                percent[i][0] = (float)factors[i][0]/ (float)total;
            }
            int h=10;
            for(int j=1;j<3;j++)
            {
                for (int i=0;i<12;i++)
                {
                    percent[i][j] = (float)factors[i][j]/ (float)factors[h][j];
                }
                h++;
            }
            
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
                        
                    } else if(temp[5].equals("No"))
                    {
                        
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

    //Increments factors whether student becomes entrepreneur, declutters init function
    private void given(String[] temp, int yn)
    {
        if(temp[0].equals("Male"))
                    {
                        factors[0][yn]++;
                    } else if(temp[0].equals("Female"))
                    {
                        factors[1][yn]++;
                    }

                    if(temp[1].equals("Yes"))
                    {
                        factors[2][yn]++;
                    } else if(temp[1].equals("No"))
                    {
                        factors[3][yn]++;
                    }

                    if(temp[2].equals("Yes"))
                    {
                        factors[4][yn]++;
                    } else if(temp[2].equals("No"))
                    {
                        factors[5][yn]++;
                    }

                    if(temp[3].equals("Urban"))
                    {
                        factors[6][yn]++;
                    } else if(temp[3].equals("Rural"))
                    {
                        factors[7][yn]++;
                    }

                    if(temp[4].equals("Yes"))
                    {
                        factors[8][yn]++;
                    } else if(temp[4].equals("No"))
                    {
                        
                        factors[9][yn]++;
                    }

                    if(temp[5].equals("Yes"))
                    {
                        factors[10][yn]++;
                        //Sets function to increment if 
                        yn = 1;
                    } else if(temp[5].equals("No"))
                    {
                        factors[11][yn]++;

                        yn = 2;
                    }
    }

    //Debug function for checking array capabilities 
    public void PrintFactors()
    {
        System.out.println("Total: "+total+"  Entrepreneurs: "+factors[10][0]+"  Not Entrepreneurs: "+factors[11][0]);
        System.out.println("Males: "+factors[0][0]+"  Females: "+factors[1][0]);
        System.out.println("Parents owned business: "+factors[2][0]+"  Parents didn't own business: "+factors[3][0]);
        System.out.println("Has part-time job: "+factors[4][0]+"  No job: "+factors[5][0]);
        System.out.println("Urban: "+factors[6][0]+"  Rural: "+factors[7][0]);
        System.out.println("Studies business: "+factors[8][0]+"  Does not study business: "+factors[9][0]);
        System.out.println("");

        System.out.println("Entreprenur==yes");
        System.out.println("Males: "+factors[0][1]+"  Females: "+factors[1][1]);
        System.out.println("Parents owned business: "+factors[2][1]+"  Parents didn't own business: "+factors[3][1]);
        System.out.println("Has part-time job: "+factors[4][1]+"  No job: "+factors[5][1]);
        System.out.println("Urban: "+factors[6][1]+"  Rural: "+factors[7][1]);
        System.out.println("Studies business: "+factors[8][1]+"  Does not study business: "+factors[9][1]);
        System.out.println("");

        System.out.println("Entreprenur==no");
        System.out.println("Males: "+factors[0][2]+"  Females: "+factors[1][2]);
        System.out.println("Parents owned business: "+factors[2][2]+"  Parents didn't own business: "+factors[3][2]);
        System.out.println("Has part-time job: "+factors[4][2]+"  No job: "+factors[5][2]);
        System.out.println("Urban: "+factors[6][2]+"  Rural: "+factors[7][2]);
        System.out.println("Studies business: "+factors[8][2]+"  Does not study business: "+factors[9][2]);
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
        System.out.println("Total: "+total+"  Entrepreneurs: "+percent[10][0]+"  Not Entrepreneurs: "+percent[11][0]);
        for(int i=0;i<3;i++)
        {
        System.out.println("Male: "+percent[0][i]+"  Female: "+percent[1][i]);
        System.out.println("Parents owned business: "+percent[2][i]+"  Parents didn't own business: "+percent[3][i]);
        System.out.println("Has part-time job: "+percent[4][i]+"  No job: "+percent[5][i]);
        System.out.println("Urban: "+percent[6][i]+"  Rural: "+percent[7][i]);
        System.out.println("Studies business: "+percent[8][i]+"  Does not study business: "+percent[9][i]);
        System.out.println("");
        }
        

        
        
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

    public int[][] getFactors() {
        return this.factors;
    }

    public void setFactors(int[][] factors) {
        this.factors = factors;
    }

    public String[][] getHold() {
        return this.hold;
    }

    public void setHold(String[][] hold) {
        this.hold = hold;
    }



}
