package com.example.rowdyhacks;

public class StDevCalc {
    //multiplying zscore and standard deviation + mean gives percentile values
    private static double[] percentileArray =
            {-2.326, //0th percentile, index 0
                    -1.282, //10th percentile,
                    -0.842,  //20th percentile
                    -0.524,  //30th percentile
                    -0.253,  //40th percentile
                    0,       //50th percentile
                    0.253,   //60th percentile
                    0.524,   //70th percentile
                    0.842,   //80th percentile
                    1.282,  //90th percentile
                    2.326};  //99th percentile

    //returns array of percentile values given a standard deviation and mean
    private static double[] returnPercentileValue(double stdev, double mean)
    {
        double[] percentileValueArray = new double[percentileArray.length];
        for (int i=0; i<percentileArray.length; i++)
        {
            percentileValueArray[i] = percentileArray[i] * stdev + mean;
        }
        return percentileValueArray;
    }

    //given a value, returns a rating 1-10 ,
    //1=10th percentile
    //10=99th percentile
    public static int calculateRating(double stdev, double nationalCrimeRate, double countryCrimeRate)
    {
        double[] percentileValues = returnPercentileValue(stdev, nationalCrimeRate);
        for (int i=0; i<percentileValues.length; i++)
        {
            if (countryCrimeRate < percentileValues[i])
            {
                return i; //rating is equivalent to index
            }
        }
        return 10;
    }
}