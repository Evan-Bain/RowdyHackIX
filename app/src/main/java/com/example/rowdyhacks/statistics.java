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


    public double getCrimeRate(String[] userData, int totalPop ) {
        String data = userData[0];
        int[] totalCrimesPerYear = new int[3];
        //need to grab data based on ori
        int[] totalCrimesFromData = new int[3];
        int[] totalCrimes = new int[3];
        int[] practicePopulation = new int[3];
        double averageCrimeRate;
        practicePopulation[0] = 100000;
        practicePopulation[1] = 102930;
        practicePopulation[2] = 123098;
        double[] averageCrimeRatePerYear = new double[3];

        totalCrimesFromData[0] = 12000;
        totalCrimesFromData[1] = 13000;
        totalCrimesFromData[2] = 14000;

        int i = 0;
        for (i = 0; i < 3; ++i) {
            if (i == 0) {
                totalCrimes[i] = totalCrimesFromData[i];
            }
            if (i == 1) {
                totalCrimes[i] = totalCrimesFromData[i];
            }
            if (i == 2) {
                totalCrimes[i] = totalCrimesFromData[i];
            }
        }

        for (i = 0; i < 3; ++i) {
            if (i == 0) {
                averageCrimeRatePerYear[i] = ((totalCrimes[i] + .00) / (practicePopulation[i] + .00));
            }
            if (i == 1) {
                averageCrimeRatePerYear[i] = ((totalCrimes[i] + .00) / (practicePopulation[i] + .00));
            }
            if (i == 2) {
                averageCrimeRatePerYear[i] = ((totalCrimes[i] + .00) / (practicePopulation[i] + .00));
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
