package p000;

import android.content.res.Resources;
import android.widget.ImageView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.editor.presets.PresetItemView;
import p003j$.util.Optional;

/* renamed from: cbi */
/* compiled from: PG */
public final class cbi {

    /* renamed from: a */
    public final ImageView f4010a;

    /* renamed from: b */
    public final Resources f4011b;

    /* renamed from: c */
    public Optional f4012c = Optional.empty();

    public cbi(PresetItemView presetItemView, hbl hbl) {
        this.f4010a = (ImageView) presetItemView.findViewById(R.id.preset_item_image);
        this.f4011b = hbl.getResources();
    }
}
