package com.android.dialer.calllog.config;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.R$dimen;
import com.android.dialer.calllog.CallLogFramework;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.configprovider.ConfigProvider;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.google.common.base.Function;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public final class CallLogConfigImpl implements CallLogConfig {
    private final Context appContext;
    private final ListeningExecutorService backgroundExecutor;
    private final CallLogFramework callLogFramework;
    private final ConfigProvider configProvider;
    private final SharedPreferences sharedPreferences;

    public static final class PollingJob extends JobService {
        public boolean onStartJob(final JobParameters jobParameters) {
            LogUtil.enterBlock("PollingJob.onStartJob");
            Futures.addCallback(((CallLogConfigImpl) CallLogConfigComponent.get(getApplicationContext()).callLogConfig()).update(), new FutureCallback<Void>() {
                public void onFailure(Throwable th) {
                    DialerExecutorModule.getUiThreadHandler().post(
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0009: INVOKE  
                          (wrap: android.os.Handler : 0x0000: INVOKE  (r0v0 android.os.Handler) =  com.android.dialer.common.concurrent.DialerExecutorModule.getUiThreadHandler():android.os.Handler type: STATIC)
                          (wrap: com.android.dialer.calllog.config.-$$Lambda$CallLogConfigImpl$PollingJob$1$LvIbyHQpe4aO6GSRFe2K3xxgkMA : 0x0006: CONSTRUCTOR  (r1v0 com.android.dialer.calllog.config.-$$Lambda$CallLogConfigImpl$PollingJob$1$LvIbyHQpe4aO6GSRFe2K3xxgkMA) = (r3v0 'th' java.lang.Throwable) call: com.android.dialer.calllog.config.-$$Lambda$CallLogConfigImpl$PollingJob$1$LvIbyHQpe4aO6GSRFe2K3xxgkMA.<init>(java.lang.Throwable):void type: CONSTRUCTOR)
                         android.os.Handler.post(java.lang.Runnable):boolean type: VIRTUAL in method: com.android.dialer.calllog.config.CallLogConfigImpl.PollingJob.1.onFailure(java.lang.Throwable):void, dex: classes.dex
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
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
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
                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: CONSTRUCTOR  (r1v0 com.android.dialer.calllog.config.-$$Lambda$CallLogConfigImpl$PollingJob$1$LvIbyHQpe4aO6GSRFe2K3xxgkMA) = (r3v0 'th' java.lang.Throwable) call: com.android.dialer.calllog.config.-$$Lambda$CallLogConfigImpl$PollingJob$1$LvIbyHQpe4aO6GSRFe2K3xxgkMA.<init>(java.lang.Throwable):void type: CONSTRUCTOR in method: com.android.dialer.calllog.config.CallLogConfigImpl.PollingJob.1.onFailure(java.lang.Throwable):void, dex: classes.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                        	... 76 more
                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.android.dialer.calllog.config.-$$Lambda$CallLogConfigImpl$PollingJob$1$LvIbyHQpe4aO6GSRFe2K3xxgkMA, state: NOT_LOADED
                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                        	... 82 more
                        */
                    /*
                        this = this;
                        android.os.Handler r0 = com.android.dialer.common.concurrent.DialerExecutorModule.getUiThreadHandler()
                        com.android.dialer.calllog.config.-$$Lambda$CallLogConfigImpl$PollingJob$1$LvIbyHQpe4aO6GSRFe2K3xxgkMA r1 = new com.android.dialer.calllog.config.-$$Lambda$CallLogConfigImpl$PollingJob$1$LvIbyHQpe4aO6GSRFe2K3xxgkMA
                        r1.<init>(r3)
                        r0.post(r1)
                        com.android.dialer.calllog.config.CallLogConfigImpl$PollingJob r3 = com.android.dialer.calllog.config.CallLogConfigImpl.PollingJob.this
                        android.app.job.JobParameters r2 = r3
                        r0 = 0
                        r3.jobFinished(r2, r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.calllog.config.CallLogConfigImpl.PollingJob.C04361.onFailure(java.lang.Throwable):void");
                }

                public void onSuccess(Object obj) {
                    Void voidR = (Void) obj;
                    PollingJob.this.jobFinished(jobParameters, false);
                }
            }, MoreExecutors.directExecutor());
            return true;
        }

        public boolean onStopJob(JobParameters jobParameters) {
            return false;
        }
    }

    public CallLogConfigImpl(Context context, CallLogFramework callLogFramework2, SharedPreferences sharedPreferences2, ConfigProvider configProvider2, ListeningExecutorService listeningExecutorService) {
        this.appContext = context;
        this.callLogFramework = callLogFramework2;
        this.sharedPreferences = sharedPreferences2;
        this.configProvider = configProvider2;
        this.backgroundExecutor = listeningExecutorService;
    }

    public boolean isCallLogFrameworkEnabled() {
        return this.sharedPreferences.getBoolean("newCallLogFrameworkEnabled", false);
    }

    public boolean isNewCallLogFragmentEnabled() {
        return this.sharedPreferences.getBoolean("newCallLogFragmentEnabled", false);
    }

    public boolean isNewPeerEnabled() {
        return this.sharedPreferences.getBoolean("newPeerEnabled", false);
    }

    public boolean isNewVoicemailFragmentEnabled() {
        return this.sharedPreferences.getBoolean("newVoicemailFragmentEnabled", false);
    }

    public /* synthetic */ Void lambda$update$0$CallLogConfigImpl(boolean z, boolean z2, boolean z3, Void voidR) {
        this.sharedPreferences.edit().putBoolean("newCallLogFragmentEnabled", z).putBoolean("newVoicemailFragmentEnabled", z2).putBoolean("newPeerEnabled", z3).putBoolean("newCallLogFrameworkEnabled", true).apply();
        return null;
    }

    public /* synthetic */ Void lambda$update$1$CallLogConfigImpl() throws Exception {
        this.sharedPreferences.edit().putBoolean("newCallLogFragmentEnabled", false).putBoolean("newVoicemailFragmentEnabled", false).putBoolean("newPeerEnabled", false).putBoolean("newCallLogFrameworkEnabled", false).apply();
        return null;
    }

    public /* synthetic */ ListenableFuture lambda$update$2$CallLogConfigImpl(Void voidR) throws Exception {
        return this.callLogFramework.disable();
    }

    public /* synthetic */ Void lambda$update$3$CallLogConfigImpl(boolean z, boolean z2, boolean z3) throws Exception {
        this.sharedPreferences.edit().putBoolean("newCallLogFragmentEnabled", z).putBoolean("newVoicemailFragmentEnabled", z2).putBoolean("newPeerEnabled", z3).apply();
        return null;
    }

    public void schedulePollingJob() {
        if (R$dimen.isUserUnlocked(this.appContext)) {
            JobScheduler jobScheduler = (JobScheduler) this.appContext.getSystemService(JobScheduler.class);
            Assert.isNotNull(jobScheduler);
            JobInfo build = new JobInfo.Builder(400, new ComponentName(this.appContext, PollingJob.class)).setPeriodic(TimeUnit.HOURS.toMillis(24)).setPersisted(true).setRequiresCharging(true).setRequiresDeviceIdle(true).build();
            LogUtil.m9i("CallLogConfigImpl.schedulePollingJob", "scheduling", new Object[0]);
            jobScheduler.schedule(build);
        }
    }

    public ListenableFuture<Void> update() {
        boolean z = false;
        boolean z2 = ((SharedPrefConfigProvider) this.configProvider).getBoolean("new_call_log_fragment_enabled", false);
        boolean z3 = ((SharedPrefConfigProvider) this.configProvider).getBoolean("new_voicemail_fragment_enabled", false);
        boolean z4 = ((SharedPrefConfigProvider) this.configProvider).getBoolean("nui_peer_enabled", false);
        boolean isCallLogFrameworkEnabled = isCallLogFrameworkEnabled();
        if (z2 || z3 || z4) {
            z = true;
        }
        if (z && !isCallLogFrameworkEnabled) {
            return Futures.transform(this.callLogFramework.enable(), new Function(z2, z3, z4) {
                private final /* synthetic */ boolean f$1;
                private final /* synthetic */ boolean f$2;
                private final /* synthetic */ boolean f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final Object apply(Object obj) {
                    return CallLogConfigImpl.this.lambda$update$0$CallLogConfigImpl(this.f$1, this.f$2, this.f$3, (Void) obj);
                }
            }, this.backgroundExecutor);
        }
        if (z || !isCallLogFrameworkEnabled) {
            return this.backgroundExecutor.submit(new Callable(z2, z3, z4) {
                private final /* synthetic */ boolean f$1;
                private final /* synthetic */ boolean f$2;
                private final /* synthetic */ boolean f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final Object call() {
                    return CallLogConfigImpl.this.lambda$update$3$CallLogConfigImpl(this.f$1, this.f$2, this.f$3);
                }
            });
        }
        return Futures.transformAsync(this.backgroundExecutor.submit(new Callable() {
            public final Object call() {
                return CallLogConfigImpl.this.lambda$update$1$CallLogConfigImpl();
            }
        }), new AsyncFunction() {
            public final ListenableFuture apply(Object obj) {
                return CallLogConfigImpl.this.lambda$update$2$CallLogConfigImpl((Void) obj);
            }
        }, MoreExecutors.directExecutor());
    }
}
