//package com.zdh.alphathink.imh.Chatting;
//
//import android.graphics.Bitmap;
//import android.media.MediaRecorder;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import com.zdh.alphathink.imh.R;
//
//import java.io.File;
//import java.io.IOException;
//
//public class ChattingActivity extends AppCompatActivity implements View.OnClickListener {
//
//    private Button btnStart;
//    private Button btnStop;
//    private Button btnPlay;
//
//    private MediaRecorder mMediaRecorder;
//    private File recAudioFile;
//    private MusicPlayer mPlayer;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chatting);
//
//        btnStart = (Button) findViewById(R.id.start);
//        btnStop = (Button) findViewById(R.id.stop);
//        btnPlay = (Button) findViewById(R.id.play);
//
//        btnStart.setOnClickListener(this);
//        btnStop.setOnClickListener(this);
//        btnPlay.setOnClickListener(this);
//        recAudioFile = new File("/mnt/sdcard", "new.amr");
//
//    }
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.start:
//                startRecorder();
//                break;
//            case R.id.stop:
//                stopRecorder();
//                break;
//            case R.id.play:
//                mPlayer = new MusicPlayer(ChattingActivity.this);
//                mPlayer.playMicFile(recAudioFile);
//                break;
//            default:
//                break;
//        }
//    }
//
//    private void startRecorder() {
//        mMediaRecorder = new MediaRecorder();
//        if (recAudioFile.exists()) {
//            recAudioFile.delete();
//        }
//
//        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
//        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
//        mMediaRecorder.setOutputFile(recAudioFile.getAbsolutePath());
//        try {
//            mMediaRecorder.prepare();
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        mMediaRecorder.start();
//    }
//
//    private void stopRecorder(){
//        if (recAudioFile!=null) {
//            mMediaRecorder.stop();
//            mMediaRecorder.release();
//        }
//    }
//
//}
