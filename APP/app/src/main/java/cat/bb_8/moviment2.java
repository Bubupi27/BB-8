package cat.bb_8;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class moviment2 extends AppCompatActivity {
    MqttHelper mqttHelper;
    private static final String TAG = "Moviment 2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startMqtt();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviment2);
    }

    public void front(View view) throws MqttException {
        String missatge = "g/"+"100"+"/"+"0"+"/"+"0"+"/"+"0/";
        MqttMessage missatgefinal = new MqttMessage(missatge.getBytes());
        mqttHelper.mqttAndroidClient.publish("moviment", missatgefinal);

    }
    public void back(View view) throws MqttException {
        String missatge = "g/"+"100"+"/"+"180"+"/"+"0"+"/"+"0/";
        MqttMessage missatgefinal = new MqttMessage(missatge.getBytes());
        mqttHelper.mqttAndroidClient.publish("moviment", missatgefinal);
    }

    public void stop(View view) throws MqttException {
        String missatge = "g/"+"0"+"/"+"0"+"/"+"0"+"/"+"0/";
        MqttMessage missatgefinal = new MqttMessage(missatge.getBytes());
        mqttHelper.mqttAndroidClient.publish("moviment", missatgefinal);
    }

    private void startMqtt(){
        mqttHelper = new MqttHelper(getApplicationContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
                Log.i(TAG, "Connection completed");
            }

            @Override
            public void connectionLost(Throwable throwable) {
                Log.w(TAG, "Connection lost");
            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) {
                Log.i(TAG, mqttMessage.toString());
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                Log.i(TAG, "Delivery Completed");
            }
        });
    }
}