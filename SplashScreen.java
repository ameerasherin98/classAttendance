package app2.garrulousgirl.in.classattendance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SharedPreferences sharedPref=this.getSharedPreferences("RegInfo",Context.MODE_PRIVATE);
        Boolean ifUserRegistered=sharedPref.getBoolean("isRegistered",false);
        if(ifUserRegistered)
        {
            Intent listView=new Intent(this,Main3Activity.class);
            startActivity(listView.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));
        }
        else
        {
            Intent regView=new Intent(this,MainActivity.class);
            startActivity(regView);
        }
    }
}
