package exam1;
import java.awt.Graphics2D;
public interface IShape {
	enum ShapeType{DRAW,LINE,RECT,OVAL};
	enum FillType{FIll,NO_FILL}
	void draw(Graphics2D g);
}
