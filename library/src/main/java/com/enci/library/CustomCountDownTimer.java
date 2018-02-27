package com.enci.library;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 倒计时
 *
 *  注意: TextView 中:
 *                   setTextSize() 的单位是sp
 *                   getTextSize() 的单位是px
 *
 *                   dimen文件中拿到的值的单位也是 px
 *                   如: mContext.getResources().getDimension(R.dimen.text_size_20)  获取到的是 转换为 px 的值
 *
 * @author hui
 * @version 1.0
 * @since 2018/2/24
 */
public class CustomCountDownTimer extends CountDownTimer {

    private TextView mView;

    private String mEndText = "获取验证码";

    private String mTickPrefix = "";

    private String mTickSuffix = " S";

    private float mEndTextSize = 0;

    private float mTickTextSize = 0;

    private int mTickTextColor = 0;

    private int mEndTextColor = 0;

    private Context mContext;

    /**
     * 是否显示零
     */
    private boolean mIsEndToZero;



    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CustomCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }


    public CustomCountDownTimer(long millisInFuture, long countDownInterval, TextView view, Build build) {
        this(millisInFuture + 1000, countDownInterval);
        mView = view;

        // 重新设置 View 的宽度, 用于Width 为 WrapContent 时, 可以 倒计时前后字体的宽度保持一致
        int width = mView.getWidth();
        ViewGroup.LayoutParams layoutParams = mView.getLayoutParams();
        layoutParams.width = width;
        mView.setLayoutParams(layoutParams);

        mContext = build.mContext;
        mEndText = build.mEndText;
        mTickPrefix = build.mTickPrefix;
        mTickSuffix = build.mTickSuffix;
        mEndTextSize = (build.mEndTextSize == 0 ? px2sp(mContext, mView.getTextSize()) : build.mEndTextSize);
        mTickTextSize = (build.mTickTextSize == 0 ? px2sp(mContext, mView.getTextSize()) : build.mTickTextSize);
        mTickTextColor = build.mTickTextColor;
        mEndTextColor = build.mEndTextColor;
        mIsEndToZero = build.isEndToZero;

//        LogUtil.d("mEndTextSize::" + mEndTextSize);
//        LogUtil.d("mTickTextSize::" + mTickTextSize);
//        LogUtil.d("mViewSize:" + mView.getTextSize());
    }


    /**
     * 倒计时 时回调
     *
     * @param millisUntilFinished 时间
     */
    @Override
    public void onTick(long millisUntilFinished) {
        if (mView != null) {
            mView.setText(mTickPrefix + (millisUntilFinished - (mIsEndToZero ? 1000 : 0)) / 1000 + mTickSuffix);
            mView.setTextColor(mContext.getResources().getColor(mTickTextColor));
            mView.setTextSize(mTickTextSize);
            mView.setClickable(false);
        }
    }

    @Override
    public void onFinish() {
        if (mView != null) {
            mView.setText(mEndText);
            mView.setTextColor(mContext.getResources().getColor(mEndTextColor));
            mView.setTextSize(mEndTextSize);
            mView.setClickable(true);
        }
    }

    /**
     * 设置目标 View
     *
     * @param view 目标 View
     */
    public void setView(TextView view) {
        mView = view;
    }


    public static class Build {

        TextView mView;

        private String mEndText = "获取验证码";

        private String mTickPrefix = "";

        private String mTickSuffix = "S";

        private float mEndTextSize = 0;

        private float mTickTextSize = 0;

        long millisInFuture;

        long countDownInterval;

        private int mTickTextColor = android.R.color.darker_gray;

        private int mEndTextColor = android.R.color.holo_blue_bright;


        private Context mContext;

        /**
         * 是否显示零
         */
        private boolean isEndToZero = false;

        public Build(Context context) {
            mContext = context;
        }

        public Build setEndText(String endText) {
            mEndText = endText;
            return this;
        }

        public Build setTickPrefix(String tickPrefix) {
            mTickPrefix = tickPrefix;
            return this;
        }

        public Build setTickSuffix(String tickSuffix) {
            mTickSuffix = tickSuffix;
            return this;
        }

        /**
         * 停止时 文字的大小  单位 SP
         *
         * @param endTextSize 字体大小
         * @return Build
         */
        public Build setEndTextSize(float endTextSize) {
            mEndTextSize = endTextSize;
            return this;
        }

        /**
         * 倒计时 文字的大小  单位 SP
         *
         * @param tickTextSize 字体大小
         * @return Build
         */
        public Build setTickTextSize(float tickTextSize) {
            mTickTextSize = tickTextSize;
            return this;
        }

        public Build setTickTextColor(int tickTextColor) {
            mTickTextColor = tickTextColor;
            return this;
        }

        public Build setEndTextColor(int endTextColor) {
            mEndTextColor = endTextColor;
            return this;
        }

        public Build setView(TextView view) {
            mView = view;
            return this;
        }

        public Build setMillisInFuture(long millisInFuture) {
            this.millisInFuture = millisInFuture;
            return this;
        }

        public Build setCountDownInterval(long countDownInterval) {
            this.countDownInterval = countDownInterval;
            return this;
        }

        public Build setEndToZero(boolean endToZero) {
            isEndToZero = endToZero;
            return this;
        }

        public CustomCountDownTimer build() {
            if (mView == null) {
                throw new IllegalArgumentException("请设置目标 View, 通过 Build.setView() 方法");
            }
            if (millisInFuture == 0) {
                throw new IllegalArgumentException("请设置倒计时长");
            }
            if (countDownInterval == 0) {
                throw new IllegalArgumentException("请设置倒计时时间间隔");
            }
            return new CustomCountDownTimer(millisInFuture, countDownInterval, mView, this);
        }
    }

    /**
     * sp转px
     *
     * @param context
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }


    /**
     * px转sp
     *
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

}
