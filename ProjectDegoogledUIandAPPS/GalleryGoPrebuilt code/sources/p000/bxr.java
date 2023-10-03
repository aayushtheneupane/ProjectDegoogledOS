package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: bxr */
/* compiled from: PG */
final class bxr extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ bxu f3863a;

    public bxr(bxu bxu) {
        this.f3863a = bxu;
    }

    public final void onAnimationEnd(Animator animator) {
        bxu bxu = this.f3863a;
        int i = bxu.f3864f;
        if (bxu.mo2892a()) {
            PipelineParams pipelineParams = ((byc) bxu.f3870c).f3888c;
            bzy bzy = byu.f3915a;
            ((bxx) bxu.f3872e).f3878a.mo2900a(byr.m3800b(bxu.f3868a).floatValue() - byr.m3800b(pipelineParams).floatValue());
            bxu.f3871d.run();
        }
    }
}
