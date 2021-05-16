package cat.bb_8;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class sensors extends AppCompatActivity {

    MqttHelper mqttHelper;
    TextView rebut;
    TextView temperatura_textview;
    TextView humitat_textview;
    TextView distancia_textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
        rebut=findViewById(R.id.rebut);
        temperatura_textview=findViewById(R.id.temperaturavalue);
        humitat_textview=findViewById(R.id.humitatvalue);
        distancia_textview=findViewById(R.id.distanciavalue);
        startMqtt();
    }

    public void send(View view){
        MqttMessage hola=new MqttMessage("hola".getBytes());
        try {
            mqttHelper.mqttAndroidClient.publish("hola/adeu", hola);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void startMqtt(){
        mqttHelper = new MqttHelper(getApplicationContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {

            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) {
                Log.w("Debug",mqttMessage.toString());
                String texttotjunt=mqttMessage.toString();
                imprimirvalors(texttotjunt);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }

    public void imprimirvalors(String texttotjunt){
        String[] separat=texttotjunt.split("//");
        String temperatura=separat[0]+"°C";
        String humitat=separat[1]+"%";
        String distancia=separat[2]+"cm";

        rebut.setText(texttotjunt);
        temperatura_textview.setText(temperatura);
        humitat_textview.setText(humitat);
        distancia_textview.setText(distancia);
    }
}