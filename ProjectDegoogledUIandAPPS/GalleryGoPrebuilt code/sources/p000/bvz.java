package p000;

import android.opengl.GLSurfaceView;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: bvz */
/* compiled from: PG */
public final class bvz implements GLSurfaceView.Renderer {

    /* renamed from: a */
    private boolean f3746a = false;

    /* renamed from: b */
    private boolean f3747b = false;

    /* renamed from: c */
    private boolean f3748c = true;

    /* renamed from: d */
    private final GLSurfaceView f3749d;

    /* renamed from: e */
    private final bxc f3750e;

    /* renamed from: f */
    private final PipelineParams f3751f = new PipelineParams();

    public bvz(GLSurfaceView gLSurfaceView, bxc bxc) {
        this.f3749d = gLSurfaceView;
        this.f3750e = bxc;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final synchronized void mo2820c() {
        this.f3748c = true;
    }

    public final synchronized void onDrawFrame(GL10 gl10) {
        if (this.f3748c) {
            if (this.f3746a) {
                this.f3746a = !this.f3750e.setPipelineParams(this.f3751f);
            }
            if (this.f3747b) {
                this.f3747b = !this.f3750e.loadGpuInputImage();
            }
            this.f3750e.drawFrame();
        }
    }

    public final void onSurfaceChanged(GL10 gl10, int i, int i2) {
        this.f3750e.surfaceChanged(i, i2);
        if (this.f3748c) {
            this.f3749d.requestRender();
        }
    }

    public final synchronized void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        this.f3750e.surfaceCreated(this.f3749d.getContext(), true);
        this.f3746a = this.f3748c;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final synchronized void mo2819b() {
        this.f3748c = false;
        this.f3746a = false;
        this.f3747b = false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized void mo2818a(PipelineParams pipelineParams) {
        if (this.f3748c) {
            cam.m3950a(pipelineParams, this.f3751f);
            this.f3746a = true;
            this.f3749d.requestRender();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized void mo2817a() {
        if (this.f3748c) {
            this.f3747b = true;
            this.f3749d.requestRender();
        }
    }
}
