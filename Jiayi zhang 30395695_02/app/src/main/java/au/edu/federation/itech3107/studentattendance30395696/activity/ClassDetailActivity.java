package au.edu.federation.itech3107.studentattendance30395696.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import au.edu.federation.itech3107.studentattendance30395696.R;
import au.edu.federation.itech3107.studentattendance30395696.db.StudentDatabase;
import au.edu.federation.itech3107.studentattendance30395696.entity.Course;
import au.edu.federation.itech3107.studentattendance30395696.entity.Student;

public class ClassDetailActivity extends Activity {

    private TextView nameText;
    private TextView dateText;
    private TextView startText;
    private TextView endText;
    private RecyclerView recy;
    private Button savaBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);
        initView();
    }

    Course course;

    private void initView() {

        final Intent intent = getIntent();
        final Bundle bundleExtra = intent.getBundleExtra("bundle");
        course = (Course) bundleExtra.getSerializable("OBJ");


        nameText = (TextView) findViewById(R.id.name_text);
        dateText = (TextView) findViewById(R.id.date_text);
        startText = (TextView) findViewById(R.id.start_text);
        endText = (TextView) findViewById(R.id.end_text);
        recy = (RecyclerView) findViewById(R.id.recy);
        recy.setLayoutManager(new LinearLayoutManager(this));

        nameText.setText("课程号:" + course.num);
        nameText.setText("课程名字:" + course.name);
        startText.setText("开始时间:" + course.startTime);
        endText.setText("结束时间:" + course.endTime);


        submit();
        savaBtn = (Button) findViewById(R.id.sava_btn);

        savaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                HashMap<String, Boolean> flagList = ((StudentRecyAdapter) recy.getAdapter()).getFlagList();

                Iterator<Map.Entry<String, Boolean>> iterator = flagList.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Boolean> next = iterator.next();
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            StudentDatabase.getAppDatabase(String.valueOf(course.classID)).studentDao().changeStudentFlag(Long.valueOf(next.getKey().trim()), next.getValue());
                            submit();
                        }
                    }.start();
                }

                Toast.makeText(ClassDetailActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void submit() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                final List<Student> list = StudentDatabase.getAppDatabase(String.valueOf(course.classID)).studentDao().queryAllStudent();

                for (Student student : list) {
                    Log.i("TAG123", "run: "+student.toString());
                }

                StudentRecyAdapter adapter = new StudentRecyAdapter(R.layout.item_student_sta, list);
                
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recy.setAdapter(adapter);
                    }
                });
            }
        }.start();
    }


    public static class StudentRecyAdapter extends BaseQuickAdapter<Student, StudentRecyAdapter.ViewHo> {
        
        private HashMap<String,Boolean> flagList;
        
        public StudentRecyAdapter(int layoutResId, List<Student> data) {
            super(layoutResId, data);
            flagList = new HashMap<>();
        }

        public HashMap<String, Boolean> getFlagList() {
            return flagList;
        }

        @Override
        protected void convert(ViewHo helper, Student item) {
            helper.idTextView.setText("id:\n" + item.id);
            helper.nameTextView.setText("姓名:\n" + item.name);

            
            flagList.put(String.valueOf(item.m_id),item.flag);
            helper.flag.check(item.flag ? R.id.radio_left : R.id.radio_right);
            
            helper.flag.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.radio_left) {//一出戏
                        flagList.put(String.valueOf(item.m_id),true);
                    } else {
                        flagList.put(String.valueOf(item.m_id),false);
                    }
                    
                }
            });
        }

        StudentFlagClick flagClick;


        public void setAddClick(StudentFlagClick flagClick1) {
            this.flagClick = flagClick1;
        }

        interface StudentFlagClick {
            void onflagClick(Student student);
        }

        class ViewHo extends BaseViewHolder {
            private TextView idTextView;
            private TextView nameTextView;
            private RadioGroup flag;


            public ViewHo(View view) {
                super(view);
                idTextView = view.findViewById(R.id.stu_id);
                nameTextView = view.findViewById(R.id.stu_name);
                flag = view.findViewById(R.id.stu_flag);
            }
        }
    }
}
