import java.math.BigDecimal;
import java.math.RoundingMode;

public class ComplexBig {
    final BigDecimal x;
    final BigDecimal y;
    public ComplexBig(BigDecimal x) {
        this.x = x;
        this.y = BigDecimal.ZERO;
    }
    public ComplexBig(BigDecimal x, BigDecimal y) {
        this.x = x.setScale(10, RoundingMode.CEILING);
        this.y = y.setScale(10, RoundingMode.CEILING);;
    }
    public ComplexBig mul(ComplexBig b) {
        return new ComplexBig(x.multiply(b.x).subtract(y.multiply(b.y)), x.multiply(b.y).add(y.multiply(b.x)));
        //return new Complex(x * b.x - y * b.y, x * b.y + y * b.x);
    }
    public double abs() {
       // return (x.multiply(x).add(y.multiply(y)));
        return  Math.sqrt((x.multiply(x).add(y.multiply(y))).doubleValue());
        //return Math.sqrt(x * x + y * y);
    }
    public ComplexBig add(ComplexBig b) {
        return new ComplexBig(x.add(b.x), y.add(b.y));
    }


    @Override
    public String toString() {
        return "Complex [x=" + x + ", y=" + y + "]";
    }
    // Usage example

}