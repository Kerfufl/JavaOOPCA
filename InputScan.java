import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.Arrays;
import java.util.ArrayList;


/*
Class to read MLdata.csv file, with functions pertaining to such
Reads the csv, creates dataset for factors
Tests the built data, adding to set with an accuracy ratio
Passes on stored data, such as the factors and their percentage chance, to Bayes class 
*/
public class InputScan
{
    Bayes bae;
    File csv;
    Scanner scan;
    private String file;
    //Used for dividing file between build and test data
    float flen;
    //Represents percentage of data used to build model
    float per;
    int build;
    
    //Floats used in the review of test data, counting the correct guesses
    float correct;
    //and storing total accuracy for use in gui
    float accuracy;

    //Stores data from csv, used for future additions to set
    private ArrayList<String[]> holding;
    //Stores data meant to test data set
    private ArrayList<String[]> testing;


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

    //Total of students, vital in calculating percentages later
    private int total;
    
    
    //Differentiates between entrepreneurs and not-entrepreneurs
    private int yn;
    //Instantiates the file name
    public InputScan(String file, float per)
    {
        this.file = file;
        //Sets length of the file
        flen = 291;
        //70% of data is used to 'build'
        this.per=per;

        //Sets percentage of build data as int, to be used in loops
        build=Math.round(flen*per);

        //System.out.println(build);
        holding = new ArrayList<String[]>();
        testing = new ArrayList<String[]>();
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
            for(int i=0;i<flen;i++)
            {
                //Prevents first three lines from being accessed
                if(i>2)
                {
                    temp = scan.nextLine().split(",\\s*");
                    //Goes up to 'build' portion of dataset, adding to factors
                    if(i<build) 
                    {
                        //Succinctly increments factors, to calculate necessary percentages 
                        factorise(temp);
                    //Places rest of factors aside to test dataset later
                    } else if(i>build) {
                        testing.add(temp);
                    }
                    
                } else {
                    //Skips line to get to relevant data
                    scan.nextLine();
                }
                
            }
            //Closes scanner to prevent resource leakage
            scan.close();

            //Takes factors and converts them to percentages, for use in Naive Bayes
            percentise();

            //Exports data required for Naive Bayes calculation
            //New class since InputScan was getting cluttered
            bae = new Bayes(this);

            float testset=testing.size();
            correct=0;
            for (String[] i : testing)
            {
                testData(i);
            }
            
            //Captures accuracy of testing, to be displayed in the gui
            accuracy = correct/testset;
            //Debug for 
            //System.out.println(Math.round(correct)+"\n"+Math.round(testset));
            //System.out.println((correct/testset)*100+"% accuracy");

            //Calculates dataset after testing of dataset
            percentise();
            
        } catch(FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
    }

    //Re-usable function to build on probabilities when element is added
    public void factorise(String[] temp)
    {
        if(temp[0].equals("Male"))
        {
            factors[0][0]++;
        } else if(temp[0].equals("Female")) {
            factors[1][0]++;
        }

        if(temp[1].equals("Yes"))
        {
            factors[2][0]++;
        } else if(temp[1].equals("No")) {
            factors[3][0]++;
        }

        if(temp[2].equals("Yes"))
        {
            factors[4][0]++;
        } else if(temp[2].equals("No")) {
            factors[5][0]++;
        }

        if(temp[3].equals("Urban"))
        {
            factors[6][0]++;
        } else if(temp[3].equals("Rural")) {
            factors[7][0]++;
        }

        if(temp[4].equals("Yes")) {
                        
            factors[8][0]++;
        } else if(temp[4].equals("No")) {
            factors[9][0]++;
        }

        if(temp[5].equals("Yes")) {
            factors[10][0]++;
            //Sets function to increment if 
            yn = 1;
        } else if(temp[5].equals("No")) {
            factors[11][0]++;

            yn = 2;
        }
        //Increments factors whether student is entrepreneur or not
        given(temp,yn);
        //Increments total number of students
        total++;
    }
    
    public void percentise()
    {
        //More succinct loop for calculating probabilities
        for (int i=0;i<12;i++)
        {
            percent[i][0] = (float)factors[i][0]/ (float)total;
        }
        //Used to dictate which total by which to divide
        int h=10;

        //Calculates 'yes' first, then 'no' second
        for(int j=1;j<3;j++)
        {
            for (int i=0;i<12;i++)
            {
                percent[i][j] = (float)factors[i][j]/ (float)factors[h][j];
            }
            h++;
        }
    }

