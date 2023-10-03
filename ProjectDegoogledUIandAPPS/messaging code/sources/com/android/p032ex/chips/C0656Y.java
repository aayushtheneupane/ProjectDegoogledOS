package com.android.p032ex.chips;

import java.util.List;

/* renamed from: com.android.ex.chips.Y */
class C0656Y implements C0680i {
    final /* synthetic */ C0697qa this$0;

    C0656Y(C0697qa qaVar) {
        this.this$0 = qaVar;
    }

    /* renamed from: f */
    public void mo5461f(List list) {
        int size = list == null ? 0 : list.size();
        if (list != null && list.size() > 0) {
            this.this$0.scrollBottomIntoView();
            if (this.this$0.mCurrentSuggestionCount == 0) {
                C0697qa qaVar = this.this$0;
                qaVar.announceForAccessibilityCompat(qaVar.getSuggestionDropdownOpenedVerbalization(size));
            }
        }
        if ((list == null || list.size() == 0) && this.this$0.mCurrentSuggestionCount != 0 && this.this$0.getText().length() > 0) {
            C0697qa qaVar2 = this.this$0;
            qaVar2.announceForAccessibilityCompat(qaVar2.getResources().getString(C0642J.accessbility_suggestion_dropdown_closed));
        }
        if (!(list != null && list.size() == 1 && ((C0699ra) list.get(0)).getEntryType() == 1)) {
            this.this$0.mDropdownAnchor.getLocationOnScreen(this.this$0.mCoords);
            C0697qa qaVar3 = this.this$0;
            qaVar3.getWindowVisibleDisplayFrame(qaVar3.mRect);
            C0697qa qaVar4 = this.this$0;
            qaVar4.setDropDownHeight(((qaVar4.mRect.bottom - this.this$0.mCoords[1]) - this.this$0.mDropdownAnchor.getHeight()) - this.this$0.getDropDownVerticalOffset());
        }
        int unused = this.this$0.mCurrentSuggestionCount = size;
    }
}
