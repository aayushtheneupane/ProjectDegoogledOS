package com.android.settings.shortcut;

import android.content.Context;
import android.os.Bundle;
import com.android.settings.dashboard.DashboardFragment;
import com.havoc.config.center.C1715R;

public class CreateShortcut extends DashboardFragment {
    public int getHelpResource() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "CreateShortcut";
    }

    public int getMetricsCategory() {
        return 1503;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.create_shortcut;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
            setArguments(arguments);
        }
        arguments.putBoolean("need_search_icon_in_action_bar", false);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        ((CreateShortcutPreferenceController) use(CreateShortcutPreferenceController.class)).setActivity(getActivity());
    }
}
