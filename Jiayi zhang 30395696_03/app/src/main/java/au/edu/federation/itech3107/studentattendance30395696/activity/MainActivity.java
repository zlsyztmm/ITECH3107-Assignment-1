package au.edu.federation.itech3107.studentattendance30395696.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import au.edu.federation.itech3107.studentattendance30395696.R;
import au.edu.federation.itech3107.studentattendance30395696.db.TeacherDatabase;
import au.edu.federation.itech3107.studentattendance30395696.entity.TeacherEntity;


public class MainActivity extends Activity {

    private TextView hintTitle;
    private EditText userName;
    private EditText userPass;
    private Button subBtn;
    private TextView hintBottom;
    private RadioGroup chooseGroup;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Toast.makeText(MainActivity.this, "" + msg.obj.toString(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        hintTitle = (TextView) findViewById(R.id.hint_title);
        userName = (EditText) findViewById(R.id.user_name);
        userPass = (EditText) findViewById(R.id.user_pass);
        subBtn = (Button) findViewById(R.id.sub_btn);
        hintBottom = (TextView) findViewById(R.id.hint_bottom);

        final Intent intent = getIntent();
        String hint_title = intent.getStringExtra("hint_title");
        String hint_bottom = intent.getStringExtra("hint_bottom");
        String sub_btn = intent.getStringExtra("sub_btn");

        hintTitle.setText(hint_title == null ? "Student Attendance System" : "Student Attendance System");
        hintBottom.setText(hint_bottom == null ? "Go to Login" : "");
        subBtn.setText(sub_btn == null ? "Log in" : "Login\n");

        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sub_btn == null) {//登录
                    if (TextUtils.isEmpty(userName.getText().toString().trim()) ||
                            TextUtils.isEmpty(userPass.getText().toString().trim())) {
                        Toast.makeText(MainActivity.this, "Please enter your account ID or password", Toast.LENGTH_SHORT).show();
                    } else {
                        gotoMain();
                    }
                } else {//注册
                    if (TextUtils.isEmpty(userName.getText().toString().trim()) ||
                            TextUtils.isEmpty(userPass.getText().toString().trim())) {
                        Toast.makeText(MainActivity.this, "Please enter your account ID or password", Toast.LENGTH_SHORT).show();
                    } else {
                            gotoDataBase(true);
                    }
                }
            }
        });

        hintBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
                intent1.putExtra("hint_title", "hh");
                intent1.putExtra("hint_bottom", "hh");
                intent1.putExtra("sub_btn", "hh");
                intent1.putExtra("choose_group", "hh");
                startActivity(intent1);
            }
        });
    }

    public void gotoMain() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                TeacherEntity teacherEntity = TeacherDatabase.getAppDatabase().teacherDao().queryTeacher(userName.getText().toString(), userPass.getText().toString());
                if (teacherEntity != null) {

                    if (teacherEntity.flag) {
                        Message message = new Message();
                        message.obj = "Login Successfully";
                        handler.sendMessage(message);
                        Intent intent1 = new Intent(MainActivity.this, ClassActivity.class);
                        startActivity(intent1);
                        finish();
                    } else {
                        Message message = new Message();
                        message.obj = "Students please stop the system";
                        handler.sendMessage(message);
                    }
                } else {
                    Message message = new Message();
                    message.obj = "Login failed. Please check whether the account or password is correct.";
                    handler.sendMessage(message);
                    Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
                }
            }
        }.start();
    }

    public void gotoDataBase(Boolean flag) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                TeacherEntity teacherEntity = TeacherDatabase.getAppDatabase().teacherDao().queryTeacher(userName.getText().toString());
                if (teacherEntity != null) {
                    Message message = new Message();
                    message.obj = "The user account already exists. Please enter another account.";
                    handler.sendMessage(message);
                } else {
                    TeacherDatabase.getAppDatabase().teacherDao().
                            insertTeacher(new TeacherEntity(userName.getText().toString(), userPass.getText().toString(), flag));
                    Message message = new Message();
                    message.obj = "Registered Successfully";
                    handler.sendMessage(message);
                    finish();
                }
            }
        }.start();
    }
}