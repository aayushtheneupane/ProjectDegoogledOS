package androidx.appcompat.view.menu;

import android.view.MenuItem;

/* renamed from: androidx.appcompat.view.menu.h */
class C0229h implements Runnable {

    /* renamed from: Hm */
    final /* synthetic */ C0231j f237Hm;

    /* renamed from: Im */
    final /* synthetic */ MenuItem f238Im;

    /* renamed from: Jm */
    final /* synthetic */ C0238q f239Jm;
    final /* synthetic */ C0230i this$1;

    C0229h(C0230i iVar, C0231j jVar, MenuItem menuItem, C0238q qVar) {
        this.this$1 = iVar;
        this.f237Hm = jVar;
        this.f238Im = menuItem;
        this.f239Jm = qVar;
    }

    public void run() {
        C0231j jVar = this.f237Hm;
        if (jVar != null) {
            this.this$1.this$0.f247Gn = true;
            jVar.menu.close(false);
            this.this$1.this$0.f247Gn = false;
        }
        if (this.f238Im.isEnabled() && this.f238Im.hasSubMenu()) {
            this.f239Jm.performItemAction(this.f238Im, 4);
        }
    }
}
