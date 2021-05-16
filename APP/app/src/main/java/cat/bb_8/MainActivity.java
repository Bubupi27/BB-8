package cat.bb_8;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int vegades = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String ip_guardada = sharedPreferences.getString("ip", "192.168.43.30");
        if(variables.getip().equals("tcp://192.168.43.30")){
            variables.setip(ip_guardada);
        }
        vegades++;
    }


    public void sensors(View view){
        startActivity(new Intent(this, sensors.class));
    }
    public void camara(View view){
        startActivity(new Intent(this, camara.class));
    }
    public void moviment(View view){
        startActivity(new Intent(this, moviment.class));
    }
    public void moviment2(View view){
        startActivity(new Intent(this, moviment2.class));
    }

    public void settings(View view){
        startActivity(new Intent(this, settings.class));
    }


}