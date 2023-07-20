package com.challenge.meli.utils;

public class InverseDistance {
        public static float[] location(float[] distances, float[][] positions){

            float xSum = 0;
            float ySum = 0;
            float totalWeight = 0;

            for (int i = 0; i < distances.length; i++) {
                float weight = 1 / (float) Math.pow(distances[i], 2);
                xSum += positions[i][0] * weight;
                ySum += positions[i][1] * weight;
                totalWeight += weight;
            }

            if (totalWeight != 0) {
                float x = xSum / totalWeight;
                float y = ySum / totalWeight;
                return new float[]{x, y};
            } else {
                throw new ArithmeticException("Division by zero: totalWeight is zero");
            }
        }

    }
