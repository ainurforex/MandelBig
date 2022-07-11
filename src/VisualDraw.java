import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.math.BigDecimal;
import java.math.BigInteger;

public class VisualDraw {
    private static BigDecimal xShift = BigDecimal.ZERO;
    private static BigDecimal yShift = BigDecimal.ZERO;
    private static BigDecimal x0Mem= BigDecimal.ZERO;
    private static BigDecimal y0Mem= BigDecimal.ZERO;
    private Canvas canvas;
    private int width;
    private int height;
    private  BigDecimal x0;
    private  BigDecimal y0;
    private double scale;
    private int paletteKoef;
    private int paletteSize;
    private static Color[] colorArray;

    VisualDraw(Canvas canvas) {
        this.canvas = canvas;
        this.width = (int) canvas.getWidth();
        this.height = (int) canvas.getHeight();
        this.x0 = new BigDecimal("0");
        this.y0 = new BigDecimal("0");
        this.paletteKoef = 8;//min=2;max=256
        this.paletteSize = paletteKoef * paletteKoef * (paletteKoef / 2);

    }


    public void update() {
        ComplexBig z;
        BigDecimal correctionX0 = new BigDecimal((width / 2) + "");
        BigDecimal correctionY0 = new BigDecimal((height / 2) + "");

        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                BigDecimal x = new BigDecimal(i + ".0").subtract(x0).subtract(correctionX0);

                x = x.divide(new BigDecimal(scale));

                BigDecimal y = new BigDecimal(j + ".0").subtract(y0).subtract(correctionY0);
                y=y.divide(new BigDecimal(scale));
                z = new ComplexBig(x, y);
                int color = checkPointAffiliaton(z, paletteSize);
                canvas.getGraphicsContext2D().getPixelWriter().setColor(i, j, colorArray[color]);
            }

        }

    }

    private static int checkPointAffiliaton(ComplexBig z0, int paletteSize) {
        ComplexBig z = z0;

        for (int i = paletteSize - 1; i > 1; i --) {
            if (z.abs() > 2) {
                return i;
            }
            z = z.mul(z).add(z0);
        }

        return 0;
    }

    public void updateMouseXYScale(double scale) {

        updateMouseXY(width / 2, height / 2);
        x0 = x0.add(xShift.divide(new BigDecimal(100)).multiply(new BigDecimal(scale)));
        y0 = y0.add(yShift.divide(new BigDecimal(100)).multiply(new BigDecimal(scale)));;
        x0Mem = x0;
        y0Mem = y0;
    }

    public void updateMouseXYStay() {
        x0 = x0Mem;
        y0 = y0Mem;
    }

    public void updateMouseXY(double mouseX, double mouseY) {
        xShift = x0Mem.add(new BigDecimal(width / 2).subtract(new BigDecimal(mouseX)));
        yShift = y0Mem.add(new BigDecimal(height /2).subtract(new BigDecimal(mouseY)));

        x0 = xShift;
        y0 = yShift;
        x0Mem = x0;
        y0Mem = y0;

    }

    public void setColorArray(Color[] colorArray) {
        this.colorArray = colorArray;
    }

    public int getPaletteKoef() {
        return paletteKoef;
    }

    public void setScale(double Scale) {
        scale =Scale;

    }
}
