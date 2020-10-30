package app2.garrulousgirl.in.classattendance;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase root;
    DatabaseReference personalInfoRef, dataRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void intent(View view)
    {
        EditText SubNos=findViewById(R.id.sub_no);
        int sn=Integer.valueOf(SubNos.getText().toString());
        EditText LabNos=findViewById(R.id.lab_no);
        int ln=Integer.valueOf(LabNos.getText().toString());
        EditText studentName=findViewById(R.id.student_name);
        String name=studentName.getText().toString();
        EditText sem=findViewById(R.id.student_sem);
        int semNo=Integer.valueOf(sem.getText().toString());
        EditText dept=findViewById(R.id.student_dept);
        String Dept=dept.getText().toString();
        PersonalInfo stud=new PersonalInfo(name,semNo,Dept,sn,ln);



        root=FirebaseDatabase.getInstance();
        personalInfoRef=root.getReference("PersonalInfo");
        personalInfoRef.push().setValue(stud);


//        dataRef=FirebaseDatabase.getInstance().getReference().child("PersonalInfo");
//        dataRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot dataChild:dataSnapshot.getChildren())
//                {
//                    String key=dataChild.getKey().toString();
//                    Log.i("keyData",key);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        Intent step2 = new Intent(this, Main2Activity.class);
        step2.putExtra("sub_nos",sn);
        step2.putExtra("lab_nos",ln);
        startActivity(step2);
    }
}
