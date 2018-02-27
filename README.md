# CustomCountDownTimer
倒计时  用于 TextView  Button


#### 一、CustomCountDownTimer具体用法

```
	new CustomCountDownTimer.Build(MainActivity.this)
                        .setView(mTvCountDown)    // 设置目标 View
                        .setMillisInFuture(6000)    // 设置倒计时 开始时间  单位 毫秒
                        .setCountDownInterval(1000)  // 设置倒计时  的时间间隔  单位 毫秒
                        .setEndTextSize(12)     // 设置 倒计时中 字体大小
                        .setTickTextSize(12)    // 设置 结束后  字体大小
                        .setEndTextColor(R.color.c_177be6)  // 设置 结束后 字体颜色
                        .setTickTextColor(R.color.c_51)   // 设置 倒计时中 字体颜色
                        .setEndToZero(true)     // 设置 是否从零结束
                        .setEndText("倒计时")    // 设置结束时的 文字
                        .setTickPrefix("")  // 设置倒计时 的前缀
                        .setTickSuffix(" S")  // 设置倒计时 的后缀
                        .build()
                        .start();
```


#### 二、CircleProgressBar具体用法

```
	<com.enci.library.view.CircleProgressBar
		android:id="@+id/circleProgressBar"
		android:layout_width="35dp"	
		android:layout_height="35dp"
		android:layout_marginTop="20dp"
		app:line_count="30"
		app:line_width="1dp"
		app:progress_background_color="@android:color/holo_green_light"	
		app:progress_end_color="@android:color/holo_red_dark"
		app:progress_shader="sweep"
		app:progress_start_color="@android:color/holo_red_dark"
		app:progress_stroke_cap="round"
		app:progress_stroke_width="2dp"	
		app:progress_text_center="跳过"	// ProgresBar 中间的字体, 如果不设置的话, 则默认为:百分比进度
		app:progress_text_color="@android:color/darker_gray"
		app:progress_text_size="13sp"	// 字体大小, 不设置默认为 11sp
		app:style="solid_line" />
```
      CircleProgressBar的其他用法  详细参见: https://github.com/dinuscxj/CircleProgressBar.git
