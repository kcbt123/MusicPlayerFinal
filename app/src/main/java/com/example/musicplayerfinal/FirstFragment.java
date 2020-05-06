package com.example.musicplayerfinal;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.musicplayerfinal.models.Song;

import java.sql.Array;
import java.util.ArrayList;

public class FirstFragment extends Fragment {

    private ImageButton play, next, back;
    private TextView songName, songSinger, currentTime, songDuration;
    private SeekBar seekBar;

    MediaPlayer mediaPlayer;
    ArrayList<Song> songList;
    int songIndex = 0;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addSong();

        play = view.findViewById(R.id.playButton);
        next = view.findViewById(R.id.forwardButton);
        back = view.findViewById(R.id.backButton);
        songName = view.findViewById(R.id.songName);
        songSinger = view.findViewById(R.id.songSinger);
        seekBar = view.findViewById(R.id.seekBar);
        currentTime = view.findViewById(R.id.currentTime);
        songDuration = view.findViewById(R.id.songDuration);

        initiateSong();
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    play.setImageResource(R.drawable.button_play);
                } else {
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.button_pause);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    play.setImageResource(R.drawable.button_play);
                }
                songIndex = songIndex + 1;
                checkResetSongIndex();
                initiateSong();
                mediaPlayer.start();
                play.setImageResource(R.drawable.button_pause);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    play.setImageResource(R.drawable.button_play);
                }
                songIndex = songIndex - 1;
                checkResetSongIndex();
                initiateSong();
                mediaPlayer.start();
                play.setImageResource(R.drawable.button_pause);
            }
        });
    }

    // MARK: - Handle Media Player

    private void initiateSong() {
        mediaPlayer = MediaPlayer.create(getActivity(), songList.get(songIndex).getFile());
        songName.setText(songList.get(songIndex).getName());
        songSinger.setText(songList.get(songIndex).getAuthor());
    }

    public void addSong() {
        songList = new ArrayList<Song>();

        songList.add(new Song("Em Của Ngày Hôm Qua", "Sơn Tùng M-TP", R.raw.emcuangayhomqua));
        songList.add(new Song("Đường Cong", "Thu Minh", R.raw.duongcongthuminh));
    }

    private void checkResetSongIndex() {
        if (songIndex >= songList.size()) {
            songIndex = 0;
        }
    }



}
