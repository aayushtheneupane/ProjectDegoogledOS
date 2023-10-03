package com.android.messaging.p041ui.photoviewer;

import android.graphics.drawable.Drawable;
import android.support.rastermill.FrameSequenceDrawable;
import androidx.loader.content.C0475f;
import com.android.p032ex.photo.fragments.PhotoViewFragment;
import com.android.p032ex.photo.p035b.C0715b;

/* renamed from: com.android.messaging.ui.photoviewer.BuglePhotoViewFragment */
public class BuglePhotoViewFragment extends PhotoViewFragment {
    /* renamed from: Bn */
    private void m3498Bn() {
        Drawable drawable = getDrawable();
        if (drawable != null && (drawable instanceof FrameSequenceDrawable)) {
            ((FrameSequenceDrawable) drawable).start();
        }
    }

    /* renamed from: M */
    public void mo5739M() {
        super.mo5739M();
        m3498Bn();
    }

    public void onPause() {
        Drawable drawable = getDrawable();
        if (drawable != null && (drawable instanceof FrameSequenceDrawable)) {
            ((FrameSequenceDrawable) drawable).stop();
        }
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        m3498Bn();
    }

    public void resetViews() {
        super.resetViews();
        Drawable drawable = getDrawable();
        if (drawable != null && (drawable instanceof FrameSequenceDrawable)) {
            ((FrameSequenceDrawable) drawable).stop();
        }
    }

    /* renamed from: a */
    public void mo221a(C0475f fVar, C0715b bVar) {
        super.mo221a(fVar, bVar);
        if (3 == fVar.getId() && bVar.status == 0 && this.mCallback.mo5805u(this)) {
            m3498Bn();
        }
    }
}
