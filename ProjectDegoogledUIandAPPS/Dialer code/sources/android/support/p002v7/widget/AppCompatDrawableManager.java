package android.support.p002v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.p000v4.graphics.ColorUtils;
import android.support.p000v4.util.ArrayMap;
import android.support.p000v4.util.LongSparseArray;
import android.support.p000v4.util.LruCache;
import android.support.p000v4.util.SparseArrayCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import com.android.dialer.R;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: android.support.v7.widget.AppCompatDrawableManager */
public final class AppCompatDrawableManager {
    private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY = {R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult};
    private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED = {R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl_dark, R.drawable.abc_text_select_handle_middle_mtrl_dark, R.drawable.abc_text_select_handle_right_mtrl_dark, R.drawable.abc_text_select_handle_left_mtrl_light, R.drawable.abc_text_select_handle_middle_mtrl_light, R.drawable.abc_text_select_handle_right_mtrl_light};
    private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL = {R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha};
    private static final ColorFilterLruCache COLOR_FILTER_CACHE = new ColorFilterLruCache(6);
    private static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
    private static AppCompatDrawableManager INSTANCE;
    private static final int[] TINT_CHECKABLE_BUTTON_LIST = {R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material};
    private static final int[] TINT_COLOR_CONTROL_NORMAL = {R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha};
    private static final int[] TINT_COLOR_CONTROL_STATE_LIST = {R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material};
    private ArrayMap<String, InflateDelegate> mDelegates;
    private final Object mDrawableCacheLock = new Object();
    private final WeakHashMap<Context, LongSparseArray<WeakReference<Drawable.ConstantState>>> mDrawableCaches = new WeakHashMap<>(0);
    private boolean mHasCheckedVectorDrawableSetup;
    private SparseArrayCompat<String> mKnownDrawableIdTags;
    private WeakHashMap<Context, SparseArrayCompat<ColorStateList>> mTintLists;
    private TypedValue mTypedValue;

    /* renamed from: android.support.v7.widget.AppCompatDrawableManager$ColorFilterLruCache */
    private static class ColorFilterLruCache extends LruCache<Integer, PorterDuffColorFilter> {
        public ColorFilterLruCache(int i) {
            super(i);
        }

        /* access modifiers changed from: package-private */
        public PorterDuffColorFilter get(int i, PorterDuff.Mode mode) {
            return (PorterDuffColorFilter) get(Integer.valueOf(mode.hashCode() + ((i + 31) * 31)));
        }

        /* access modifiers changed from: package-private */
        public PorterDuffColorFilter put(int i, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return (PorterDuffColorFilter) put(Integer.valueOf(mode.hashCode() + ((i + 31) * 31)), porterDuffColorFilter);
        }
    }

    /* renamed from: android.support.v7.widget.AppCompatDrawableManager$InflateDelegate */
    private interface InflateDelegate {
        Drawable createFromXmlInner(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    private boolean addDrawableToCache(Context context, long j, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        synchronized (this.mDrawableCacheLock) {
            LongSparseArray longSparseArray = this.mDrawableCaches.get(context);
            if (longSparseArray == null) {
                longSparseArray = new LongSparseArray();
                this.mDrawableCaches.put(context, longSparseArray);
            }
            longSparseArray.put(j, new WeakReference(constantState));
        }
        return true;
    }

    private static boolean arrayContains(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private ColorStateList createButtonColorStateList(Context context, int i) {
        int themeAttrColor = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlHighlight);
        int disabledThemeAttrColor = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorButtonNormal);
        return new ColorStateList(new int[][]{ThemeUtils.DISABLED_STATE_SET, ThemeUtils.PRESSED_STATE_SET, ThemeUtils.FOCUSED_STATE_SET, ThemeUtils.EMPTY_STATE_SET}, new int[]{disabledThemeAttrColor, ColorUtils.compositeColors(themeAttrColor, i), ColorUtils.compositeColors(themeAttrColor, i), i});
    }

