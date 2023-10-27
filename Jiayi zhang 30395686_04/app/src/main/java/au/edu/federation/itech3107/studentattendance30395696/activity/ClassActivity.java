package au.edu.federation.itech3107.studentattendance30395696.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.io.Serializable;
import java.util.List;

import au.edu.federation.itech3107.studentattendance30395696.R;
import au.edu.federation.itech3107.studentattendance30395696.db.ClassDatabase;
import au.edu.federation.itech3107.studentattendance30395696.entity.Course;
import au.edu.federation.itech3107.studentattendance30395696.jiekou.CallBack;
import au.edu.federation.itech3107.studentattendance30395696.jiekou.CallBackUtils;

public class ClassActivity extends Activity implements CallBack {
    private TextView addClass;
    private RecyclerView recy;
    ProRecyAdapter recyAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        initView();

        CallBackUtils.setCallBack(this);
    }

    private void initView() {
        addClass = (TextView) findViewById(R.id.add_class);
        recy = (RecyclerView) findViewById(R.id.recy);

        recy.setLayoutManager(new LinearLayoutManager(this));

        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClassActivity.this, ClassItemActivity.class));
            }
        });

        new Thread() {
            @Override
            public void run() {
                super.run();
                List<Course> courses = ClassDatabase.getAppDatabase().classDao().queryAll();

                recyAdapter = new ProRecyAdapter(R.layout.item_class, courses);

                recyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Course item = (Course) adapter.getItem(position);

                        Intent intent = new Intent(ClassActivity.this, ClassDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("OBJ", (Serializable) item);
                        intent.putExtra("bundle", bundle);
                        startActivity(intent);
                    }
                });
                recyAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

                        AlertDialog.Builder bui = new AlertDialog.Builder(ClassActivity.this);

                        bui.setTitle("Whether to delete the course");

                        bui.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ClassActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                                Course item = (Course) adapter.getItem(position);
                                new Thread() {
                                    @Override
                                    public void run() {
                                        super.run();
                                        ClassDatabase.getAppDatabase().classDao().delete(item);
                                    }
                                }.start();
                                recyAdapter.remove(position);
                                recyAdapter.notifyDataSetChanged();
                            }
                        });
                        bui.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        bui.show();

                        return true;
                    }
                });

                recyAdapter.setAddClick(new ProRecyAdapter.AddClick() {
                    @Override
                    public void onAddClick(Course course) {
                        Intent intent = new Intent(ClassActivity.this, AddStudentActivity.class);
                        intent.putExtra("tbName", String.valueOf(course.classID));
                        startActivity(intent);
                    }
                });
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recy.setAdapter(recyAdapter);
                    }
                });
            }
        }.start();
    }

    // Define a custom RecyclerView adapter
    public static class ProRecyAdapter extends BaseQuickAdapter<Course, ProRecyAdapter.ViewHo> {
        public ProRecyAdapter(int layoutResId, List<Course> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(ProRecyAdapter.ViewHo helper, Course item) {
            helper.numTextView.setText("Course Number:" + item.num);
            helper.nameTextView.setText("Course Name:" + item.name);
            helper.startTextView.setText("Start Time:" + item.startTime);
            helper.endTextView.setText("End Time:" + item.endTime);

            helper.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addClick.onAddClick(item);
                }
            });
        }

        // Interface for handling additional click events
        AddClick addClick;

        public void setAddClick(AddClick addClick) {
            this.addClick = addClick;
        }

        interface AddClick {
            void onAddClick(Course course);
        }

        // ViewHolder for the RecyclerView
        class ViewHo extends BaseViewHolder {
            private TextView nameTextView;
            private TextView numTextView;
            private TextView startTextView;
            private TextView endTextView;

            private Button button;

            public ViewHo(View view) {
                super(view);
                nameTextView = view.findViewById(R.id.name_text);
                numTextView = view.findViewById(R.id.date_text);
                startTextView = view.findViewById(R.id.start_text);
                endTextView = view.findViewById(R.id.end_text);
                button = view.findViewById(R.id.add_btn);
            }
        }
    }

    // Callback implementation for doing something with a Course object
    @Override
    public void doSomeThing(Course course) {
        Message obtain = Message.obtain();
        obtain.obj = course;
        handler.sendMessage(obtain);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Course course = (Course) msg.obj;
            recyAdapter.addData(course);
        }
    };

}
