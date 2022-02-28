public class TgSeries {
    public static double calculate(double x, int terms) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            throw new IllegalArgumentException("X must be finite and not NaN. X: " + x);
        }

        if (terms <= 0 || terms > 10) {
            throw new IllegalArgumentException("Terms must be in between 1 and 10. Terms: " + terms);
        }

        if (Math.abs(x) >= Math.PI / 2) {
            throw new IllegalArgumentException("Tangent maybe be approximated only between -PI/2 and PI/2. x: " + x);
        }

        double res = 0;
        for (int n = 1; n <= terms; ++n) {
            res += (bernoulli_coefs[n - 1] * Math.pow(-4, n) * (1 - Math.pow(4, n))
                    / factorial(2 * n)) * Math.pow(x, 2 * n - 1);
        }
        return res;
    }

    private static final double[] bernoulli_coefs = {
            1. / 6, -1. / 30, 1. / 42, -1. / 30, 5. / 66,
            -691. / 2730, 7. / 6, -3617. / 510, 43867. / 798, -174611. / 330
    };

    private static long factorial(int number) {
        long result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }
}
