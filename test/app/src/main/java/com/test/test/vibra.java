package com.test.test;

import android.os.Vibrator;
import android.media.SoundPool;

public class vibra {
    public static void Vibrate(Vibrator vib,long[] pattern) {
        vib.vibrate(pattern, 2);
    }

    public static void cancel(Vibrator vib, SoundPool sp) {
        vib.cancel();
        sp.stop(1);
    }
}
