package com.android.messaging.p041ui.attachmentchooser;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1486ya;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.android.messaging.ui.attachmentchooser.AttachmentGridView */
public class AttachmentGridView extends GridView implements C1110f {

    /* renamed from: Xe */
    private final Set f1760Xe = new HashSet();
    private C1112h mHost;

    /* renamed from: com.android.messaging.ui.attachmentchooser.AttachmentGridView$SavedState */
    public class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR = new C1113i();

        /* renamed from: Se */
        MessagePartData[] f1761Se;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f1761Se.length);
            for (MessagePartData writeParcelable : this.f1761Se) {
                parcel.writeParcelable(writeParcelable, i);
            }
        }

        /* synthetic */ SavedState(Parcel parcel, C1111g gVar) {
            super(parcel);
            int readInt = parcel.readInt();
            this.f1761Se = new MessagePartData[readInt];
            for (int i = 0; i < readInt; i++) {
                this.f1761Se[i] = (MessagePartData) parcel.readParcelable(MessagePartData.class.getClassLoader());
            }
        }
    }

    public AttachmentGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: Db */
    public Set mo7208Db() {
        return Collections.unmodifiableSet(this.f1760Xe);
    }

    /* renamed from: a */
    public void mo7210a(C1112h hVar) {
        this.mHost = hVar;
    }

    /* renamed from: b */
    public void mo7212b(AttachmentGridItemView attachmentGridItemView, MessagePartData messagePartData) {
        if (mo7211a(messagePartData)) {
            this.f1760Xe.add(messagePartData);
        } else {
            this.f1760Xe.remove(messagePartData);
        }
        attachmentGridItemView.mo7203Ib();
        int count = getAdapter().getCount() - this.f1760Xe.size();
        C1424b.m3592ia(count >= 0);
        this.mHost.mo7198f(count);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f1760Xe.clear();
        int i = 0;
        while (true) {
            MessagePartData[] messagePartDataArr = savedState.f1761Se;
            if (i >= messagePartDataArr.length) {
                break;
            }
            this.f1760Xe.add(messagePartDataArr[i]);
            i++;
        }
        if (getAdapter() instanceof C1106b) {
            ((C1106b) getAdapter()).notifyDataSetChanged();
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Set set = this.f1760Xe;
        savedState.f1761Se = (MessagePartData[]) set.toArray(new MessagePartData[set.size()]);
        return savedState;
    }

    /* renamed from: a */
    public boolean mo7211a(MessagePartData messagePartData) {
        return !this.f1760Xe.contains(messagePartData);
    }

    /* renamed from: a */
    public void mo7209a(AttachmentGridItemView attachmentGridItemView, MessagePartData messagePartData) {
        if (messagePartData.mo6304fh()) {
            this.mHost.mo7194a(C1486ya.m3858h(attachmentGridItemView), messagePartData.getContentUri());
        }
    }
}
