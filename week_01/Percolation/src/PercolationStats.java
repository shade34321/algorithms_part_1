/**
 * Created by shade on 7/3/2015.
 */

public class PercolationStats {

    private int experimentCount;
    private Percolation p;
    private double[] results;

    //Perform T independent experiments on an NxN grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        experimentCount = T;
        results = new double[T];
        for (int i = 0; i < experimentCount; i++) {
            p = new Percolation(N);
            int sites = 0;
            while (!p.percolates()) {
                int x = StdRandom.uniform(1, N+1);
                int y = StdRandom.uniform(1, N+1);
                if (!p.isOpen(x, y)) {
                    p.open(x, y);
                    sites++;
                }
            }

            double testResults = (double) sites / (N*N);
            results[i] = testResults;
        }
    }

    // Sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(experimentCount));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(experimentCount));
    }

    //Test client
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N, T);

        System.out.printf("mean\t\t= %f\n", ps.mean());
        System.out.printf("stddev\t\t= %f\n", ps.stddev());
        System.out.printf("95%% confidence interval = %f, %f", ps.confidenceLo(),
                ps.confidenceHi());
    }
}
