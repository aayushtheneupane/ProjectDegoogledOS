package p000;

import android.text.TextUtils;
import android.text.format.Formatter;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.infosheet.InfoSheetListView;
import java.util.Date;
import java.util.List;
import p003j$.util.Optional;
import p003j$.util.stream.Collectors;
import p003j$.util.stream.Stream$$CC;

/* renamed from: cst */
/* compiled from: PG */
final class cst implements gvc {

    /* renamed from: a */
    private final InfoSheetListView f5596a;

    /* renamed from: b */
    private final /* synthetic */ csu f5597b;

    public cst(csu csu, InfoSheetListView infoSheetListView) {
        this.f5597b = csu;
        this.f5596a = infoSheetListView;
    }

    /* renamed from: a */
    public final void mo2679a() {
    }

    /* renamed from: a */
    public final void mo2681a(Throwable th) {
        cwn.m5515b(th, "InfoSheetFragment: Failed to get metadata from media[%s]", this.f5597b.f5600c);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2680a(Object obj) {
        Optional optional;
        Optional optional2;
        String str;
        String str2;
        cxq cxq = (cxq) obj;
        csy a = this.f5596a.mo2635n();
        a.f5611c.setVisibility(8);
        String str3 = null;
        String str4 = (String) cxq.mo3875a().orElse((Object) null);
        String str5 = (String) cxq.mo3876b().orElse((Object) null);
        if (!(str4 == null || str5 == null)) {
            ctb a2 = a.mo3808a();
            a2.mo3813b((int) R.drawable.quantum_gm_ic_folder_open_vd_theme_24);
            a2.mo3814b(str4);
            a2.mo3812a(str5);
            a2.mo3811a((int) R.string.info_sheet_folder_content_description_prefix);
        }
        String format = cxq.mo3878d().isPresent() ? a.f5610b.f5631c.format((Date) cxq.mo3878d().get()) : null;
        String format2 = cxq.mo3878d().isPresent() ? a.f5610b.f5632d.format((Date) cxq.mo3878d().get()) : null;
        if (!(format == null || format2 == null)) {
            ctb a3 = a.mo3808a();
            a3.mo3813b((int) R.drawable.quantum_gm_ic_event_vd_theme_24);
            a3.mo3814b(format);
            a3.mo3812a(format2);
            a3.mo3811a((int) R.string.info_sheet_date_and_time_content_description_prefix);
        }
        String str6 = (String) cxq.mo3877c().orElse((Object) null);
        ctk ctk = a.f5610b;
        if (!cxq.mo3881f().isPresent() || !cxq.mo3879e().isPresent() || !cxq.mo3882g().isPresent()) {
            optional = Optional.empty();
        } else {
            optional = Optional.m16285of(Integer.valueOf(cyc.m5640a(((Integer) cxq.mo3881f().get()).intValue(), ((Integer) cxq.mo3879e().get()).intValue(), ((Integer) cxq.mo3882g().get()).intValue())));
        }
        if (!cxq.mo3881f().isPresent() || !cxq.mo3879e().isPresent() || !cxq.mo3882g().isPresent()) {
            optional2 = Optional.empty();
        } else {
            optional2 = Optional.m16285of(Integer.valueOf(cyc.m5646b(((Integer) cxq.mo3881f().get()).intValue(), ((Integer) cxq.mo3879e().get()).intValue(), ((Integer) cxq.mo3882g().get()).intValue())));
        }
        if (optional.isPresent() && optional2.isPresent()) {
            str = String.format(ctk.f5630b, ctk.f5629a.getString(R.string.info_sheet_pixel_count_format), new Object[]{Float.valueOf(((float) (((Integer) optional.get()).intValue() * ((Integer) optional2.get()).intValue())) / 1000000.0f)});
            str2 = String.format(ctk.f5630b, ctk.f5629a.getString(R.string.info_sheet_resolution_format), new Object[]{optional.get(), optional2.get()});
        } else {
            str2 = null;
            str = null;
        }
        String join = TextUtils.join(" • ", (List) Stream$$CC.of$$STATIC$$(str, str2, cxq.mo3883h().isPresent() ? Formatter.formatFileSize(ctk.f5629a, ((Long) cxq.mo3883h().get()).longValue()) : null).filter(cte.f5623a).collect(Collectors.toList()));
        if (TextUtils.isEmpty(join)) {
            join = null;
        }
        if (!(str6 == null || join == null)) {
            ctb a4 = a.mo3808a();
            Optional p = cxq.mo3892p();
            boolean isPresent = p.isPresent();
            int i = R.drawable.quantum_gm_ic_image_vd_theme_24;
            if (isPresent && dgt.m6098c((String) p.get())) {
                i = R.drawable.quantum_gm_ic_movie_vd_theme_24;
            }
            a4.mo3813b(i);
            a4.mo3814b(str6);
            a4.mo3812a(join);
            a4.mo3811a((int) R.string.info_sheet_size_content_description_prefix);
        }
        String str7 = (String) cxq.mo3885i().orElse((Object) null);
        ctk ctk2 = a.f5610b;
        String join2 = TextUtils.join(" • ", (List) Stream$$CC.of$$STATIC$$((String) cxq.mo3886j().map(new ctf(ctk2)).orElse((Object) null), (String) cxq.mo3887k().map(new ctg(ctk2)).orElse((Object) null), (String) cxq.mo3888l().map(new cth(ctk2)).orElse((Object) null), (String) cxq.mo3889m().map(new cti(ctk2)).orElse((Object) null)).filter(ctj.f5628a).collect(Collectors.toList()));
        if (!TextUtils.isEmpty(join2)) {
            str3 = join2;
        }
        if (!(str7 == null || str3 == null)) {
            ctb a5 = a.mo3808a();
            a5.mo3813b((int) R.drawable.quantum_gm_ic_camera_vd_theme_24);
            a5.mo3814b(str7);
            a5.mo3812a(str3);
            a5.mo3811a((int) R.string.info_sheet_exposure_settings_content_description_prefix);
        }
        if (cxq.mo3890n().isPresent() && cxq.mo3891o().isPresent()) {
            ctb a6 = a.mo3808a();
            a6.mo3813b((int) R.drawable.quantum_gm_ic_location_on_vd_theme_24);
            a6.f5617a.setImageResource(R.drawable.quantum_gm_ic_exit_to_app_vd_theme_24);
            a6.mo3814b(a.f5609a.getString(R.string.see_photo_location));
            a6.mo3812a(a.f5609a.getString(R.string.connection_required));
            a6.f5618b.setOnClickListener(new csx(a, cxq));
        }
    }
}
