package com.android.settings.widget;

import android.content.Context;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.ArrayList;
import java.util.List;

public class PreferenceCategoryController extends BasePreferenceController {
    private final List<AbstractPreferenceController> mChildren = new ArrayList();
    private final String mKey;

    public PreferenceCategoryController(Context context, String str) {
        super(context, str);
        this.mKey = str;
    }

    public int getAvailabilityStatus() {
        List<AbstractPreferenceController> list = this.mChildren;
        if (list == null || list.isEmpty()) {
            return 3;
        }
        for (AbstractPreferenceController isAvailable : this.mChildren) {
            if (isAvailable.isAvailable()) {
                return 0;
            }
        }
        return 2;
    }

    public String getPreferenceKey() {
        return this.mKey;
    }

    public PreferenceCategoryController setChildren(List<AbstractPreferenceController> list) {
        this.mChildren.clear();
        if (list != null) {
            this.mChildren.addAll(list);
        }
        return this;
    }
}
