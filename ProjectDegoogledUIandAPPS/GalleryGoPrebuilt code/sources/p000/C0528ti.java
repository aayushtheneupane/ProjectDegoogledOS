package p000;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import com.google.android.apps.photosgo.R;

/* renamed from: ti */
/* compiled from: PG */
final class C0528ti implements C0670yp {

    /* renamed from: a */
    public final int[] f15928a = {R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha};

    /* renamed from: b */
    public final int[] f15929b = {R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha};

    /* renamed from: c */
    public final int[] f15930c = {R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl_dark, R.drawable.abc_text_select_handle_middle_mtrl_dark, R.drawable.abc_text_select_handle_right_mtrl_dark, R.drawable.abc_text_select_handle_left_mtrl_light, R.drawable.abc_text_select_handle_middle_mtrl_light, R.drawable.abc_text_select_handle_right_mtrl_light};

    /* renamed from: d */
    public final int[] f15931d = {R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult};

    /* renamed from: e */
    public final int[] f15932e = {R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material};

    /* renamed from: f */
    public final int[] f15933f = {R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material, R.drawable.abc_btn_check_material_anim, R.drawable.abc_btn_radio_material_anim};

    /* renamed from: a */
    public static final boolean m15436a(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static final ColorStateList m15434a(Context context, int i) {
        int a = C0678yx.m16183a(context, R.attr.colorControlHighlight);
        int c = C0678yx.m16185c(context, R.attr.colorButtonNormal);
        return new ColorStateList(new int[][]{C0678yx.f16424a, C0678yx.f16426c, C0678yx.f16425b, C0678yx.f16428e}, new int[]{c, C0238ip.m14265a(a, i), C0238ip.m14265a(a, i), i});
    }

    /* renamed from: a */
    public static final void m15435a(Drawable drawable, int i, PorterDuff.Mode mode) {
        if (C0579vf.m15606b(drawable)) {
            drawable = drawable.mutate();
        }
        if (mode == null) {
            mode = C0529tj.f15934a;
        }
        drawable.setColorFilter(C0529tj.m15437a(i, mode));
    }
}
