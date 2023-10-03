package com.android.settings.gestures;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import androidx.preference.PreferenceScreen;
import com.android.settings.SettingsTutorialDialogWrapperActivity;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.utils.CandidateInfoExtra;
import com.android.settings.widget.RadioButtonPickerFragment;
import com.android.settings.widget.RadioButtonPreference;
import com.android.settings.widget.RadioButtonPreferenceWithExtraWidget;
import com.android.settings.widget.VideoPreference;
import com.android.settingslib.widget.CandidateInfo;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SystemNavigationGestureSettings extends RadioButtonPickerFragment {
    static int BACK_GESTURE_INSET_DEFAULT_OVERLAY = 1;
    static final String[] BACK_GESTURE_INSET_OVERLAYS = {NAV_BAR_MODE_GESTURAL_OVERLAY_NARROW_BACK, "com.android.internal.systemui.navbar.gestural", NAV_BAR_MODE_GESTURAL_OVERLAY_WIDE_BACK, NAV_BAR_MODE_GESTURAL_OVERLAY_EXTRA_WIDE_BACK};
    static final String KEY_SYSTEM_NAV_2BUTTONS = "system_nav_2buttons";
    static final String KEY_SYSTEM_NAV_3BUTTONS = "system_nav_3buttons";
    static final String KEY_SYSTEM_NAV_GESTURAL = "system_nav_gestural";
    static final String NAV_BAR_MODE_GESTURAL_OVERLAY_EXTRA_WIDE_BACK = "com.android.internal.systemui.navbar.gestural_extra_wide_back";
    static final String NAV_BAR_MODE_GESTURAL_OVERLAY_NARROW_BACK = "com.android.internal.systemui.navbar.gestural_narrow_back";
    static final String NAV_BAR_MODE_GESTURAL_OVERLAY_WIDE_BACK = "com.android.internal.systemui.navbar.gestural_wide_back";
    static final String PREFS_BACK_SENSITIVITY_KEY = "system_navigation_back_sensitivity";
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.system_navigation_gesture_settings;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            return SystemNavigationPreferenceController.isGestureAvailable(context);
        }
    };
    static final String SHARED_PREFERENCES_NAME = "system_navigation_settings_preferences";
    private IOverlayManager mOverlayManager;
    private VideoPreference mVideoPreference;

    public int getMetricsCategory() {
        return 1374;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.system_navigation_gesture_settings;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        FeatureFactory.getFactory(context).getSuggestionFeatureProvider(context).getSharedPrefs(context).edit().putBoolean("pref_system_navigation_suggestion_complete", true).apply();
        this.mOverlayManager = IOverlayManager.Stub.asInterface(ServiceManager.getService("overlay"));
        this.mVideoPreference = new VideoPreference(context);
        setIllustrationVideo(this.mVideoPreference, getDefaultKey());
        this.mVideoPreference.setHeight(getResources().getDimension(C1715R.dimen.system_navigation_illustration_height) / getResources().getDisplayMetrics().density);
    }

    public void updateCandidates() {
        String defaultKey = getDefaultKey();
        String systemDefaultKey = getSystemDefaultKey();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preferenceScreen.removeAll();
        preferenceScreen.addPreference(this.mVideoPreference);
        List<? extends CandidateInfo> candidates = getCandidates();
        if (candidates != null) {
            for (CandidateInfo candidateInfo : candidates) {
                RadioButtonPreferenceWithExtraWidget radioButtonPreferenceWithExtraWidget = new RadioButtonPreferenceWithExtraWidget(getPrefContext());
                bindPreference(radioButtonPreferenceWithExtraWidget, candidateInfo.getKey(), candidateInfo, defaultKey);
                bindPreferenceExtra(radioButtonPreferenceWithExtraWidget, candidateInfo.getKey(), candidateInfo, defaultKey, systemDefaultKey);
                preferenceScreen.addPreference(radioButtonPreferenceWithExtraWidget);
            }
            mayCheckOnlyRadioButton();
        }
    }

    public void bindPreferenceExtra(RadioButtonPreference radioButtonPreference, String str, CandidateInfo candidateInfo, String str2, String str3) {
        if ((candidateInfo instanceof CandidateInfoExtra) && (radioButtonPreference instanceof RadioButtonPreferenceWithExtraWidget)) {
            radioButtonPreference.setSummary(((CandidateInfoExtra) candidateInfo).loadSummary());
            RadioButtonPreferenceWithExtraWidget radioButtonPreferenceWithExtraWidget = (RadioButtonPreferenceWithExtraWidget) radioButtonPreference;
            if (candidateInfo.getKey() == KEY_SYSTEM_NAV_GESTURAL) {
                radioButtonPreferenceWithExtraWidget.setExtraWidgetVisibility(2);
                radioButtonPreferenceWithExtraWidget.setExtraWidgetOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        SystemNavigationGestureSettings.this.lambda$bindPreferenceExtra$0$SystemNavigationGestureSettings(view);
                    }
                });
                return;
            }
            radioButtonPreferenceWithExtraWidget.setExtraWidgetVisibility(0);
        }
    }

    public /* synthetic */ void lambda$bindPreferenceExtra$0$SystemNavigationGestureSettings(View view) {
        GestureNavigationBackSensitivityDialog.show(this, getBackSensitivity(getContext(), this.mOverlayManager), getBackHeight(getContext()), getHomeHandleSize(getContext()));
    }

    /* access modifiers changed from: protected */
    public List<? extends CandidateInfo> getCandidates() {
        Context context = getContext();
        ArrayList arrayList = new ArrayList();
        if (SystemNavigationPreferenceController.isOverlayPackageAvailable(context, "com.android.internal.systemui.navbar.gestural")) {
            arrayList.add(new CandidateInfoExtra(context.getText(C1715R.string.edge_to_edge_navigation_title), context.getText(C1715R.string.edge_to_edge_navigation_summary), KEY_SYSTEM_NAV_GESTURAL, true));
        }
        if (SystemNavigationPreferenceController.isOverlayPackageAvailable(context, "com.android.internal.systemui.navbar.twobutton")) {
            arrayList.add(new CandidateInfoExtra(context.getText(C1715R.string.swipe_up_to_switch_apps_title), context.getText(C1715R.string.swipe_up_to_switch_apps_summary), KEY_SYSTEM_NAV_2BUTTONS, true));
        }
        if (SystemNavigationPreferenceController.isOverlayPackageAvailable(context, "com.android.internal.systemui.navbar.threebutton")) {
            arrayList.add(new CandidateInfoExtra(context.getText(C1715R.string.legacy_navigation_title), context.getText(C1715R.string.legacy_navigation_summary), KEY_SYSTEM_NAV_3BUTTONS, true));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public String getDefaultKey() {
        return getCurrentSystemNavigationMode(getContext());
    }

    /* access modifiers changed from: protected */
    public boolean setDefaultKey(String str) {
        setCurrentSystemNavigationMode(getContext(), this.mOverlayManager, str);
        setIllustrationVideo(this.mVideoPreference, str);
        if (!TextUtils.equals(KEY_SYSTEM_NAV_GESTURAL, str)) {
            return true;
        }
        if (!isAnyServiceSupportAccessibilityButton() && !isNavBarMagnificationEnabled()) {
            return true;
        }
        Intent intent = new Intent(getActivity(), SettingsTutorialDialogWrapperActivity.class);
        intent.addFlags(268435456);
        startActivity(intent);
        return true;
    }

    static void setBackSensitivity(Context context, IOverlayManager iOverlayManager, int i) {
        if (i < 0 || i >= BACK_GESTURE_INSET_OVERLAYS.length) {
            throw new IllegalArgumentException("Sensitivity out of range.");
        }
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit().putInt(PREFS_BACK_SENSITIVITY_KEY, i).apply();
        if (getCurrentSystemNavigationMode(context) == KEY_SYSTEM_NAV_GESTURAL) {
            setNavBarInteractionMode(iOverlayManager, BACK_GESTURE_INSET_OVERLAYS[i], false);
        }
    }

    static int getBackSensitivity(Context context, IOverlayManager iOverlayManager) {
        int i = 0;
        while (true) {
            String[] strArr = BACK_GESTURE_INSET_OVERLAYS;
            if (i >= strArr.length) {
                return context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getInt(PREFS_BACK_SENSITIVITY_KEY, BACK_GESTURE_INSET_DEFAULT_OVERLAY);
            }
            OverlayInfo overlayInfo = null;
            try {
                overlayInfo = iOverlayManager.getOverlayInfo(strArr[i], -2);
            } catch (RemoteException unused) {
            }
            if (overlayInfo != null && overlayInfo.isEnabled()) {
                return i;
            }
            i++;
        }
    }

    static void setBackHeight(Context context, int i) {
        Settings.System.putInt(context.getContentResolver(), "back_gesture_height", i);
    }

    static int getBackHeight(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "back_gesture_height", 0);
    }

    static void setHomeHandleSize(Context context, int i) {
        Settings.System.putInt(context.getContentResolver(), "navigation_handle_width", i);
        updateNavigationBarOverlays(context);
    }

    static int getHomeHandleSize(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "navigation_handle_width", 1);
    }

    static String getCurrentSystemNavigationMode(Context context) {
        if (SystemNavigationPreferenceController.isEdgeToEdgeEnabled(context)) {
            return KEY_SYSTEM_NAV_GESTURAL;
        }
        return SystemNavigationPreferenceController.isSwipeUpEnabled(context) ? KEY_SYSTEM_NAV_2BUTTONS : KEY_SYSTEM_NAV_3BUTTONS;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0049  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void setCurrentSystemNavigationMode(android.content.Context r5, android.content.om.IOverlayManager r6, java.lang.String r7) {
        /*
            int r0 = r7.hashCode()
            r1 = -1860313413(0xffffffff911ddebb, float:-1.245375E-28)
            r2 = 2
            r3 = 1
            r4 = 0
            if (r0 == r1) goto L_0x002b
            r1 = -1375361165(0xffffffffae05a773, float:-3.0389424E-11)
            if (r0 == r1) goto L_0x0021
            r1 = -117503078(0xfffffffff8ff0b9a, float:-4.138347E34)
            if (r0 == r1) goto L_0x0017
            goto L_0x0035
        L_0x0017:
            java.lang.String r0 = "system_nav_3buttons"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0035
            r7 = r2
            goto L_0x0036
        L_0x0021:
            java.lang.String r0 = "system_nav_gestural"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0035
            r7 = r4
            goto L_0x0036
        L_0x002b:
            java.lang.String r0 = "system_nav_2buttons"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0035
            r7 = r3
            goto L_0x0036
        L_0x0035:
            r7 = -1
        L_0x0036:
            if (r7 == 0) goto L_0x0049
            if (r7 == r3) goto L_0x0043
            if (r7 == r2) goto L_0x003d
            goto L_0x0054
        L_0x003d:
            java.lang.String r5 = "com.android.internal.systemui.navbar.threebutton"
            setNavBarInteractionMode(r6, r5, r4)
            goto L_0x0054
        L_0x0043:
            java.lang.String r5 = "com.android.internal.systemui.navbar.twobutton"
            setNavBarInteractionMode(r6, r5, r4)
            goto L_0x0054
        L_0x0049:
            int r5 = getBackSensitivity(r5, r6)
            java.lang.String[] r7 = BACK_GESTURE_INSET_OVERLAYS
            r5 = r7[r5]
            setNavBarInteractionMode(r6, r5, r4)
        L_0x0054:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.gestures.SystemNavigationGestureSettings.setCurrentSystemNavigationMode(android.content.Context, android.content.om.IOverlayManager, java.lang.String):void");
    }

    private static void setNavBarInteractionMode(IOverlayManager iOverlayManager, String str, boolean z) {
        if (z) {
            try {
                iOverlayManager.setEnabled(str, false, -2);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        iOverlayManager.setEnabledExclusiveInCategory(str, -2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002a, code lost:
        if (r6.equals(KEY_SYSTEM_NAV_GESTURAL) != false) goto L_0x0038;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0053  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void setIllustrationVideo(com.android.settings.widget.VideoPreference r5, java.lang.String r6) {
        /*
            r0 = 0
            r5.setVideo(r0, r0)
            int r1 = r6.hashCode()
            r2 = -1860313413(0xffffffff911ddebb, float:-1.245375E-28)
            r3 = 2
            r4 = 1
            if (r1 == r2) goto L_0x002d
            r2 = -1375361165(0xffffffffae05a773, float:-3.0389424E-11)
            if (r1 == r2) goto L_0x0024
            r0 = -117503078(0xfffffffff8ff0b9a, float:-4.138347E34)
            if (r1 == r0) goto L_0x001a
            goto L_0x0037
        L_0x001a:
            java.lang.String r0 = "system_nav_3buttons"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0037
            r0 = r3
            goto L_0x0038
        L_0x0024:
            java.lang.String r1 = "system_nav_gestural"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0037
            goto L_0x0038
        L_0x002d:
            java.lang.String r0 = "system_nav_2buttons"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0037
            r0 = r4
            goto L_0x0038
        L_0x0037:
            r0 = -1
        L_0x0038:
            if (r0 == 0) goto L_0x0053
            if (r0 == r4) goto L_0x0049
            if (r0 == r3) goto L_0x003f
            goto L_0x005c
        L_0x003f:
            r6 = 2131820577(0x7f110021, float:1.9273873E38)
            r0 = 2131231518(0x7f08031e, float:1.807912E38)
            r5.setVideo(r6, r0)
            goto L_0x005c
        L_0x0049:
            r6 = 2131820576(0x7f110020, float:1.927387E38)
            r0 = 2131231517(0x7f08031d, float:1.8079117E38)
            r5.setVideo(r6, r0)
            goto L_0x005c
        L_0x0053:
            r6 = 2131820578(0x7f110022, float:1.9273875E38)
            r0 = 2131231519(0x7f08031f, float:1.8079121E38)
            r5.setVideo(r6, r0)
        L_0x005c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.gestures.SystemNavigationGestureSettings.setIllustrationVideo(com.android.settings.widget.VideoPreference, java.lang.String):void");
    }

    private boolean isAnyServiceSupportAccessibilityButton() {
        for (AccessibilityServiceInfo accessibilityServiceInfo : ((AccessibilityManager) getContext().getSystemService("accessibility")).getEnabledAccessibilityServiceList(-1)) {
            if ((accessibilityServiceInfo.flags & 256) != 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isNavBarMagnificationEnabled() {
        return Settings.Secure.getInt(getContext().getContentResolver(), "accessibility_display_magnification_navbar_enabled", 0) == 1;
    }

    public static void updateNavigationBarOverlays(Context context) {
        if (getCurrentSystemNavigationMode(context) == KEY_SYSTEM_NAV_GESTURAL) {
            IOverlayManager asInterface = IOverlayManager.Stub.asInterface(ServiceManager.getService("overlay"));
            setNavBarInteractionMode(asInterface, BACK_GESTURE_INSET_OVERLAYS[getBackSensitivity(context, asInterface)], true);
        }
    }
}
