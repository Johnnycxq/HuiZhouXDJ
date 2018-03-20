package com.rehome.huizhouxdj.weight;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.rehome.huizhouxdj.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 时间日期选择器
 * Created by Rehome-rjb1 on 2017/4/10.
 */

public class DateTimePickDialog extends Dialog implements View.OnClickListener, DatePicker.OnDateChangedListener,
        TimePicker.OnTimeChangedListener {

    private TextView tv_dialog_title, tv_dialog_msg, tv_dialog_commit, tv_dialog_cancel;
    private View lay_dialog_title, lay_dialog_cancel, lay_dialog_commit;
    private DateTimePickDialog.CommitClickListener commitClickListener;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private String dateTime, outPutDateTime, outPutDateTime1, outPutDateTime2;
    private Calendar calendar = Calendar.getInstance();
    private Calendar nowCalendar = Calendar.getInstance();//现在时间
    private Calendar tempCalendar = Calendar.getInstance();//历史时间


    private SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DateTimePickDialog(Context context, DateTimePickDialog.CommitClickListener commitClickListener) {
        super(context);
        this.commitClickListener = commitClickListener;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.layout_datetime_dialog);

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (getScreenWidth(context)) * 4 / 5;
        window.setGravity(Gravity.CENTER);

        lay_dialog_title = findViewById(R.id.lay_dialog_title);
        tv_dialog_title = (TextView) findViewById(R.id.dialog_title);

        lay_dialog_cancel = findViewById(R.id.lay_dialog_cancel);
//        lay_dialog_commit = findViewById(R.id.lay_dialog_commit);

        tv_dialog_cancel = (TextView) findViewById(R.id.dialog_cancel);
        tv_dialog_commit = (TextView) findViewById(R.id.dialog_commit);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        if (calendar != null) {
            initDateTime(datePicker, timePicker, calendar);
        }

        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(this);
        tv_dialog_cancel.setOnClickListener(this);
        tv_dialog_commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_cancel:
                dismiss();
                break;
            case R.id.dialog_commit:
                commitClickListener.confirm(outPutDateTime, outPutDateTime1,outPutDateTime2);
                dismiss();
                break;
        }
    }

    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        onDateChanged(null, 0, 0, 0);
    }

    /**
     * 时间改变时候回调
     *
     * @param view
     * @param year        年
     * @param monthOfYear 月
     * @param dayOfMonth  日
     */
    public void onDateChanged(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
        tempCalendar.set(datePicker.getYear(), datePicker.getMonth(),
                datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
                timePicker.getCurrentMinute(),0);

        /**
         * 如果当前时间大于选择的时间，就重置时间
         */
//        if (nowCalendar.getTimeInMillis() > tempCalendar.getTimeInMillis()) {
//            changeDateTime(datePicker, timePicker, nowCalendar);
//        } else {
        calendar = tempCalendar;
        dateTime = simpleDateFormat1.format(calendar.getTime());
        tv_dialog_title.setText(dateTime);
        outPutDateTime = simpleDateFormat2.format(calendar.getTime());
        outPutDateTime1 = simpleDateFormat3.format(calendar.getTime());
        outPutDateTime2 = simpleDateFormat4.format(calendar.getTime());
//        }
    }

    /**
     * 改变时间
     *
     * @param datePicker 日期选择控件
     * @param timePicker 时间选择控件
     * @param calendar   日历类
     */
    private void changeDateTime(DatePicker datePicker, TimePicker timePicker, Calendar calendar) {
        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), this);
        //设置不显示年
        if (datePicker != null) {
            ((ViewGroup) ((ViewGroup) datePicker.getChildAt(0)).getChildAt(0)).getChildAt(0).setVisibility(View.GONE);
        }
        tv_dialog_title.setText(simpleDateFormat1.format(calendar.getTime()));

        //设置显示时分
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
    }

    /**
     * 初始化时间
     *
     * @param datePicker 日期选择控件
     * @param timePicker 时间选择控件
     * @param calendar   日历类
     */
    private void initDateTime(DatePicker datePicker, TimePicker timePicker, Calendar calendar) {
//        calendar.add(Calendar.DATE, 1);
        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), this);
//        设置不显示年
        if (datePicker != null) {
            ((ViewGroup) ((ViewGroup) datePicker.getChildAt(0)).getChildAt(0)).getChildAt(0).setVisibility(View.GONE);
        }

        calendar.set(datePicker.getYear(), datePicker.getMonth(),
                datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
                timePicker.getCurrentMinute(),0);


        tv_dialog_title.setText(simpleDateFormat1.format(calendar.getTime()));
        //设置显示时分
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        tempCalendar = calendar;
        outPutDateTime = simpleDateFormat2.format(calendar.getTime());
        outPutDateTime1 = simpleDateFormat3.format(calendar.getTime());
        outPutDateTime2 = simpleDateFormat4.format(calendar.getTime());
    }

    public interface CommitClickListener {
        /**
         * @param outPutDate  精确到分
         * @param outPutDate1 精确到天
         * @param outPutDate2 精确到秒
         */
        void confirm(String outPutDate, String outPutDate1, String outPutDate2);
    }

    //获取屏幕宽度
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

}
