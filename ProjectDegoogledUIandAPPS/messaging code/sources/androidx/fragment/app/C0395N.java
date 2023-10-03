package androidx.fragment.app;

import androidx.lifecycle.Lifecycle$State;

/* renamed from: androidx.fragment.app.N */
final class C0395N {

    /* renamed from: Zo */
    C0424j f358Zo;

    /* renamed from: _o */
    Lifecycle$State f359_o;

    /* renamed from: ap */
    Lifecycle$State f360ap;
    int mCmd;
    int mEnterAnim;
    int mExitAnim;
    int mPopEnterAnim;
    int mPopExitAnim;

    C0395N() {
    }

    C0395N(int i, C0424j jVar) {
        this.mCmd = i;
        this.f358Zo = jVar;
        Lifecycle$State lifecycle$State = Lifecycle$State.RESUMED;
        this.f359_o = lifecycle$State;
        this.f360ap = lifecycle$State;
    }
}
