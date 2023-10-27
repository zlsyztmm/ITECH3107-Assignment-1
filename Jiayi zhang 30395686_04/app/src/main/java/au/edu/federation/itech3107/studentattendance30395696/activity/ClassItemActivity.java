package au.edu.federation.itech3107.studentattendance30395696.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import au.edu.federation.itech3107.studentattendance30395696.R;
import au.edu.federation.itech3107.studentattendance30395696.db.ClassDatabase;
import au.edu.federation.itech3107.studentattendance30395696.entity.Course;
import au.edu.federation.itech3107.studentattendance30395696.jiekou.CallBackUtils;

public class ClassItemActivity extends Activity {
    private EditText className;
    private EditText classNum;
    private TextView startTime;
    private TextView endTime;
    private Button subBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_item);
        initView();
    }

    private void initView() {
        classNum = (EditText) findViewById(R.id.class_num);
        className = (EditText) findViewById(R.id.class_name);
        startTime = (TextView) findViewById(R.id.start_time);
        endTime = (TextView) findViewById(R.id.end_time);
        subBtn = (Button) findViewById(R.id.sub_btn);

        setTime();

        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event for submitting a new class
                if (TextUtils.isEmpty(classNum.getText().toString())) {
                    Toast.makeText(ClassItemActivity.this, "The course number has not been filled in", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(className.getText().toString())) {
                    Toast.makeText(ClassItemActivity.this, "The course name has not yet been filled in", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (startTime.getText().toString().trim().equals("Click to select a start time")) {
                    Toast.makeText(ClassItemActivity.this, "You haven't chosen a start time", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (endTime.getText().toString().trim().equals("Click to select the end time")) {
                    Toast.makeText(ClassItemActivity.this, "You haven't chosen an end time", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create a new Course object with class details
                final Course course = new Course(System.currentTimeMillis(),
                        classNum.getText().toString(),
                        className.getText().toString(),
                        startTime.getText().toString()
                        , endTime.getText().toString());


                CallBackUtils.doCallBackMethod(course);
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        ClassDatabase.getAppDatabase().classDao().insertClass(course);
                        finish();// Finish the activity
                    }
                }.start();
            }
        });
    }

    boolean checkFlag = false;
    Date date1;

    // Set up time selection using a TimePicker
    private void setTime() {

        TimePickerView pvTime = new TimePickerBuilder(ClassItemActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                checkFlag = true;
                startTime.setText((date.getYear() + 1900)
                        + " " + (date.getMonth() + 1)
                        + " " + date.getDate());

                date1 = dealDays(date, 84);
                endTime.setText((date1.getYear() + 1900) + " " + (date1.getMonth() + 1) + " " + (date1.getDate()));

            }
        }).build();

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show();
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFlag) {

                    // Handle end time selection based on the selected start time
                    final String[] s = startTime.getText().toString().split(" ");

                    Calendar startDate = Calendar.getInstance();
                    Calendar endDate = Calendar.getInstance();

                    startDate.set(Integer.valueOf(s[0]), Integer.valueOf(s[1]), Integer.valueOf(s[2]));

                    final int yea = date1.getYear() + 1900;
                    final int mon = date1.getMonth() + 1;
                    final int dat = date1.getDate();
                    endDate.set(yea, mon, dat);
                    TimePickerView pvTime2 = new TimePickerBuilder(ClassItemActivity.this, new OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            endTime.setText((date.getYear() + 1900)
                                    + " " + (date.getMonth() + 1)
                                    + " " + date.getDate());
                        }
                    }).setRangDate(startDate, endDate).build();
                    pvTime2.show();
                } else {
                    Toast.makeText(ClassItemActivity.this, "Please select a start time", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Helper method to add days to a date
    public static Date dealDays(Date date, int amount) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + amount);
        return now.getTime();
    }
}
