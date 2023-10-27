package au.edu.federation.itech3107.studentattendance30395696.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;

import java.util.ArrayList;

import au.edu.federation.itech3107.studentattendance30395696.R;
import au.edu.federation.itech3107.studentattendance30395696.db.StudentDatabase;
import au.edu.federation.itech3107.studentattendance30395696.entity.Student;

public class AddStudentActivity extends Activity {

    private TextView addText;
    private LinearLayoutCompat lin;

    ArrayList<EditText> list1 = new ArrayList<>();
    ArrayList<EditText> list2 = new ArrayList<>();
    private Button savaBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        initView();
    }

    private void initView() {
        addText = (TextView) findViewById(R.id.add_text);
        lin = (LinearLayoutCompat) findViewById(R.id.lin);

        addText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }// Call the add() method when the TextView is clicked
        });

        add();

        final Intent intent = getIntent();
        final String tbName = intent.getStringExtra("tbName");

        Log.i("TAG123", "initView: " + tbName);

        savaBtn = (Button) findViewById(R.id.sava_btn);
        savaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean flag = false;

                for (int i = 0; i < list1.size(); i++) {
                    String id = list1.get(i).getText().toString();
                    String name = list2.get(i).getText().toString();
                    if (id.length() <= 0 || name.length() <= 0) {
                        flag = true;
                        Toast.makeText(AddStudentActivity.this, "Student number or name not entered", Toast.LENGTH_SHORT).show();
                        return;// Show a Toast message and return if student number or name is empty
                    }
                }

                for (int i = 0; i < list1.size(); i++) {
                    String id = list1.get(i).getText().toString();
                    String name = list2.get(i).getText().toString();
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            // Insert student information into the database in a new thread
                            StudentDatabase.getAppDatabase(tbName).studentDao()
                                    .insertStudent(new Student(id, name, false));
                        }
                    }.start();
                }
                finish();
            }
        });
    }

    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    private void add() {
        final View inflate = LayoutInflater.from(this).inflate(R.layout.item_student, null);
        final EditText id = inflate.findViewById(R.id.stu_id);
        final EditText name = inflate.findViewById(R.id.stu_name);
        list1.add(id);
        list2.add(name);
        lin.addView(inflate);
    }
}
