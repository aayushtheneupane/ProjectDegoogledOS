package com.android.messaging.p041ui.mediapicker;

import android.view.View;
import android.view.ViewGroup;
import com.android.messaging.R;
import com.android.messaging.util.C1464na;

/* renamed from: com.android.messaging.ui.mediapicker.b */
class C1300b extends C1323ea implements C1326g {

    /* renamed from: pF */
    private View f2056pF;

    /* renamed from: qF */
    private View f2057qF;

    C1300b(C1345pa paVar) {
        super(paVar);
    }

    /* renamed from: Kb */
    public void mo7770Kb() {
        ((AudioRecordView) this.mView).mo7656Kb();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Pi */
    public int mo7672Pi() {
        return R.string.mediapicker_audio_title;
    }

    /* renamed from: Qi */
    public int mo7673Qi() {
        return R.string.mediapicker_audioChooserDescription;
    }

    /* renamed from: Ri */
    public int mo7674Ri() {
        return 4;
    }

    /* renamed from: Ti */
    public boolean mo7771Ti() {
        return ((AudioRecordView) this.mView).mo7655Jb();
    }

    /* access modifiers changed from: protected */
    public View createView(ViewGroup viewGroup) {
        AudioRecordView audioRecordView = (AudioRecordView) getLayoutInflater().inflate(R.layout.mediapicker_audio_chooser, viewGroup, false);
        audioRecordView.mo7657a((C1326g) this);
        audioRecordView.mo7667u(this.f2118Dj.mo7886Ea());
        this.f2056pF = audioRecordView.findViewById(R.id.mediapicker_enabled);
        this.f2057qF = audioRecordView.findViewById(R.id.missing_permission_view);
        return audioRecordView;
    }

    public int getIconResource() {
        return R.drawable.ic_audio_light;
    }

    public void onPause() {
        View view = this.mView;
        if (view != null) {
            ((AudioRecordView) view).onPause();
        }
    }

    /* access modifiers changed from: protected */
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 3) {
            int i2 = 0;
            boolean z = iArr[0] == 0;
            View view = this.f2056pF;
            if (view != null) {
                view.setVisibility(z ? 0 : 8);
                View view2 = this.f2057qF;
                if (z) {
                    i2 = 8;
                }
                view2.setVisibility(i2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setSelected(boolean z) {
        super.setSelected(z);
        if (z && !C1464na.m3750Ha("android.permission.RECORD_AUDIO")) {
            this.f2118Dj.requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, 3);
        }
    }

    /* renamed from: u */
    public void mo7773u(int i) {
        View view = this.mView;
        if (view != null) {
            ((AudioRecordView) view).mo7667u(i);
        }
    }
}
