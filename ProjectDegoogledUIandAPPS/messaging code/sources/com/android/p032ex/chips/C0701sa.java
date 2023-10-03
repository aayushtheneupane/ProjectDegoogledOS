package com.android.p032ex.chips;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/* renamed from: com.android.ex.chips.sa */
class C0701sa extends ArrayAdapter {
    private final C0704v mDropdownChipLayouter;

    /* renamed from: nl */
    private final StateListDrawable f828nl;

    public C0701sa(Context context, C0699ra raVar, C0704v vVar, StateListDrawable stateListDrawable) {
        super(context, vVar.getAlternateItemLayoutResId(DropdownChipLayouter$AdapterType.SINGLE_RECIPIENT), new C0699ra[]{raVar});
        this.mDropdownChipLayouter = vVar;
        this.f828nl = stateListDrawable;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return this.mDropdownChipLayouter.bindView(view, viewGroup, (C0699ra) getItem(i), i, DropdownChipLayouter$AdapterType.SINGLE_RECIPIENT, (String) null, this.f828nl);
    }
}
