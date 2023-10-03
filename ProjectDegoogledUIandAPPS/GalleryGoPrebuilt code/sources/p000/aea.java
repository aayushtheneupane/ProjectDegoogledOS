package p000;

import android.widget.SeekBar;
import androidx.preference.SeekBarPreference;

/* renamed from: aea */
/* compiled from: PG */
public final class aea implements SeekBar.OnSeekBarChangeListener {

    /* renamed from: a */
    private final /* synthetic */ SeekBarPreference f265a;

    public aea(SeekBarPreference seekBarPreference) {
        this.f265a = seekBarPreference;
    }

    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            SeekBarPreference seekBarPreference = this.f265a;
            if (seekBarPreference.f1143f || !seekBarPreference.f1140c) {
                seekBarPreference.mo1206a(seekBar);
                return;
            }
        }
        SeekBarPreference seekBarPreference2 = this.f265a;
        seekBarPreference2.mo1207f(i + seekBarPreference2.f1139b);
    }

    public final void onStartTrackingTouch(SeekBar seekBar) {
        this.f265a.f1140c = true;
    }

    public final void onStopTrackingTouch(SeekBar seekBar) {
        this.f265a.f1140c = false;
        int progress = seekBar.getProgress();
        SeekBarPreference seekBarPreference = this.f265a;
        if (progress + seekBarPreference.f1139b != seekBarPreference.f1138a) {
            seekBarPreference.mo1206a(seekBar);
        }
    }
}
