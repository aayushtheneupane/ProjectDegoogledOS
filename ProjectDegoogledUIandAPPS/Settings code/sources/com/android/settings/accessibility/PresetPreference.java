package com.android.settings.accessibility;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.CaptioningManager;
import android.widget.TextView;
import com.android.internal.widget.SubtitleView;
import com.havoc.config.center.C1715R;

public class PresetPreference extends ListDialogPreference {
    private final CaptioningManager mCaptioningManager;

    public PresetPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDialogLayoutResource(C1715R.layout.grid_picker_dialog);
        setListItemLayoutResource(C1715R.layout.preset_picker_item);
        this.mCaptioningManager = (CaptioningManager) context.getSystemService("captioning");
    }

    public boolean shouldDisableDependents() {
        return getValue() != -1 || super.shouldDisableDependents();
    }

    /* access modifiers changed from: protected */
    public void onBindListItem(View view, int i) {
        View findViewById = view.findViewById(C1715R.C1718id.preview_viewport);
        SubtitleView findViewById2 = view.findViewById(C1715R.C1718id.preview);
        CaptionPropertiesFragment.applyCaptionProperties(this.mCaptioningManager, findViewById2, findViewById, getValueAt(i));
        findViewById2.setTextSize(getContext().getResources().getDisplayMetrics().density * 32.0f);
        CharSequence titleAt = getTitleAt(i);
        if (titleAt != null) {
            ((TextView) view.findViewById(C1715R.C1718id.summary)).setText(titleAt);
        }
    }
}
