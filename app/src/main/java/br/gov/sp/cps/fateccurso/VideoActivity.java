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

        // O código de carregamento do vídeo foi comentado para evitar erro de compilação.
        // Adicione um vídeo em res/raw/video_curso.mp4 e descomente as linhas abaixo.
        /*
        String path = "android.resource://" + getPackageName() + "/" + R.raw.video_curso;
        videoView.setVideoURI(Uri.parse(path));
        */

        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificação para não crashar se o vídeo não estiver carregado
                try {
                    if (videoView.isPlaying()) {
                        videoView.pause();
                        btnPlayPause.setImageResource(android.R.drawable.ic_media_play);
                    } else if (videoView.getDuration() > 0) {
                        videoView.start();
                        btnPlayPause.setImageResource(android.R.drawable.ic_media_pause);
                        updateSeekBar();
                    }
                } catch (Exception e) {
                    // Previne crash caso o VideoView não tenha mídia carregada
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
                    try {
                        videoView.seekTo(progress);
                    } catch (Exception e) {}
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
            try {
                int duration = videoView.getDuration();
                if (duration > 0) {
                    seekBar.setMax(duration);
                    seekBar.setProgress(videoView.getCurrentPosition());
                }
                if (videoView.isPlaying()) {
                    handler.postDelayed(this::updateSeekBar, 1000);
                }
            } catch (Exception e) {
                // Silencia erros caso o player não esteja pronto
            }
        }
    }
}
