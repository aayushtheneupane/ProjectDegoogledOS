package p000;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Shader;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.editor.presets.PresetItemView;
import p003j$.util.Optional;

/* renamed from: cbo */
/* compiled from: PG */
final class cbo extends gwe {

    /* renamed from: a */
    private final /* synthetic */ cbp f4016a;

    public cbo(cbp cbp) {
        this.f4016a = cbp;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2643a(View view, Object obj) {
        cbd cbd = (cbd) obj;
        cbi a = ((PresetItemView) view).mo2635n();
        a.f4012c = Optional.m16285of(cbd);
        a.f4010a.setContentDescription(a.f4011b.getString(((Integer) ife.m12898e((Object) (Integer) cbm.f4014a.get(cbd.mo2973b()))).intValue()));
        Bitmap a2 = cbd.mo2972a();
        Resources resources = a.f4011b;
        int i = Build.VERSION.SDK_INT;
        C0251jb jbVar = new C0251jb(resources, a2);
        float dimensionPixelSize = (float) a.f4011b.getDimensionPixelSize(R.dimen.photos_photoeditor_presets_list_item_corner_radius);
        if (jbVar.f15069c != dimensionPixelSize) {
            if (C0252jc.m14459a(dimensionPixelSize)) {
                jbVar.f15067a.setShader(jbVar.f15068b);
            } else {
                jbVar.f15067a.setShader((Shader) null);
            }
            jbVar.f15069c = dimensionPixelSize;
            jbVar.invalidateSelf();
        }
        a.f4010a.setImageDrawable(jbVar);
        a.f4010a.setSelected(cbd.mo2974c());
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ View mo2641a(ViewGroup viewGroup) {
        return (PresetItemView) this.f4016a.f4017a.inflate(R.layout.preset_item_view, viewGroup, false);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2642a(View view) {
        cbi a = ((PresetItemView) view).mo2635n();
        a.f4012c = Optional.empty();
        a.f4010a.setImageBitmap((Bitmap) null);
    }
}
