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

        givenyes = calculateYes();
        givenno = calculateNo();
    }

    public float calculateYes()
    {
        //Test using 'default' value for each factor where entrepreneur == yes
        nom = (percent[0][1] * percent[2][1] * percent[4][1] * percent[6][1] * percent[8][1] * percent[10][0]);
        denom =  percent[0][0] * percent[2][0] * percent[4][0] * percent[6][0] * percent[8][0];
        return nom/denom;
    }
    
    public float calculateNo()
    {
        nom = (percent[0][2] * percent[2][2] * percent[4][2] * percent[6][2] * percent[8][2] * percent[11][0]);
        denom =  percent[0][0] * percent[2][0] * percent[4][0] * percent[6][0] * percent[8][0];
        return nom/denom;
    }
    
}