    private static long createCacheKey(TypedValue typedValue) {
        return (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
    }

    public static AppCompatDrawableManager get() {
        if (INSTANCE == null) {
            INSTANCE = new AppCompatDrawableManager();
            int i = Build.VERSION.SDK_INT;
        }
        return INSTANCE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002e, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.drawable.Drawable getCachedDrawable(android.content.Context r4, long r5) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mDrawableCacheLock
            monitor-enter(r0)
            java.util.WeakHashMap<android.content.Context, android.support.v4.util.LongSparseArray<java.lang.ref.WeakReference<android.graphics.drawable.Drawable$ConstantState>>> r3 = r3.mDrawableCaches     // Catch:{ all -> 0x002f }
            java.lang.Object r3 = r3.get(r4)     // Catch:{ all -> 0x002f }
            android.support.v4.util.LongSparseArray r3 = (android.support.p000v4.util.LongSparseArray) r3     // Catch:{ all -> 0x002f }
            r1 = 0
            if (r3 != 0) goto L_0x0010
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return r1
        L_0x0010:
            java.lang.Object r2 = r3.get(r5)     // Catch:{ all -> 0x002f }
            java.lang.ref.WeakReference r2 = (java.lang.ref.WeakReference) r2     // Catch:{ all -> 0x002f }
            if (r2 == 0) goto L_0x002d
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x002f }
            android.graphics.drawable.Drawable$ConstantState r2 = (android.graphics.drawable.Drawable.ConstantState) r2     // Catch:{ all -> 0x002f }
            if (r2 == 0) goto L_0x002a
            android.content.res.Resources r3 = r4.getResources()     // Catch:{ all -> 0x002f }
            android.graphics.drawable.Drawable r3 = r2.newDrawable(r3)     // Catch:{ all -> 0x002f }
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return r3
        L_0x002a:
            r3.delete(r5)     // Catch:{ all -> 0x002f }
        L_0x002d:
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return r1
        L_0x002f:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.AppCompatDrawableManager.getCachedDrawable(android.content.Context, long):android.graphics.drawable.Drawable");
    }

