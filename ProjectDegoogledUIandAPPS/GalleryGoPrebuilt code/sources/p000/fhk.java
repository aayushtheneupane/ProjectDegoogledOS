package p000;

import com.google.android.libraries.material.progress.MaterialProgressBar;

/* renamed from: fhk */
/* compiled from: PG */
public final /* synthetic */ class fhk implements Runnable {

    /* renamed from: a */
    private final MaterialProgressBar f9681a;

    public fhk(MaterialProgressBar materialProgressBar) {
        this.f9681a = materialProgressBar;
    }

    public final void run() {
        MaterialProgressBar materialProgressBar = this.f9681a;
        if (materialProgressBar.getVisibility() == 0) {
            materialProgressBar.setVisibility(4);
        }
    }
}
