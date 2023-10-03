package com.android.settings.display;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.hardware.display.ColorDisplayManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import androidx.preference.PreferenceScreen;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.widget.RadioButtonPickerFragment;
import com.android.settingslib.widget.CandidateInfo;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorModePreferenceFragment extends RadioButtonPickerFragment {
    static final String KEY_COLOR_MODE_AUTOMATIC = "color_mode_automatic";
    static final String KEY_COLOR_MODE_BOOSTED = "color_mode_boosted";
    static final String KEY_COLOR_MODE_NATURAL = "color_mode_natural";
    static final String KEY_COLOR_MODE_SATURATED = "color_mode_saturated";
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.color_mode_settings;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            int[] intArray = context.getResources().getIntArray(17235992);
            return intArray != null && intArray.length > 0 && !ColorDisplayManager.areAccessibilityTransformsEnabled(context);
        }
    };
    private ColorDisplayManager mColorDisplayManager;
    private ContentObserver mContentObserver;

    public int getMetricsCategory() {
        return 1143;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.color_mode_settings;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mColorDisplayManager = (ColorDisplayManager) context.getSystemService(ColorDisplayManager.class);
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
            public void onChange(boolean z, Uri uri) {
                super.onChange(z, uri);
                if (ColorDisplayManager.areAccessibilityTransformsEnabled(ColorModePreferenceFragment.this.getContext())) {
                    ColorModePreferenceFragment.this.getActivity().finish();
                }
            }
        };
        contentResolver.registerContentObserver(Settings.Secure.getUriFor("accessibility_display_inversion_enabled"), false, this.mContentObserver, this.mUserId);
        contentResolver.registerContentObserver(Settings.Secure.getUriFor("accessibility_display_daltonizer_enabled"), false, this.mContentObserver, this.mUserId);
    }

    public void onDetach() {
        super.onDetach();
        if (this.mContentObserver != null) {
            getContext().getContentResolver().unregisterContentObserver(this.mContentObserver);
            this.mContentObserver = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void configureAndInstallPreview(LayoutPreference layoutPreference, PreferenceScreen preferenceScreen) {
        layoutPreference.setSelectable(false);
        preferenceScreen.addPreference(layoutPreference);
    }

    /* access modifiers changed from: protected */
    public void addStaticPreferences(PreferenceScreen preferenceScreen) {
        configureAndInstallPreview(new LayoutPreference(preferenceScreen.getContext(), (int) C1715R.layout.color_mode_preview), preferenceScreen);
    }

    /* access modifiers changed from: protected */
    public List<? extends CandidateInfo> getCandidates() {
        Context context = getContext();
        int[] intArray = context.getResources().getIntArray(17235992);
        ArrayList arrayList = new ArrayList();
        if (intArray != null) {
            for (int i : intArray) {
                if (i == 0) {
                    arrayList.add(new ColorModeCandidateInfo(context.getText(C1715R.string.color_mode_option_natural), KEY_COLOR_MODE_NATURAL, true));
                } else if (i == 1) {
                    arrayList.add(new ColorModeCandidateInfo(context.getText(C1715R.string.color_mode_option_boosted), KEY_COLOR_MODE_BOOSTED, true));
                } else if (i == 2) {
                    arrayList.add(new ColorModeCandidateInfo(context.getText(C1715R.string.color_mode_option_saturated), KEY_COLOR_MODE_SATURATED, true));
                } else if (i == 3) {
                    arrayList.add(new ColorModeCandidateInfo(context.getText(C1715R.string.color_mode_option_automatic), KEY_COLOR_MODE_AUTOMATIC, true));
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public String getDefaultKey() {
        int colorMode = this.mColorDisplayManager.getColorMode();
        if (colorMode == 3) {
            return KEY_COLOR_MODE_AUTOMATIC;
        }
        if (colorMode == 2) {
            return KEY_COLOR_MODE_SATURATED;
        }
        return colorMode == 1 ? KEY_COLOR_MODE_BOOSTED : KEY_COLOR_MODE_NATURAL;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setDefaultKey(java.lang.String r6) {
        /*
            r5 = this;
            int r0 = r6.hashCode()
            r1 = 0
            r2 = 3
            r3 = 2
            r4 = 1
            switch(r0) {
                case -2029194174: goto L_0x002a;
                case -739564821: goto L_0x0020;
                case -365217559: goto L_0x0016;
                case 765917269: goto L_0x000c;
                default: goto L_0x000b;
            }
        L_0x000b:
            goto L_0x0034
        L_0x000c:
            java.lang.String r0 = "color_mode_saturated"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0034
            r6 = r3
            goto L_0x0035
        L_0x0016:
            java.lang.String r0 = "color_mode_natural"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0034
            r6 = r1
            goto L_0x0035
        L_0x0020:
            java.lang.String r0 = "color_mode_automatic"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0034
            r6 = r2
            goto L_0x0035
        L_0x002a:
            java.lang.String r0 = "color_mode_boosted"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0034
            r6 = r4
            goto L_0x0035
        L_0x0034:
            r6 = -1
        L_0x0035:
            if (r6 == 0) goto L_0x0050
            if (r6 == r4) goto L_0x004a
            if (r6 == r3) goto L_0x0044
            if (r6 == r2) goto L_0x003e
            goto L_0x0055
        L_0x003e:
            android.hardware.display.ColorDisplayManager r5 = r5.mColorDisplayManager
            r5.setColorMode(r2)
            goto L_0x0055
        L_0x0044:
            android.hardware.display.ColorDisplayManager r5 = r5.mColorDisplayManager
            r5.setColorMode(r3)
            goto L_0x0055
        L_0x004a:
            android.hardware.display.ColorDisplayManager r5 = r5.mColorDisplayManager
            r5.setColorMode(r4)
            goto L_0x0055
        L_0x0050:
            android.hardware.display.ColorDisplayManager r5 = r5.mColorDisplayManager
            r5.setColorMode(r1)
        L_0x0055:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.display.ColorModePreferenceFragment.setDefaultKey(java.lang.String):boolean");
    }

    static class ColorModeCandidateInfo extends CandidateInfo {
        private final String mKey;
        private final CharSequence mLabel;

        public Drawable loadIcon() {
            return null;
        }

        ColorModeCandidateInfo(CharSequence charSequence, String str, boolean z) {
            super(z);
            this.mLabel = charSequence;
            this.mKey = str;
        }

        public CharSequence loadLabel() {
            return this.mLabel;
        }

        public String getKey() {
            return this.mKey;
        }
    }
}