    //Increments factors whether student becomes entrepreneur, declutters init function
    private void given(String[] temp, int yn)
    {
        if(temp[0].equals("Male")) {
            factors[0][yn]++;
        } else if(temp[0].equals("Female")) {
            factors[1][yn]++;
        }

        if(temp[1].equals("Yes")) {
            factors[2][yn]++;
        } else if(temp[1].equals("No")) {
            factors[3][yn]++;
        }

        if(temp[2].equals("Yes")) {
            factors[4][yn]++;
        } else if(temp[2].equals("No")) {
            factors[5][yn]++;
        }

        if(temp[3].equals("Urban")) {
            factors[6][yn]++;
        } else if(temp[3].equals("Rural")) {
            factors[7][yn]++;
        }

        if(temp[4].equals("Yes")) {
            factors[8][yn]++;
        } else if(temp[4].equals("No")) {
            factors[9][yn]++;
        }

        if(temp[5].equals("Yes")) {
            factors[10][yn]++;
            //Sets function to increment if 
        } else if(temp[5].equals("No")) {
            factors[11][yn]++;
        }
    }

    //Used in gui to add instances to dataset
    public void addElement(String[] ele)
    {
        //Adds instance to internal dataset
        holding.add(ele);
        //Adjusts numbers in factors array for data display
        factorise(ele);
        //Updates percentages to match changes in factors
        percentise();
    }
    
    //Uses rest of csv to test data sheet 
    public void testData(String[] temp)
    {
        int g,p,j,a,b;
        g=p=j=a=b=0;
        if(temp[0].equals("Male")) {
            g=0;
        } else if(temp[0].equals("Female")) {
            g=1;
        }

        if(temp[1].equals("Yes")) {
            p=0;
        } else if(temp[1].equals("No")) {
            p=1;
        }

        if(temp[2].equals("Yes")) {
            j=0;
        } else if(temp[2].equals("No")) {
            j=1;
        }

        if(temp[3].equals("Urban")) {
            a=0;
        } else if(temp[3].equals("Rural")) {
            a=1;
        }

        if(temp[4].equals("Yes")) {
           b=0; 
        } else if(temp[4].equals("No")){
           b=1;
        }

        //Passes in values, determining if data is predicted as entrepreneur or not
        String test = bae.answerTest(g, p, j, a, b);

        //Compares. Couldn't figure out a way of influencing probabilities, so left blank for now
        if(temp[5].contains(test))
        {
            correct++;
            addElement(temp);
        } else {
            addElement(temp);
        }
    }
    //Debug function for checking array capabilities
    //Superceded by toString() implementation
    public void printFactors()
    {
        System.out.println("Total: "+total+"  Entrepreneurs: "+factors[10][0]+"  Not Entrepreneurs: "+factors[11][0]);
        System.out.println("Males: "+factors[0][0]+"  Females: "+factors[1][0]);
        System.out.println("Parents owned business: "+factors[2][0]+"  Parents didn't own business: "+factors[3][0]);
        System.out.println("Has part-time job: "+factors[4][0]+"  No job: "+factors[5][0]);
        System.out.println("Urban: "+factors[6][0]+"  Rural: "+factors[7][0]);
        System.out.println("Studies business: "+factors[8][0]+"  Does not study business: "+factors[9][0]);
        System.out.println("");

        System.out.println("Entrepreneur==yes");
        System.out.println("Males: "+factors[0][1]+"  Females: "+factors[1][1]);
        System.out.println("Parents owned business: "+factors[2][1]+"  Parents didn't own business: "+factors[3][1]);
        System.out.println("Has part-time job: "+factors[4][1]+"  No job: "+factors[5][1]);
        System.out.println("Urban: "+factors[6][1]+"  Rural: "+factors[7][1]);
        System.out.println("Studies business: "+factors[8][1]+"  Does not study business: "+factors[9][1]);
        System.out.println("");

        System.out.println("Entrepreneur==no");
        System.out.println("Males: "+factors[0][2]+"  Females: "+factors[1][2]);
        System.out.println("Parents owned business: "+factors[2][2]+"  Parents didn't own business: "+factors[3][2]);
        System.out.println("Has part-time job: "+factors[4][2]+"  No job: "+factors[5][2]);
        System.out.println("Urban: "+factors[6][2]+"  Rural: "+factors[7][2]);
        System.out.println("Studies business: "+factors[8][2]+"  Does not study business: "+factors[9][2]);
        System.out.println("");
    }

    public void printPercent()
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

