package umitkose.iot_demo_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {

    MqttHelper mqttHelper;
    ChartHelper mChart;
    LineChart chart;

    TextView dataReceived;
    TextView textView,textView1,textView2,olcum;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        textView1 = (TextView) findViewById(R.id.textView2);
        textView2 = (TextView) findViewById(R.id.textView3);
        olcum = (TextView) findViewById(R.id.textView5);

        dataReceived = (TextView) findViewById(R.id.dataReceived);
        chart = (LineChart) findViewById(R.id.chart);
        mChart = new ChartHelper(chart);

        startMqtt();
    }

    private void startMqtt(){
        mqttHelper = new MqttHelper(getApplicationContext());
        mqttHelper.mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
                Log.w("Debug","Connected");
            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                Log.w("Debug",mqttMessage.toString());
                switch (topic.toString()){
                    case "sensor/temp":
                        dataReceived.setText(mqttMessage.toString());
                        break;
                    case "sensor/text1":
                        textView.setText(mqttMessage.toString());
                        break;
                    case "sensor/text2":
                        textView1.setText(mqttMessage.toString());
                        break;
                    case "sensor/text3":
                        textView2.setText(mqttMessage.toString());
                        break;
                    case "sensor/olcum":
                        olcum.setText(mqttMessage.toString());
                        olcum.setBackgroundColor(getResources().getColor(R.color.colorStatus));
                        break;

                        default:
                            Log.d("Error","Error ocquired");
                }

       //         dataReceived.setText(mqttMessage.toString());
                mChart.addEntry(Float.valueOf(mqttMessage.toString()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }
}