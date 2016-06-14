package com.example.mrpan.annprogress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by mrpan on 16/6/14.
 */
public class AnnProgress extends View {

    private String TAG=AnnProgress.class.getName();

    private String customText="";
    private float customTextSize=100.0f;
    private float customRadius=20.0f;

    private float customStrokeWidth = 10f;
    private int customTextColor = Color.WHITE;
    private int customInnerColor = Color.RED;
    private int customOuterColor = Color.BLUE;
    private int customProgressColor = Color.WHITE;
    private int customCurrentProgress = 0;
    private int customMaxProgress = 100;

    private Paint customInnerPaint;
    private Paint customOuterPaint;
    private Paint customProgressPaint;
    private TextPaint customTextPaint;

    public AnnProgress(Context context) {
        super(context);
        initView(context,null,0);
    }

    public AnnProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs,0);
    }

    public AnnProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs,defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG,"onMeasure");
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = (int) (getPaddingLeft() + customRadius * 2 + customStrokeWidth * 2 + getPaddingRight());
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = (int) (getPaddingTop() + customRadius * 2 + customStrokeWidth * 2 + getPaddingBottom());
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw");
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        int centerX = contentWidth / 2, centerY = contentHeight / 2;
        float angle = customCurrentProgress / (customMaxProgress * 1.0f) * 360;

        RectF rect = new RectF();
        int viewSize = (int) (customRadius * 2 + customStrokeWidth);
        int left = (int) customStrokeWidth / 2;
        int top = (int) customStrokeWidth / 2;
        int right = left + viewSize;
        int bottom = top + viewSize;
        rect.set(left, top, right, bottom);

        canvas.drawCircle(centerX, centerY, customRadius + customStrokeWidth / 2, customOuterPaint);
        canvas.drawCircle(centerX, centerY, customRadius, customInnerPaint);
        canvas.drawArc(rect, -90, angle, false, customProgressPaint);
        canvas.drawText(customText, centerX, centerY, customTextPaint);
    }

    private void initView(Context context,AttributeSet attrs, int defStyle){
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.AnnProgress, defStyle, 0);
        customText = a.getString(R.styleable.AnnProgress_text);
        customTextColor = a.getColor(R.styleable.AnnProgress_textColor, customTextColor);
        customTextSize = a.getDimension(R.styleable.AnnProgress_textSize, (int) customTextSize);
        customInnerColor = a.getColor(R.styleable.AnnProgress_innerColor, customInnerColor);
        customOuterColor = a.getColor(R.styleable.AnnProgress_outerColor, customOuterColor);
        customProgressColor = a.getColor(R.styleable.AnnProgress_progressColor, customProgressColor);
        customRadius = a.getDimension(R.styleable.AnnProgress_radius, (int) customRadius);
        customStrokeWidth = a.getDimension(R.styleable.AnnProgress_strokeWidth, (int) customStrokeWidth);
        customCurrentProgress = a.getInteger(R.styleable.AnnProgress_currentProgress, customCurrentProgress);
        customMaxProgress = a.getInteger(R.styleable.AnnProgress_maxProgress, customMaxProgress);

        a.recycle();

        customTextPaint = new TextPaint();
        customTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        customTextPaint.setTextAlign(Paint.Align.CENTER);
        customTextPaint.setTypeface(Typeface.MONOSPACE);
        customTextPaint.setTextSize(customTextSize);
        customTextPaint.setColor(customTextColor);

        customInnerPaint = new Paint();
        customInnerPaint.setAntiAlias(true);
        customInnerPaint.setDither(true);
        customInnerPaint.setStyle(Paint.Style.FILL);
        //customInnerPaint.setStrokeWidth(customStrokeWidth);
        customInnerPaint.setColor(customInnerColor);

        customOuterPaint = new Paint();
        customOuterPaint.setAntiAlias(true);
        customOuterPaint.setStyle(Paint.Style.STROKE);
        customOuterPaint.setStrokeWidth(customStrokeWidth);
        customOuterPaint.setColor(customOuterColor);

        customProgressPaint = new Paint();
        customProgressPaint.setAntiAlias(true);
        customProgressPaint.setStyle(Paint.Style.STROKE);
        customProgressPaint.setStrokeWidth(customStrokeWidth);
        customProgressPaint.setColor(customProgressColor);
    }

    public void setCustomText(String text){
        customText=text;
        invalidate();
    }
    public void setCustomMaxProgress(int maxProgress){
        customMaxProgress=maxProgress;
        invalidate();
    }
    public void setCustomCurrentProgress(int CurrentProgress){
        customCurrentProgress=CurrentProgress;
        invalidate();
    }
    public void setCustomInnerColor(int color){
        customInnerColor=color;
        invalidate();
    }
    public void setCustomOuterColor(int color){
        customOuterColor=color;
        invalidate();
    }
    public void setCustomTextColor(int color){
        customTextColor=color;
        invalidate();
    }
}
