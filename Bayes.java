//Holds methods for calculating Naive Bayes
//Can be accessed through InputScan
//Uses data held in InputScan
public class Bayes {
    //Floats to contain class probabilities for Naive Bayes
    private float nom;
    private float denom;
    private float givenyes;
    private float givenno;

    //Used to determine entrepreneurship when adding insatances from the gui
    private String entre;

    //Defines InputScan class to access data for use in Naive Bayes
    InputScan scan;

    public Bayes(InputScan i)
    {
        //this.percent = percent;
        scan = i;

        entre = new String();

        givenyes = calculateYes(0,0,0,0,0);
        givenno = calculateNo(0,0,0,0,0);
    }

    //Functions to compare yes/no values
    //Returns floats for testing dataset
    public String answerTest(int g, int p, int j, int a, int b)
    {
        givenyes= calculateYes(g, p, j, a, b);
        givenno = calculateNo(g, p, j, a, b);
        //System.out.println(givenyes +"\n"+ givenno+"\n");

        if (givenyes > givenno)
        {
            entre = "Yes";
        } else {
            entre = "No";
        }

        return entre;
    }
    //Returns string for GUI/standalone purposes
    public String answerString(int g, int p, int j, int a, int b)
    {
        givenyes= calculateYes(g, p, j, a, b);
        givenno = calculateNo(g, p, j, a, b);
        //System.out.println(givenyes +"\n"+ givenno+"\n");

        if (givenyes > givenno)
        {
            entre = "Yes";
            return "This student is likely to become an entrepreneur";
        } else {
            entre = "No";
            return "This student is unlikely to become an entrepreneur";
        }
    }
    //
    public float calculateYes(int g, int p, int j, int a, int b)
    {
        //Test using 'default' value for each factor where entrepreneur == yes
        nom = (scan.getPercent()[0+g][1] * scan.getPercent()[2+p][1] * scan.getPercent()[4+j][1] * scan.getPercent()[6+a][1] * scan.getPercent()[8+b][1] * scan.getPercent()[10][0]);
        denom =  scan.getPercent()[0+g][0] * scan.getPercent()[2+p][0] * scan.getPercent()[4+j][0] * scan.getPercent()[6+a][0] * scan.getPercent()[8+b][0];
        return nom/denom;
    }
    
    public float calculateNo(int g, int p, int j, int a, int b)
    {
        nom = (scan.getPercent()[0+g][2] * scan.getPercent()[2+p][2] * scan.getPercent()[4+j][2] * scan.getPercent()[6+a][2] * scan.getPercent()[8+b][2] * scan.getPercent()[11][0]);
        denom =  scan.getPercent()[0+g][0] * scan.getPercent()[2+p][0] * scan.getPercent()[4+j][0] * scan.getPercent()[6+a][0] * scan.getPercent()[8+b][0];
        return nom/denom;
    }


    public float getNom()
    {
        return this.nom;
    }

    public void setNom(float nom)
    {
        this.nom = nom;
    }

    public float getDenom()
    {
        return this.denom;
    }

    public void setDenom(float denom)
    {
        this.denom = denom;
    }

    public float getGivenyes()
    {
        return this.givenyes;
    }

    public void setGivenyes(float givenyes)
    {
        this.givenyes = givenyes;
    }

    public float getGivenno() {
        return this.givenno;
    }

    public void setGivenno(float givenno)
    {
        this.givenno = givenno;
    }
    
    public String getEntre() {
        return this.entre;
    }

    public void setEntre(String entre) {
        this.entre = entre;
    }

    public InputScan getScan() {
        return this.scan;
    }

    public void setScan(InputScan scan) {
        this.scan = scan;
    }

    
}
