package com.example.rowdyhacks;

public class statistics {
    //private int[] totalCrimesPerYear;
    //private int[] crimeRate;
    //private int averageCrimeRate;
    private int totalPop;
    private String[] bexarOri;


    public statistics(int totalPop, String[] bexarOri){
        this.totalPop = totalPop;
        this.bexarOri = bexarOri;
    }

    // Getters and Setters
    public int getTotalPop() {
        return totalPop;
    }

    public void setTotalPop(int totalPop) {
        this.totalPop = totalPop;
    }

    public String[] getBexarOri() {
        // Ensure the getter returns an empty array if bexarOri is null
        if (this.bexarOri == null) {
            return new String[0]; // Return an empty array to avoid null
        }
        return bexarOri;
    }

    public void setBexarOri(String[] bexarOri) {
        this.bexarOri = bexarOri;
    }


    public double getCrimeRate(int [] totalCrimes) {
        //String data = userData[0];
        final double bexarCountyPop2017 = 1956988.0;
        final double bexarCountyPop2018 = 1981187.0;
        final double bexarCountyPop2019 = 2003554.0;
        int[] totalCrimesPerYear = new int[3];
        //need to grab data based on ori

        double averageCrimeRate;

        double[] averageCrimeRatePerYear = new double[3];



        int i = 0;


        for (i = 0; i < 3; ++i) {
            if (i == 0) {
                averageCrimeRatePerYear[i] = ((totalCrimes[i] + .00) / (bexarCountyPop2017));
            }
            if (i == 1) {
                averageCrimeRatePerYear[i] = ((totalCrimes[i] + .00) / (bexarCountyPop2018));
            }
            if (i == 2) {
                averageCrimeRatePerYear[i] = ((totalCrimes[i] + .00) / (bexarCountyPop2019));
            }
        }

        averageCrimeRate = (averageCrimeRatePerYear[0] + averageCrimeRatePerYear[1] + averageCrimeRatePerYear[2]) / 3;

        return averageCrimeRate;
    }

    public double standardDeviation( double[] averageCrimeRate){
        double sum = 0.0;
        double standardDeviation = 0.0;
        int length = averageCrimeRate.length;

        // Step 1: Calculate the mean (average)
        for(double num : averageCrimeRate) {
            sum += num;
        }
        double mean = sum / length;

        // Step 2: Calculate the variance
        for(double num: averageCrimeRate) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        double variance = standardDeviation / length;

        // Step 3: Calculate the standard deviation
        return Math.sqrt(variance);
    }



}
