//Holds methods for calculating Naive Bayes
public class Bayes {
    //Floats to contain class probabilities for Naive Bayes
    private float nom;
    private float denom;
    private float givenyes;
    private float givenno;
    /*Stores percentages for
        0: Total
        1: Entrepreneur==yes
        2: Entrepreneur==no
        [12][3], stores 1/0s together for easier access
        eg [1-2][0] stores genders overall etc
    */
    private float[][] percent;

    public Bayes(float[][] percent)
    {
        this.percent = percent;

        givenyes = calculateYes(0,0,0,0,0);
        givenno = calculateNo(0,0,0,0,0);
    }

    //
    public float calculateYes(int g, int p, int j, int a, int b)
    {
        //Test using 'default' value for each factor where entrepreneur == yes
        nom = (percent[0+g][1] * percent[2+p][1] * percent[4+j][1] * percent[6+a][1] * percent[8+b][1] * percent[10][0]);
        denom =  percent[0+g][0] * percent[2+p][0] * percent[4+j][0] * percent[6+a][0] * percent[8+b][0];
        return nom/denom;
    }
    
    public float calculateNo(int g, int p, int j, int a, int b)
    {
        nom = (percent[0+g][2] * percent[2+p][2] * percent[4+j][2] * percent[6+a][2] * percent[8+b][2] * percent[11][0]);
        denom =  percent[0+g][0] * percent[2+p][0] * percent[4+j][0] * percent[6+a][0] * percent[8+b][0];
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

    public float[][] getPercent()
    {
        return this.percent;
    }

    public void setPercent(float[][] percent)
    {
        this.percent = percent;
    }
    
    
}
