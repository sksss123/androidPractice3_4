package kr.ac.kookmin.exer3_4.exer3_4;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mMplayer;
    private Button bt_start;
    private Button bt_stop;
    private ListView lv_music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create MediaPlayer object
        mMplayer = new MediaPlayer();

        // create Button object and added Event lisener.
        bt_start = (Button) findViewById(R.id.bt_start);
        bt_stop = (Button) findViewById(R.id.bt_stop);
        lv_music = (ListView) findViewById(R.id.lv_music);


        // Prepare mp3 file for MediaPlayer
        try {
            AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.boysandgirls);
            mMplayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        } catch(Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        // Music Start
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mMplayer.prepare();
                    mMplayer.start();
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Music Stop
        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMplayer.stop();
            }
        });


        // Create listview
        final String[] musicList = {"Boys and Girls", "No Make Up", "Zeze"};
        ArrayAdapter<String> musicAdapter;
        musicAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, musicList);
        lv_music.setAdapter(musicAdapter);
        lv_music.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "click : "+ musicList[position], Toast.LENGTH_LONG).show();
            }
        });

    }
}
