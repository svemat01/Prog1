package paket;

public class assignment2 {
    public static void main(String[] args) {

        // Make test values
        double[] inputs = {5,1,2,8,3,4,5};
        int count = 7;

        double varWidth = variationWidth(inputs, count); // Get variadtionWidth from function
        System.out.printf("Variation Width: %s\n", varWidth);
    }

    public static double variationWidth(double[] inputs, int inputCount) {
        //
        double highestInput = highest(inputs, inputCount); // Grab highest value from double array
        double lowestInput = lowest(inputs, inputCount); // Grab lowest value from double array

        return highestInput - lowestInput;
    }

    public static double highest(double[] inputs, int inputCount) {
        double prevValue = inputs[0]; // define start value

        for (int i = 0; i < inputCount; i++) { // run loop on all doubles in array
            if (inputs[i] > prevValue) { // is the previous value smaller than new value?
                prevValue = inputs[i]; // save new value as prevValue
            }
        }

        return prevValue; // return the highest number
    }

    public static double lowest(double[] inputs, int inputCount) {
        double prevValue = inputs[0]; // define start value

        for (int i = 0; i < inputCount; i++) { // run loop on all doubles in array
            if (inputs[i] < prevValue) { // is the previous value bigger than new value?
                prevValue = inputs[i]; // save new value as prevValue
            }
        }

        return prevValue;// return the highest number
    }
}
