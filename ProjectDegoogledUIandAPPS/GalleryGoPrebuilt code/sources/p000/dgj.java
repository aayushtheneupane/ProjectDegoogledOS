package p000;

/* renamed from: dgj */
/* compiled from: PG */
final /* synthetic */ class dgj implements icf {

    /* renamed from: a */
    private final dgo f6502a;

    /* renamed from: b */
    private final ccc f6503b;

    public dgj(dgo dgo, ccc ccc) {
        this.f6502a = dgo;
        this.f6503b = ccc;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:89:0x022a, code lost:
        if (r10.advance() == false) goto L_0x022c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x024d A[SYNTHETIC, Splitter:B:106:0x024d] */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0256 A[Catch:{ all -> 0x02cb, IOException -> 0x0323, InterruptedException -> 0x0316 }] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x025f  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x029e  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0103 A[Catch:{ all -> 0x02cb, IOException -> 0x0323, InterruptedException -> 0x0316 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0112 A[Catch:{ all -> 0x02cb, IOException -> 0x0323, InterruptedException -> 0x0316 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x015a A[Catch:{ all -> 0x0240, all -> 0x02a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01a7 A[Catch:{ all -> 0x0240, all -> 0x02a5 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.ieh mo2538a(java.lang.Object r26) {
        /*
            r25 = this;
            r1 = r25
            java.lang.String r2 = "max-input-size"
            dgo r3 = r1.f6502a
            ccc r4 = r1.f6503b
            r5 = r26
            egw r5 = (p000.egw) r5
            ccb r6 = r4.mo3008f()     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            android.media.MediaExtractor r0 = new android.media.MediaExtractor     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            r0.<init>()     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            ebh r7 = r4.mo3022g()     // Catch:{ IllegalStateException -> 0x0310 }
            android.content.Context r8 = r3.f6508a     // Catch:{ IllegalStateException -> 0x0310 }
            android.os.ParcelFileDescriptor r7 = r7.mo4660a((android.content.Context) r8)     // Catch:{ IllegalStateException -> 0x0310 }
            r0.getClass()     // Catch:{ all -> 0x0302 }
            dgm r8 = new dgm     // Catch:{ all -> 0x0302 }
            r8.<init>(r0)     // Catch:{ all -> 0x0302 }
            java.io.FileDescriptor r9 = r7.getFileDescriptor()     // Catch:{ all -> 0x02f6 }
            r0.setDataSource(r9)     // Catch:{ all -> 0x02f6 }
            android.util.SparseIntArray r9 = new android.util.SparseIntArray     // Catch:{ all -> 0x02f6 }
            int r10 = r0.getTrackCount()     // Catch:{ all -> 0x02f6 }
            r9.<init>(r10)     // Catch:{ all -> 0x02f6 }
            r3.mo4121a(r0, r9)     // Catch:{ all -> 0x02f6 }
            long r9 = r4.mo3004c()     // Catch:{ all -> 0x02f6 }
            r11 = 1000(0x3e8, double:4.94E-321)
            long r9 = r9 * r11
            r13 = 0
            r0.seekTo(r9, r13)     // Catch:{ all -> 0x02f6 }
            boolean r9 = p000.dgo.m6089a((android.media.MediaExtractor) r0)     // Catch:{ all -> 0x02f6 }
            java.lang.String r10 = "unable to sync to prior keyframe"
            p000.ife.m12876b((boolean) r9, (java.lang.Object) r10)     // Catch:{ all -> 0x02f6 }
            long r9 = r0.getSampleTime()     // Catch:{ all -> 0x02f6 }
            long r9 = r9 / r11
            r6.mo3021b(r9)     // Catch:{ all -> 0x02f6 }
            r9 = 1
            long r14 = r4.mo3005d()     // Catch:{ Exception -> 0x0073 }
            long r14 = r14 * r11
            r0.seekTo(r14, r9)     // Catch:{ Exception -> 0x0073 }
            boolean r10 = p000.dgo.m6089a((android.media.MediaExtractor) r0)     // Catch:{ Exception -> 0x0073 }
            java.lang.String r14 = "unable to sync to posterior keyframe"
            p000.ife.m12876b((boolean) r10, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0073 }
            long r14 = r0.getSampleTime()     // Catch:{ Exception -> 0x0073 }
            long r14 = r14 / r11
            r6.mo3018a((long) r14)     // Catch:{ Exception -> 0x0073 }
            goto L_0x007b
        L_0x0073:
            r0 = move-exception
            java.lang.String r10 = "MediaCodecVideoTrimmer: Couldn't auto-correct end milliseconds."
            java.lang.Object[] r14 = new java.lang.Object[r13]     // Catch:{ all -> 0x02f6 }
            p000.cwn.m5515b((java.lang.Throwable) r0, (java.lang.String) r10, (java.lang.Object[]) r14)     // Catch:{ all -> 0x02f6 }
        L_0x007b:
            r8.close()     // Catch:{ all -> 0x0302 }
            if (r7 == 0) goto L_0x0083
            r7.close()     // Catch:{ IllegalStateException -> 0x0310 }
        L_0x0083:
            ccc r6 = r6.mo3017a()     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            r0 = 4
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            long r7 = r4.mo3004c()     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            r0[r13] = r7     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            long r7 = r4.mo3005d()     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            r0[r9] = r7     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            r7 = r6
            cbv r7 = (p000.cbv) r7     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            long r7 = r7.f4026c     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            r8 = 2
            r0[r8] = r7     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            r7 = r6
            cbv r7 = (p000.cbv) r7     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            long r14 = r7.f4027d     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            r7 = 3
            java.lang.Long r10 = java.lang.Long.valueOf(r14)     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            r0[r7] = r10     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            ebh r0 = r6.mo3022g()     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            android.media.MediaMetadataRetriever r7 = new android.media.MediaMetadataRetriever     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            r7.<init>()     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            android.content.Context r10 = r3.f6508a     // Catch:{ all -> 0x02f1 }
            android.os.ParcelFileDescriptor r10 = r0.mo4660a((android.content.Context) r10)     // Catch:{ all -> 0x02f1 }
            java.io.FileDescriptor r14 = r10.getFileDescriptor()     // Catch:{ all -> 0x02e3 }
            r7.setDataSource(r14)     // Catch:{ all -> 0x02e3 }
            r14 = 24
            java.lang.String r14 = r7.extractMetadata(r14)     // Catch:{ all -> 0x02e3 }
            if (r14 == 0) goto L_0x00ea
            int r14 = java.lang.Integer.parseInt(r14)     // Catch:{ NumberFormatException -> 0x00e3 }
            if (r14 < 0) goto L_0x00ea
            if (r10 == 0) goto L_0x00df
            r10.close()     // Catch:{ all -> 0x02f1 }
        L_0x00df:
            r7.release()     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            goto L_0x00f3
        L_0x00e3:
            r0 = move-exception
            r2 = r0
            cca r0 = p000.cca.m4027a((java.lang.Throwable) r2)     // Catch:{ all -> 0x02e3 }
            throw r0     // Catch:{ all -> 0x02e3 }
        L_0x00ea:
            if (r10 == 0) goto L_0x00ef
            r10.close()     // Catch:{ all -> 0x02f1 }
        L_0x00ef:
            r7.release()     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            r14 = 0
        L_0x00f3:
            android.media.MediaMuxer r7 = new android.media.MediaMuxer     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            java.lang.String r10 = r5.mo4806f()     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            java.lang.String r15 = r5.f8233a     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            java.lang.String r11 = ".3gp"
            boolean r11 = r15.equals(r11)     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            if (r11 != 0) goto L_0x0112
            java.lang.String r11 = r5.f8233a     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            java.lang.String r12 = ".webm"
            boolean r11 = r11.equals(r12)     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            if (r11 != 0) goto L_0x010f
            r11 = 0
            goto L_0x0113
        L_0x010f:
            r11 = 1
            goto L_0x0113
        L_0x0112:
            r11 = 2
        L_0x0113:
            r7.<init>(r10, r11)     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            android.media.MediaExtractor r10 = new android.media.MediaExtractor     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            r10.<init>()     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            android.content.Context r11 = r3.f6508a     // Catch:{ IOException -> 0x02d3, IllegalStateException -> 0x02cd }
            android.os.ParcelFileDescriptor r11 = r0.mo4660a((android.content.Context) r11)     // Catch:{ IOException -> 0x02d3, IllegalStateException -> 0x02cd }
            r7.getClass()     // Catch:{ all -> 0x02bd }
            dgk r12 = new dgk     // Catch:{ all -> 0x02bd }
            r12.<init>(r7)     // Catch:{ all -> 0x02bd }
            r10.getClass()     // Catch:{ all -> 0x02b1 }
            dgl r15 = new dgl     // Catch:{ all -> 0x02b1 }
            r15.<init>(r10)     // Catch:{ all -> 0x02b1 }
            java.lang.Object[] r0 = new java.lang.Object[r9]     // Catch:{ all -> 0x02a5 }
            java.lang.Integer r18 = java.lang.Integer.valueOf(r14)     // Catch:{ all -> 0x02a5 }
            r0[r13] = r18     // Catch:{ all -> 0x02a5 }
            r7.setOrientationHint(r14)     // Catch:{ all -> 0x02a5 }
            java.io.FileDescriptor r0 = r11.getFileDescriptor()     // Catch:{ all -> 0x02a5 }
            r10.setDataSource(r0)     // Catch:{ all -> 0x02a5 }
            android.util.SparseIntArray r0 = new android.util.SparseIntArray     // Catch:{ all -> 0x02a5 }
            int r14 = r10.getTrackCount()     // Catch:{ all -> 0x02a5 }
            r0.<init>(r14)     // Catch:{ all -> 0x02a5 }
            r3.mo4121a(r10, r0)     // Catch:{ all -> 0x02a5 }
            int r14 = r0.size()     // Catch:{ all -> 0x02a5 }
            r18 = 1048576(0x100000, float:1.469368E-39)
            r9 = 0
            r19 = 1048576(0x100000, float:1.469368E-39)
        L_0x0158:
            if (r9 >= r14) goto L_0x019c
            int r13 = r0.keyAt(r9)     // Catch:{ all -> 0x02a5 }
            android.media.MediaFormat r8 = r10.getTrackFormat(r13)     // Catch:{ all -> 0x02a5 }
            int r1 = r7.addTrack(r8)     // Catch:{ all -> 0x02a5 }
            r0.put(r13, r1)     // Catch:{ all -> 0x02a5 }
            r22 = r4
            r21 = r14
            r14 = 2
            java.lang.Object[] r4 = new java.lang.Object[r14]     // Catch:{ all -> 0x02a5 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x02a5 }
            r14 = 0
            r4[r14] = r13     // Catch:{ all -> 0x02a5 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x02a5 }
            r13 = 1
            r4[r13] = r1     // Catch:{ all -> 0x02a5 }
            boolean r1 = r8.containsKey(r2)     // Catch:{ all -> 0x02a5 }
            if (r1 != 0) goto L_0x0185
            goto L_0x0191
        L_0x0185:
            int r1 = r8.getInteger(r2)     // Catch:{ all -> 0x02a5 }
            r4 = r19
            int r1 = java.lang.Math.max(r1, r4)     // Catch:{ all -> 0x02a5 }
            r19 = r1
        L_0x0191:
            int r9 = r9 + 1
            r1 = r25
            r14 = r21
            r4 = r22
            r8 = 2
            r13 = 0
            goto L_0x0158
        L_0x019c:
            r22 = r4
            r4 = r19
            r1 = r6
            cbv r1 = (p000.cbv) r1     // Catch:{ all -> 0x02a5 }
            boolean r1 = r1.f4025b     // Catch:{ all -> 0x02a5 }
            if (r1 != 0) goto L_0x0245
            r1 = 1
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x02a5 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x02a5 }
            r8 = 0
            r2[r8] = r1     // Catch:{ all -> 0x02a5 }
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r4)     // Catch:{ all -> 0x02a5 }
            r2 = r6
            cbv r2 = (p000.cbv) r2     // Catch:{ all -> 0x02a5 }
            long r8 = r2.f4026c     // Catch:{ all -> 0x02a5 }
            r2 = r6
            cbv r2 = (p000.cbv) r2     // Catch:{ all -> 0x02a5 }
            long r13 = r2.f4027d     // Catch:{ all -> 0x02a5 }
            r23 = 0
            int r2 = (r8 > r23 ? 1 : (r8 == r23 ? 0 : -1))
            if (r2 <= 0) goto L_0x01d8
            r2 = 1
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ all -> 0x02a5 }
            java.lang.Long r2 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x02a5 }
            r18 = 0
            r4[r18] = r2     // Catch:{ all -> 0x02a5 }
            r16 = 1000(0x3e8, double:4.94E-321)
            long r8 = r8 * r16
            r2 = 2
            r10.seekTo(r8, r2)     // Catch:{ all -> 0x02a5 }
        L_0x01d8:
            android.media.MediaCodec$BufferInfo r2 = new android.media.MediaCodec$BufferInfo     // Catch:{ all -> 0x02a5 }
            r2.<init>()     // Catch:{ all -> 0x02a5 }
            r7.start()     // Catch:{ all -> 0x0240 }
        L_0x01e0:
            java.util.concurrent.atomic.AtomicBoolean r4 = r3.f6512e     // Catch:{ all -> 0x0240 }
            r8 = 1
            r9 = 0
            boolean r4 = r4.compareAndSet(r8, r9)     // Catch:{ all -> 0x0240 }
            if (r4 != 0) goto L_0x0236
            boolean r4 = java.lang.Thread.interrupted()     // Catch:{ all -> 0x0240 }
            if (r4 != 0) goto L_0x0230
            r2.offset = r9     // Catch:{ all -> 0x0240 }
            int r4 = r2.offset     // Catch:{ all -> 0x0240 }
            int r4 = r10.readSampleData(r1, r4)     // Catch:{ all -> 0x0240 }
            r2.size = r4     // Catch:{ all -> 0x0240 }
            int r4 = r2.size     // Catch:{ all -> 0x0240 }
            if (r4 < 0) goto L_0x022c
            long r8 = r10.getSampleTime()     // Catch:{ all -> 0x0240 }
            int r4 = (r13 > r23 ? 1 : (r13 == r23 ? 0 : -1))
            if (r4 > 0) goto L_0x0209
            r16 = 1000(0x3e8, double:4.94E-321)
            goto L_0x0213
        L_0x0209:
            r16 = 1000(0x3e8, double:4.94E-321)
            long r19 = r13 * r16
            int r4 = (r8 > r19 ? 1 : (r8 == r19 ? 0 : -1))
            if (r4 <= 0) goto L_0x0213
            goto L_0x022c
        L_0x0213:
            r2.presentationTimeUs = r8     // Catch:{ all -> 0x0240 }
            int r4 = r10.getSampleFlags()     // Catch:{ all -> 0x0240 }
            r2.flags = r4     // Catch:{ all -> 0x0240 }
            int r4 = r10.getSampleTrackIndex()     // Catch:{ all -> 0x0240 }
            int r4 = r0.get(r4)     // Catch:{ all -> 0x0240 }
            r7.writeSampleData(r4, r1, r2)     // Catch:{ all -> 0x0240 }
            boolean r4 = r10.advance()     // Catch:{ all -> 0x0240 }
            if (r4 != 0) goto L_0x01e0
        L_0x022c:
            r7.stop()     // Catch:{ all -> 0x02a5 }
            goto L_0x0245
        L_0x0230:
            java.lang.InterruptedException r0 = new java.lang.InterruptedException     // Catch:{ all -> 0x0240 }
            r0.<init>()     // Catch:{ all -> 0x0240 }
            throw r0     // Catch:{ all -> 0x0240 }
        L_0x0236:
            java.lang.InterruptedException r0 = new java.lang.InterruptedException     // Catch:{ all -> 0x0240 }
            r0.<init>()     // Catch:{ all -> 0x0240 }
            cca r0 = p000.cca.m4026a((java.lang.InterruptedException) r0)     // Catch:{ all -> 0x0240 }
            throw r0     // Catch:{ all -> 0x0240 }
        L_0x0240:
            r0 = move-exception
            r7.stop()     // Catch:{ all -> 0x02a5 }
            throw r0     // Catch:{ all -> 0x02a5 }
        L_0x0245:
            r15.close()     // Catch:{ all -> 0x02b1 }
            r12.close()     // Catch:{ all -> 0x02bd }
            if (r11 == 0) goto L_0x0250
            r11.close()     // Catch:{ IOException -> 0x02d3, IllegalStateException -> 0x02cd }
        L_0x0250:
            cbv r6 = (p000.cbv) r6     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            boolean r0 = r6.f4025b     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            if (r0 == 0) goto L_0x0259
            r5.mo4807g()     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
        L_0x0259:
            boolean r0 = r22.mo3003b()
            if (r0 != 0) goto L_0x029e
            j$.util.Optional r0 = r22.mo3006e()
            boolean r0 = r0.isPresent()
            if (r0 == 0) goto L_0x0287
            dbs r0 = r3.f6510c
            cxi r1 = r22.mo3002a()
            cyd r1 = p000.cyc.m5648b(r1)
            j$.util.Optional r1 = p003j$.util.Optional.m16285of(r1)
            j$.util.Optional r2 = r22.mo3006e()
            java.lang.Object r2 = r2.get()
            java.lang.String r2 = (java.lang.String) r2
            r4 = 0
            ieh r0 = r0.mo4040a(r1, r2, r5, r4)
            goto L_0x0295
        L_0x0287:
            dbs r0 = r3.f6510c
            cxi r1 = r22.mo3002a()
            cyd r1 = p000.cyc.m5648b(r1)
            ieh r0 = r0.mo4038a(r1, r5)
        L_0x0295:
            icf r1 = p000.dgn.f6507a
            iek r2 = r3.f6511d
            ieh r0 = p000.gte.m10771a((p000.ieh) r0, (p000.icf) r1, (java.util.concurrent.Executor) r2)
            goto L_0x02a4
        L_0x029e:
            cxi r0 = p000.cxi.f5908x
            ieh r0 = p000.ife.m12820a((java.lang.Object) r0)
        L_0x02a4:
            return r0
        L_0x02a5:
            r0 = move-exception
            r1 = r0
            r15.close()     // Catch:{ all -> 0x02ab }
            goto L_0x02b0
        L_0x02ab:
            r0 = move-exception
            r2 = r0
            p000.ifn.m12935a((java.lang.Throwable) r1, (java.lang.Throwable) r2)     // Catch:{ all -> 0x02b1 }
        L_0x02b0:
            throw r1     // Catch:{ all -> 0x02b1 }
        L_0x02b1:
            r0 = move-exception
            r1 = r0
            r12.close()     // Catch:{ all -> 0x02b7 }
            goto L_0x02bc
        L_0x02b7:
            r0 = move-exception
            r2 = r0
            p000.ifn.m12935a((java.lang.Throwable) r1, (java.lang.Throwable) r2)     // Catch:{ all -> 0x02bd }
        L_0x02bc:
            throw r1     // Catch:{ all -> 0x02bd }
        L_0x02bd:
            r0 = move-exception
            r1 = r0
            if (r11 == 0) goto L_0x02ca
            r11.close()     // Catch:{ all -> 0x02c5 }
            goto L_0x02ca
        L_0x02c5:
            r0 = move-exception
            r2 = r0
            p000.ifn.m12935a((java.lang.Throwable) r1, (java.lang.Throwable) r2)     // Catch:{ IOException -> 0x02d3, IllegalStateException -> 0x02cd }
        L_0x02ca:
            throw r1     // Catch:{ IOException -> 0x02d3, IllegalStateException -> 0x02cd }
        L_0x02cb:
            r0 = move-exception
            goto L_0x02d9
        L_0x02cd:
            r0 = move-exception
            cca r0 = p000.cca.m4027a((java.lang.Throwable) r0)     // Catch:{ all -> 0x02cb }
            throw r0     // Catch:{ all -> 0x02cb }
        L_0x02d3:
            r0 = move-exception
            cca r0 = p000.cca.m4025a((java.io.IOException) r0)     // Catch:{ all -> 0x02cb }
            throw r0     // Catch:{ all -> 0x02cb }
        L_0x02d9:
            cbv r6 = (p000.cbv) r6     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            boolean r1 = r6.f4025b     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            if (r1 == 0) goto L_0x02e2
            r5.mo4807g()     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
        L_0x02e2:
            throw r0     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
        L_0x02e3:
            r0 = move-exception
            r1 = r0
            if (r10 == 0) goto L_0x02f0
            r10.close()     // Catch:{ all -> 0x02eb }
            goto L_0x02f0
        L_0x02eb:
            r0 = move-exception
            r2 = r0
            p000.ifn.m12935a((java.lang.Throwable) r1, (java.lang.Throwable) r2)     // Catch:{ all -> 0x02f1 }
        L_0x02f0:
            throw r1     // Catch:{ all -> 0x02f1 }
        L_0x02f1:
            r0 = move-exception
            r7.release()     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            throw r0     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
        L_0x02f6:
            r0 = move-exception
            r1 = r0
            r8.close()     // Catch:{ all -> 0x02fc }
            goto L_0x0301
        L_0x02fc:
            r0 = move-exception
            r2 = r0
            p000.ifn.m12935a((java.lang.Throwable) r1, (java.lang.Throwable) r2)     // Catch:{ all -> 0x0302 }
        L_0x0301:
            throw r1     // Catch:{ all -> 0x0302 }
        L_0x0302:
            r0 = move-exception
            r1 = r0
            if (r7 == 0) goto L_0x030f
            r7.close()     // Catch:{ all -> 0x030a }
            goto L_0x030f
        L_0x030a:
            r0 = move-exception
            r2 = r0
            p000.ifn.m12935a((java.lang.Throwable) r1, (java.lang.Throwable) r2)     // Catch:{ IllegalStateException -> 0x0310 }
        L_0x030f:
            throw r1     // Catch:{ IllegalStateException -> 0x0310 }
        L_0x0310:
            r0 = move-exception
            cca r0 = p000.cca.m4027a((java.lang.Throwable) r0)     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
            throw r0     // Catch:{ IOException -> 0x0323, InterruptedException -> 0x0316 }
        L_0x0316:
            r0 = move-exception
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            r1.interrupt()
            cca r0 = p000.cca.m4026a((java.lang.InterruptedException) r0)
            throw r0
        L_0x0323:
            r0 = move-exception
            cca r0 = p000.cca.m4025a((java.io.IOException) r0)
            goto L_0x032a
        L_0x0329:
            throw r0
        L_0x032a:
            goto L_0x0329
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.dgj.mo2538a(java.lang.Object):ieh");
    }
}
