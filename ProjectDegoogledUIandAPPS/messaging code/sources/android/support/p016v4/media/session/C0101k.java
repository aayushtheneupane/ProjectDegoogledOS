package android.support.p016v4.media.session;

import android.content.Context;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* renamed from: android.support.v4.media.session.k */
class C0101k implements C0099i {
    final Object mLock = new Object();

    /* renamed from: ue */
    protected final MediaController f114ue;

    /* renamed from: ve */
    private final List f115ve = new ArrayList();

    /* renamed from: we */
    private HashMap f116we = new HashMap();

    /* renamed from: xe */
    final MediaSessionCompat$Token f117xe;

    public C0101k(Context context, MediaSessionCompat$Token mediaSessionCompat$Token) {
        this.f117xe = mediaSessionCompat$Token;
        this.f114ue = new MediaController(context, (MediaSession.Token) this.f117xe.getToken());
        if (this.f114ue == null) {
            throw new RemoteException();
        } else if (this.f117xe.mo526Ab() == null) {
            this.f114ue.sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", (Bundle) null, new C0090x50fd9e4a(this));
        }
    }

    public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
        return this.f114ue.dispatchMediaButtonEvent(keyEvent);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: zb */
    public void mo577zb() {
        if (this.f117xe.mo526Ab() != null) {
            for (C0098h hVar : this.f115ve) {
                C0100j jVar = new C0100j(hVar);
                this.f116we.put(hVar, jVar);
                hVar.f113te = jVar;
                try {
                    ((C0093c) this.f117xe.mo526Ab()).mo550a(jVar);
                    hVar.mo566a(13, (Object) null, (Bundle) null);
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                }
            }
            this.f115ve.clear();
        }
    }
}
