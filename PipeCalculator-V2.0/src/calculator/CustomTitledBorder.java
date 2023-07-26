package calculator;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CustomTitledBorder extends TitledBorder {

    private Font titleFont;

    public CustomTitledBorder(Border border, String title, int titleJustification, int titlePosition) {
        super(border, title, titleJustification, titlePosition);
        this.titleFont = UIManager.getFont("TitledBorder.font");
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        FontMetrics metrics = g.getFontMetrics(titleFont);
        Rectangle2D stringBounds = metrics.getStringBounds(getTitle(), g);

        int titleWidth = (int) stringBounds.getWidth();
        int titleHeight = (int) stringBounds.getHeight();

        int titleX = x + (width - titleWidth) / 2;
        int titleY = y + metrics.getAscent();

        super.paintBorder(c, g, x, y, width, height);

        g.setFont(titleFont);
        g.drawString(getTitle(), titleX, titleY);
    }
}