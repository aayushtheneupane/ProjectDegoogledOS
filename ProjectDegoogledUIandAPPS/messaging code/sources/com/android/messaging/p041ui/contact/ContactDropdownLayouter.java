package com.android.messaging.p041ui.contact;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.text.BidiFormatter;
import androidx.core.text.TextDirectionHeuristicsCompat;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0918c;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.p041ui.ContactIconView;
import com.android.messaging.p041ui.contact.ContactListItemView;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1426c;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.ContactUtil;
import com.android.p032ex.chips.C0699ra;
import com.android.p032ex.chips.C0704v;
import com.android.p032ex.chips.DropdownChipLayouter$AdapterType;

/* renamed from: com.android.messaging.ui.contact.ContactDropdownLayouter */
public class ContactDropdownLayouter extends C0704v {
    private final ContactListItemView.HostInterface mClivHostInterface = new ContactListItemView.HostInterface() {
        public boolean isContactSelected(C0918c cVar) {
            return false;
        }

        public void onContactListItemClicked(C0918c cVar, ContactListItemView contactListItemView) {
        }
    };

    /* renamed from: com.android.messaging.ui.contact.ContactDropdownLayouter$2 */
    /* synthetic */ class C11192 {
        static final /* synthetic */ int[] $SwitchMap$com$android$ex$chips$DropdownChipLayouter$AdapterType = new int[DropdownChipLayouter$AdapterType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.android.ex.chips.DropdownChipLayouter$AdapterType[] r0 = com.android.p032ex.chips.DropdownChipLayouter$AdapterType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$android$ex$chips$DropdownChipLayouter$AdapterType = r0
                int[] r0 = $SwitchMap$com$android$ex$chips$DropdownChipLayouter$AdapterType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.android.ex.chips.DropdownChipLayouter$AdapterType r1 = com.android.p032ex.chips.DropdownChipLayouter$AdapterType.BASE_RECIPIENT     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$android$ex$chips$DropdownChipLayouter$AdapterType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.android.ex.chips.DropdownChipLayouter$AdapterType r1 = com.android.p032ex.chips.DropdownChipLayouter$AdapterType.RECIPIENT_ALTERNATES     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.contact.ContactDropdownLayouter.C11192.<clinit>():void");
        }
    }

    public ContactDropdownLayouter(LayoutInflater layoutInflater, Context context, ContactListItemView.HostInterface hostInterface) {
        super(layoutInflater, context);
    }

    /* access modifiers changed from: protected */
    public void bindIconToView(boolean z, C0699ra raVar, ImageView imageView, DropdownChipLayouter$AdapterType dropdownChipLayouter$AdapterType) {
        if (!z || !(imageView instanceof ContactIconView)) {
            super.bindIconToView(z, raVar, imageView, dropdownChipLayouter$AdapterType);
            return;
        }
        ContactIconView contactIconView = (ContactIconView) imageView;
        contactIconView.setImageClickHandlerDisabled(true);
        contactIconView.mo6930f(C1426c.m3601c(ParticipantData.m1833b(raVar)));
    }

    public View bindView(View view, ViewGroup viewGroup, C0699ra raVar, int i, DropdownChipLayouter$AdapterType dropdownChipLayouter$AdapterType, String str, StateListDrawable stateListDrawable) {
        if (dropdownChipLayouter$AdapterType != DropdownChipLayouter$AdapterType.BASE_RECIPIENT) {
            if (dropdownChipLayouter$AdapterType == DropdownChipLayouter$AdapterType.SINGLE_RECIPIENT) {
                dropdownChipLayouter$AdapterType = DropdownChipLayouter$AdapterType.RECIPIENT_ALTERNATES;
            }
            return super.bindView(view, viewGroup, raVar, i, dropdownChipLayouter$AdapterType, str, stateListDrawable);
        }
        BidiFormatter instance = BidiFormatter.getInstance();
        String unicodeWrap = instance.unicodeWrap(C1430e.m3619d(raVar), TextDirectionHeuristicsCompat.LTR);
        String unicodeWrap2 = instance.unicodeWrap(C1430e.m3617c(raVar), TextDirectionHeuristicsCompat.LTR);
        View reuseOrInflateView = reuseOrInflateView(view, viewGroup, dropdownChipLayouter$AdapterType);
        CharSequence[] styledResults = getStyledResults(str, unicodeWrap, unicodeWrap2);
        C1424b.m3592ia(reuseOrInflateView instanceof ContactListItemView);
        ContactListItemView contactListItemView = (ContactListItemView) reuseOrInflateView;
        contactListItemView.setImageClickHandlerDisabled(true);
        contactListItemView.bind(raVar, styledResults[0], styledResults[1], this.mClivHostInterface, dropdownChipLayouter$AdapterType == DropdownChipLayouter$AdapterType.SINGLE_RECIPIENT, ContactUtil.isEnterpriseContactId(raVar.getContactId()));
        return reuseOrInflateView;
    }

    /* access modifiers changed from: protected */
    public int getAlternateItemLayoutResId(DropdownChipLayouter$AdapterType dropdownChipLayouter$AdapterType) {
        return getItemLayoutResId(dropdownChipLayouter$AdapterType);
    }

    /* access modifiers changed from: protected */
    public int getItemLayoutResId(DropdownChipLayouter$AdapterType dropdownChipLayouter$AdapterType) {
        int ordinal = dropdownChipLayouter$AdapterType.ordinal();
        if (ordinal == 0) {
            return R.layout.contact_list_item_view;
        }
        if (ordinal != 1) {
        }
        return R.layout.chips_alternates_dropdown_item;
    }
}
