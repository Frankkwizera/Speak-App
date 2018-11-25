package rw.nexin.nexvoice;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech speech;
    EditText txt;
    Button speakBtn;
    Spinner spinner;
    String toSpeak;
    String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Speak");

        txt = (EditText) findViewById(R.id.text);
        speakBtn = (Button) findViewById(R.id.speak);

        spinner = (Spinner) findViewById(R.id.languages);
        String[] items = new String[] {"ENGLISH","FRENCH","ITALIAN","JAPANESE","CHINESE","GERMANY","KOREAN"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,items);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item",(String) parent.getItemAtPosition(position));
                lang = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        speakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toSpeak = txt.getText().toString();
                speech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS){
                            switch (lang){
                                case "ENGLISH":
                                    speech.setLanguage(Locale.ENGLISH);
                                    break;
                                case "FRENCH":
                                    speech.setLanguage(Locale.FRENCH);
                                    break;
                                case "ITALIAN":
                                    speech.setLanguage(Locale.ITALIAN);
                                    break;
                                case "JAPANESE":
                                    speech.setLanguage(Locale.JAPANESE);
                                    break;
                                case "CHINESE":
                                    speech.setLanguage(Locale.CHINESE);
                                    break;
                                case "GERMANY":
                                    speech.setLanguage(Locale.GERMANY);
                                    break;
                                case "KOREAN":
                                    speech.setLanguage(Locale.KOREAN);
                                    break;
                            }
                            speech.speak(toSpeak,TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                });
            }
        });
    }

    public void onPause(){
        if(speech != null){
            speech.stop();
            speech.shutdown();
        }
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
