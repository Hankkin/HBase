package com.hankkin.xlibrary.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.hankkin.xlibrary.utils.SizeUtils;


public class ProgressBar extends CustomView {

	final static String ANDROIDXML = "http://schemas.android.com/apk/res/android";

	int backgroundColor = Color.parseColor("#1E88E5");

	public ProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		setAttributes(attrs);

	}

	protected void setAttributes(AttributeSet attrs) {

		setMinimumHeight(SizeUtils.dp2px(32));
		setMinimumWidth(SizeUtils.dp2px(32));

		int bacgroundColor = attrs.getAttributeResourceValue(ANDROIDXML,
				"background", -1);
		if (bacgroundColor != -1) {
			setBackgroundColor(getResources().getColor(bacgroundColor));
		} else {
			int background = attrs.getAttributeIntValue(ANDROIDXML,
					"background", -1);
			if (background != -1)
				setBackgroundColor(background);
			else
				setBackgroundColor(Color.parseColor("#1E88E5"));
		}

		setMinimumHeight(SizeUtils.dp2px(3));

	}

	protected int makePressColor() {
		int r = (this.backgroundColor >> 16) & 0xFF;
		int g = (this.backgroundColor >> 8) & 0xFF;
		int b = (this.backgroundColor >> 0) & 0xFF;
		return Color.argb(128, r, g, b);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawAnimation(canvas);
		invalidate();

	}

	int arcD = 1;
	int arcO = 0;
	float rotateAngle = 0;
	int limite = 0;

	private void drawAnimation(Canvas canvas) {
		if (arcO == limite)
			arcD += 6;
		if (arcD >= 290 || arcO > limite) {
			arcO += 6;
			arcD -= 6;
		}
		if (arcO > limite + 290) {
			limite = arcO;
			arcO = limite;
			arcD = 1;
		}
		rotateAngle += 4;
		canvas.rotate(rotateAngle, getWidth() / 2, getHeight() / 2);

		Bitmap bitmap = Bitmap.createBitmap(canvas.getWidth(),
				canvas.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas temp = new Canvas(bitmap);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(backgroundColor);
		temp.drawArc(new RectF(0, 0, getWidth(), getHeight()), arcO, arcD,
				true, paint);
		Paint transparentPaint = new Paint();
		transparentPaint.setAntiAlias(true);
		transparentPaint.setColor(getResources().getColor(
				android.R.color.transparent));
		transparentPaint.setXfermode(new PorterDuffXfermode(
				PorterDuff.Mode.CLEAR));
		temp.drawCircle(getWidth() / 2, getHeight() / 2, (getWidth() / 2)
				- SizeUtils.dp2px(4), transparentPaint);

		canvas.drawBitmap(bitmap, 0, 0, new Paint());
	}

	public void setBackgroundColor(int color) {
		super.setBackgroundColor(getResources().getColor(
				android.R.color.transparent));
		if (isEnabled())
			beforeBackground = backgroundColor;
		this.backgroundColor = color;
	}

}
