package com.android.messaging.p041ui.mediapicker;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0889A;
import com.android.messaging.datamodel.data.C0890B;
import com.android.messaging.datamodel.data.C0942y;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.datamodel.p037a.C0786f;
import com.android.messaging.p041ui.C1072Z;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1481w;
import java.util.Iterator;
import java.util.Map;
import p000a.p005b.C0015b;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.ui.mediapicker.GalleryGridView */
public class GalleryGridView extends C1347qa implements C1290S, C1072Z, C0942y {

    /* renamed from: Sa */
    private C0786f f2019Sa;

    /* renamed from: Ye */
    private final C0015b f2020Ye = new C0015b();

    /* renamed from: Ze */
    private boolean f2021Ze = false;
    private C1292U mListener;

    /* renamed from: com.android.messaging.ui.mediapicker.GalleryGridView$SavedState */
    public class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR = new C1293V();

        /* renamed from: Te */
        boolean f2022Te;

        /* renamed from: Ue */
        MessagePartData[] f2023Ue;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f2022Te ? 1 : 0);
            parcel.writeInt(this.f2023Ue.length);
            for (MessagePartData writeParcelable : this.f2023Ue) {
                parcel.writeParcelable(writeParcelable, i);
            }
        }

        /* synthetic */ SavedState(Parcel parcel, C1291T t) {
            super(parcel);
            this.f2022Te = parcel.readInt() != 1 ? false : true;
            int readInt = parcel.readInt();
            this.f2023Ue = new MessagePartData[readInt];
            for (int i = 0; i < readInt; i++) {
                this.f2023Ue[i] = (MessagePartData) parcel.readParcelable(MessagePartData.class.getClassLoader());
            }
        }
    }

    public GalleryGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: Jm */
    private boolean m3234Jm() {
        return this.f2020Ye.size() == 0;
    }

    /* renamed from: Km */
    private void m3235Km() {
        Iterator it = this.f2020Ye.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (!((C0889A) this.f2019Sa.getData()).mo6191g((Uri) ((Map.Entry) it.next()).getKey())) {
                it.remove();
                z = true;
            }
        }
        if (z) {
            ((C1295X) this.mListener).f2118Dj.invalidateOptionsMenu();
            invalidateViews();
        }
    }

    /* renamed from: sa */
    private void m3236sa(boolean z) {
        boolean z2 = this.f2021Ze;
        if (z2 != z) {
            this.f2021Ze = !z2;
            invalidateViews();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Fb */
    public int mo7706Fb() {
        return this.f2020Ye.size();
    }

    /* renamed from: a */
    public void mo7710a(C1292U u) {
        this.mListener = u;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_confirm_multiselect) {
            C1424b.m3592ia(!m3234Jm());
            ((C1295X) this.mListener).mo7757da();
            return true;
        } else if (itemId != R.id.action_multiselect) {
            return false;
        } else {
            C1424b.m3592ia(m3234Jm());
            this.f2021Ze = !this.f2021Ze;
            invalidateViews();
            return true;
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f2021Ze = savedState.f2022Te;
        this.f2020Ye.clear();
        int i = 0;
        while (true) {
            MessagePartData[] messagePartDataArr = savedState.f2023Ue;
            if (i < messagePartDataArr.length) {
                MessagePartData messagePartData = messagePartDataArr[i];
                this.f2020Ye.put(messagePartData.getContentUri(), messagePartData);
                i++;
            } else {
                return;
            }
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f2022Te = this.f2021Ze;
        savedState.f2023Ue = (MessagePartData[]) this.f2020Ye.values().toArray(new MessagePartData[this.f2020Ye.size()]);
        return savedState;
    }

    /* renamed from: q */
    public boolean mo7715q() {
        return this.f2021Ze;
    }

    /* renamed from: r */
    public void mo6223r() {
    }

    public void resetState() {
        this.f2020Ye.clear();
        this.f2021Ze = false;
        invalidateViews();
    }

    public void restoreState(Parcelable parcelable) {
        onRestoreInstanceState(parcelable);
        invalidateViews();
    }

    public Parcelable saveState() {
        return onSaveInstanceState();
    }

    /* renamed from: a */
    public void mo7709a(C0784d dVar) {
        this.f2019Sa = C0784d.m1314f(dVar);
        ((C0889A) this.f2019Sa.getData()).mo6176a((C0942y) this);
    }

    /* renamed from: a */
    public void mo7708a(View view, C0890B b, boolean z) {
        if (b.mo6208Ng()) {
            ((C1295X) this.mListener).mo7755Vi();
        } else if (C1481w.m3827Ba(b.getContentType())) {
            if (z) {
                m3236sa(true);
            }
            Rect rect = new Rect();
            view.getGlobalVisibleRect(rect);
            if (mo7715q()) {
                C1424b.m3592ia(mo7715q());
                if (mo7711a(b)) {
                    ((C1295X) this.mListener).f2118Dj.mo7902d((MessagePartData) this.f2020Ye.remove(b.mo6207Mg()));
                    if (this.f2020Ye.size() == 0) {
                        m3236sa(false);
                    }
                } else {
                    MessagePartData b2 = b.mo6210b(rect);
                    this.f2020Ye.put(b.mo6207Mg(), b2);
                    ((C1295X) this.mListener).mo7758h(b2);
                }
                invalidateViews();
                return;
            }
            ((C1295X) this.mListener).mo7758h(b.mo6210b(rect));
        } else {
            StringBuilder Pa = C0632a.m1011Pa("Selected item has invalid contentType ");
            Pa.append(b.getContentType());
            C1430e.m3630w("MessagingApp", Pa.toString());
        }
    }

    /* renamed from: a */
    public boolean mo7711a(C0890B b) {
        return this.f2020Ye.containsKey(b.mo6207Mg());
    }

    /* renamed from: a */
    public void mo7707a(MenuInflater menuInflater, Menu menu) {
        menuInflater.inflate(R.menu.gallery_picker_menu, menu);
        MenuItem findItem = menu.findItem(R.id.action_multiselect);
        MenuItem findItem2 = menu.findItem(R.id.action_confirm_multiselect);
        boolean Jm = m3234Jm();
        findItem.setVisible(Jm);
        findItem2.setVisible(!Jm);
    }

    /* renamed from: a */
    public void mo6222a(C0889A a, int i) {
        this.f2019Sa.mo5929a(a);
        if ((i & 1) == 1) {
            m3235Km();
        }
    }

    /* renamed from: a */
    public void mo6221a(C0889A a) {
        this.f2019Sa.mo5929a(a);
        m3235Km();
    }
}
