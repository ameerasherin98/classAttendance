package app2.garrulousgirl.in.classattendance;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main3Activity extends AppCompatActivity implements itemSelectorInterface {
    RecyclerView subListView;
    String[] papers;
    int labNo=0, subNo=0,pNo;
    PaperAdapter paperAdapter;
    DatabaseReference dataRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent i = getIntent();


        getCount();





    }

    private void createView() {
        subListView = findViewById(R.id.sub_lists);
        paperAdapter = new PaperAdapter(papers, this, this);
        RecyclerView.LayoutManager verticalLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        subListView.setLayoutManager(verticalLayoutManager);
        subListView.setAdapter(paperAdapter);
    }

    public void getCount() {
        dataRef= FirebaseDatabase.getInstance().getReference("PersonalInfo");
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataChild:dataSnapshot.getChildren())
                {
                    for(DataSnapshot labSnap:dataChild.child("Labs").getChildren())
                    {
                        labNo++;

                    }
                    for(DataSnapshot subSnap:dataChild.child("Subjects").getChildren())
                    {
                        subNo++;

                    }


                }

                Log.i("Values are",labNo+"and"+subNo);
                pNo=labNo+subNo;
                createPaperList();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    public void createPaperList()
    {
        papers=new String[pNo];
        dataRef= FirebaseDatabase.getInstance().getReference("PersonalInfo");
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataChild:dataSnapshot.getChildren())
                {
                    int i=0;
                    for(DataSnapshot labSnap:dataChild.child("Labs").getChildren())
                    {
                        papers[i]=labSnap.getValue().toString();

                        Log.i("paper",papers[i]+"and"+i);
                        i++;

                    }
                    for(DataSnapshot subSnap:dataChild.child("Subjects").getChildren())
                    {
                        papers[i]=subSnap.getValue().toString();
                        Log.i("paper",papers[i]+"and"+i);
                        i++;

                    }


                }

                createView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    @Override
    public void onItemSelected(int position) {
        Toast.makeText(this,papers[position]+" Selected",Toast.LENGTH_SHORT).show();

        Intent detail=new Intent(this,subjectDetails.class);
        detail.putExtra("subjects",papers);
        detail.putExtra("index",position);
        detail.putExtra("totalPapers",pNo);
        startActivity(detail);
    }
}
