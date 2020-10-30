package app2.garrulousgirl.in.classattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class subjectDetails extends AppCompatActivity {
    int index;
    int totalPapers;
    AttendanceDetails[] ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);


        Intent detail=getIntent();
        String[] subject=detail.getStringArrayExtra("subjects");
        index=detail.getIntExtra("index",0);
        totalPapers=detail.getIntExtra("totalPapers",0);
        ad=new AttendanceDetails[totalPapers];


        for(int i=0;i<totalPapers;i++){
            if(i==index)
            {
                TextView subName=findViewById(R.id.sub_name);
                subName.setText(subject[i]);

            }
        }
//        getDetails();

    }

    public void getDetails() {
        String summary = "Total no.of Hours: " + ad[index].totalDays + "\n" +
                "No.of Hours present: " + ad[index].present + "\n" +
                "No.of Hours absent: " + ad[index].absent + "\n";
        TextView details=findViewById(R.id.details);
        details.setText(summary);
    }


    public void present(View view){
        ad[index].totalDays=+1;
        ad[index].present=+1;
        getDetails();
    }
    public void absent(View view){
        ad[index].totalDays=+1;
        ad[index].absent=+1;
        getDetails();

    }
}
