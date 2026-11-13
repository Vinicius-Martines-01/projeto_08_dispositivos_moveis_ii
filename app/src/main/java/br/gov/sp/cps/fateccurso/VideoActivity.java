package br.gov.sp.cps.fateccurso;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    private VideoView videoView;
    private ImageButton btnPlayPause;
    private SeekBar seekBar;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.videoView);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        seekBar = findViewById(R.id.seekBar);
        Button btnVoltar = findViewById(R.id.btnVoltar);

        // Caminho do vídeo em res/raw
        String path = "android.resource://" + getPackageName() + "/" + R.raw.video_curso;
        videoView.setVideoURI(Uri.parse(path));

        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                    btnPlayPause.setImageResource(android.R.drawable.ic_media_play);
                } else {
                    videoView.start();
                    btnPlayPause.setImageResource(android.R.drawable.ic_media_pause);
                    updateSeekBar();
                }
            }
        });

        videoView.setOnCompletionListener(mp -> {
            btnPlayPause.setImageResource(android.R.drawable.ic_media_play);
            seekBar.setProgress(0);
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    videoView.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnVoltar.setOnClickListener(v -> finish());
    }

    private void updateSeekBar() {
        if (videoView != null) {
            seekBar.setMax(videoView.getDuration());
            seekBar.setProgress(videoView.getCurrentPosition());
            if (videoView.isPlaying()) {
                handler.postDelayed(this::updateSeekBar, 1000);
            }
        }
    }
}
