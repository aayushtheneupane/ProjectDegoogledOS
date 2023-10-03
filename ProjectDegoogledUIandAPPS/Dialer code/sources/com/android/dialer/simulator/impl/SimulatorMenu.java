package com.android.dialer.simulator.impl;

import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import com.android.dialer.common.Assert;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Map;

public final class SimulatorMenu extends ActionProvider {
    Context context;
    SimulatorPortalEntryGroup portal;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SimulatorMenu(Context context2, SimulatorPortalEntryGroup simulatorPortalEntryGroup) {
        super(context2);
        Assert.isNotNull(context2);
        this.context = context2;
        this.portal = simulatorPortalEntryGroup;
    }

    public boolean hasSubMenu() {
        return true;
    }

    public View onCreateActionView() {
        return null;
    }

    public View onCreateActionView(MenuItem menuItem) {
        return null;
    }

    public void onPrepareSubMenu(SubMenu subMenu) {
        super.onPrepareSubMenu(subMenu);
        subMenu.clear();
        UnmodifiableIterator<Map.Entry<String, SimulatorPortalEntryGroup>> it = this.portal.subPortals().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            subMenu.add((CharSequence) next.getKey()).setActionProvider(new SimulatorMenu(this.context, (SimulatorPortalEntryGroup) next.getValue()));
        }
        UnmodifiableIterator<Map.Entry<String, Runnable>> it2 = this.portal.methods().entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry next2 = it2.next();
            subMenu.add((CharSequence) next2.getKey()).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(next2) {
                private final /* synthetic */ Map.Entry f$0;

                {
                    this.f$0 = r1;
                }

                public final boolean onMenuItemClick(MenuItem menuItem) {
                    boolean unused = ((Runnable) this.f$0.getValue()).run();
                    return true;
                }
            });
        }
    }
}