        System.out.println(bae.getGivenyes()+ "  "+bae.getGivenno());
        }
    }

    public void printHolding()
    {
        for(String[] i: holding)
        {
            System.out.println(Arrays.toString(i));
        }
        
    }

    public void printTesting()
    {
        for(String[] i: testing)
        {
            System.out.println(Arrays.toString(i));
        }
        
    }
    //Used to return equivalent of toString, but for percentages instead of factors
    public String toStringPercent()
    {
        return "A run-down of the factors gathered so far, in terms of percentages:\n"+
        "Total: "+total+"  Entrepreneurs: "+percent[10][0]+"  Not Entrepreneurs: "+percent[11][0]+"\n"+
        "Males: "+percent[0][0]+"  Females: "+percent[1][0]+"\n"+
        "Parents owned business: "+percent[2][0]+"  Parents didn't own business: "+percent[3][0]+"\n"+
        "Has part-time job: "+percent[4][0]+"  No job: "+percent[5][0]+"\n"+
        "Urban: "+percent[6][0]+"  Rural: "+percent[7][0]+"\n"+
        "Studies business: "+percent[8][0]+"  Does not study business: "+percent[9][0]+"\n\n"+
        "That became Entrepreneurs:\n"+
        "Males: "+percent[0][1]+"  Females: "+percent[1][1]+"\n"+
        "Parents owned business: "+percent[2][1]+"  Parents didn't own business: "+percent[3][1]+"\n"+
        "Has part-time job: "+percent[4][1]+"  No job: "+percent[5][1]+"\n"+
        "Urban: "+percent[6][1]+"  Rural: "+percent[7][1]+"\n"+
        "Studies business: "+percent[8][1]+"  Does not study business: "+percent[9][1]+"\n\n"+
        "That did not become Entrepreneurs:\n"+
        "Males: "+percent[0][2]+"  Females: "+percent[1][2]+"\n"+
        "Parents owned business: "+percent[2][2]+"  Parents didn't own business: "+percent[3][2]+"\n"+
        "Has part-time job: "+percent[4][2]+"  No job: "+percent[5][2]+"\n"+
        "Urban: "+percent[6][2]+"  Rural: "+percent[7][2]+"\n"+
        "Studies business: "+percent[8][2]+"  Does not study business: "+percent[9][2]+"\n\n";
    }
    //Returns factors, unclutters gui
    public String toString()
    {
        return "A run-down of the factors gathered so far:\n"+
        "Total: "+total+"  Entrepreneurs: "+factors[10][0]+"  Not Entrepreneurs: "+factors[11][0]+"\n"+
        "Males: "+factors[0][0]+"  Females: "+factors[1][0]+"\n"+
        "Parents owned business: "+factors[2][0]+"  Parents didn't own business: "+factors[3][0]+"\n"+
        "Has part-time job: "+factors[4][0]+"  No job: "+factors[5][0]+"\n"+
        "Urban: "+factors[6][0]+"  Rural: "+factors[7][0]+"\n"+
        "Studies business: "+factors[8][0]+"  Does not study business: "+factors[9][0]+"\n\n"+
        "That became Entrepreneurs:\n"+
        "Males: "+factors[0][1]+"  Females: "+factors[1][1]+"\n"+
        "Parents owned business: "+factors[2][1]+"  Parents didn't own business: "+factors[3][1]+"\n"+
        "Has part-time job: "+factors[4][1]+"  No job: "+factors[5][1]+"\n"+
        "Urban: "+factors[6][1]+"  Rural: "+factors[7][1]+"\n"+
        "Studies business: "+factors[8][1]+"  Does not study business: "+factors[9][1]+"\n\n"+
        "That did not become Entrepreneurs:\n"+
        "Males: "+factors[0][2]+"  Females: "+factors[1][2]+"\n"+
        "Parents owned business: "+factors[2][2]+"  Parents didn't own business: "+factors[3][2]+"\n"+
        "Has part-time job: "+factors[4][2]+"  No job: "+factors[5][2]+"\n"+
        "Urban: "+factors[6][2]+"  Rural: "+factors[7][2]+"\n"+
        "Studies business: "+factors[8][2]+"  Does not study business: "+factors[9][2]+"\n\n";
    }

    //Get/Sets for private variables
    public String getFile() {
        return this.file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int[][] getFactors() {
        return this.factors;
    }

    public void setFactors(int[][] factors) {
        this.factors = factors;
    }
    public float[][] getPercent() {
        return this.percent;
    }

    public void setPercent(float[][] percent) {
        this.percent = percent;
    }


    public Bayes getBae() {
        return this.bae;
    }

    public void setBae(Bayes bae) {
        this.bae = bae;
    }

    public File getCsv() {
        return this.csv;
    }

    public void setCsv(File csv) {
        this.csv = csv;
    }

    public Scanner getScan() {
        return this.scan;
    }

    public void setScan(Scanner scan) {
        this.scan = scan;
    }

    public float getFlen() {
        return this.flen;
    }

    public void setFlen(float flen) {
        this.flen = flen;
    }

    public float getPer() {
        return this.per;
    }

    public void setPer(float per) {
        this.per = per;
    }

    public int getBuild() {
        return this.build;
    }

    public void setBuild(int build) {
        this.build = build;
    }

    public ArrayList<String[]> getHolding() {
        return this.holding;
    }

    public void setHolding(ArrayList<String[]> holding) {
        this.holding = holding;
    }

    public ArrayList<String[]> getTesting() {
        return this.testing;
    }

    public void setTesting(ArrayList<String[]> testing) {
        this.testing = testing;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getYn() {
        return this.yn;
    }

    public void setYn(int yn) {
        this.yn = yn;
    }


    public float getCorrect() {
        return this.correct;
    }

    public void setCorrect(float correct) {
        this.correct = correct;
    }

    public float getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }



}
