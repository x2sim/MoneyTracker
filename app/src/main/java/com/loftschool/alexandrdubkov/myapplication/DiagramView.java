package com.loftschool.alexandrdubkov.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

public class DiagramView extends View {
    private Paint expensePaint = new Paint();
    private Paint incomePaint = new Paint();
    private int expence;
    private int income;
    public DiagramView(final Context context) {
        super(context);
        init(context, null);
    }

    public DiagramView(final Context context,final AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DiagramView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs);
        init(context, attrs);
    }

    public DiagramView(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }
    public void init (Context context, AttributeSet attrs)
    {
        if (attrs!=null){
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DiagramView, 0, 0);
            expensePaint.setColor(ContextCompat.getColor(context, typedArray.getResourceId(R.styleable.DiagramView_expenseColor, R.color.dark_sky_blue)));
            incomePaint.setColor(ContextCompat.getColor(context, typedArray.getResourceId(R.styleable.DiagramView_incomeColor, R.color.apple_green)));
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    protected void update(int income, int expence) {
        this.income = income;
        this.expence = expence;
        invalidate();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        final int total = expence + income;
        if (total == 0) {
            return;
        }
        float expenseAngle = 360f * expence/ total;
        float incomeAngle = 360f * income/ total;
        int space = 10;
        int size = Math.min(getWidth(), getHeight()) - space*2;
        int xMargin = (getWidth() - size)/2;
        int yMargin = (getHeight() - size)/2;
        canvas.drawArc(xMargin - space, yMargin, getWidth() - xMargin - space, getHeight()- yMargin, 180 - expenseAngle/2, expenseAngle,true, expensePaint);
        canvas.drawArc(xMargin + space, yMargin, getWidth() - xMargin + space, getHeight() - yMargin, 360 - incomeAngle/ 2, incomeAngle, true, incomePaint);
        }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }



}
