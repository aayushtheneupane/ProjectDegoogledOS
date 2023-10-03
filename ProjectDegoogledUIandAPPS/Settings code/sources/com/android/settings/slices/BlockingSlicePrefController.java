package com.android.settings.slices;

import android.content.Context;
import androidx.slice.Slice;
import com.android.settings.core.BasePreferenceController;

public class BlockingSlicePrefController extends SlicePreferenceController implements BasePreferenceController.UiBlocker {
    public BlockingSlicePrefController(Context context, String str) {
        super(context, str);
    }

    public void onChanged(Slice slice) {
        super.onChanged(slice);
        BasePreferenceController.UiBlockListener uiBlockListener = this.mUiBlockListener;
        if (uiBlockListener != null) {
            uiBlockListener.onBlockerWorkFinished(this);
        }
    }
}
