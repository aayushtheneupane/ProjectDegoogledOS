package com.android.settings.applications.assist;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.text.TextUtils;
import com.android.internal.app.AssistUtils;
import com.android.settings.applications.assist.VoiceInputHelper;
import com.android.settings.applications.defaultapps.DefaultAppPickerFragment;
import com.android.settingslib.applications.DefaultAppInfo;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultVoiceInputPicker extends DefaultAppPickerFragment {
    private String mAssistRestrict;
    private AssistUtils mAssistUtils;
    private VoiceInputHelper mHelper;

    public int getMetricsCategory() {
        return 844;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.default_voice_settings;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mAssistUtils = new AssistUtils(context);
        this.mHelper = new VoiceInputHelper(context);
        this.mHelper.buildUi();
        ComponentName currentAssist = getCurrentAssist();
        if (isCurrentAssistVoiceService(currentAssist, getCurrentService(this.mHelper))) {
            this.mAssistRestrict = currentAssist.flattenToShortString();
        }
    }

    /* access modifiers changed from: protected */
    public List<VoiceInputDefaultAppInfo> getCandidates() {
        ArrayList arrayList = new ArrayList();
        Context context = getContext();
        Iterator<VoiceInputHelper.InteractionInfo> it = this.mHelper.mAvailableInteractionInfos.iterator();
        boolean z = true;
        while (it.hasNext()) {
            VoiceInputHelper.InteractionInfo next = it.next();
            boolean equals = TextUtils.equals(next.key, this.mAssistRestrict);
            arrayList.add(new VoiceInputDefaultAppInfo(context, this.mPm, this.mUserId, next, equals));
            z |= equals;
        }
        boolean z2 = !z;
        Iterator<VoiceInputHelper.RecognizerInfo> it2 = this.mHelper.mAvailableRecognizerInfos.iterator();
        while (it2.hasNext()) {
            arrayList.add(new VoiceInputDefaultAppInfo(context, this.mPm, this.mUserId, it2.next(), !z2));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public String getDefaultKey() {
        ComponentName currentService = getCurrentService(this.mHelper);
        if (currentService == null) {
            return null;
        }
        return currentService.flattenToShortString();
    }

    /* access modifiers changed from: protected */
    public boolean setDefaultKey(String str) {
        Iterator<VoiceInputHelper.InteractionInfo> it = this.mHelper.mAvailableInteractionInfos.iterator();
        while (it.hasNext()) {
            VoiceInputHelper.InteractionInfo next = it.next();
            if (TextUtils.equals(str, next.key)) {
                Settings.Secure.putString(getContext().getContentResolver(), "voice_interaction_service", str);
                Settings.Secure.putString(getContext().getContentResolver(), "voice_recognition_service", new ComponentName(next.service.packageName, next.serviceInfo.getRecognitionService()).flattenToShortString());
                return true;
            }
        }
        Iterator<VoiceInputHelper.RecognizerInfo> it2 = this.mHelper.mAvailableRecognizerInfos.iterator();
        while (true) {
            if (it2.hasNext()) {
                if (TextUtils.equals(str, it2.next().key)) {
                    Settings.Secure.putString(getContext().getContentResolver(), "voice_interaction_service", "");
                    Settings.Secure.putString(getContext().getContentResolver(), "voice_recognition_service", str);
                    break;
                }
            } else {
                break;
            }
        }
        return true;
    }

    public static ComponentName getCurrentService(VoiceInputHelper voiceInputHelper) {
        ComponentName componentName = voiceInputHelper.mCurrentVoiceInteraction;
        if (componentName != null) {
            return componentName;
        }
        ComponentName componentName2 = voiceInputHelper.mCurrentRecognizer;
        if (componentName2 != null) {
            return componentName2;
        }
        return null;
    }

    private ComponentName getCurrentAssist() {
        return this.mAssistUtils.getAssistComponentForUser(this.mUserId);
    }

    public static boolean isCurrentAssistVoiceService(ComponentName componentName, ComponentName componentName2) {
        return (componentName == null && componentName2 == null) || (componentName != null && componentName.equals(componentName2));
    }

    public static class VoiceInputDefaultAppInfo extends DefaultAppInfo {
        public VoiceInputHelper.BaseInfo mInfo;

        public VoiceInputDefaultAppInfo(Context context, PackageManager packageManager, int i, VoiceInputHelper.BaseInfo baseInfo, boolean z) {
            super(context, packageManager, i, baseInfo.componentName, (String) null, z);
            this.mInfo = baseInfo;
        }

        public String getKey() {
            return this.mInfo.key;
        }

        public CharSequence loadLabel() {
            VoiceInputHelper.BaseInfo baseInfo = this.mInfo;
            if (baseInfo instanceof VoiceInputHelper.InteractionInfo) {
                return baseInfo.appLabel;
            }
            return baseInfo.label;
        }

        public Intent getSettingIntent() {
            if (this.mInfo.settings == null) {
                return null;
            }
            return new Intent("android.intent.action.MAIN").setComponent(this.mInfo.settings);
        }
    }
}
