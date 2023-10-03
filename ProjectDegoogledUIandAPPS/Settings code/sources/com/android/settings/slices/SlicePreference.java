package com.android.settings.slices;

import android.content.Context;
import android.util.AttributeSet;
import androidx.slice.Slice;
import androidx.slice.widget.SliceView;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;

public class SlicePreference extends LayoutPreference {
    private SliceView mSliceView;

    public SlicePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, C1715R.attr.slicePreferenceStyle);
        init();
    }

    public SlicePreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.mSliceView = (SliceView) findViewById(C1715R.C1718id.slice_view);
        this.mSliceView.showTitleItems(true);
        this.mSliceView.setScrollable(false);
    }

    public void onSliceUpdated(Slice slice) {
        this.mSliceView.onChanged(slice);
        notifyChanged();
    }
}
