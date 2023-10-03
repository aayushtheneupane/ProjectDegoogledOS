package com.android.settings.dream;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.android.settings.widget.RadioButtonPickerFragment;
import com.android.settingslib.dream.DreamBackend;
import com.android.settingslib.widget.CandidateInfo;
import com.havoc.config.center.C1715R;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class CurrentDreamPicker extends RadioButtonPickerFragment {
    private DreamBackend mBackend;

    public int getMetricsCategory() {
        return 47;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.current_dream_settings;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mBackend = DreamBackend.getInstance(context);
    }

    /* access modifiers changed from: protected */
    public boolean setDefaultKey(String str) {
        Map<String, ComponentName> dreamComponentsMap = getDreamComponentsMap();
        if (dreamComponentsMap.get(str) == null) {
            return false;
        }
        this.mBackend.setActiveDream(dreamComponentsMap.get(str));
        return true;
    }

    /* access modifiers changed from: protected */
    public String getDefaultKey() {
        return this.mBackend.getActiveDream().flattenToString();
    }

    /* access modifiers changed from: protected */
    public List<? extends CandidateInfo> getCandidates() {
        return (List) this.mBackend.getDreamInfos().stream().map($$Lambda$hBSizG3ais67bSjAeIqNEa6sDBo.INSTANCE).collect(Collectors.toList());
    }

    /* access modifiers changed from: protected */
    public void onSelectionPerformed(boolean z) {
        super.onSelectionPerformed(z);
        getActivity().finish();
    }

    private Map<String, ComponentName> getDreamComponentsMap() {
        HashMap hashMap = new HashMap();
        this.mBackend.getDreamInfos().forEach(new Consumer(hashMap) {
            private final /* synthetic */ Map f$0;

            {
                this.f$0 = r1;
            }

            public final void accept(Object obj) {
                CurrentDreamPicker.lambda$getDreamComponentsMap$0(this.f$0, (DreamBackend.DreamInfo) obj);
            }
        });
        return hashMap;
    }

    static /* synthetic */ void lambda$getDreamComponentsMap$0(Map map, DreamBackend.DreamInfo dreamInfo) {
        ComponentName componentName = (ComponentName) map.put(dreamInfo.componentName.flattenToString(), dreamInfo.componentName);
    }

    private static final class DreamCandidateInfo extends CandidateInfo {
        private final Drawable icon;
        private final String key;
        private final CharSequence name;

        DreamCandidateInfo(DreamBackend.DreamInfo dreamInfo) {
            super(true);
            this.name = dreamInfo.caption;
            this.icon = dreamInfo.icon;
            this.key = dreamInfo.componentName.flattenToString();
        }

        public CharSequence loadLabel() {
            return this.name;
        }

        public Drawable loadIcon() {
            return this.icon;
        }

        public String getKey() {
            return this.key;
        }
    }
}
