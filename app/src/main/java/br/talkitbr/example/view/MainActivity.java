package br.talkitbr.example.view;

import android.app.Activity;
import android.os.Bundle;

import br.talkitbr.example.R;

/**
 * Activity que implementa do modo tradicional.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
