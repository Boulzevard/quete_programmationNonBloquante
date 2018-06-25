package fr.wcs.quete_programmationnonbloquante;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private boolean isLiftMoving = false;
    private int currentFloor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bouton0 = findViewById(R.id.bt_0);
        Button bouton1 = findViewById(R.id.bt_1);
        Button bouton2 = findViewById(R.id.bt_2);
        Button bouton3 = findViewById(R.id.bt_3);
        Button bouton4 = findViewById(R.id.bt_4);
        Button bouton5 = findViewById(R.id.bt_5);
        Button bouton6 = findViewById(R.id.bt_6);
        Button bouton7 = findViewById(R.id.bt_7);
        Button bouton8 = findViewById(R.id.bt_8);
        Button bouton9 = findViewById(R.id.bt_9);

        bouton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(0);
            }
        });

        bouton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(1);
            }
        });

        bouton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(2);
            }
        });

        bouton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(3);
            }
        });

        bouton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(4);
            }
        });

        bouton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(5);
            }
        });

        bouton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(6);
            }
        });

        bouton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(7);
            }
        });

        bouton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(8);
            }
        });

        bouton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(9);
            }
        });

    }
    private void goToFloor(int floor) {
        if (!isLiftMoving && floor != currentFloor) {
            moveNextFloor(floor);
            isLiftMoving = false;
        }
    }

    private void moveNextFloor(int floor) {
            isLiftMoving = true;
            MoveLift move = new MoveLift();
            move.execute(floor);
    }


    private class MoveLift extends AsyncTask<Integer, Integer, Integer> {
        protected void onPreExecute() {

        }

        protected Integer doInBackground(Integer... etage) {

            while (etage[0] != currentFloor) {
                try {
                    Thread.sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                currentFloor = (etage[0] > currentFloor) ? currentFloor + 1 : currentFloor - 1;
                this.publishProgress(currentFloor);

            }
            isLiftMoving = false;
            return currentFloor;

        }

        protected void onProgressUpdate(Integer... values) {
            TextView floorCount = (TextView) findViewById(R.id.floor_count);
            floorCount.setText(String.valueOf(values[0]));

        }

        protected void onPostExecute(Integer result) {

            Toast.makeText(MainActivity.this, R.string.arrivé, Toast.LENGTH_SHORT).show();
        }
    }

}


