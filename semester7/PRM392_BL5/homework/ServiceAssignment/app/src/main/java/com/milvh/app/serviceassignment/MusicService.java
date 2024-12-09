package com.milvh.app.serviceassignment;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    public MusicService() {
    }

    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        // Khởi tạo MediaPlayer và thiết lập nhạc để phát
        mediaPlayer = MediaPlayer.create(this, R.raw.beautiful_loop_253269); // Nhạc nằm trong thư mục res/raw
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Bắt đầu phát nhạc khi Service được start
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(true); // Để nhạc phát liên tục
            mediaPlayer.start();
        }
        return START_STICKY; // Nếu Service bị dừng, hệ thống sẽ tự động khởi động lại
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Dừng nhạc khi Service bị hủy
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null; // Không cần sử dụng cho Started Service
    }
}