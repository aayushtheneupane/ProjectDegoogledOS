package com.android.dialer.simulator.impl;

import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;

final class SimulatorRemoteVideo {
    private boolean isStopped;
    private final RenderThread thread;

    private static class RenderThread extends HandlerThread {
        private final Renderer renderer;

        RenderThread(Renderer renderer2) {
            super("SimulatorRemoteVideo");
            Assert.isNotNull(renderer2);
            this.renderer = renderer2;
        }

        /* access modifiers changed from: package-private */
        public Runnable getRenderer() {
            return this.renderer;
        }

        /* access modifiers changed from: protected */
        public void onLooperPrepared() {
            Assert.isWorkerThread();
            this.renderer.schedule();
        }
    }

    private static class Renderer implements Runnable {
        private double angle;
        private float circleX;
        private float circleY;
        private float radius;
        private final Surface surface;

        Renderer(Surface surface2) {
            Assert.isNotNull(surface2);
            this.surface = surface2;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:15:0x00a5, code lost:
            if ((r0 - r5) > 0.0f) goto L_0x00b1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r9 = this;
                java.lang.String r0 = "SimulatorRemoteVideo.RenderThread.drawFrame"
                com.android.dialer.common.Assert.isWorkerThread()
                android.view.Surface r1 = r9.surface     // Catch:{ IllegalArgumentException -> 0x00d3 }
                r2 = 0
                android.graphics.Canvas r1 = r1.lockCanvas(r2)     // Catch:{ IllegalArgumentException -> 0x00d3 }
                r2 = 2
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                int r4 = r1.getWidth()
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                r2[r3] = r4
                r3 = 1
                int r4 = r1.getHeight()
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                r2[r3] = r4
                java.lang.String r3 = "size; %d x %d"
                com.android.dialer.common.LogUtil.m9i(r0, r3, r2)
                r0 = -16711936(0xffffffffff00ff00, float:-1.7146522E38)
                r1.drawColor(r0)
                com.android.dialer.common.Assert.isWorkerThread()
                int r0 = r1.getWidth()
                int r2 = r1.getHeight()
                float r3 = r9.circleX
                r4 = 0
                int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
                if (r3 != 0) goto L_0x006d
                float r3 = r9.circleY
                int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
                if (r3 != 0) goto L_0x006d
                float r0 = (float) r0
                r3 = 1073741824(0x40000000, float:2.0)
                float r0 = r0 / r3
                r9.circleX = r0
                float r0 = (float) r2
                float r0 = r0 / r3
                r9.circleY = r0
                r2 = 4605249457297304856(0x3fe921fb54442d18, double:0.7853981633974483)
                r9.angle = r2
                int r0 = r1.getWidth()
                int r2 = r1.getHeight()
                int r0 = java.lang.Math.min(r0, r2)
                float r0 = (float) r0
                r2 = 1041865114(0x3e19999a, float:0.15)
                float r0 = r0 * r2
                r9.radius = r0
                goto L_0x00b1
            L_0x006d:
                float r3 = r9.circleX
                double r5 = r9.angle
                double r5 = java.lang.Math.cos(r5)
                float r5 = (float) r5
                r6 = 1098907648(0x41800000, float:16.0)
                float r5 = r5 * r6
                float r5 = r5 + r3
                r9.circleX = r5
                float r3 = r9.circleY
                double r7 = r9.angle
                double r7 = java.lang.Math.sin(r7)
                float r5 = (float) r7
                float r5 = r5 * r6
                float r5 = r5 + r3
                r9.circleY = r5
                float r3 = r9.circleX
                float r5 = r9.radius
                float r6 = r3 + r5
                float r0 = (float) r0
                int r0 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
                if (r0 >= 0) goto L_0x00a7
                float r3 = r3 - r5
                int r0 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
                if (r0 <= 0) goto L_0x00a7
                float r0 = r9.circleY
                float r3 = r0 + r5
                float r2 = (float) r2
                int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
                if (r2 >= 0) goto L_0x00a7
                float r0 = r0 - r5
                int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
                if (r0 > 0) goto L_0x00b1
            L_0x00a7:
                double r2 = r9.angle
                r4 = 4609753056924675352(0x3ff921fb54442d18, double:1.5707963267948966)
                double r2 = r2 + r4
                r9.angle = r2
            L_0x00b1:
                com.android.dialer.common.Assert.isWorkerThread()
                android.graphics.Paint r0 = new android.graphics.Paint
                r0.<init>()
                r2 = -65281(0xffffffffffff00ff, float:NaN)
                r0.setColor(r2)
                android.graphics.Paint$Style r2 = android.graphics.Paint.Style.FILL
                r0.setStyle(r2)
                float r2 = r9.circleX
                float r3 = r9.circleY
                float r4 = r9.radius
                r1.drawCircle(r2, r3, r4, r0)
                android.view.Surface r0 = r9.surface
                r0.unlockCanvasAndPost(r1)
                goto L_0x00d9
            L_0x00d3:
                r1 = move-exception
                java.lang.String r2 = "unable to lock canvas"
                com.android.dialer.common.LogUtil.m7e((java.lang.String) r0, (java.lang.String) r2, (java.lang.Throwable) r1)
            L_0x00d9:
                r9.schedule()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.simulator.impl.SimulatorRemoteVideo.Renderer.run():void");
        }

        /* access modifiers changed from: package-private */
        public void schedule() {
            Assert.isWorkerThread();
            new Handler().postDelayed(this, 33);
        }
    }

    SimulatorRemoteVideo(Surface surface) {
        this.thread = new RenderThread(new Renderer(surface));
    }

    /* access modifiers changed from: package-private */
    public Runnable getRenderer() {
        return this.thread.getRenderer();
    }

    /* access modifiers changed from: package-private */
    public void startVideo() {
        LogUtil.enterBlock("SimulatorRemoteVideo.startVideo");
        Assert.checkState(!this.isStopped);
        this.thread.start();
    }

    /* access modifiers changed from: package-private */
    public void stopVideo() {
        LogUtil.enterBlock("SimulatorRemoteVideo.stopVideo");
        this.isStopped = true;
        this.thread.quitSafely();
    }
}
