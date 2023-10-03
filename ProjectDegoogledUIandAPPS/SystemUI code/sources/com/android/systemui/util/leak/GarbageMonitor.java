package com.android.systemui.util.leak;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import android.util.LongSparseArray;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1778R$integer;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dumpable;
import com.android.systemui.SystemUI;
import com.android.systemui.SystemUIFactory;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class GarbageMonitor implements Dumpable {
    private static final boolean DEBUG = Log.isLoggable("GarbageMonitor", 3);
    private static final boolean ENABLE_AM_HEAP_LIMIT;
    /* access modifiers changed from: private */
    public static final boolean HEAP_TRACKING_ENABLED;
    /* access modifiers changed from: private */
    public static final boolean LEAK_REPORTING_ENABLED;
    private final ActivityManager mAm;
    private final Context mContext;
    private final LongSparseArray<ProcessMemInfo> mData = new LongSparseArray<>();
    private DumpTruck mDumpTruck;
    private final Handler mHandler;
    /* access modifiers changed from: private */
    public long mHeapLimit;
    private final LeakReporter mLeakReporter;
    private final ArrayList<Long> mPids = new ArrayList<>();
    private int[] mPidsArray = new int[1];
    private MemoryTile mQSTile;
    private final TrackedGarbage mTrackedGarbage;

    static {
        boolean z = false;
        if (Build.IS_DEBUGGABLE && SystemProperties.getBoolean("debug.enable_leak_reporting", false)) {
            z = true;
        }
        LEAK_REPORTING_ENABLED = z;
        boolean z2 = Build.IS_DEBUGGABLE;
        HEAP_TRACKING_ENABLED = z2;
        ENABLE_AM_HEAP_LIMIT = z2;
    }

    public GarbageMonitor(Context context, Looper looper, LeakDetector leakDetector, LeakReporter leakReporter) {
        this.mContext = context.getApplicationContext();
        this.mAm = (ActivityManager) context.getSystemService("activity");
        this.mHandler = new BackgroundHeapCheckHandler(looper);
        this.mTrackedGarbage = leakDetector.getTrackedGarbage();
        this.mLeakReporter = leakReporter;
        this.mDumpTruck = new DumpTruck(this.mContext);
        if (ENABLE_AM_HEAP_LIMIT) {
            this.mHeapLimit = (long) Settings.Global.getInt(context.getContentResolver(), "systemui_am_heap_limit", this.mContext.getResources().getInteger(C1778R$integer.watch_heap_limit));
        }
    }

    public void startLeakMonitor() {
        if (this.mTrackedGarbage != null) {
            this.mHandler.sendEmptyMessage(1000);
        }
    }

    public void startHeapTracking() {
        startTrackingProcess((long) Process.myPid(), this.mContext.getPackageName(), System.currentTimeMillis());
        this.mHandler.sendEmptyMessage(3000);
    }

    /* access modifiers changed from: private */
    public boolean gcAndCheckGarbage() {
        if (this.mTrackedGarbage.countOldGarbage() <= 5) {
            return false;
        }
        Runtime.getRuntime().gc();
        return true;
    }

    /* access modifiers changed from: package-private */
    public void reinspectGarbageAfterGc() {
        int countOldGarbage = this.mTrackedGarbage.countOldGarbage();
        if (countOldGarbage > 5) {
            this.mLeakReporter.dumpLeak(countOldGarbage);
        }
    }

    public ProcessMemInfo getMemInfo(int i) {
        return this.mData.get((long) i);
    }

    public int[] getTrackedProcesses() {
        return this.mPidsArray;
    }

    public void startTrackingProcess(long j, String str, long j2) {
        synchronized (this.mPids) {
            if (!this.mPids.contains(Long.valueOf(j))) {
                this.mPids.add(Long.valueOf(j));
                updatePidsArrayL();
                this.mData.put(j, new ProcessMemInfo(j, str, j2));
            }
        }
    }

    private void updatePidsArrayL() {
        int size = this.mPids.size();
        this.mPidsArray = new int[size];
        StringBuffer stringBuffer = new StringBuffer("Now tracking processes: ");
        for (int i = 0; i < size; i++) {
            int intValue = this.mPids.get(i).intValue();
            this.mPidsArray[i] = intValue;
            stringBuffer.append(intValue);
            stringBuffer.append(" ");
        }
        if (DEBUG) {
            Log.v("GarbageMonitor", stringBuffer.toString());
        }
    }

    /* access modifiers changed from: private */
    public void update() {
        synchronized (this.mPids) {
            Debug.MemoryInfo[] processMemoryInfo = this.mAm.getProcessMemoryInfo(this.mPidsArray);
            int i = 0;
            while (true) {
                if (i >= processMemoryInfo.length) {
                    break;
                }
                Debug.MemoryInfo memoryInfo = processMemoryInfo[i];
                if (i <= this.mPids.size()) {
                    long intValue = (long) this.mPids.get(i).intValue();
                    ProcessMemInfo processMemInfo = this.mData.get(intValue);
                    long[] jArr = processMemInfo.pss;
                    int i2 = processMemInfo.head;
                    long totalPss = (long) memoryInfo.getTotalPss();
                    processMemInfo.currentPss = totalPss;
                    jArr[i2] = totalPss;
                    long[] jArr2 = processMemInfo.uss;
                    int i3 = processMemInfo.head;
                    long totalPrivateDirty = (long) memoryInfo.getTotalPrivateDirty();
                    processMemInfo.currentUss = totalPrivateDirty;
                    jArr2[i3] = totalPrivateDirty;
                    processMemInfo.head = (processMemInfo.head + 1) % processMemInfo.pss.length;
                    if (processMemInfo.currentPss > processMemInfo.max) {
                        processMemInfo.max = processMemInfo.currentPss;
                    }
                    if (processMemInfo.currentUss > processMemInfo.max) {
                        processMemInfo.max = processMemInfo.currentUss;
                    }
                    if (processMemInfo.currentPss == 0) {
                        if (DEBUG) {
                            Log.v("GarbageMonitor", "update: pid " + intValue + " has pss=0, it probably died");
                        }
                        this.mData.remove(intValue);
                    }
                    i++;
                } else if (DEBUG) {
                    Log.e("GarbageMonitor", "update: unknown process info received: " + memoryInfo);
                }
            }
            for (int size = this.mPids.size() - 1; size >= 0; size--) {
                if (this.mData.get((long) this.mPids.get(size).intValue()) == null) {
                    this.mPids.remove(size);
                    updatePidsArrayL();
                }
            }
        }
        MemoryTile memoryTile = this.mQSTile;
        if (memoryTile != null) {
            memoryTile.update();
        }
    }

    /* access modifiers changed from: private */
    public void setTile(MemoryTile memoryTile) {
        this.mQSTile = memoryTile;
        if (memoryTile != null) {
            memoryTile.update();
        }
    }

    /* access modifiers changed from: private */
    public static String formatBytes(long j) {
        String[] strArr = {"B", "K", "M", "G", "T"};
        int i = 0;
        while (i < strArr.length && j >= 1024) {
            j /= 1024;
            i++;
        }
        return j + strArr[i];
    }

    /* access modifiers changed from: private */
    public Intent dumpHprofAndGetShareIntent() {
        DumpTruck dumpTruck = this.mDumpTruck;
        dumpTruck.captureHeaps(getTrackedProcesses());
        return dumpTruck.createShareIntent();
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("GarbageMonitor params:");
        printWriter.println(String.format("   mHeapLimit=%d KB", new Object[]{Long.valueOf(this.mHeapLimit)}));
        printWriter.println(String.format("   GARBAGE_INSPECTION_INTERVAL=%d (%.1f mins)", new Object[]{900000L, Float.valueOf(15.0f)}));
        printWriter.println(String.format("   HEAP_TRACK_INTERVAL=%d (%.1f mins)", new Object[]{60000L, Float.valueOf(1.0f)}));
        printWriter.println(String.format("   HEAP_TRACK_HISTORY_LEN=%d (%.1f hr total)", new Object[]{720, Float.valueOf(12.0f)}));
        printWriter.println("GarbageMonitor tracked processes:");
        Iterator<Long> it = this.mPids.iterator();
        while (it.hasNext()) {
            ProcessMemInfo processMemInfo = this.mData.get(it.next().longValue());
            if (processMemInfo != null) {
                processMemInfo.dump(fileDescriptor, printWriter, strArr);
            }
        }
    }

    private static class MemoryIconDrawable extends Drawable {
        final Drawable baseIcon;

        /* renamed from: dp */
        final float f76dp;
        long limit;
        final Paint paint = new Paint();
        long pss;

        public int getOpacity() {
            return -3;
        }

        MemoryIconDrawable(Context context) {
            this.baseIcon = context.getDrawable(C1776R$drawable.ic_memory).mutate();
            this.f76dp = context.getResources().getDisplayMetrics().density;
            this.paint.setColor(QSTileImpl.getColorForState(context, 2));
        }

        public void setPss(long j) {
            if (j != this.pss) {
                this.pss = j;
                invalidateSelf();
            }
        }

        public void setLimit(long j) {
            if (j != this.limit) {
                this.limit = j;
                invalidateSelf();
            }
        }

        public void draw(Canvas canvas) {
            this.baseIcon.draw(canvas);
            long j = this.limit;
            if (j > 0) {
                long j2 = this.pss;
                if (j2 > 0) {
                    float min = Math.min(1.0f, ((float) j2) / ((float) j));
                    Rect bounds = getBounds();
                    float f = this.f76dp;
                    canvas.translate(((float) bounds.left) + (f * 8.0f), ((float) bounds.top) + (f * 5.0f));
                    float f2 = this.f76dp;
                    canvas.drawRect(0.0f, f2 * 14.0f * (1.0f - min), (8.0f * f2) + 1.0f, (f2 * 14.0f) + 1.0f, this.paint);
                }
            }
        }

        public void setBounds(int i, int i2, int i3, int i4) {
            super.setBounds(i, i2, i3, i4);
            this.baseIcon.setBounds(i, i2, i3, i4);
        }

        public int getIntrinsicHeight() {
            return this.baseIcon.getIntrinsicHeight();
        }

        public int getIntrinsicWidth() {
            return this.baseIcon.getIntrinsicWidth();
        }

        public void setAlpha(int i) {
            this.baseIcon.setAlpha(i);
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.baseIcon.setColorFilter(colorFilter);
            this.paint.setColorFilter(colorFilter);
        }

        public void setTint(int i) {
            super.setTint(i);
            this.baseIcon.setTint(i);
        }

        public void setTintList(ColorStateList colorStateList) {
            super.setTintList(colorStateList);
            this.baseIcon.setTintList(colorStateList);
        }

        public void setTintMode(PorterDuff.Mode mode) {
            super.setTintMode(mode);
            this.baseIcon.setTintMode(mode);
        }
    }

    private static class MemoryGraphIcon extends QSTile.Icon {
        long limit;
        long pss;

        private MemoryGraphIcon() {
        }

        public void setPss(long j) {
            this.pss = j;
        }

        public void setHeapLimit(long j) {
            this.limit = j;
        }

        public Drawable getDrawable(Context context) {
            MemoryIconDrawable memoryIconDrawable = new MemoryIconDrawable(context);
            memoryIconDrawable.setPss(this.pss);
            memoryIconDrawable.setLimit(this.limit);
            return memoryIconDrawable;
        }
    }

    public static class MemoryTile extends QSTileImpl<QSTile.State> {
        /* access modifiers changed from: private */
        public boolean dumpInProgress;
        /* access modifiers changed from: private */

        /* renamed from: gm */
        public final GarbageMonitor f77gm = SystemUIFactory.getInstance().getRootComponent().createGarbageMonitor();
        private ProcessMemInfo pmi;

        public int getMetricsCategory() {
            return 0;
        }

        public MemoryTile(QSHost qSHost) {
            super(qSHost);
        }

        public QSTile.State newTileState() {
            return new QSTile.State();
        }

        public Intent getLongClickIntent() {
            return new Intent();
        }

        /* access modifiers changed from: protected */
        public void handleClick() {
            if (!this.dumpInProgress) {
                this.dumpInProgress = true;
                refreshState();
                new Thread("HeapDumpThread") {
                    public void run() {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException unused) {
                        }
                        MemoryTile.this.mHandler.post(
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001a: INVOKE  
                              (wrap: com.android.systemui.qs.tileimpl.QSTileImpl$H : 0x0011: INVOKE  (r1v1 com.android.systemui.qs.tileimpl.QSTileImpl$H) = 
                              (wrap: com.android.systemui.util.leak.GarbageMonitor$MemoryTile : 0x000f: IGET  (r1v0 com.android.systemui.util.leak.GarbageMonitor$MemoryTile) = 
                              (r3v0 'this' com.android.systemui.util.leak.GarbageMonitor$MemoryTile$1 A[THIS])
                             com.android.systemui.util.leak.GarbageMonitor.MemoryTile.1.this$0 com.android.systemui.util.leak.GarbageMonitor$MemoryTile)
                             com.android.systemui.util.leak.GarbageMonitor.MemoryTile.access$200(com.android.systemui.util.leak.GarbageMonitor$MemoryTile):com.android.systemui.qs.tileimpl.QSTileImpl$H type: STATIC)
                              (wrap: com.android.systemui.util.leak.-$$Lambda$GarbageMonitor$MemoryTile$1$cmBeuqKr1b9hrY1trlao7X6pfIc : 0x0017: CONSTRUCTOR  (r2v0 com.android.systemui.util.leak.-$$Lambda$GarbageMonitor$MemoryTile$1$cmBeuqKr1b9hrY1trlao7X6pfIc) = 
                              (r3v0 'this' com.android.systemui.util.leak.GarbageMonitor$MemoryTile$1 A[THIS])
                              (wrap: android.content.Intent : 0x000b: INVOKE  (r0v3 android.content.Intent) = 
                              (wrap: com.android.systemui.util.leak.GarbageMonitor : 0x0007: INVOKE  (r0v2 com.android.systemui.util.leak.GarbageMonitor) = 
                              (wrap: com.android.systemui.util.leak.GarbageMonitor$MemoryTile : 0x0005: IGET  (r0v1 com.android.systemui.util.leak.GarbageMonitor$MemoryTile) = 
                              (r3v0 'this' com.android.systemui.util.leak.GarbageMonitor$MemoryTile$1 A[THIS])
                             com.android.systemui.util.leak.GarbageMonitor.MemoryTile.1.this$0 com.android.systemui.util.leak.GarbageMonitor$MemoryTile)
                             com.android.systemui.util.leak.GarbageMonitor.MemoryTile.access$000(com.android.systemui.util.leak.GarbageMonitor$MemoryTile):com.android.systemui.util.leak.GarbageMonitor type: STATIC)
                             com.android.systemui.util.leak.GarbageMonitor.access$100(com.android.systemui.util.leak.GarbageMonitor):android.content.Intent type: STATIC)
                             call: com.android.systemui.util.leak.-$$Lambda$GarbageMonitor$MemoryTile$1$cmBeuqKr1b9hrY1trlao7X6pfIc.<init>(com.android.systemui.util.leak.GarbageMonitor$MemoryTile$1, android.content.Intent):void type: CONSTRUCTOR)
                             android.os.Handler.post(java.lang.Runnable):boolean type: VIRTUAL in method: com.android.systemui.util.leak.GarbageMonitor.MemoryTile.1.run():void, dex: classes.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                            	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                            	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                            	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                            	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:91)
                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:697)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                            	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                            	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                            	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                            	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                            	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                            	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                            	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                            	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                            	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                            	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                            	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0017: CONSTRUCTOR  (r2v0 com.android.systemui.util.leak.-$$Lambda$GarbageMonitor$MemoryTile$1$cmBeuqKr1b9hrY1trlao7X6pfIc) = 
                              (r3v0 'this' com.android.systemui.util.leak.GarbageMonitor$MemoryTile$1 A[THIS])
                              (wrap: android.content.Intent : 0x000b: INVOKE  (r0v3 android.content.Intent) = 
                              (wrap: com.android.systemui.util.leak.GarbageMonitor : 0x0007: INVOKE  (r0v2 com.android.systemui.util.leak.GarbageMonitor) = 
                              (wrap: com.android.systemui.util.leak.GarbageMonitor$MemoryTile : 0x0005: IGET  (r0v1 com.android.systemui.util.leak.GarbageMonitor$MemoryTile) = 
                              (r3v0 'this' com.android.systemui.util.leak.GarbageMonitor$MemoryTile$1 A[THIS])
                             com.android.systemui.util.leak.GarbageMonitor.MemoryTile.1.this$0 com.android.systemui.util.leak.GarbageMonitor$MemoryTile)
                             com.android.systemui.util.leak.GarbageMonitor.MemoryTile.access$000(com.android.systemui.util.leak.GarbageMonitor$MemoryTile):com.android.systemui.util.leak.GarbageMonitor type: STATIC)
                             com.android.systemui.util.leak.GarbageMonitor.access$100(com.android.systemui.util.leak.GarbageMonitor):android.content.Intent type: STATIC)
                             call: com.android.systemui.util.leak.-$$Lambda$GarbageMonitor$MemoryTile$1$cmBeuqKr1b9hrY1trlao7X6pfIc.<init>(com.android.systemui.util.leak.GarbageMonitor$MemoryTile$1, android.content.Intent):void type: CONSTRUCTOR in method: com.android.systemui.util.leak.GarbageMonitor.MemoryTile.1.run():void, dex: classes.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                            	... 83 more
                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.android.systemui.util.leak.-$$Lambda$GarbageMonitor$MemoryTile$1$cmBeuqKr1b9hrY1trlao7X6pfIc, state: NOT_LOADED
                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                            	... 89 more
                            */
                        /*
                            this = this;
                            r0 = 500(0x1f4, double:2.47E-321)
                            java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x0005 }
                        L_0x0005:
                            com.android.systemui.util.leak.GarbageMonitor$MemoryTile r0 = com.android.systemui.util.leak.GarbageMonitor.MemoryTile.this
                            com.android.systemui.util.leak.GarbageMonitor r0 = r0.f77gm
                            android.content.Intent r0 = r0.dumpHprofAndGetShareIntent()
                            com.android.systemui.util.leak.GarbageMonitor$MemoryTile r1 = com.android.systemui.util.leak.GarbageMonitor.MemoryTile.this
                            com.android.systemui.qs.tileimpl.QSTileImpl$H r1 = r1.mHandler
                            com.android.systemui.util.leak.-$$Lambda$GarbageMonitor$MemoryTile$1$cmBeuqKr1b9hrY1trlao7X6pfIc r2 = new com.android.systemui.util.leak.-$$Lambda$GarbageMonitor$MemoryTile$1$cmBeuqKr1b9hrY1trlao7X6pfIc
                            r2.<init>(r3, r0)
                            r1.post(r2)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.util.leak.GarbageMonitor.MemoryTile.C16171.run():void");
                    }

                    public /* synthetic */ void lambda$run$0$GarbageMonitor$MemoryTile$1(Intent intent) {
                        boolean unused = MemoryTile.this.dumpInProgress = false;
                        MemoryTile.this.refreshState();
                        MemoryTile.this.getHost().collapsePanels();
                        MemoryTile.this.mContext.startActivity(intent);
                    }
                }.start();
            }
        }

        public void handleSetListening(boolean z) {
            GarbageMonitor garbageMonitor = this.f77gm;
            if (garbageMonitor != null) {
                garbageMonitor.setTile(z ? this : null);
            }
            ActivityManager activityManager = (ActivityManager) this.mContext.getSystemService(ActivityManager.class);
            if (!z || this.f77gm.mHeapLimit <= 0) {
                activityManager.clearWatchHeapLimit();
            } else {
                activityManager.setWatchHeapLimit(this.f77gm.mHeapLimit * 1024);
            }
        }

        public CharSequence getTileLabel() {
            return getState().label;
        }

        /* access modifiers changed from: protected */
        public void handleUpdateState(QSTile.State state, Object obj) {
            String str;
            this.pmi = this.f77gm.getMemInfo(Process.myPid());
            MemoryGraphIcon memoryGraphIcon = new MemoryGraphIcon();
            memoryGraphIcon.setHeapLimit(this.f77gm.mHeapLimit);
            state.state = this.dumpInProgress ? 0 : 2;
            if (this.dumpInProgress) {
                str = "Dumping...";
            } else {
                str = this.mContext.getString(C1784R$string.heap_dump_tile_name);
            }
            state.label = str;
            ProcessMemInfo processMemInfo = this.pmi;
            if (processMemInfo != null) {
                memoryGraphIcon.setPss(processMemInfo.currentPss);
                state.secondaryLabel = String.format("pss: %s / %s", new Object[]{GarbageMonitor.formatBytes(this.pmi.currentPss * 1024), GarbageMonitor.formatBytes(this.f77gm.mHeapLimit * 1024)});
            } else {
                memoryGraphIcon.setPss(0);
                state.secondaryLabel = null;
            }
            state.icon = memoryGraphIcon;
        }

        public void update() {
            refreshState();
        }
    }

    public static class ProcessMemInfo implements Dumpable {
        public long currentPss;
        public long currentUss;
        public int head = 0;
        public long max = 1;
        public String name;
        public long pid;
        public long[] pss = new long[720];
        public long startTime;
        public long[] uss = new long[720];

        public ProcessMemInfo(long j, String str, long j2) {
            this.pid = j;
            this.name = str;
            this.startTime = j2;
        }

        public long getUptime() {
            return System.currentTimeMillis() - this.startTime;
        }

        public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print("{ \"pid\": ");
            printWriter.print(this.pid);
            printWriter.print(", \"name\": \"");
            printWriter.print(this.name.replace('\"', '-'));
            printWriter.print("\", \"start\": ");
            printWriter.print(this.startTime);
            printWriter.print(", \"pss\": [");
            for (int i = 0; i < this.pss.length; i++) {
                if (i > 0) {
                    printWriter.print(",");
                }
                long[] jArr = this.pss;
                printWriter.print(jArr[(this.head + i) % jArr.length]);
            }
            printWriter.print("], \"uss\": [");
            for (int i2 = 0; i2 < this.uss.length; i2++) {
                if (i2 > 0) {
                    printWriter.print(",");
                }
                long[] jArr2 = this.uss;
                printWriter.print(jArr2[(this.head + i2) % jArr2.length]);
            }
            printWriter.println("] }");
        }
    }

    public static class Service extends SystemUI implements Dumpable {
        private GarbageMonitor mGarbageMonitor;

        public void start() {
            boolean z = false;
            if (Settings.Secure.getInt(this.mContext.getContentResolver(), "sysui_force_enable_leak_reporting", 0) != 0) {
                z = true;
            }
            this.mGarbageMonitor = SystemUIFactory.getInstance().getRootComponent().createGarbageMonitor();
            if (GarbageMonitor.LEAK_REPORTING_ENABLED || z) {
                this.mGarbageMonitor.startLeakMonitor();
            }
            if (GarbageMonitor.HEAP_TRACKING_ENABLED || z) {
                this.mGarbageMonitor.startHeapTracking();
            }
        }

        public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            GarbageMonitor garbageMonitor = this.mGarbageMonitor;
            if (garbageMonitor != null) {
                garbageMonitor.dump(fileDescriptor, printWriter, strArr);
            }
        }
    }

    private class BackgroundHeapCheckHandler extends Handler {
        BackgroundHeapCheckHandler(Looper looper) {
            super(looper);
            if (Looper.getMainLooper().equals(looper)) {
                throw new RuntimeException("BackgroundHeapCheckHandler may not run on the ui thread");
            }
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1000) {
                if (GarbageMonitor.this.gcAndCheckGarbage()) {
                    postDelayed(new Runnable() {
                        public final void run() {
                            GarbageMonitor.this.reinspectGarbageAfterGc();
                        }
                    }, 100);
                }
                removeMessages(1000);
                sendEmptyMessageDelayed(1000, 900000);
            } else if (i == 3000) {
                GarbageMonitor.this.update();
                removeMessages(3000);
                sendEmptyMessageDelayed(3000, 60000);
            }
        }
    }
}
