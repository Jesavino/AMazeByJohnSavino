package edu.wm.cs.cs301.jesavino.falstad;

/*import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;*/
import java.io.File;
import java.io.Serializable;

import edu.wm.cs.cs301.jesavino.R;

/*import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JToggleButton;*/

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class MazePanel extends View implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* Panel operates a double buffer see
	 * http://www.codeproject.com/Articles/2136/Double-buffer-in-standard-Java-AWT
	 * for details
	 */
	//Image bufferImage ;
	//Graphics gc;
	// Maze object for mutating
	Maze maze;
	Bitmap b;
	Canvas c;
	Paint paint;

	MazeFileReader mfr;
	MazeFileWriter mfw;
	View mazeView;
	
	public MazePanel(Context context , AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public void init() {
		this.setDrawingCacheEnabled(true);
		this.buildDrawingCache();
		
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		this.setFocusable(false) ;
	}
	
	void setGraphicsWhite() {
		paint.setColor(Color.WHITE);
	}
	
	void drawLine(int nx1 , int ny1 , int nx2 , int ny2) {
		c.drawLine(nx1 , ny1 , nx2 , ny2 , paint);
		
	}
	void setGraphicsGray() {
		paint.setColor(Color.GRAY);
		
	}
	void setGraphicsYellow() {
		paint.setColor(Color.YELLOW);
		
	}
	void setGraphicsRed() {
		paint.setColor(Color.RED);
				
	}
	void fillOval(int first , int second, int third, int fourth) {
		paint.setStyle(Paint.Style.FILL);
		//RectF oval = new RectF(first , second, third, fourth);
		//c.drawOval(oval , paint);
		
	}
	void setGraphicsBlack() {
		paint.setColor(Color.BLACK);
		
	}
	void setGraphicsBlue() {
		paint.setColor(Color.BLUE);
		
	}
	void setGraphicsDarkGray() {
		paint.setColor(Color.rgb(90,90,90)); //Alpha not 0
		
	}
	void fillRect(int x , int y, int width , int height) {
		
		paint.setStyle(Paint.Style.FILL);
		c.drawRect(x, y, x+width, y+height, paint);
		
	}
	void setColor(Seg seg) {
		int color = Color.rgb(seg.col[0] , seg.col[1] , seg.col[2]);
		paint.setColor(color);
		
	}
	void fillPolygon( int[] xps , int[] yps , int nPoints) {
		paint.setStyle(Paint.Style.FILL);
		Path wallpath = new Path();
		wallpath.reset();
		wallpath.moveTo(xps[0], yps[0]);
		for(int i = 1 ; i < nPoints ; i++)
			wallpath.lineTo(xps[i], yps[i]);
		wallpath.close();
		c.drawPath(wallpath , paint);
		
	}
	/**
	 * For getting rid of awt in Seg. 
	 * @param col
	 * @return
	 */
	public static int getRGB(int[] col) {
		int color =  Color.rgb(col[0] , col[1] , col[2]);
		
		return color;
	}
	public static int[] returnColorArray(int col) {
		int[] color = new int[3];
		color[0] = Color.red(col);
		color[1] = Color.green(col);
		color[2] = Color.blue(col);
		
		return color;
		
	}
	int one = 0;
	int two = 0;
	@Override
	public void onDraw(Canvas canvas) {
		//drawing things
		canvas.drawBitmap(b,src , dest , paint);
	}
	Rect src;
	Rect dest;
	public void setScreen() {
		// sets the bit map to be drawn on
		int height = this.getLayoutParams().height;
		int width = this.getLayoutParams().width; // assume left to right screen size
		if(height<1)
			height = 400;
		if(width<1)
			width = 400;
		src = new Rect(0,0,400,400);
		dest = new Rect(0,0,400,400);
		b = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		c = new Canvas(b);
		
	}
	@Override
	protected void onMeasure(int widthMeasureSpec , int heightMeasureSpec) {
		setMeasuredDimension(widthMeasureSpec , heightMeasureSpec);
	}
}
	
