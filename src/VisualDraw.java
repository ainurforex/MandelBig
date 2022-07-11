import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class VisualDraw {
    private static double xShift = 0;
    private static double yShift = 0;
    private static double x0Mem;
    private static double y0Mem;
    private Canvas canvas;
    private int width;
    private int height;
    private double x0;
    private double y0;
    private double scale;
    private int paletteKoef;
    private int paletteSize;
    private static Color[] colorArray;

    VisualDraw(Canvas canvas) {
        this.canvas = canvas;
        this.width = (int) canvas.getWidth();
        this.height = (int) canvas.getHeight();
        this.x0 = 0;
        this.y0 = 0;
        this.paletteKoef = 8;//min=2;max=256
        this.paletteSize = paletteKoef * paletteKoef * (paletteKoef / 2);
    }


    public void update() {
        Complex z;
        int correctionX0 = width / 2;
        int correctionY0 = height / 2;

        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                double x = (i - x0 - correctionX0) / scale;
                double y = (j - y0 - correctionY0) / scale;
                z = new Complex(x, y);
                int color = checkPointAffiliaton(z, paletteSize);
                canvas.getGraphicsContext2D().getPixelWriter().setColor(i, j, colorArray[color]);
            }
        }

    }

    private static int checkPointAffiliaton(Complex z0, int paletteSize) {
        Complex z = z0;

        for (int i = paletteSize - 1; i > 1; i -= 1) {
            if (z.abs() > 2) {
                return i;
            }
            z = z.mul(z).add(z0);
        }

        return 0;
    }

    public void updateMouseXYScale(double scale) {

        updateMouseXY(width / 2, height / 2);

        x0 = x0 + (xShift / 100 * scale);
        y0 = y0 + (yShift / 100 * scale);
        x0Mem = x0;
        y0Mem = y0;

    }

    public void updateMouseXYStay() {
        x0 = x0Mem;
        y0 = y0Mem;
    }

    public void updateMouseXY(double mouseX, double mouseY) {
        xShift = x0Mem + (width / 2 - mouseX);
        yShift = y0Mem + (height / 2 - mouseY);
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
        scale = Scale;
    }
}
