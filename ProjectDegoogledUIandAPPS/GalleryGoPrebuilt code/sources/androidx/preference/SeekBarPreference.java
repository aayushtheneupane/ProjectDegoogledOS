package androidx.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public class SeekBarPreference extends Preference {

    /* renamed from: D */
    private boolean f1135D;

    /* renamed from: E */
    private SeekBar.OnSeekBarChangeListener f1136E = new aea(this);

    /* renamed from: F */
    private View.OnKeyListener f1137F = new aeb(this);

    /* renamed from: a */
    public int f1138a;

    /* renamed from: b */
    public int f1139b;

    /* renamed from: c */
    public boolean f1140c;

    /* renamed from: d */
    public SeekBar f1141d;

    /* renamed from: e */
    public boolean f1142e;

    /* renamed from: f */
    public boolean f1143f;

    /* renamed from: g */
    private int f1144g;

    /* renamed from: h */
    private int f1145h;

    /* renamed from: i */
    private TextView f1146i;

    public SeekBarPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.seekBarPreferenceStyle);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, adz.f260k, R.attr.seekBarPreferenceStyle, 0);
        this.f1139b = obtainStyledAttributes.getInt(3, 0);
        int i = obtainStyledAttributes.getInt(1, 100);
        int i2 = this.f1139b;
        i = i < i2 ? i2 : i;
        if (i != this.f1144g) {
            this.f1144g = i;
            mo1157b();
        }
        int i3 = obtainStyledAttributes.getInt(4, 0);
        if (i3 != this.f1145h) {
            this.f1145h = Math.min(this.f1144g - this.f1139b, Math.abs(i3));
            mo1157b();
        }
        this.f1142e = obtainStyledAttributes.getBoolean(2, true);
        this.f1135D = obtainStyledAttributes.getBoolean(5, false);
        this.f1143f = obtainStyledAttributes.getBoolean(6, false);
        obtainStyledAttributes.recycle();
    }

    /* renamed from: a */
    public final void mo169a(ady ady) {
        super.mo169a(ady);
        ady.f16382a.setOnKeyListener(this.f1137F);
        this.f1141d = (SeekBar) ady.mo235c(R.id.seekbar);
        TextView textView = (TextView) ady.mo235c(R.id.seekbar_value);
        this.f1146i = textView;
        if (this.f1135D) {
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
            this.f1146i = null;
        }
        SeekBar seekBar = this.f1141d;
        if (seekBar != null) {
            seekBar.setOnSeekBarChangeListener(this.f1136E);
            this.f1141d.setMax(this.f1144g - this.f1139b);
            int i = this.f1145h;
            if (i != 0) {
                this.f1141d.setKeyProgressIncrement(i);
            } else {
                this.f1145h = this.f1141d.getKeyProgressIncrement();
            }
            this.f1141d.setProgress(this.f1138a - this.f1139b);
            mo1207f(this.f1138a);
            this.f1141d.setEnabled(mo1189h());
            return;
        }
        Log.e("SeekBarPreference", "SeekBar view is null in onBindViewHolder.");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo1158a(TypedArray typedArray, int i) {
        return Integer.valueOf(typedArray.getInt(i, 0));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo1159a(Parcelable parcelable) {
        if (!parcelable.getClass().equals(aed.class)) {
            super.mo1159a(parcelable);
            return;
        }
        aed aed = (aed) parcelable;
        super.mo1159a(aed.getSuperState());
        this.f1138a = aed.f267a;
        this.f1139b = aed.f268b;
        this.f1144g = aed.f269c;
        mo1157b();
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final Parcelable mo1163d() {
        Parcelable d = super.mo1163d();
        if (this.f1123v) {
            return d;
        }
        aed aed = new aed(d);
        aed.f267a = this.f1138a;
        aed.f268b = this.f1139b;
        aed.f269c = this.f1144g;
        return aed;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo1160a(Object obj) {
        if (obj == null) {
            obj = 0;
        }
        m1077a(mo1187e(((Integer) obj).intValue()), true);
    }

    /* renamed from: a */
    private final void m1077a(int i, boolean z) {
        int i2 = this.f1139b;
        if (i < i2) {
            i = i2;
        }
        int i3 = this.f1144g;
        if (i > i3) {
            i = i3;
        }
        if (i != this.f1138a) {
            this.f1138a = i;
            mo1207f(i);
            if (mo1191j() && i != mo1187e(i ^ -1)) {
                SharedPreferences.Editor c = this.f1112k.mo231c();
                c.putInt(this.f1119r, i);
                Preference.m1014a(c);
            }
            if (z) {
                mo1157b();
            }
        }
    }

    /* renamed from: a */
    public final void mo1206a(SeekBar seekBar) {
        int progress = this.f1139b + seekBar.getProgress();
        if (progress == this.f1138a) {
            return;
        }
        if (!mo1179b((Object) Integer.valueOf(progress))) {
            seekBar.setProgress(this.f1138a - this.f1139b);
            mo1207f(this.f1138a);
            return;
        }
        m1077a(progress, false);
    }

    /* renamed from: f */
    public final void mo1207f(int i) {
        TextView textView = this.f1146i;
        if (textView != null) {
            textView.setText(String.valueOf(i));
        }
    }
}
