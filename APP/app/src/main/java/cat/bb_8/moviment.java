package cat.bb_8;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.HashMap;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class moviment extends AppCompatActivity {
    private static final String TAG = "Moviment";
    MqttHelper mqttHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startMqtt();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviment);

        final HashMap<String, Integer> valors=new HashMap<>();

        final TextView textview =findViewById(R.id.textView2);
        final TextView textview2 = findViewById(R.id.textView3);
        JoystickView joystick = findViewById(R.id.joystick);
        joystick.setOnMoveListener((angle, strength) -> {
            String text = "Angle: "+angle+" Strength: "+strength;
            textview.setText(text);
            valors.put("strength", strength);
            valors.put("angle", angle);
            send(valors);
        });
        JoystickView joystick2 = findViewById(R.id.joystick2);
        joystick2.setOnMoveListener((angle, strength) -> {
            String text = "Angle: "+angle+" Strength: "+strength;
            textview2.setText(text);
            valors.put("strength2", strength);
            valors.put("angle2", angle);
            send(valors);
        });

    }
    public void send(HashMap<String, Integer> hashmap){

        TextView missatgetv=findViewById(R.id.textView5);

        String strenght;
        String angle;
        String strenght2;
        String angle2;
        strenght=String.valueOf(hashmap.get("strength"));
        angle=String.valueOf(hashmap.get("angle"));
        strenght2=String.valueOf(hashmap.get("strength2"));
        angle2=String.valueOf(hashmap.get("angle2"));
        try{
            if(!(Integer.parseInt(angle2) >=0)){
                angle2="0";
            }
        } catch (NumberFormatException e) {
            angle2="0";
        }

        String totjunt;
        totjunt="g/"+strenght+"/"+angle+"/"+"0"+"/"+"0"+"/";
        missatgetv.setText(totjunt);
        MqttMessage missatge=new MqttMessage(totjunt.getBytes());
        try {
            mqttHelper.mqttAndroidClient.publish("moviment", missatge);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    private void startMqtt(){
        mqttHelper = new MqttHelper(getApplicationContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
                Log.i(TAG, "Connection Completed");
                Toast.makeText(getApplicationContext(), "MQTT Connection completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void connectionLost(Throwable throwable) {
                Log.i(TAG, "Connection Lost");
                Toast.makeText(getApplicationContext(), "MQTT Connection lost", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) {
                Log.w(TAG, mqttMessage.toString());
                Toast.makeText(getApplicationContext(), "topic --> " + topic + " message --> " + mqttMessage.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                Log.i(TAG, "Delivery Completed");
            }
        });
    }

}