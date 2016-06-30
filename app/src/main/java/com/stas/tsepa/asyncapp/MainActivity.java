package com.stas.tsepa.asyncapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

class MainActivity extends AppCompatActivity {
    private final static String LOG_TAG = MainActivity.class.getSimpleName();

    private static int id = 0;
    private static int taskId = 0;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy" + " " + Integer.toString(id));
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Log.d(LOG_TAG, "onPause" + " " + Integer.toString(id));
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Log.d(LOG_TAG, "onStop" + " " + Integer.toString(id));
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Log.d(LOG_TAG, "onResume" + " " + Integer.toString(id));
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Log.d(LOG_TAG, "onStart" + " " + Integer.toString(id));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.start_button))
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTask();
            }
        });

        id++;
        Log.d(LOG_TAG, "onCreate" + " " + Integer.toString(id));
    }

    private void startTask() {
        taskId++;
        Log.d(LOG_TAG, Integer.toString(id) + " start task " + Integer.toString(taskId));
        new MyTask().execute(taskId);
    }

    private class MyTask extends AsyncTask<Integer, Void, Void> {
        private final String LOG_TAG = MyTask.class.getSimpleName();

        @Override
        protected Void doInBackground(Integer... ints) {
            try {
                Log.d(LOG_TAG, "Execution started" + " task " + Integer.toString(ints[0]));
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Log.d(LOG_TAG, "Execution interrupted" + " task " + Integer.toString(ints[0]));
                return null;
            }
            Log.d(LOG_TAG, "Executed" + " task " + Integer.toString(ints[0]));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d(LOG_TAG, "onPostExecute" + " " + Integer.toString(id));
            // Видно что при повороте onPostExecute отрабатывает уже в новой activity
        }
    }
}