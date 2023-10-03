package com.android.settings.accessibility;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.accessibility.CaptioningManager;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.internal.widget.SubtitleView;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.accessibility.ListDialogPreference;
import com.android.settingslib.accessibility.AccessibilityUtils;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CaptionPropertiesFragment extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, ListDialogPreference.OnValueChangedListener {
    private ColorPreference mBackgroundColor;
    private ColorPreference mBackgroundOpacity;
    private CaptioningManager mCaptioningManager;
    private PreferenceCategory mCustom;
    private ColorPreference mEdgeColor;
    private EdgeTypePreference mEdgeType;
    private ListPreference mFontSize;
    private ColorPreference mForegroundColor;
    private ColorPreference mForegroundOpacity;
    private LocalePreference mLocale;
    private final List<Preference> mPreferenceList = new ArrayList();
    private PresetPreference mPreset;
    private SubtitleView mPreviewText;
    private View mPreviewViewport;
    private View mPreviewWindow;
    private boolean mShowingCustom;
    private SwitchPreference mSwitch;
    private ListPreference mTypeface;
    private ColorPreference mWindowColor;
    private ColorPreference mWindowOpacity;

    public int getMetricsCategory() {
        return 3;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCaptioningManager = (CaptioningManager) getSystemService("captioning");
        addPreferencesFromResource(C1715R.xml.captioning_settings);
        initializeAllPreferences();
        updateAllPreferences();
        refreshShowingCustom();
        installUpdateListeners();
        refreshPreviewText();
    }

    private void setPreferenceViewEnabled(boolean z) {
        for (Preference enabled : this.mPreferenceList) {
            enabled.setEnabled(z);
        }
    }

    private void refreshPreferenceViewEnabled(boolean z) {
        setPreferenceViewEnabled(z);
        this.mPreviewText.setVisibility(z ? 0 : 4);
    }

    private void refreshPreviewText() {
        SubtitleView subtitleView;
        FragmentActivity activity = getActivity();
        if (activity != null && (subtitleView = this.mPreviewText) != null) {
            applyCaptionProperties(this.mCaptioningManager, subtitleView, this.mPreviewViewport, this.mCaptioningManager.getRawUserStyle());
            Locale locale = this.mCaptioningManager.getLocale();
            if (locale != null) {
                subtitleView.setText(AccessibilityUtils.getTextForLocale(activity, locale, C1715R.string.captioning_preview_text));
            } else {
                subtitleView.setText(C1715R.string.captioning_preview_text);
            }
            CaptioningManager.CaptionStyle userStyle = this.mCaptioningManager.getUserStyle();
            if (userStyle.hasWindowColor()) {
                this.mPreviewWindow.setBackgroundColor(userStyle.windowColor);
                return;
            }
            this.mPreviewWindow.setBackgroundColor(CaptioningManager.CaptionStyle.DEFAULT.windowColor);
        }
    }

    public static void applyCaptionProperties(CaptioningManager captioningManager, SubtitleView subtitleView, View view, int i) {
        subtitleView.setStyle(i);
        Context context = subtitleView.getContext();
        context.getContentResolver();
        float fontScale = captioningManager.getFontScale();
        if (view != null) {
            subtitleView.setTextSize((((float) Math.max(view.getWidth() * 9, view.getHeight() * 16)) / 16.0f) * 0.0533f * fontScale);
        } else {
            subtitleView.setTextSize(context.getResources().getDimension(C1715R.dimen.caption_preview_text_size) * fontScale);
        }
        Locale locale = captioningManager.getLocale();
        if (locale != null) {
            subtitleView.setText(AccessibilityUtils.getTextForLocale(context, locale, C1715R.string.captioning_preview_characters));
        } else {
            subtitleView.setText(C1715R.string.captioning_preview_characters);
        }
    }

    private void initializeAllPreferences() {
        LayoutPreference layoutPreference = (LayoutPreference) findPreference("caption_preview");
        this.mPreviewText = layoutPreference.findViewById(C1715R.C1718id.preview_text);
        this.mPreviewWindow = layoutPreference.findViewById(C1715R.C1718id.preview_window);
        this.mPreviewViewport = layoutPreference.findViewById(C1715R.C1718id.preview_viewport);
        this.mPreviewViewport.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                CaptionPropertiesFragment.this.lambda$initializeAllPreferences$0$CaptionPropertiesFragment(view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
        Resources resources = getResources();
        int[] intArray = resources.getIntArray(C1715R.array.captioning_preset_selector_values);
        String[] stringArray = resources.getStringArray(C1715R.array.captioning_preset_selector_titles);
        this.mPreset = (PresetPreference) findPreference("captioning_preset");
        this.mPreset.setValues(intArray);
        this.mPreset.setTitles(stringArray);
        this.mSwitch = (SwitchPreference) findPreference("captioning_preference_switch");
        this.mLocale = (LocalePreference) findPreference("captioning_locale");
        this.mFontSize = (ListPreference) findPreference("captioning_font_size");
        this.mPreferenceList.add(this.mLocale);
        this.mPreferenceList.add(this.mFontSize);
        this.mPreferenceList.add(this.mPreset);
        refreshPreferenceViewEnabled(this.mCaptioningManager.isEnabled());
        this.mCustom = (PreferenceCategory) findPreference("custom");
        this.mShowingCustom = true;
        int[] intArray2 = resources.getIntArray(C1715R.array.captioning_color_selector_values);
        String[] stringArray2 = resources.getStringArray(C1715R.array.captioning_color_selector_titles);
        this.mForegroundColor = (ColorPreference) this.mCustom.findPreference("captioning_foreground_color");
        this.mForegroundColor.setTitles(stringArray2);
        this.mForegroundColor.setValues(intArray2);
        int[] intArray3 = resources.getIntArray(C1715R.array.captioning_opacity_selector_values);
        String[] stringArray3 = resources.getStringArray(C1715R.array.captioning_opacity_selector_titles);
        this.mForegroundOpacity = (ColorPreference) this.mCustom.findPreference("captioning_foreground_opacity");
        this.mForegroundOpacity.setTitles(stringArray3);
        this.mForegroundOpacity.setValues(intArray3);
        this.mEdgeColor = (ColorPreference) this.mCustom.findPreference("captioning_edge_color");
        this.mEdgeColor.setTitles(stringArray2);
        this.mEdgeColor.setValues(intArray2);
        int[] iArr = new int[(intArray2.length + 1)];
        String[] strArr = new String[(stringArray2.length + 1)];
        System.arraycopy(intArray2, 0, iArr, 1, intArray2.length);
        System.arraycopy(stringArray2, 0, strArr, 1, stringArray2.length);
        iArr[0] = 0;
        strArr[0] = getString(C1715R.string.color_none);
        this.mBackgroundColor = (ColorPreference) this.mCustom.findPreference("captioning_background_color");
        this.mBackgroundColor.setTitles(strArr);
        this.mBackgroundColor.setValues(iArr);
        this.mBackgroundOpacity = (ColorPreference) this.mCustom.findPreference("captioning_background_opacity");
        this.mBackgroundOpacity.setTitles(stringArray3);
        this.mBackgroundOpacity.setValues(intArray3);
        this.mWindowColor = (ColorPreference) this.mCustom.findPreference("captioning_window_color");
        this.mWindowColor.setTitles(strArr);
        this.mWindowColor.setValues(iArr);
        this.mWindowOpacity = (ColorPreference) this.mCustom.findPreference("captioning_window_opacity");
        this.mWindowOpacity.setTitles(stringArray3);
        this.mWindowOpacity.setValues(intArray3);
        this.mEdgeType = (EdgeTypePreference) this.mCustom.findPreference("captioning_edge_type");
        this.mTypeface = (ListPreference) this.mCustom.findPreference("captioning_typeface");
    }

    public /* synthetic */ void lambda$initializeAllPreferences$0$CaptionPropertiesFragment(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        refreshPreviewText();
    }

    private void installUpdateListeners() {
        this.mPreset.setOnValueChangedListener(this);
        this.mForegroundColor.setOnValueChangedListener(this);
        this.mForegroundOpacity.setOnValueChangedListener(this);
        this.mEdgeColor.setOnValueChangedListener(this);
        this.mBackgroundColor.setOnValueChangedListener(this);
        this.mBackgroundOpacity.setOnValueChangedListener(this);
        this.mWindowColor.setOnValueChangedListener(this);
        this.mWindowOpacity.setOnValueChangedListener(this);
        this.mEdgeType.setOnValueChangedListener(this);
        this.mSwitch.setOnPreferenceChangeListener(this);
        this.mTypeface.setOnPreferenceChangeListener(this);
        this.mFontSize.setOnPreferenceChangeListener(this);
        this.mLocale.setOnPreferenceChangeListener(this);
    }

    private void updateAllPreferences() {
        this.mPreset.setValue(this.mCaptioningManager.getRawUserStyle());
        this.mFontSize.setValue(Float.toString(this.mCaptioningManager.getFontScale()));
        CaptioningManager.CaptionStyle customStyle = CaptioningManager.CaptionStyle.getCustomStyle(getContentResolver());
        this.mEdgeType.setValue(customStyle.edgeType);
        this.mEdgeColor.setValue(customStyle.edgeColor);
        int i = 16777215;
        parseColorOpacity(this.mForegroundColor, this.mForegroundOpacity, customStyle.hasForegroundColor() ? customStyle.foregroundColor : 16777215);
        parseColorOpacity(this.mBackgroundColor, this.mBackgroundOpacity, customStyle.hasBackgroundColor() ? customStyle.backgroundColor : 16777215);
        if (customStyle.hasWindowColor()) {
            i = customStyle.windowColor;
        }
        parseColorOpacity(this.mWindowColor, this.mWindowOpacity, i);
        String str = customStyle.mRawTypeface;
        ListPreference listPreference = this.mTypeface;
        if (str == null) {
            str = "";
        }
        listPreference.setValue(str);
        String rawLocale = this.mCaptioningManager.getRawLocale();
        LocalePreference localePreference = this.mLocale;
        if (rawLocale == null) {
            rawLocale = "";
        }
        localePreference.setValue(rawLocale);
        this.mSwitch.setChecked(this.mCaptioningManager.isEnabled());
    }

    private void parseColorOpacity(ColorPreference colorPreference, ColorPreference colorPreference2, int i) {
        int i2;
        int i3;
        if (!CaptioningManager.CaptionStyle.hasColor(i)) {
            i2 = (i & 255) << 24;
            i3 = 16777215;
        } else if ((i >>> 24) == 0) {
            i3 = 0;
            i2 = (i & 255) << 24;
        } else {
            i3 = i | -16777216;
            i2 = -16777216 & i;
        }
        colorPreference2.setValue(i2 | 16777215);
        colorPreference.setValue(i3);
    }

    private int mergeColorOpacity(ColorPreference colorPreference, ColorPreference colorPreference2) {
        int value = colorPreference.getValue();
        int value2 = colorPreference2.getValue();
        if (!CaptioningManager.CaptionStyle.hasColor(value)) {
            return 16776960 | Color.alpha(value2);
        }
        return value == 0 ? Color.alpha(value2) : (value & 16777215) | (value2 & -16777216);
    }

    private void refreshShowingCustom() {
        boolean z = this.mPreset.getValue() == -1;
        if (!z && this.mShowingCustom) {
            getPreferenceScreen().removePreference(this.mCustom);
            this.mShowingCustom = false;
        } else if (z && !this.mShowingCustom) {
            getPreferenceScreen().addPreference(this.mCustom);
            this.mShowingCustom = true;
        }
    }

    public void onValueChanged(ListDialogPreference listDialogPreference, int i) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        if (this.mForegroundColor == listDialogPreference || this.mForegroundOpacity == listDialogPreference) {
            Settings.Secure.putInt(contentResolver, "accessibility_captioning_foreground_color", mergeColorOpacity(this.mForegroundColor, this.mForegroundOpacity));
        } else if (this.mBackgroundColor == listDialogPreference || this.mBackgroundOpacity == listDialogPreference) {
            Settings.Secure.putInt(contentResolver, "accessibility_captioning_background_color", mergeColorOpacity(this.mBackgroundColor, this.mBackgroundOpacity));
        } else if (this.mWindowColor == listDialogPreference || this.mWindowOpacity == listDialogPreference) {
            Settings.Secure.putInt(contentResolver, "accessibility_captioning_window_color", mergeColorOpacity(this.mWindowColor, this.mWindowOpacity));
        } else if (this.mEdgeColor == listDialogPreference) {
            Settings.Secure.putInt(contentResolver, "accessibility_captioning_edge_color", i);
        } else if (this.mPreset == listDialogPreference) {
            Settings.Secure.putInt(contentResolver, "accessibility_captioning_preset", i);
            refreshShowingCustom();
        } else if (this.mEdgeType == listDialogPreference) {
            Settings.Secure.putInt(contentResolver, "accessibility_captioning_edge_type", i);
        }
        refreshPreviewText();
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        if (this.mTypeface == preference) {
            Settings.Secure.putString(contentResolver, "accessibility_captioning_typeface", (String) obj);
            refreshPreviewText();
            return true;
        } else if (this.mFontSize == preference) {
            Settings.Secure.putFloat(contentResolver, "accessibility_captioning_font_scale", Float.parseFloat((String) obj));
            refreshPreviewText();
            return true;
        } else if (this.mLocale == preference) {
            Settings.Secure.putString(contentResolver, "accessibility_captioning_locale", (String) obj);
            refreshPreviewText();
            return true;
        } else if (this.mSwitch != preference) {
            return true;
        } else {
            Boolean bool = (Boolean) obj;
            Settings.Secure.putInt(contentResolver, "accessibility_captioning_enabled", bool.booleanValue() ? 1 : 0);
            refreshPreferenceViewEnabled(bool.booleanValue());
            return true;
        }
    }
}
