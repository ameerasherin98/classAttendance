package app2.garrulousgirl.in.classattendance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class Main2Activity extends AppCompatActivity {
    public static EditText textView[]=new EditText[100];
    ArrayList<EditText> SubEditText=new ArrayList<>();
    ArrayList<EditText> LabEditText=new ArrayList<>();
    String subjects[],labs[],UId;
    int no_of_sub,no_of_lab;
    FirebaseDatabase root;
    DatabaseReference subReference,labReference,dataRef;
    String key;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent step2=getIntent();
        no_of_sub = step2.getIntExtra("sub_nos", 0);
        no_of_lab=step2.getIntExtra("lab_nos",0);


        dataRef=FirebaseDatabase.getInstance().getReference().child("PersonalInfo");
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataChild:dataSnapshot.getChildren())
                {
                    key=dataChild.getKey().toString();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        makeSubLists(no_of_sub,no_of_lab);


    }

    public void makeSubLists(int no_of_sub,int no_of_lab) {

        for (int i=0;i<no_of_sub;i++)
        {
            textView[i]=new EditText(this);
            SubEditText.add(textView[i]);
            textView[i].setId(i);
            textView[i].setHint("Subject");
            textView[i].setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            LinearLayout ll=findViewById(R.id.tsub_menu);
            ll.addView(textView[i]);

        }

        for (int i=0;i<no_of_lab;i++)
        {
            textView[i]=new EditText(this);
            LabEditText.add(textView[i]);
            textView[i].setId(i);
            textView[i].setHint("Lab");
            textView[i].setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            LinearLayout ll=findViewById(R.id.lsub_menu);
            ll.addView(textView[i]);

        }

    }
    public void build() {
        root=FirebaseDatabase.getInstance();
        subReference=root.getReference("PersonalInfo").child(key);
        subjects = new String[no_of_sub];
        for (int i = 0; i < no_of_sub; i++) {
            subjects[i] = SubEditText.get(i).getText().toString();
            subReference.child("Subjects").push().setValue(subjects[i]);
        }

        labReference=root.getReference("PersonalInfo").child(key);
        labs = new String[no_of_lab];
        for (int i = 0; i < no_of_lab; i++) {
            labs[i] = LabEditText.get(i).getText().toString();
            labReference.child("Labs").push().setValue(labs[i]);

        }
    }

    public void next(View view){
        if (validatedInput()) {
            build();
        }

        SharedPreferences sharedPreferences=Main2Activity.this.getSharedPreferences("RegInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("isRegistered",true);
        editor.commit();


        Intent i=new Intent(this, Main3Activity.class);
        i.putExtra("subjects",subjects);
        i.putExtra("labs",labs);
        i.putExtra("no_of_subs",no_of_sub);
        i.putExtra("no_of_labs",no_of_lab);
        startActivity(i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));

        }

    private boolean validatedInput() {
        int flag = 0;
        for (int i = 0; i < no_of_sub; i++) {
            if (SubEditText.get(i).getText().toString().isEmpty())
                flag = 1;
        }
        for (int i = 0; i < no_of_lab; i++) {
            if (LabEditText.get(i).getText().toString().isEmpty())
                flag = 1;
        }
        return flag != 1;
    }

}
