package com.gotech.tv.launcher.view;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.gotech.tv.launcher.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

public class CustomAnalogClock extends View {

    private Drawable mHourHand;
    private Drawable mMinuteHand;
    private Drawable mSecondHand;
    private Drawable mDial;

    private int mDialWidth;
    private int mDialHeight;
    private boolean mAttached;
    private float mMinutes;
    private float mHour;
    private boolean mChanged;
    private static int SECONDS_FLAG = 0;
    private Message secondsMsg;
    private float mSeconds;
    private boolean bGMTTime = false;

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    invalidate();
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public CustomAnalogClock(Context context) {
        this(context, null);
    }

    public CustomAnalogClock(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomAnalogClock(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Resources r = context.getResources();

        if (mDial == null) {
            mDial = r.getDrawable(R.drawable.clock_dial);
        }

        if (mHourHand == null) {
            mHourHand = r.getDrawable(R.drawable.clock_hand_hour);
        }

        if (mMinuteHand == null) {
            mMinuteHand = r.getDrawable(R.drawable.clock_hand_minute);
        }

        if (mSecondHand == null) {
            mSecondHand = r.getDrawable(R.drawable.clock_hand_second);
        }

        mDialWidth = mDial.getIntrinsicWidth();
        mDialHeight = mDial.getIntrinsicHeight();
    }

    public void setGMTTime(boolean bGMT) {
        bGMTTime = bGMT;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (!mAttached) {
            mAttached = true;
        }

        onTimeChanged();

        initSecondsThread();
    }

    private void initSecondsThread() {
        Thread newThread = new Thread() {
            @Override
            public void run() {
                while (mAttached) {
                    onTimeChanged();
                    secondsMsg = mHandler.obtainMessage(SECONDS_FLAG);
                    mHandler.sendMessage(secondsMsg);

                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        newThread.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mAttached) {
            mAttached = false;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        float hScale = 1.0f;
        float vScale = 1.0f;

        if (widthMode != MeasureSpec.UNSPECIFIED && widthSize < mDialWidth) {
            hScale = (float) widthSize / (float) mDialWidth;
        }

        if (heightMode != MeasureSpec.UNSPECIFIED && heightSize < mDialHeight) {
            vScale = (float) heightSize / (float) mDialHeight;
        }

        float scale = Math.min(hScale, vScale);

        setMeasuredDimension(resolveSize((int) (mDialWidth * scale), widthMeasureSpec), resolveSize((int) (mDialHeight * scale), heightMeasureSpec));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mChanged = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        boolean changed = mChanged;
        if (changed) {
            mChanged = false;
        }

        int availableWidth = getWidth();
        int availableHeight = getHeight();

        int x = availableWidth / 2;
        int y = availableHeight / 2;

        final Drawable dial = mDial;
        int w = dial.getIntrinsicWidth();
        int h = dial.getIntrinsicHeight();

        boolean scaled = false;

        if (availableWidth < w || availableHeight < h) {
            scaled = true;
            float scale = Math.min((float) availableWidth / (float) w, (float) availableHeight / (float) h);
            canvas.save();
            canvas.scale(scale, scale, x, y);
        }

        if (changed) {
            dial.setBounds(x - (w / 2), y - (h / 2), x + (w / 2), y + (h / 2));
        }
        dial.draw(canvas);

        canvas.save();
        canvas.rotate(mHour / 12.0f * 360.0f, x, y);

        final Drawable hourHand = mHourHand;
        if (changed) {
            w = hourHand.getIntrinsicWidth();
            h = hourHand.getIntrinsicHeight();
            hourHand.setBounds(x - (w / 2), y - (h / 2), x + (w / 2), y + (h / 2));
        }
        hourHand.draw(canvas);
        canvas.restore();

        canvas.save();
        canvas.rotate(mMinutes / 60.0f * 360.0f, x, y);

        final Drawable minuteHand = mMinuteHand;
        if (changed) {
            w = minuteHand.getIntrinsicWidth();
            h = minuteHand.getIntrinsicHeight();
            minuteHand.setBounds(x - (w / 2), y - (h / 2), x + (w / 2), y + (h / 2));
        }
        minuteHand.draw(canvas);
        canvas.restore();

        // add second draw
        canvas.save();
        canvas.rotate(mSeconds / 60.0f * 360.0f, x, y);

        final Drawable secondHand = mSecondHand;
        if (changed) {
            w = secondHand.getIntrinsicWidth();
            h = secondHand.getIntrinsicHeight();
            secondHand.setBounds(x - (w / 2), y - (h / 2), x + (w / 2), y + (h / 2));
        }
        secondHand.draw(canvas);
        canvas.restore();

        if (scaled) {
            canvas.restore();
        }
    }

    @SuppressWarnings("deprecation")
    private void onTimeChanged() {
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (bGMTTime) {
            Date gmtDate = getGmtTimer();
            hour = gmtDate.getHours();
            minute = gmtDate.getMinutes();
            second = gmtDate.getSeconds() + 1;
        } else {
            Date curDate = Calendar.getInstance().getTime();
            hour = curDate.getHours();
            minute = curDate.getMinutes();
            second = curDate.getSeconds() + 1;
        }

        mSeconds = second;
        mMinutes = minute + second / 60.0f;
        mHour = hour + mMinutes / 60.0f;

        mChanged = true;
    }

    /**
     * @return　CN:当前GMT时间
     */
    public Date getGmtTimer() {
        Date curDate = Calendar.getInstance().getTime();
        Date GmtDate = changeTimeZone(curDate, TimeZone.getDefault(), TimeZone.getTimeZone("GMT"));
        return GmtDate;
    }

    /**
     * CN:根据指定时区的时间计算另一个时区对应的时间
     *
     * @param date    　CN:指定的时间
     * @param oldZone 　CN:指定的时区
     * @param newZone 　CN:需要计算的时区
     * @return
     */
    public static Date changeTimeZone(Date date, TimeZone oldZone, TimeZone newZone) {
        Date dateTmp = null;
        if (date != null) {
            int timeOffset = oldZone.getOffset(date.getTime()) - newZone.getOffset(date.getTime());
            dateTmp = new Date(date.getTime() - timeOffset);
        }
        return dateTmp;
    }
}
