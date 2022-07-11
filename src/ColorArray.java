import javafx.scene.paint.Color;

public class ColorArray {
    public static Color[] colorArray(int paletteKoef,int RGBPalitre) {

        int redLenght = paletteKoef;
        int blueLenght = (int) (paletteKoef / 2);
        int redBeginPalette = 0;
        int blueBeginPalette = 0;

        int red[] = new int[redLenght];
        int blue[] = new int[blueLenght];

        int redPaletteAdd = (int) (255 / (redLenght - 1));
        if (redPaletteAdd < 1) {
            redPaletteAdd = 1;
        }

        int bluePaletteAdd = (int) (255 / (blueLenght - 1));
        if (bluePaletteAdd < 1) {
            bluePaletteAdd = 1;
        }

        for (int p = 0; p < red.length; p++) {
            red[p] = redBeginPalette;
            redBeginPalette += redPaletteAdd;
        }
        red[red.length - 1] = 255;

        for (int p = 0; p < blue.length; p++) {
            blue[p] = blueBeginPalette;
            blueBeginPalette += bluePaletteAdd;
        }
        blue[blue.length - 1] = 255;

        int green[] = red;

        Color colorArray[] = new Color[red.length * green.length * blue.length];

        int m = 0;

        for (int i = 0; i < red.length; i++) {

            for (int j = 0; j < green.length; j++) {

                for (int k = 0; k < blue.length; k++) {


                    switch (RGBPalitre) {
                        case (1):
                            colorArray[m++] = Color.rgb(red[i], green[j], blue[k]);
                            break;
                        case (2):
                            colorArray[m++] = Color.rgb(red[i], blue[k], green[j]);
                            break;
                        case (3):
                            colorArray[m++] = Color.rgb(blue[k], red[i], green[j]);
                            break;
                        case (4):
                            colorArray[m++] = Color.rgb(green[j], blue[k], red[i]);
                            break;
                        case (5):
                            colorArray[m++] = Color.rgb(blue[k], green[j], red[i]);
                            break;
                        case (6):
                            colorArray[m++] = Color.rgb(green[j], red[i], blue[k]);
                            break;
                        case (7):
                            colorArray[m++] = Color.rgb(rnd(255), rnd(255), rnd(255));
                            break;
                        default:
                            colorArray[m++] = Color.rgb(0, 0, 0);
                            break;
                    }

                }
            }
        }
        return colorArray;
    }
    private static int rnd(int max) {
        return (int) (Math.random() * ++max);
    }
}