    public static PorterDuffColorFilter getPorterDuffColorFilter(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter = COLOR_FILTER_CACHE.get(i, mode);
        if (porterDuffColorFilter != null) {
            return porterDuffColorFilter;
        }
        PorterDuffColorFilter porterDuffColorFilter2 = new PorterDuffColorFilter(i, mode);
        COLOR_FILTER_CACHE.put(i, mode, porterDuffColorFilter2);
        return porterDuffColorFilter2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x007b A[Catch:{ Exception -> 0x00a9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a1 A[Catch:{ Exception -> 0x00a9 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.drawable.Drawable loadDrawableFromDelegates(android.content.Context r11, int r12) {
        /*
            r10 = this;
            android.support.v4.util.ArrayMap<java.lang.String, android.support.v7.widget.AppCompatDrawableManager$InflateDelegate> r0 = r10.mDelegates
            r1 = 0
            if (r0 == 0) goto L_0x00b9
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x00b9
            android.support.v4.util.SparseArrayCompat<java.lang.String> r0 = r10.mKnownDrawableIdTags
            java.lang.String r2 = "appcompat_skip_skip"
            if (r0 == 0) goto L_0x0028
            java.lang.Object r0 = r0.get(r12)
            java.lang.String r0 = (java.lang.String) r0
            boolean r3 = r2.equals(r0)
            if (r3 != 0) goto L_0x0027
            if (r0 == 0) goto L_0x0031
            android.support.v4.util.ArrayMap<java.lang.String, android.support.v7.widget.AppCompatDrawableManager$InflateDelegate> r3 = r10.mDelegates
            java.lang.Object r0 = r3.get(r0)
            if (r0 != 0) goto L_0x0031
        L_0x0027:
            return r1
        L_0x0028:
            android.support.v4.util.SparseArrayCompat r0 = new android.support.v4.util.SparseArrayCompat
            r1 = 10
            r0.<init>(r1)
            r10.mKnownDrawableIdTags = r0
        L_0x0031:
            android.util.TypedValue r0 = r10.mTypedValue
            if (r0 != 0) goto L_0x003c
            android.util.TypedValue r0 = new android.util.TypedValue
            r0.<init>()
            r10.mTypedValue = r0
        L_0x003c:
            android.util.TypedValue r0 = r10.mTypedValue
            android.content.res.Resources r1 = r11.getResources()
            r3 = 1
            r1.getValue(r12, r0, r3)
            int r4 = r0.assetCookie
            long r4 = (long) r4
            r6 = 32
            long r4 = r4 << r6
            int r6 = r0.data
            long r6 = (long) r6
            long r4 = r4 | r6
            android.graphics.drawable.Drawable r6 = r10.getCachedDrawable(r11, r4)
            if (r6 == 0) goto L_0x0057
            return r6
        L_0x0057:
            java.lang.CharSequence r7 = r0.string
            if (r7 == 0) goto L_0x00b1
            java.lang.String r7 = r7.toString()
            java.lang.String r8 = ".xml"
            boolean r7 = r7.endsWith(r8)
            if (r7 == 0) goto L_0x00b1
            android.content.res.XmlResourceParser r1 = r1.getXml(r12)     // Catch:{ Exception -> 0x00a9 }
            android.util.AttributeSet r7 = android.util.Xml.asAttributeSet(r1)     // Catch:{ Exception -> 0x00a9 }
        L_0x006f:
            int r8 = r1.next()     // Catch:{ Exception -> 0x00a9 }
            r9 = 2
            if (r8 == r9) goto L_0x0079
            if (r8 == r3) goto L_0x0079
            goto L_0x006f
        L_0x0079:
            if (r8 != r9) goto L_0x00a1
            java.lang.String r3 = r1.getName()     // Catch:{ Exception -> 0x00a9 }
            android.support.v4.util.SparseArrayCompat<java.lang.String> r8 = r10.mKnownDrawableIdTags     // Catch:{ Exception -> 0x00a9 }
            r8.append(r12, r3)     // Catch:{ Exception -> 0x00a9 }
            android.support.v4.util.ArrayMap<java.lang.String, android.support.v7.widget.AppCompatDrawableManager$InflateDelegate> r8 = r10.mDelegates     // Catch:{ Exception -> 0x00a9 }
            java.lang.Object r3 = r8.get(r3)     // Catch:{ Exception -> 0x00a9 }
            android.support.v7.widget.AppCompatDrawableManager$InflateDelegate r3 = (android.support.p002v7.widget.AppCompatDrawableManager.InflateDelegate) r3     // Catch:{ Exception -> 0x00a9 }
            if (r3 == 0) goto L_0x0096
            android.content.res.Resources$Theme r8 = r11.getTheme()     // Catch:{ Exception -> 0x00a9 }
            android.graphics.drawable.Drawable r6 = r3.createFromXmlInner(r11, r1, r7, r8)     // Catch:{ Exception -> 0x00a9 }
        L_0x0096:
            if (r6 == 0) goto L_0x00b1
            int r0 = r0.changingConfigurations     // Catch:{ Exception -> 0x00a9 }
            r6.setChangingConfigurations(r0)     // Catch:{ Exception -> 0x00a9 }
            r10.addDrawableToCache(r11, r4, r6)     // Catch:{ Exception -> 0x00a9 }
            goto L_0x00b1
        L_0x00a1:
            org.xmlpull.v1.XmlPullParserException r11 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r0 = "No start tag found"
            r11.<init>(r0)     // Catch:{ Exception -> 0x00a9 }
            throw r11     // Catch:{ Exception -> 0x00a9 }
        L_0x00a9:
            r11 = move-exception
            java.lang.String r0 = "AppCompatDrawableManag"
            java.lang.String r1 = "Exception while inflating drawable"
            android.util.Log.e(r0, r1, r11)
        L_0x00b1:
            if (r6 != 0) goto L_0x00b8
            android.support.v4.util.SparseArrayCompat<java.lang.String> r10 = r10.mKnownDrawableIdTags
            r10.append(r12, r2)
        L_0x00b8:
            return r6
        L_0x00b9:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.AppCompatDrawableManager.loadDrawableFromDelegates(android.content.Context, int):android.graphics.drawable.Drawable");
    }

    private static void setPorterDuffColorFilter(Drawable drawable, int i, PorterDuff.Mode mode) {
        if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
            drawable = drawable.mutate();
        }
        if (mode == null) {
            mode = DEFAULT_MODE;
        }
        drawable.setColorFilter(getPorterDuffColorFilter(i, mode));
    }

    private Drawable tintDrawable(Context context, int i, boolean z, Drawable drawable) {
        ColorStateList tintList = getTintList(context, i);
        PorterDuff.Mode mode = null;
        if (tintList != null) {
            if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
                drawable = drawable.mutate();
            }
            int i2 = Build.VERSION.SDK_INT;
            drawable.setTintList(tintList);
            if (i == R.drawable.abc_switch_thumb_material) {
                mode = PorterDuff.Mode.MULTIPLY;
            }
            if (mode == null) {
                return drawable;
            }
            int i3 = Build.VERSION.SDK_INT;
            drawable.setTintMode(mode);
            return drawable;
        } else if (i == R.drawable.abc_seekbar_track_material) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908288), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal), DEFAULT_MODE);
            setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal), DEFAULT_MODE);
            setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), DEFAULT_MODE);
            return drawable;
        } else if (i == R.drawable.abc_ratingbar_material || i == R.drawable.abc_ratingbar_indicator_material || i == R.drawable.abc_ratingbar_small_material) {
            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
            setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908288), ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal), DEFAULT_MODE);
            setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), DEFAULT_MODE);
            setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), DEFAULT_MODE);
            return drawable;
        } else if (tintDrawableUsingColorFilter(context, i, drawable) || !z) {
            return drawable;
        } else {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0064 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean tintDrawableUsingColorFilter(android.content.Context r6, int r7, android.graphics.drawable.Drawable r8) {
        /*
            android.graphics.PorterDuff$Mode r0 = DEFAULT_MODE
            int[] r1 = COLORFILTER_TINT_COLOR_CONTROL_NORMAL
            boolean r1 = arrayContains(r1, r7)
            r2 = 16842801(0x1010031, float:2.3693695E-38)
            r3 = -1
            r4 = 0
            r5 = 1
            if (r1 == 0) goto L_0x0016
            r2 = 2130968717(0x7f04008d, float:1.7546096E38)
        L_0x0013:
            r1 = r3
        L_0x0014:
            r7 = r5
            goto L_0x0047
        L_0x0016:
            int[] r1 = COLORFILTER_COLOR_CONTROL_ACTIVATED
            boolean r1 = arrayContains(r1, r7)
            if (r1 == 0) goto L_0x0022
            r2 = 2130968715(0x7f04008b, float:1.7546091E38)
            goto L_0x0013
        L_0x0022:
            int[] r1 = COLORFILTER_COLOR_BACKGROUND_MULTIPLY
            boolean r1 = arrayContains(r1, r7)
            if (r1 == 0) goto L_0x002d
            android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
            goto L_0x0013
        L_0x002d:
            r1 = 2131230774(0x7f080036, float:1.807761E38)
            if (r7 != r1) goto L_0x003e
            r2 = 16842800(0x1010030, float:2.3693693E-38)
            r7 = 1109603123(0x42233333, float:40.8)
            int r7 = java.lang.Math.round(r7)
            r1 = r7
            goto L_0x0014
        L_0x003e:
            r1 = 2131230750(0x7f08001e, float:1.8077562E38)
            if (r7 != r1) goto L_0x0044
            goto L_0x0013
        L_0x0044:
            r1 = r3
            r7 = r4
            r2 = r7
        L_0x0047:
            if (r7 == 0) goto L_0x0064
            boolean r7 = android.support.p002v7.widget.DrawableUtils.canSafelyMutateDrawable(r8)
            if (r7 == 0) goto L_0x0053
            android.graphics.drawable.Drawable r8 = r8.mutate()
        L_0x0053:
            int r6 = android.support.p002v7.widget.ThemeUtils.getThemeAttrColor(r6, r2)
            android.graphics.PorterDuffColorFilter r6 = getPorterDuffColorFilter(r6, r0)
            r8.setColorFilter(r6)
            if (r1 == r3) goto L_0x0063
            r8.setAlpha(r1)
        L_0x0063:
            return r5
        L_0x0064:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.AppCompatDrawableManager.tintDrawableUsingColorFilter(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
    }

    public Drawable getDrawable(Context context, int i) {
        return getDrawable(context, i, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v38, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v16, resolved type: android.content.res.ColorStateList} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.content.res.ColorStateList getTintList(android.content.Context r10, int r11) {
        /*
            r9 = this;
            java.util.WeakHashMap<android.content.Context, android.support.v4.util.SparseArrayCompat<android.content.res.ColorStateList>> r0 = r9.mTintLists
            r1 = 0
            if (r0 == 0) goto L_0x0014
            java.lang.Object r0 = r0.get(r10)
            android.support.v4.util.SparseArrayCompat r0 = (android.support.p000v4.util.SparseArrayCompat) r0
            if (r0 == 0) goto L_0x0014
            java.lang.Object r0 = r0.get(r11)
            r1 = r0
            android.content.res.ColorStateList r1 = (android.content.res.ColorStateList) r1
        L_0x0014:
            if (r1 != 0) goto L_0x013a
            r0 = 2131230751(0x7f08001f, float:1.8077564E38)
            if (r11 != r0) goto L_0x0024
            r0 = 2131099669(0x7f060015, float:1.7811698E38)
            android.content.res.ColorStateList r1 = android.support.p002v7.content.res.AppCompatResources.getColorStateList(r10, r0)
            goto L_0x0114
        L_0x0024:
            r0 = 2131230801(0x7f080051, float:1.8077665E38)
            if (r11 != r0) goto L_0x0032
            r0 = 2131099672(0x7f060018, float:1.7811704E38)
            android.content.res.ColorStateList r1 = android.support.p002v7.content.res.AppCompatResources.getColorStateList(r10, r0)
            goto L_0x0114
        L_0x0032:
            r0 = 2131230800(0x7f080050, float:1.8077663E38)
            r2 = 0
            if (r11 != r0) goto L_0x0098
            r0 = 3
            int[][] r1 = new int[r0][]
            int[] r0 = new int[r0]
            r3 = 2130968730(0x7f04009a, float:1.7546122E38)
            android.content.res.ColorStateList r4 = android.support.p002v7.widget.ThemeUtils.getThemeAttrColorStateList(r10, r3)
            r5 = 2
            r6 = 2130968715(0x7f04008b, float:1.7546091E38)
            r7 = 1
            if (r4 == 0) goto L_0x0072
            boolean r8 = r4.isStateful()
            if (r8 == 0) goto L_0x0072
            int[] r3 = android.support.p002v7.widget.ThemeUtils.DISABLED_STATE_SET
            r1[r2] = r3
            r3 = r1[r2]
            int r3 = r4.getColorForState(r3, r2)
            r0[r2] = r3
            int[] r2 = android.support.p002v7.widget.ThemeUtils.CHECKED_STATE_SET
            r1[r7] = r2
            int r2 = android.support.p002v7.widget.ThemeUtils.getThemeAttrColor(r10, r6)
            r0[r7] = r2
            int[] r2 = android.support.p002v7.widget.ThemeUtils.EMPTY_STATE_SET
            r1[r5] = r2
            int r2 = r4.getDefaultColor()
            r0[r5] = r2
            goto L_0x0090
        L_0x0072:
            int[] r4 = android.support.p002v7.widget.ThemeUtils.DISABLED_STATE_SET
            r1[r2] = r4
            int r4 = android.support.p002v7.widget.ThemeUtils.getDisabledThemeAttrColor(r10, r3)
            r0[r2] = r4
            int[] r2 = android.support.p002v7.widget.ThemeUtils.CHECKED_STATE_SET
            r1[r7] = r2
            int r2 = android.support.p002v7.widget.ThemeUtils.getThemeAttrColor(r10, r6)
            r0[r7] = r2
            int[] r2 = android.support.p002v7.widget.ThemeUtils.EMPTY_STATE_SET
            r1[r5] = r2
            int r2 = android.support.p002v7.widget.ThemeUtils.getThemeAttrColor(r10, r3)
            r0[r5] = r2
        L_0x0090:
            android.content.res.ColorStateList r2 = new android.content.res.ColorStateList
            r2.<init>(r1, r0)
            r1 = r2
            goto L_0x0114
        L_0x0098:
            r0 = 2131230740(0x7f080014, float:1.8077541E38)
            if (r11 != r0) goto L_0x00aa
            r0 = 2130968714(0x7f04008a, float:1.754609E38)
            int r0 = android.support.p002v7.widget.ThemeUtils.getThemeAttrColor(r10, r0)
            android.content.res.ColorStateList r1 = r9.createButtonColorStateList(r10, r0)
            goto L_0x0114
        L_0x00aa:
            r0 = 2131230735(0x7f08000f, float:1.8077531E38)
            if (r11 != r0) goto L_0x00b4
            android.content.res.ColorStateList r1 = r9.createButtonColorStateList(r10, r2)
            goto L_0x0114
        L_0x00b4:
            r0 = 2131230739(0x7f080013, float:1.807754E38)
            if (r11 != r0) goto L_0x00c5
            r0 = 2130968712(0x7f040088, float:1.7546085E38)
            int r0 = android.support.p002v7.widget.ThemeUtils.getThemeAttrColor(r10, r0)
            android.content.res.ColorStateList r1 = r9.createButtonColorStateList(r10, r0)
            goto L_0x0114
        L_0x00c5:
            r0 = 2131230798(0x7f08004e, float:1.8077659E38)
            if (r11 == r0) goto L_0x010d
            r0 = 2131230799(0x7f08004f, float:1.807766E38)
            if (r11 != r0) goto L_0x00d0
            goto L_0x010d
        L_0x00d0:
            int[] r0 = TINT_COLOR_CONTROL_NORMAL
            boolean r0 = arrayContains(r0, r11)
            if (r0 == 0) goto L_0x00e0
            r0 = 2130968717(0x7f04008d, float:1.7546096E38)
            android.content.res.ColorStateList r1 = android.support.p002v7.widget.ThemeUtils.getThemeAttrColorStateList(r10, r0)
            goto L_0x0114
        L_0x00e0:
            int[] r0 = TINT_COLOR_CONTROL_STATE_LIST
            boolean r0 = arrayContains(r0, r11)
            if (r0 == 0) goto L_0x00f0
            r0 = 2131099668(0x7f060014, float:1.7811696E38)
            android.content.res.ColorStateList r1 = android.support.p002v7.content.res.AppCompatResources.getColorStateList(r10, r0)
            goto L_0x0114
        L_0x00f0:
            int[] r0 = TINT_CHECKABLE_BUTTON_LIST
            boolean r0 = arrayContains(r0, r11)
            if (r0 == 0) goto L_0x0100
            r0 = 2131099667(0x7f060013, float:1.7811694E38)
            android.content.res.ColorStateList r1 = android.support.p002v7.content.res.AppCompatResources.getColorStateList(r10, r0)
            goto L_0x0114
        L_0x0100:
            r0 = 2131230795(0x7f08004b, float:1.8077653E38)
            if (r11 != r0) goto L_0x0114
            r0 = 2131099670(0x7f060016, float:1.78117E38)
            android.content.res.ColorStateList r1 = android.support.p002v7.content.res.AppCompatResources.getColorStateList(r10, r0)
            goto L_0x0114
        L_0x010d:
            r0 = 2131099671(0x7f060017, float:1.7811702E38)
            android.content.res.ColorStateList r1 = android.support.p002v7.content.res.AppCompatResources.getColorStateList(r10, r0)
        L_0x0114:
            if (r1 == 0) goto L_0x013a
            java.util.WeakHashMap<android.content.Context, android.support.v4.util.SparseArrayCompat<android.content.res.ColorStateList>> r0 = r9.mTintLists
            if (r0 != 0) goto L_0x0121
            java.util.WeakHashMap r0 = new java.util.WeakHashMap
            r0.<init>()
            r9.mTintLists = r0
        L_0x0121:
            java.util.WeakHashMap<android.content.Context, android.support.v4.util.SparseArrayCompat<android.content.res.ColorStateList>> r0 = r9.mTintLists
            java.lang.Object r0 = r0.get(r10)
            android.support.v4.util.SparseArrayCompat r0 = (android.support.p000v4.util.SparseArrayCompat) r0
            if (r0 != 0) goto L_0x0137
            android.support.v4.util.SparseArrayCompat r0 = new android.support.v4.util.SparseArrayCompat
            r2 = 10
            r0.<init>(r2)
            java.util.WeakHashMap<android.content.Context, android.support.v4.util.SparseArrayCompat<android.content.res.ColorStateList>> r9 = r9.mTintLists
            r9.put(r10, r0)
        L_0x0137:
            r0.append(r11, r1)
        L_0x013a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.AppCompatDrawableManager.getTintList(android.content.Context, int):android.content.res.ColorStateList");
    }

    public void onConfigurationChanged(Context context) {
        synchronized (this.mDrawableCacheLock) {
            LongSparseArray longSparseArray = this.mDrawableCaches.get(context);
            if (longSparseArray != null) {
                longSparseArray.clear();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002a, code lost:
        if (((r0 instanceof android.support.graphics.drawable.VectorDrawableCompat) || "android.graphics.drawable.VectorDrawable".equals(r0.getClass().getName())) != false) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.drawable.Drawable getDrawable(android.content.Context r9, int r10, boolean r11) {
        /*
            r8 = this;
            boolean r0 = r8.mHasCheckedVectorDrawableSetup
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0007
            goto L_0x002c
        L_0x0007:
            r8.mHasCheckedVectorDrawableSetup = r2
            r0 = 2131230816(0x7f080060, float:1.8077695E38)
            android.graphics.drawable.Drawable r0 = r8.getDrawable(r9, r0)
            if (r0 == 0) goto L_0x008e
            boolean r3 = r0 instanceof android.support.graphics.drawable.VectorDrawableCompat
            if (r3 != 0) goto L_0x0029
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getName()
            java.lang.String r3 = "android.graphics.drawable.VectorDrawable"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0027
            goto L_0x0029
        L_0x0027:
            r0 = r1
            goto L_0x002a
        L_0x0029:
            r0 = r2
        L_0x002a:
            if (r0 == 0) goto L_0x008e
        L_0x002c:
            android.graphics.drawable.Drawable r0 = r8.loadDrawableFromDelegates(r9, r10)
            if (r0 != 0) goto L_0x007c
            android.util.TypedValue r0 = r8.mTypedValue
            if (r0 != 0) goto L_0x003d
            android.util.TypedValue r0 = new android.util.TypedValue
            r0.<init>()
            r8.mTypedValue = r0
        L_0x003d:
            android.util.TypedValue r0 = r8.mTypedValue
            android.content.res.Resources r3 = r9.getResources()
            r3.getValue(r10, r0, r2)
            long r3 = createCacheKey(r0)
            android.graphics.drawable.Drawable r5 = r8.getCachedDrawable(r9, r3)
            if (r5 == 0) goto L_0x0052
        L_0x0050:
            r0 = r5
            goto L_0x007c
        L_0x0052:
            r6 = 2131230747(0x7f08001b, float:1.8077556E38)
            if (r10 != r6) goto L_0x0071
            android.graphics.drawable.LayerDrawable r5 = new android.graphics.drawable.LayerDrawable
            r6 = 2
            android.graphics.drawable.Drawable[] r6 = new android.graphics.drawable.Drawable[r6]
            r7 = 2131230746(0x7f08001a, float:1.8077553E38)
            android.graphics.drawable.Drawable r7 = r8.getDrawable(r9, r7)
            r6[r1] = r7
            r1 = 2131230748(0x7f08001c, float:1.8077558E38)
            android.graphics.drawable.Drawable r1 = r8.getDrawable(r9, r1)
            r6[r2] = r1
            r5.<init>(r6)
        L_0x0071:
            if (r5 == 0) goto L_0x0050
            int r0 = r0.changingConfigurations
            r5.setChangingConfigurations(r0)
            r8.addDrawableToCache(r9, r3, r5)
            goto L_0x0050
        L_0x007c:
            if (r0 != 0) goto L_0x0082
            android.graphics.drawable.Drawable r0 = android.support.p000v4.content.ContextCompat.getDrawable(r9, r10)
        L_0x0082:
            if (r0 == 0) goto L_0x0088
            android.graphics.drawable.Drawable r0 = r8.tintDrawable(r9, r10, r11, r0)
        L_0x0088:
            if (r0 == 0) goto L_0x008d
            android.support.p002v7.widget.DrawableUtils.fixDrawable(r0)
        L_0x008d:
            return r0
        L_0x008e:
            r8.mHasCheckedVectorDrawableSetup = r1
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat."
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.AppCompatDrawableManager.getDrawable(android.content.Context, int, boolean):android.graphics.drawable.Drawable");
    }

    static void tintDrawable(Drawable drawable, TintInfo tintInfo, int[] iArr) {
        if (!DrawableUtils.canSafelyMutateDrawable(drawable) || drawable.mutate() == drawable) {
            if (tintInfo.mHasTintList || tintInfo.mHasTintMode) {
                PorterDuffColorFilter porterDuffColorFilter = null;
                ColorStateList colorStateList = tintInfo.mHasTintList ? tintInfo.mTintList : null;
                PorterDuff.Mode mode = tintInfo.mHasTintMode ? tintInfo.mTintMode : DEFAULT_MODE;
                if (!(colorStateList == null || mode == null)) {
                    porterDuffColorFilter = getPorterDuffColorFilter(colorStateList.getColorForState(iArr, 0), mode);
                }
                drawable.setColorFilter(porterDuffColorFilter);
            } else {
                drawable.clearColorFilter();
            }
            int i = Build.VERSION.SDK_INT;
            return;
        }
        Log.d("AppCompatDrawableManag", "Mutated drawable is not the same instance as the input.");
    }
}
