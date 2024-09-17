package src.sth;

public class Mat {

    public static int clamp(int value, int min, int max){
        if (max < min) {
            try {
                throw new Exception("Minimum value can't be greater than maximum value");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value < min ? min : value > max ? max : value;
    }

    public static double clampf(double value, int min, int max){
        if (max < min) {
            try {
                throw new Exception("Minimum value can't be greater than maximum value");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value < min ? min : value > max ? max : value;
    }

}
