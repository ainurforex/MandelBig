import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SceneController {
    @FXML
    public TextField Scale;
    private static double mouseX;
    private static double mouseY;
    private static int RGBPalitre = 2;
    private static boolean firstRun = true;
    public javafx.scene.canvas.Canvas Canvas;



    public void updateScale(double scale) {
        VisualDraw draw = new VisualDraw(Canvas);
        draw.setScale(getScale());
        if (firstRun) {
            draw.setColorArray(ColorArray.colorArray(draw.getPaletteKoef(), RGBPalitre));
            firstRun=false;
        } else {
            draw.updateMouseXYScale(scale);
        }
        draw.update();
    }

    public void updateMouse(MouseEvent e) {
        VisualDraw draw = new VisualDraw(Canvas);
        draw.setScale(getScale());
        if (firstRun) {
            draw.setColorArray(ColorArray.colorArray(draw.getPaletteKoef(), RGBPalitre));
            firstRun=false;
        } else {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
            draw.updateMouseXY(mouseX, mouseY);
        }
        draw.update();
    }

    public void updateRGB() {
        VisualDraw draw = new VisualDraw(Canvas);
        draw.setScale(getScale());
        draw.setColorArray(ColorArray.colorArray(draw.getPaletteKoef(), RGBPalitre));
        draw.updateMouseXYStay();
        draw.update();
    }
    @FXML
    private void RGBRandom(ActionEvent actionEvent) {
        RGBPalitre = 7;
        updateRGB();
    }


    private void changeScale(double s) {
        double currentScale = Double.parseDouble(Scale.getText());
        if (currentScale + (currentScale * s / 100) > 0) {
            Scale.setText(currentScale + (currentScale * s / 100) + "");
            updateScale(s);
        }
    }


    private double getScale() {
        return Double.parseDouble(Scale.getText());
    }


    @FXML
    private void increase10(ActionEvent actionEvent) {
        changeScale(10);
    }

    @FXML
    private void decrease10(ActionEvent actionEvent) {
        changeScale(-10);
    }

    @FXML
    private void increase100(ActionEvent actionEvent) {
        changeScale(+100);
    }

    @FXML
    private void decrease50(ActionEvent actionEvent) {
        changeScale(-50);
    }

    @FXML
    private void RGBChange(ActionEvent actionEvent) {
        if (RGBPalitre < 7) {
            RGBPalitre++;
        }
        if (RGBPalitre == 7) {
            RGBPalitre = 1;
        }
        updateRGB();

    }



}
