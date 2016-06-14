package dreoapps.timertest;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import dreoapps.timertest.timer.Timer;

public class MainActivity extends AppCompatActivity implements Timer.OnTimerStateChangeListener {

    private Timer timer;

    private TextView meter;

    private Button start,stop,pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meter = (TextView) findViewById(R.id.meter);

        start = (Button) findViewById(R.id.btn_start);

        stop = (Button) findViewById(R.id.btn_stop);

        pause = (Button) findViewById(R.id.btn_pause);

        timer = new Timer();

        timer.setOnTimerStateChangeListener(this);







        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timer.start();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.stop();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timer.pause();

            }
        });

    }

    @Override
    public void onTimerTick(long time) {

        meter.setText(time+"");

    }

    @Override
    public void onTimerStart() {

        Toast.makeText(this,"Started",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTimerStop() {

        Toast.makeText(this,"Stoped",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTimerPause() {

        Toast.makeText(this,"PAUSED",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTimerStopPause() {

        Toast.makeText(this,"PAUSED STOPED",Toast.LENGTH_SHORT).show();

    }
}
