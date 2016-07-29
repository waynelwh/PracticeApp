package polyu.comp.wayne.practiceapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;

import android.widget.Button;
import android.widget.ImageButton;
import android.view.View;
import android.view.View.OnClickListener;
import android.media.MediaRecorder;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;
import android.content.ClipData;
import android.content.ClipboardManager;

//import java.text.DateFormat;
import java.util.Date;
//import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {
    ImageButton recordButton;
    Button largerButton,smallerButton,colorButton,copyButton,clearButton;
    LinearLayout colors;
    private static TextView log;
    private String outputFile = null;
    private MediaRecorder audioRecorder;
    private boolean recordingState = false;
    private ClipboardManager clipboard;
    private float size;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordButton = (ImageButton)findViewById(R.id.recordButton);
        largerButton = (Button)findViewById(R.id.largerButton);
        smallerButton = (Button)findViewById(R.id.smallerButton);
        colorButton = (Button)findViewById(R.id.colorButton);
        copyButton = (Button)findViewById(R.id.copyButton);
        clearButton = (Button)findViewById(R.id.clearButton);

        log = (TextView)findViewById(R.id.log);
        log.setMovementMethod(new ScrollingMovementMethod());
        size = log.getTextSize();

        colors = (LinearLayout)findViewById(R.id.colors);

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";

        clipboard= (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

//        audioRecorder = new MediaRecorder();
//        audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//        audioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
//        audioRecorder.setAudioChannels(1);
//        audioRecorder.setAudioEncodingBitRate(16);
//        audioRecorder.setAudioSamplingRate(16000);
//        audioRecorder.setOutputFile(outputFile);

        recordButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!recordingState){
                    recordingState = true;
                }
                else{
                    recordingState = false;
                }
                __log("recordButton is clicked and the recordingState now becomes "+recordingState);
            }
        });

        largerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(size<30)
                    log.setTextSize(++size);
                __log("largerButton is clicked and the size is "+size);
            }
        });

        smallerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(size>24)
                    log.setTextSize(--size);
                __log("smallerButton is clicked and the size is "+size);
            }
        });

        colorButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.buttons).setVisibility(View.GONE);
                colors.setVisibility(View.VISIBLE);
                __log("colorButton is clicked");
            }
        });

        copyButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipData clip = ClipData.newPlainText("text", log.getText());
                clipboard.setPrimaryClip(clip);
                __log("copyButton is clicked");
            }
        });

        clearButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                log.setText("");
                __log("clearButton is clicked");
            }
        });
    }

    private static void __log(String data){
        log.append("["+new Date().toString()+"] "+data+"\n");
    }

    public void changeColor(View view){
        switch(view.getId()) {
            case R.id.blackColor:
                log.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
                break;
            case R.id.redColor:
                log.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
                break;
            case R.id.orangeColor:
                log.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.orange));
                break;
            case R.id.greenColor:
                log.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                break;
            case R.id.blueColor:
                log.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
                break;
        }

        colors.setVisibility(View.GONE);
        findViewById(R.id.buttons).setVisibility(View.VISIBLE);
    }
}
