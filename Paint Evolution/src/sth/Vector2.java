package src.sth;

public class Vector2 {

    public double x;
    public double y;

    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    public static Vector2 sum(Vector2 vct1, Vector2 vct2){
        return new Vector2(vct1.x + vct2.x, vct1.y + vct2.y);
    }

    public static Vector2 sub(Vector2 vct1, Vector2 vct2){
        return new Vector2(vct1.x - vct2.x, vct1.y - vct2.y);
    }

    public static Vector2 mult(Vector2 vct1, Vector2 vct2){
        return new Vector2(vct1.x * vct2.x, vct1.y * vct2.y);
    }

    public static Vector2 mult(Vector2 vct, double num){
        return new Vector2(vct.x * num, vct.y * num);
    }

    public static Vector2 div(Vector2 vct1, Vector2 vct2){
        return new Vector2(vct1.x / vct2.x, vct1.y / vct2.y);
    }

    public static Vector2 div(Vector2 vct, double num){
        return new Vector2(vct.x / num, vct.y / num);
    }

    public boolean isGreaterThan(Vector2 vct){
        return x > vct.x ? y > vct.y ? true : false : false;
    }

    public boolean isGreaterOrEqualThan(Vector2 vct){
        return x >= vct.x ? y >= vct.y ? true : false : false;
    }

    public boolean isLessThan(Vector2 vct){
        return x < vct.x ? y < vct.y ? true : false : false;
    }

    public boolean isLessOrEqualThan(Vector2 vct){
        return x <= vct.x ? y <= vct.y ? true : false : false;
    }

    public static Vector2 rotate(Vector2 vct, double radians){
        double rotatedX = vct.x * Math.cos(radians) - vct.y * Math.sin(radians);
        double rotatedY = vct.x * Math.sin(radians) + vct.y * Math.cos(radians);
        return new Vector2(rotatedX, rotatedY);
    }

    public static double distanceBetween(Vector2 vct1, Vector2 vct2){
        return Math.sqrt(Math.pow(vct1.x - vct2.x, 2) + Math.pow(vct1.y - vct2.y, 2));
    }

    public double distanceTo(Vector2 vct){
        return Math.sqrt(Math.pow(x - vct.x, 2) + Math.pow(y - vct.y, 2));
    }

    public String toString(){
        return "(" + x + ", " + y + ")";
    }

}

