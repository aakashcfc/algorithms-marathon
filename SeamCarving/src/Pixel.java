package SeamCarving.src;

public class Pixel {
    int red;
    int green;
    int blue;

    public Pixel() {
        this.red = 0;
        this.green = 0;
        this.blue = 0;
    }

    public Pixel(int val) {
        this.red = clamp(0, 255, val);
        this.green = clamp(0, 255, val);
        this.blue = clamp(0, 255, val);
    }

    public Pixel(int red, int green, int blue) {
        this.red = clamp(0, 255, red);
        this.green = clamp(0, 255, green);
        this.blue = clamp(0, 255, blue);
    }

    public String toString() {
        return String.format("#%02x%02x%02x", this.red, this.green, this.blue);
    }

    public Pixel blend(double ratio, Pixel other) {
        int red = (int) weighted_average(ratio, (double) other.red, (double) this.red);
        int green = (int) weighted_average(ratio, (double) other.green, (double) this.green);
        int blue = (int) weighted_average(ratio, (double) other.blue, (double) this.blue);
        return new Pixel(red, green, blue);
    }

    public Pixel invert() {
        int red = 255 - this.red;
        int green = 255 - this.green;
        int blue = 255 - this.blue;
        return new Pixel(red, green, blue);
    }

    public static double weighted_average(double ratio, double x, double y) {
        ratio = (double) clamp(0.0, 1.0, ratio);
        double inv_ratio = 1 - ratio;
        return (ratio * x) + (inv_ratio * y);
    }

    public static <N extends Comparable<? super N>> N clamp(N lo, N hi, N n) {
        if (n.compareTo(lo) < 0) {
            return lo;
        } else if (n.compareTo(hi) > 0) {
            return hi;
        } else {
            return n;
        }
    }
}