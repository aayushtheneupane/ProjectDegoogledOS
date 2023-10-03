package p000;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SeekBar;
import androidx.preference.SeekBarPreference;

/* renamed from: aeb */
/* compiled from: PG */
public final class aeb implements View.OnKeyListener {

    /* renamed from: a */
    private final /* synthetic */ SeekBarPreference f266a;

    public aeb(SeekBarPreference seekBarPreference) {
        this.f266a = seekBarPreference;
    }

    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            SeekBarPreference seekBarPreference = this.f266a;
            if (!((!seekBarPreference.f1142e && (i == 21 || i == 22)) || i == 23 || i == 66)) {
                SeekBar seekBar = seekBarPreference.f1141d;
                if (seekBar != null) {
                    return seekBar.onKeyDown(i, keyEvent);
                }
                Log.e("SeekBarPreference", "SeekBar view is null and hence cannot be adjusted.");
                return false;
            }
        }
        return false;
    }
}
