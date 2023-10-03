package com.android.dialer.binary.aosp;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.bubble.Bubble;
import com.android.bubble.BubbleComponent;
import com.android.bubble.stub.BubbleStub_Factory;
import com.android.dialer.activecalls.ActiveCalls;
import com.android.dialer.activecalls.ActiveCallsComponent;
import com.android.dialer.activecalls.impl.ActiveCallsImpl_Factory;
import com.android.dialer.calllog.AnnotatedCallLogMigrator;
import com.android.dialer.calllog.AnnotatedCallLogMigrator_Factory;
import com.android.dialer.calllog.CallLogCacheUpdater;
import com.android.dialer.calllog.CallLogCacheUpdater_Factory;
import com.android.dialer.calllog.CallLogComponent;
import com.android.dialer.calllog.CallLogFramework;
import com.android.dialer.calllog.CallLogFramework_Factory;
import com.android.dialer.calllog.CallLogModule_ProvideCallLogDataSourcesFactory;
import com.android.dialer.calllog.CallLogState;
import com.android.dialer.calllog.CallLogState_Factory;
import com.android.dialer.calllog.ClearMissedCalls;
import com.android.dialer.calllog.ClearMissedCalls_Factory;
import com.android.dialer.calllog.RefreshAnnotatedCallLogWorker;
import com.android.dialer.calllog.RefreshAnnotatedCallLogWorker_Factory;
import com.android.dialer.calllog.config.CallLogConfig;
import com.android.dialer.calllog.config.CallLogConfigComponent;
import com.android.dialer.calllog.config.CallLogConfigImpl;
import com.android.dialer.calllog.config.CallLogConfigImpl_Factory;
import com.android.dialer.calllog.database.AnnotatedCallLogDatabaseHelper;
import com.android.dialer.calllog.database.AnnotatedCallLogDatabaseHelper_Factory;
import com.android.dialer.calllog.database.CallLogDatabaseComponent;
import com.android.dialer.calllog.database.CallLogDatabaseModule_ProvideMaxRowsFactory;
import com.android.dialer.calllog.database.Coalescer;
import com.android.dialer.calllog.database.Coalescer_Factory;
import com.android.dialer.calllog.database.MutationApplier;
import com.android.dialer.calllog.database.MutationApplier_Factory;
import com.android.dialer.calllog.datasources.DataSources;
import com.android.dialer.calllog.datasources.phonelookup.PhoneLookupDataSource;
import com.android.dialer.calllog.datasources.phonelookup.PhoneLookupDataSource_Factory;
import com.android.dialer.calllog.datasources.systemcalllog.SystemCallLogDataSource;
import com.android.dialer.calllog.datasources.systemcalllog.SystemCallLogDataSource_Factory;
import com.android.dialer.calllog.datasources.voicemail.VoicemailDataSource;
import com.android.dialer.calllog.datasources.voicemail.VoicemailDataSource_Factory;
import com.android.dialer.calllog.notifier.RefreshAnnotatedCallLogNotifier;
import com.android.dialer.calllog.notifier.RefreshAnnotatedCallLogNotifier_Factory;
import com.android.dialer.calllog.observer.MarkDirtyObserver;
import com.android.dialer.calllog.observer.MarkDirtyObserver_Factory;
import com.android.dialer.calllog.p004ui.CallLogUiComponent;
import com.android.dialer.calllog.p004ui.RealtimeRowProcessor;
import com.android.dialer.calllog.p004ui.RealtimeRowProcessor_Factory;
import com.android.dialer.commandline.Command;
import com.android.dialer.commandline.CommandLineComponent;
import com.android.dialer.commandline.CommandLineModule$AospCommandInjector;
import com.android.dialer.commandline.CommandLineModule_AospCommandInjector_Factory;
import com.android.dialer.commandline.CommandLineModule_ProvideCommandSupplierFactory;
import com.android.dialer.commandline.impl.ActiveCallsCommand;
import com.android.dialer.commandline.impl.ActiveCallsCommand_Factory;
import com.android.dialer.commandline.impl.BlockingCommand;
import com.android.dialer.commandline.impl.BlockingCommand_Factory;
import com.android.dialer.commandline.impl.CallCommand;
import com.android.dialer.commandline.impl.CallCommand_Factory;
import com.android.dialer.commandline.impl.Echo_Factory;
import com.android.dialer.commandline.impl.Help;
import com.android.dialer.commandline.impl.Help_Factory;
import com.android.dialer.commandline.impl.Version;
import com.android.dialer.commandline.impl.Version_Factory;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory_Factory;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.common.concurrent.DialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutorModule_ProvideBackgroundExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutorModule_ProvideLightweightExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutorModule_ProvideNonUiSerialExecutorServiceFactory;
import com.android.dialer.common.concurrent.DialerExecutorModule_ProvideNonUiThreadPoolFactory;
import com.android.dialer.common.concurrent.DialerExecutorModule_ProvideUiSerialExecutorServiceFactory;
import com.android.dialer.common.concurrent.DialerExecutorModule_ProvideUiThreadExecutorServiceFactory;
import com.android.dialer.common.concurrent.DialerExecutorModule_ProvideUiThreadPoolFactory;
import com.android.dialer.configprovider.ConfigProvider;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.configprovider.SharedPrefConfigProvider_Factory;
import com.android.dialer.contacts.ContactsComponent;
import com.android.dialer.contacts.ContactsModule_ProvideContactDisplayPreferencesFactory;
import com.android.dialer.contacts.displaypreference.ContactDisplayPreferences;
import com.android.dialer.contacts.displaypreference.ContactDisplayPreferencesImpl;
import com.android.dialer.contacts.displaypreference.ContactDisplayPreferencesImpl_Factory;
import com.android.dialer.contacts.displaypreference.ContactDisplayPreferencesStub_Factory;
import com.android.dialer.contacts.hiresphoto.HighResolutionPhotoRequester;
import com.android.dialer.contacts.hiresphoto.HighResolutionPhotoRequesterImpl;
import com.android.dialer.contacts.hiresphoto.HighResolutionPhotoRequesterImpl_Factory;
import com.android.dialer.duo.Duo;
import com.android.dialer.duo.DuoComponent;
import com.android.dialer.duo.stub.DuoStub_Factory;
import com.android.dialer.enrichedcall.EnrichedCallComponent;
import com.android.dialer.enrichedcall.EnrichedCallManager;
import com.android.dialer.enrichedcall.RcsVideoShareFactory;
import com.android.dialer.enrichedcall.stub.StubEnrichedCallModule_ProvideEnrichedCallManagerFactory;
import com.android.dialer.enrichedcall.stub.StubEnrichedCallModule_ProvidesRcsVideoShareFactoryFactory;
import com.android.dialer.feedback.FeedbackComponent;
import com.android.dialer.feedback.stub.StubFeedbackModule_ProvideCallFeedbackListenerFactory;
import com.android.dialer.function.Supplier;
import com.android.dialer.glidephotomanager.GlidePhotoManager;
import com.android.dialer.glidephotomanager.GlidePhotoManagerComponent;
import com.android.dialer.glidephotomanager.impl.GlidePhotoManagerImpl;
import com.android.dialer.glidephotomanager.impl.GlidePhotoManagerImpl_Factory;
import com.android.dialer.inject.ContextModule;
import com.android.dialer.inject.ContextModule_ProvideContextFactory;
import com.android.dialer.metrics.FutureTimer;
import com.android.dialer.metrics.FutureTimer_Factory;
import com.android.dialer.metrics.Metrics;
import com.android.dialer.metrics.MetricsComponent;
import com.android.dialer.metrics.StubMetrics;
import com.android.dialer.metrics.StubMetricsInitializer_Factory;
import com.android.dialer.metrics.StubMetrics_Factory;
import com.android.dialer.phonelookup.PhoneLookup;
import com.android.dialer.phonelookup.PhoneLookupComponent;
import com.android.dialer.phonelookup.PhoneLookupModule_ProvidePhoneLookupListFactory;
import com.android.dialer.phonelookup.blockednumber.SystemBlockedNumberPhoneLookup;
import com.android.dialer.phonelookup.blockednumber.SystemBlockedNumberPhoneLookup_Factory;
import com.android.dialer.phonelookup.cequint.CequintPhoneLookup;
import com.android.dialer.phonelookup.cequint.CequintPhoneLookup_Factory;
import com.android.dialer.phonelookup.cnap.CnapPhoneLookup;
import com.android.dialer.phonelookup.cnap.CnapPhoneLookup_Factory;
import com.android.dialer.phonelookup.composite.CompositePhoneLookup;
import com.android.dialer.phonelookup.composite.CompositePhoneLookup_Factory;
import com.android.dialer.phonelookup.cp2.Cp2DefaultDirectoryPhoneLookup;
import com.android.dialer.phonelookup.cp2.Cp2DefaultDirectoryPhoneLookup_Factory;
import com.android.dialer.phonelookup.cp2.Cp2ExtendedDirectoryPhoneLookup;
import com.android.dialer.phonelookup.cp2.Cp2ExtendedDirectoryPhoneLookup_Factory;
import com.android.dialer.phonelookup.cp2.MissingPermissionsOperations_Factory;
import com.android.dialer.phonelookup.database.PhoneLookupDatabaseComponent;
import com.android.dialer.phonelookup.database.PhoneLookupHistoryDatabaseHelper;
import com.android.dialer.phonelookup.database.PhoneLookupHistoryDatabaseHelper_Factory;
import com.android.dialer.phonelookup.emergency.EmergencyPhoneLookup;
import com.android.dialer.phonelookup.emergency.EmergencyPhoneLookup_Factory;
import com.android.dialer.phonelookup.spam.SpamPhoneLookup;
import com.android.dialer.phonelookup.spam.SpamPhoneLookup_Factory;
import com.android.dialer.phonenumbergeoutil.PhoneNumberGeoUtil;
import com.android.dialer.phonenumbergeoutil.PhoneNumberGeoUtilComponent;
import com.android.dialer.phonenumbergeoutil.impl.PhoneNumberGeoUtilImpl_Factory;
import com.android.dialer.precall.PreCall;
import com.android.dialer.precall.PreCallAction;
import com.android.dialer.precall.PreCallComponent;
import com.android.dialer.precall.impl.CallingAccountSelector;
import com.android.dialer.precall.impl.CallingAccountSelector_Factory;
import com.android.dialer.precall.impl.DuoAction;
import com.android.dialer.precall.impl.DuoAction_Factory;
import com.android.dialer.precall.impl.PreCallImpl;
import com.android.dialer.precall.impl.PreCallImpl_Factory;
import com.android.dialer.precall.impl.PreCallModule_ProvideActionsFactory;
import com.android.dialer.preferredsim.PreferredAccountWorker;
import com.android.dialer.preferredsim.PreferredSimComponent;
import com.android.dialer.preferredsim.impl.PreferredAccountWorkerImpl;
import com.android.dialer.preferredsim.impl.PreferredAccountWorkerImpl_Factory;
import com.android.dialer.preferredsim.suggestion.SimSuggestionComponent;
import com.android.dialer.preferredsim.suggestion.SuggestionProvider;
import com.android.dialer.preferredsim.suggestion.stub.StubSuggestionProvider_Factory;
import com.android.dialer.promotion.Promotion;
import com.android.dialer.promotion.PromotionComponent;
import com.android.dialer.promotion.PromotionManager;
import com.android.dialer.promotion.PromotionManager_Factory;
import com.android.dialer.promotion.impl.DuoPromotion_Factory;
import com.android.dialer.promotion.impl.PromotionModule_ProvidePriorityPromotionListFactory;
import com.android.dialer.promotion.impl.RttPromotion;
import com.android.dialer.promotion.impl.RttPromotion_Factory;
import com.android.dialer.simulator.Simulator;
import com.android.dialer.simulator.SimulatorComponent;
import com.android.dialer.simulator.SimulatorConnectionsBank;
import com.android.dialer.simulator.SimulatorEnrichedCall;
import com.android.dialer.simulator.impl.SimulatorConnectionsBankImpl_Factory;
import com.android.dialer.simulator.impl.SimulatorImpl_Factory;
import com.android.dialer.simulator.stub.SimulatorEnrichedCallStub_Factory;
import com.android.dialer.spam.Spam;
import com.android.dialer.spam.SpamComponent;
import com.android.dialer.spam.SpamSettings;
import com.android.dialer.spam.stub.SpamSettingsStub_Factory;
import com.android.dialer.spam.stub.SpamStub;
import com.android.dialer.spam.stub.SpamStub_Factory;
import com.android.dialer.speeddial.loader.SpeedDialUiItemMutator;
import com.android.dialer.speeddial.loader.SpeedDialUiItemMutator_Factory;
import com.android.dialer.speeddial.loader.UiItemLoaderComponent;
import com.android.dialer.storage.StorageComponent;
import com.android.dialer.storage.StorageModule_ProvideUnencryptedSharedPrefsFactory;
import com.android.dialer.strictmode.DialerStrictMode;
import com.android.dialer.strictmode.StrictModeComponent;
import com.android.dialer.strictmode.impl.SystemDialerStrictMode_Factory;
import com.android.dialer.theme.base.Theme;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeModule_ProvideThemeModuleFactory;
import com.android.incallui.call.CallList;
import com.android.incallui.calllocation.CallLocation;
import com.android.incallui.calllocation.CallLocationComponent;
import com.android.incallui.calllocation.stub.StubCallLocationModule_StubCallLocation_Factory;
import com.android.incallui.maps.Maps;
import com.android.incallui.maps.MapsComponent;
import com.android.incallui.maps.stub.StubMapsModule_StubMaps_Factory;
import com.android.incallui.speakeasy.SpeakEasyCallManager;
import com.android.incallui.speakeasy.SpeakEasyCallManagerStub_Factory;
import com.android.incallui.speakeasy.SpeakEasyComponent;
import com.android.incallui.speakeasy.StubSpeakEasyModule_ProvideSpeakEasyChipFactory;
import com.android.voicemail.VoicemailClient;
import com.android.voicemail.VoicemailComponent;
import com.android.voicemail.impl.VoicemailModule_ProvideVoicemailClientFactory;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.DoubleCheck;
import dagger.internal.MembersInjectors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;

public final class DaggerAospDialerRootComponent implements AospDialerRootComponent {
    private Provider<ActiveCallsCommand> activeCallsCommandProvider;
    /* access modifiers changed from: private */
    public Provider<AnnotatedCallLogDatabaseHelper> annotatedCallLogDatabaseHelperProvider;
    private Provider<AnnotatedCallLogMigrator> annotatedCallLogMigratorProvider;
    private Provider<CommandLineModule$AospCommandInjector> aospCommandInjectorProvider;
    /* access modifiers changed from: private */
    public Provider<DialerStrictMode> bindDialerStrictModeProvider;
    /* access modifiers changed from: private */
    public Provider<GlidePhotoManager> bindGlidePhotoManagerProvider;
    /* access modifiers changed from: private */
    public Provider<Maps> bindMapsProvider;
    /* access modifiers changed from: private */
    public Provider<Metrics> bindMetricsProvider;
    /* access modifiers changed from: private */
    public Provider<PhoneNumberGeoUtil> bindPhoneNumberGeoUtilProvider;
    /* access modifiers changed from: private */
    public Provider<Spam> bindSpamProvider;
    /* access modifiers changed from: private */
    public Provider<SuggestionProvider> bindSuggestionProvider;
    /* access modifiers changed from: private */
    public Provider<Bubble> bindsBubbleProvider = DoubleCheck.provider(BubbleStub_Factory.INSTANCE);
    /* access modifiers changed from: private */
    public Provider<Duo> bindsDuoProvider;
    /* access modifiers changed from: private */
    public Provider<SimulatorConnectionsBank> bindsSimulatorConnectionsBankProvider;
    /* access modifiers changed from: private */
    public Provider<SimulatorEnrichedCall> bindsSimulatorEnrichedCallProvider;
    /* access modifiers changed from: private */
    public Provider<Simulator> bindsSimulatorProvider;
    private Provider<BlockingCommand> blockingCommandProvider;
    private Provider<CallCommand> callCommandProvider;
    private Provider<CallLogCacheUpdater> callLogCacheUpdaterProvider;
    /* access modifiers changed from: private */
    public Provider<CallLogFramework> callLogFrameworkProvider;
    private Provider<CallLogState> callLogStateProvider;
    private Provider<CallingAccountSelector> callingAccountSelectorProvider;
    private Provider<CequintPhoneLookup> cequintPhoneLookupProvider;
    private Provider<CnapPhoneLookup> cnapPhoneLookupProvider;
    /* access modifiers changed from: private */
    public Provider<CompositePhoneLookup> compositePhoneLookupProvider;
    private Provider<ContactDisplayPreferencesImpl> contactDisplayPreferencesImplProvider;
    private Provider<Cp2DefaultDirectoryPhoneLookup> cp2DefaultDirectoryPhoneLookupProvider;
    private Provider<Cp2ExtendedDirectoryPhoneLookup> cp2ExtendedDirectoryPhoneLookupProvider;
    private Provider<DuoAction> duoActionProvider;
    private Provider duoPromotionProvider;
    private Provider<EmergencyPhoneLookup> emergencyPhoneLookupProvider;
    /* access modifiers changed from: private */
    public Provider<FutureTimer> futureTimerProvider;
    private Provider<GlidePhotoManagerImpl> glidePhotoManagerImplProvider;
    private Provider<Help> helpProvider;
    private Provider<HighResolutionPhotoRequesterImpl> highResolutionPhotoRequesterImplProvider;
    private Provider<MarkDirtyObserver> markDirtyObserverProvider;
    private Provider missingPermissionsOperationsProvider;
    private Provider<MutationApplier> mutationApplierProvider;
    private Provider<PhoneLookupDataSource> phoneLookupDataSourceProvider;
    /* access modifiers changed from: private */
    public Provider<PhoneLookupHistoryDatabaseHelper> phoneLookupHistoryDatabaseHelperProvider;
    private Provider<PreCallImpl> preCallImplProvider;
    private Provider<PreferredAccountWorkerImpl> preferredAccountWorkerImplProvider;
    /* access modifiers changed from: private */
    public Provider<ImmutableList<PreCallAction>> provideActionsProvider;
    /* access modifiers changed from: private */
    public Provider<ListeningExecutorService> provideBackgroundExecutorProvider;
    /* access modifiers changed from: private */
    public Provider<CallList.Listener> provideCallFeedbackListenerProvider;
    private Provider<DataSources> provideCallLogDataSourcesProvider;
    /* access modifiers changed from: private */
    public Provider<Supplier<ImmutableMap<String, Command>>> provideCommandSupplierProvider;
    /* access modifiers changed from: private */
    public Provider<ContactDisplayPreferences> provideContactDisplayPreferencesProvider;
    /* access modifiers changed from: private */
    public Provider<Context> provideContextProvider;
    /* access modifiers changed from: private */
    public Provider<EnrichedCallManager> provideEnrichedCallManagerProvider;
    /* access modifiers changed from: private */
    public Provider<ListeningExecutorService> provideLightweightExecutorProvider;
    /* access modifiers changed from: private */
    public Provider<ScheduledExecutorService> provideNonUiSerialExecutorServiceProvider;
    /* access modifiers changed from: private */
    public Provider<ExecutorService> provideNonUiThreadPoolProvider;
    private Provider<ImmutableList<PhoneLookup>> providePhoneLookupListProvider;
    /* access modifiers changed from: private */
    public Provider<ImmutableList<Promotion>> providePriorityPromotionListProvider;
    /* access modifiers changed from: private */
    public Provider<Theme> provideThemeModuleProvider;
    /* access modifiers changed from: private */
    public Provider<ScheduledExecutorService> provideUiSerialExecutorServiceProvider;
    /* access modifiers changed from: private */
    public Provider<ListeningExecutorService> provideUiThreadExecutorServiceProvider;
    /* access modifiers changed from: private */
    public Provider<SharedPreferences> provideUnencryptedSharedPrefsProvider;
    /* access modifiers changed from: private */
    public Provider<VoicemailClient> provideVoicemailClientProvider;
    /* access modifiers changed from: private */
    public Provider<RcsVideoShareFactory> providesRcsVideoShareFactoryProvider;
    /* access modifiers changed from: private */
    public Provider<RefreshAnnotatedCallLogNotifier> refreshAnnotatedCallLogNotifierProvider;
    /* access modifiers changed from: private */
    public Provider<RefreshAnnotatedCallLogWorker> refreshAnnotatedCallLogWorkerProvider;
    private Provider<RttPromotion> rttPromotionProvider;
    private Provider<SharedPrefConfigProvider> sharedPrefConfigProvider;
    private Provider<SpamPhoneLookup> spamPhoneLookupProvider;
    private Provider<SpamStub> spamStubProvider;
    /* access modifiers changed from: private */
    public Provider<SpeedDialUiItemMutator> speedDialUiItemMutatorProvider;
    private Provider<StubMetrics> stubMetricsProvider;
    private Provider<SystemBlockedNumberPhoneLookup> systemBlockedNumberPhoneLookupProvider;
    private Provider<SystemCallLogDataSource> systemCallLogDataSourceProvider;
    private Provider<HighResolutionPhotoRequester> toHighResolutionPhotoRequesterImplProvider;
    /* access modifiers changed from: private */
    public Provider<ActiveCalls> toProvider = DoubleCheck.provider(ActiveCallsImpl_Factory.INSTANCE);
    /* access modifiers changed from: private */
    public Provider<ConfigProvider> toProvider2;
    /* access modifiers changed from: private */
    public Provider<PreferredAccountWorker> toProvider3;
    /* access modifiers changed from: private */
    public Provider<PreCall> toProvider4;
    private Provider<Version> versionProvider;
    private Provider<VoicemailDataSource> voicemailDataSourceProvider;

    private final class ActiveCallsComponentImpl extends ActiveCallsComponent {
        /* synthetic */ ActiveCallsComponentImpl(C03661 r2) {
        }

        public ActiveCalls activeCalls() {
            return (ActiveCalls) DaggerAospDialerRootComponent.this.toProvider.get();
        }
    }

    private final class BubbleComponentImpl extends BubbleComponent {
        /* synthetic */ BubbleComponentImpl(C03661 r2) {
        }

        public Bubble getBubble() {
            return (Bubble) DaggerAospDialerRootComponent.this.bindsBubbleProvider.get();
        }
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public ContextModule contextModule;

        /* synthetic */ Builder(C03661 r1) {
        }

        public AospDialerRootComponent build() {
            if (this.contextModule != null) {
                return new DaggerAospDialerRootComponent(this, (C03661) null);
            }
            throw new IllegalStateException(ContextModule.class.getCanonicalName() + " must be set");
        }

        public Builder contextModule(ContextModule contextModule2) {
            if (contextModule2 != null) {
                this.contextModule = contextModule2;
                return this;
            }
            throw new NullPointerException();
        }
    }

    private final class CallLocationComponentImpl extends CallLocationComponent {
        private Provider<CallLocation> bindCallLocationProvider = StubCallLocationModule_StubCallLocation_Factory.INSTANCE;

        /* synthetic */ CallLocationComponentImpl(DaggerAospDialerRootComponent daggerAospDialerRootComponent, C03661 r2) {
        }

        public CallLocation getCallLocation() {
            return this.bindCallLocationProvider.get();
        }
    }

    private final class CallLogComponentImpl extends CallLogComponent {
        private Provider<ClearMissedCalls> clearMissedCallsProvider = new ClearMissedCalls_Factory(DaggerAospDialerRootComponent.this.provideContextProvider, DaggerAospDialerRootComponent.this.provideBackgroundExecutorProvider, DaggerAospDialerRootComponent.this.provideUiThreadExecutorServiceProvider);

        /* synthetic */ CallLogComponentImpl(C03661 r4) {
        }

        public CallLogFramework callLogFramework() {
            return (CallLogFramework) DaggerAospDialerRootComponent.this.callLogFrameworkProvider.get();
        }

        public ClearMissedCalls getClearMissedCalls() {
            return this.clearMissedCallsProvider.get();
        }

        public RefreshAnnotatedCallLogNotifier getRefreshAnnotatedCallLogNotifier() {
            return (RefreshAnnotatedCallLogNotifier) DaggerAospDialerRootComponent.this.refreshAnnotatedCallLogNotifierProvider.get();
        }

        public RefreshAnnotatedCallLogWorker getRefreshAnnotatedCallLogWorker() {
            return (RefreshAnnotatedCallLogWorker) DaggerAospDialerRootComponent.this.refreshAnnotatedCallLogWorkerProvider.get();
        }
    }

    private final class CallLogConfigComponentImpl extends CallLogConfigComponent {
        private Provider<CallLogConfigImpl> callLogConfigImplProvider = new CallLogConfigImpl_Factory(DaggerAospDialerRootComponent.this.provideContextProvider, DaggerAospDialerRootComponent.this.callLogFrameworkProvider, DaggerAospDialerRootComponent.this.provideUnencryptedSharedPrefsProvider, DaggerAospDialerRootComponent.this.toProvider2, DaggerAospDialerRootComponent.this.provideBackgroundExecutorProvider);
        private Provider<CallLogConfig> toProvider = this.callLogConfigImplProvider;

        /* synthetic */ CallLogConfigComponentImpl(C03661 r8) {
        }

        public CallLogConfig callLogConfig() {
            return this.toProvider.get();
        }
    }

    private final class CallLogDatabaseComponentImpl extends CallLogDatabaseComponent {
        private Provider<Coalescer> coalescerProvider = new Coalescer_Factory(DaggerAospDialerRootComponent.this.provideBackgroundExecutorProvider, DaggerAospDialerRootComponent.this.futureTimerProvider);

        /* synthetic */ CallLogDatabaseComponentImpl(C03661 r3) {
        }

        public AnnotatedCallLogDatabaseHelper annotatedCallLogDatabaseHelper() {
            return (AnnotatedCallLogDatabaseHelper) DaggerAospDialerRootComponent.this.annotatedCallLogDatabaseHelperProvider.get();
        }

        public Coalescer coalescer() {
            return this.coalescerProvider.get();
        }
    }

    private final class CallLogUiComponentImpl extends CallLogUiComponent {
        private Provider<RealtimeRowProcessor> realtimeRowProcessorProvider = new RealtimeRowProcessor_Factory(DaggerAospDialerRootComponent.this.provideContextProvider, DaggerAospDialerRootComponent.this.provideUiThreadExecutorServiceProvider, DaggerAospDialerRootComponent.this.provideBackgroundExecutorProvider, DaggerAospDialerRootComponent.this.compositePhoneLookupProvider);

        /* synthetic */ CallLogUiComponentImpl(C03661 r5) {
        }

        public RealtimeRowProcessor realtimeRowProcessor() {
            return this.realtimeRowProcessorProvider.get();
        }
    }

    private final class CommandLineComponentImpl extends CommandLineComponent {
        /* synthetic */ CommandLineComponentImpl(C03661 r2) {
        }

        public Supplier<ImmutableMap<String, Command>> commandSupplier() {
            return (Supplier) DaggerAospDialerRootComponent.this.provideCommandSupplierProvider.get();
        }
    }

    private final class ConfigProviderComponentImpl extends ConfigProviderComponent {
        /* synthetic */ ConfigProviderComponentImpl(C03661 r2) {
        }

        public ConfigProvider getConfigProvider() {
            return (ConfigProvider) DaggerAospDialerRootComponent.this.toProvider2.get();
        }
    }

    private final class ContactsComponentImpl extends ContactsComponent {
        private Provider<HighResolutionPhotoRequesterImpl> highResolutionPhotoRequesterImplProvider = HighResolutionPhotoRequesterImpl_Factory.create(DaggerAospDialerRootComponent.this.provideContextProvider, DaggerAospDialerRootComponent.this.provideBackgroundExecutorProvider);
        private Provider<HighResolutionPhotoRequester> toHighResolutionPhotoRequesterImplProvider = this.highResolutionPhotoRequesterImplProvider;

        /* synthetic */ ContactsComponentImpl(C03661 r2) {
        }

        public ContactDisplayPreferences contactDisplayPreferences() {
            return (ContactDisplayPreferences) DaggerAospDialerRootComponent.this.provideContactDisplayPreferencesProvider.get();
        }

        public HighResolutionPhotoRequester highResolutionPhotoLoader() {
            return this.toHighResolutionPhotoRequesterImplProvider.get();
        }
    }

    private final class DialerExecutorComponentImpl extends DialerExecutorComponent {
        private Provider<DialerExecutorFactory> bindDialerExecutorFactoryProvider = this.defaultDialerExecutorFactoryProvider;
        private Provider<DefaultDialerExecutorFactory> defaultDialerExecutorFactoryProvider = new DefaultDialerExecutorFactory_Factory(DaggerAospDialerRootComponent.this.provideNonUiThreadPoolProvider, DaggerAospDialerRootComponent.this.provideNonUiSerialExecutorServiceProvider, DialerExecutorModule_ProvideUiThreadPoolFactory.create(), DaggerAospDialerRootComponent.this.provideUiSerialExecutorServiceProvider);

        /* synthetic */ DialerExecutorComponentImpl(C03661 r5) {
        }

        public ListeningExecutorService backgroundExecutor() {
            return (ListeningExecutorService) DaggerAospDialerRootComponent.this.provideBackgroundExecutorProvider.get();
        }

        public DialerExecutorFactory dialerExecutorFactory() {
            return this.bindDialerExecutorFactoryProvider.get();
        }

        public ListeningExecutorService lightweightExecutor() {
            return (ListeningExecutorService) DaggerAospDialerRootComponent.this.provideLightweightExecutorProvider.get();
        }

        public ListeningExecutorService uiExecutor() {
            return (ListeningExecutorService) DaggerAospDialerRootComponent.this.provideUiThreadExecutorServiceProvider.get();
        }
    }

    private final class DuoComponentImpl extends DuoComponent {
        /* synthetic */ DuoComponentImpl(C03661 r2) {
        }

        public Duo getDuo() {
            return (Duo) DaggerAospDialerRootComponent.this.bindsDuoProvider.get();
        }
    }

    private final class EnrichedCallComponentImpl extends EnrichedCallComponent {
        /* synthetic */ EnrichedCallComponentImpl(C03661 r2) {
        }

        public EnrichedCallManager getEnrichedCallManager() {
            return (EnrichedCallManager) DaggerAospDialerRootComponent.this.provideEnrichedCallManagerProvider.get();
        }

        public RcsVideoShareFactory getRcsVideoShareFactory() {
            return (RcsVideoShareFactory) DaggerAospDialerRootComponent.this.providesRcsVideoShareFactoryProvider.get();
        }
    }

    private final class FeedbackComponentImpl extends FeedbackComponent {
        /* synthetic */ FeedbackComponentImpl(C03661 r2) {
        }

        public CallList.Listener getCallFeedbackListener() {
            return (CallList.Listener) DaggerAospDialerRootComponent.this.provideCallFeedbackListenerProvider.get();
        }
    }

    private final class GlidePhotoManagerComponentImpl extends GlidePhotoManagerComponent {
        /* synthetic */ GlidePhotoManagerComponentImpl(C03661 r2) {
        }

        public GlidePhotoManager glidePhotoManager() {
            return (GlidePhotoManager) DaggerAospDialerRootComponent.this.bindGlidePhotoManagerProvider.get();
        }
    }

    private final class MapsComponentImpl extends MapsComponent {
        /* synthetic */ MapsComponentImpl(C03661 r2) {
        }

        public Maps getMaps() {
            return (Maps) DaggerAospDialerRootComponent.this.bindMapsProvider.get();
        }
    }

    private final class MetricsComponentImpl extends MetricsComponent {
        /* synthetic */ MetricsComponentImpl(C03661 r2) {
            StubMetricsInitializer_Factory stubMetricsInitializer_Factory = StubMetricsInitializer_Factory.INSTANCE;
        }

        public FutureTimer futureTimer() {
            return (FutureTimer) DaggerAospDialerRootComponent.this.futureTimerProvider.get();
        }

        public Metrics metrics() {
            return (Metrics) DaggerAospDialerRootComponent.this.bindMetricsProvider.get();
        }
    }

    private final class PhoneLookupComponentImpl extends PhoneLookupComponent {
        /* synthetic */ PhoneLookupComponentImpl(C03661 r2) {
        }

        public CompositePhoneLookup compositePhoneLookup() {
            return (CompositePhoneLookup) DaggerAospDialerRootComponent.this.compositePhoneLookupProvider.get();
        }
    }

    private final class PhoneLookupDatabaseComponentImpl extends PhoneLookupDatabaseComponent {
        /* synthetic */ PhoneLookupDatabaseComponentImpl(C03661 r2) {
        }

        public PhoneLookupHistoryDatabaseHelper phoneLookupHistoryDatabaseHelper() {
            return (PhoneLookupHistoryDatabaseHelper) DaggerAospDialerRootComponent.this.phoneLookupHistoryDatabaseHelperProvider.get();
        }
    }

    private final class PhoneNumberGeoUtilComponentImpl extends PhoneNumberGeoUtilComponent {
        /* synthetic */ PhoneNumberGeoUtilComponentImpl(C03661 r2) {
        }

        public PhoneNumberGeoUtil getPhoneNumberGeoUtil() {
            return (PhoneNumberGeoUtil) DaggerAospDialerRootComponent.this.bindPhoneNumberGeoUtilProvider.get();
        }
    }

    private final class PreCallComponentImpl extends PreCallComponent {
        /* synthetic */ PreCallComponentImpl(C03661 r2) {
        }

        public ImmutableList<PreCallAction> createActions() {
            return (ImmutableList) DaggerAospDialerRootComponent.this.provideActionsProvider.get();
        }

        public PreCall getPreCall() {
            return (PreCall) DaggerAospDialerRootComponent.this.toProvider4.get();
        }
    }

    private final class PreferredSimComponentImpl extends PreferredSimComponent {
        /* synthetic */ PreferredSimComponentImpl(C03661 r2) {
        }

        public PreferredAccountWorker preferredAccountWorker() {
            return (PreferredAccountWorker) DaggerAospDialerRootComponent.this.toProvider3.get();
        }
    }

    private final class PromotionComponentImpl extends PromotionComponent {
        private Provider<PromotionManager> promotionManagerProvider = new PromotionManager_Factory(DaggerAospDialerRootComponent.this.providePriorityPromotionListProvider);

        /* synthetic */ PromotionComponentImpl(C03661 r2) {
        }

        public PromotionManager promotionManager() {
            return this.promotionManagerProvider.get();
        }
    }

    private final class SimSuggestionComponentImpl extends SimSuggestionComponent {
        /* synthetic */ SimSuggestionComponentImpl(C03661 r2) {
        }

        public SuggestionProvider getSuggestionProvider() {
            return (SuggestionProvider) DaggerAospDialerRootComponent.this.bindSuggestionProvider.get();
        }
    }

    private final class SimulatorComponentImpl extends SimulatorComponent {
        /* synthetic */ SimulatorComponentImpl(C03661 r2) {
        }

        public Simulator getSimulator() {
            return (Simulator) DaggerAospDialerRootComponent.this.bindsSimulatorProvider.get();
        }

        public SimulatorConnectionsBank getSimulatorConnectionsBank() {
            return (SimulatorConnectionsBank) DaggerAospDialerRootComponent.this.bindsSimulatorConnectionsBankProvider.get();
        }

        public SimulatorEnrichedCall getSimulatorEnrichedCall() {
            return (SimulatorEnrichedCall) DaggerAospDialerRootComponent.this.bindsSimulatorEnrichedCallProvider.get();
        }
    }

    private final class SpamComponentImpl extends SpamComponent {
        private Provider<SpamSettings> bindSpamSettingsProvider = SpamSettingsStub_Factory.INSTANCE;

        /* synthetic */ SpamComponentImpl(C03661 r2) {
        }

        public Spam spam() {
            return (Spam) DaggerAospDialerRootComponent.this.bindSpamProvider.get();
        }

        public SpamSettings spamSettings() {
            return this.bindSpamSettingsProvider.get();
        }
    }

    private final class SpeakEasyComponentImpl extends SpeakEasyComponent {
        private Provider<SpeakEasyCallManager> bindsSpeakEasyProvider = SpeakEasyCallManagerStub_Factory.INSTANCE;

        /* synthetic */ SpeakEasyComponentImpl(DaggerAospDialerRootComponent daggerAospDialerRootComponent, C03661 r2) {
        }

        public SpeakEasyCallManager speakEasyCallManager() {
            return this.bindsSpeakEasyProvider.get();
        }

        public Optional<Integer> speakEasyChip() {
            return (Optional) StubSpeakEasyModule_ProvideSpeakEasyChipFactory.INSTANCE.get();
        }
    }

    private final class StorageComponentImpl extends StorageComponent {
        /* synthetic */ StorageComponentImpl(C03661 r2) {
        }

        public SharedPreferences unencryptedSharedPrefs() {
            return (SharedPreferences) DaggerAospDialerRootComponent.this.provideUnencryptedSharedPrefsProvider.get();
        }
    }

    private final class StrictModeComponentImpl extends StrictModeComponent {
        /* synthetic */ StrictModeComponentImpl(C03661 r2) {
        }

        public DialerStrictMode getDialerStrictMode() {
            return (DialerStrictMode) DaggerAospDialerRootComponent.this.bindDialerStrictModeProvider.get();
        }
    }

    private final class ThemeComponentImpl extends ThemeComponent {
        /* synthetic */ ThemeComponentImpl(C03661 r2) {
        }

        public Theme theme() {
            return (Theme) DaggerAospDialerRootComponent.this.provideThemeModuleProvider.get();
        }
    }

    private final class UiItemLoaderComponentImpl extends UiItemLoaderComponent {
        /* synthetic */ UiItemLoaderComponentImpl(C03661 r2) {
        }

        public SpeedDialUiItemMutator speedDialUiItemMutator() {
            return (SpeedDialUiItemMutator) DaggerAospDialerRootComponent.this.speedDialUiItemMutatorProvider.get();
        }
    }

    private final class VoicemailComponentImpl extends VoicemailComponent {
        /* synthetic */ VoicemailComponentImpl(C03661 r2) {
        }

        public VoicemailClient getVoicemailClient() {
            return (VoicemailClient) DaggerAospDialerRootComponent.this.provideVoicemailClientProvider.get();
        }
    }

    /* synthetic */ DaggerAospDialerRootComponent(Builder builder, C03661 r14) {
        this.provideContextProvider = new ContextModule_ProvideContextFactory(builder.contextModule);
        this.provideNonUiThreadPoolProvider = DoubleCheck.provider(DialerExecutorModule_ProvideNonUiThreadPoolFactory.INSTANCE);
        this.provideBackgroundExecutorProvider = DoubleCheck.provider(new DialerExecutorModule_ProvideBackgroundExecutorFactory(this.provideNonUiThreadPoolProvider));
        this.provideUnencryptedSharedPrefsProvider = DoubleCheck.provider(new StorageModule_ProvideUnencryptedSharedPrefsFactory(this.provideContextProvider));
        this.refreshAnnotatedCallLogNotifierProvider = DoubleCheck.provider(new RefreshAnnotatedCallLogNotifier_Factory(this.provideContextProvider, this.provideUnencryptedSharedPrefsProvider));
        this.markDirtyObserverProvider = new MarkDirtyObserver_Factory(MembersInjectors.noOp(), this.refreshAnnotatedCallLogNotifierProvider);
        this.annotatedCallLogDatabaseHelperProvider = DoubleCheck.provider(new AnnotatedCallLogDatabaseHelper_Factory(MembersInjectors.noOp(), this.provideContextProvider, CallLogDatabaseModule_ProvideMaxRowsFactory.INSTANCE, this.provideBackgroundExecutorProvider));
        this.bindsDuoProvider = DoubleCheck.provider(DuoStub_Factory.INSTANCE);
        this.systemCallLogDataSourceProvider = DoubleCheck.provider(new SystemCallLogDataSource_Factory(this.provideContextProvider, this.provideBackgroundExecutorProvider, this.markDirtyObserverProvider, this.provideUnencryptedSharedPrefsProvider, this.annotatedCallLogDatabaseHelperProvider, this.bindsDuoProvider));
        this.provideLightweightExecutorProvider = DoubleCheck.provider(new DialerExecutorModule_ProvideLightweightExecutorFactory(DialerExecutorModule_ProvideUiThreadPoolFactory.create()));
        this.cequintPhoneLookupProvider = new CequintPhoneLookup_Factory(this.provideContextProvider, this.provideBackgroundExecutorProvider, this.provideLightweightExecutorProvider);
        this.cnapPhoneLookupProvider = new CnapPhoneLookup_Factory(this.provideContextProvider, this.provideBackgroundExecutorProvider);
        this.sharedPrefConfigProvider = new SharedPrefConfigProvider_Factory(this.provideUnencryptedSharedPrefsProvider);
        this.toProvider2 = DoubleCheck.provider(this.sharedPrefConfigProvider);
        this.missingPermissionsOperationsProvider = new MissingPermissionsOperations_Factory(this.provideContextProvider, this.provideBackgroundExecutorProvider, this.provideLightweightExecutorProvider);
        this.cp2DefaultDirectoryPhoneLookupProvider = new Cp2DefaultDirectoryPhoneLookup_Factory(this.provideContextProvider, this.provideUnencryptedSharedPrefsProvider, this.provideBackgroundExecutorProvider, this.provideLightweightExecutorProvider, this.toProvider2, this.missingPermissionsOperationsProvider);
        this.provideNonUiSerialExecutorServiceProvider = DoubleCheck.provider(DialerExecutorModule_ProvideNonUiSerialExecutorServiceFactory.INSTANCE);
        this.cp2ExtendedDirectoryPhoneLookupProvider = new Cp2ExtendedDirectoryPhoneLookup_Factory(this.provideContextProvider, this.provideBackgroundExecutorProvider, this.provideLightweightExecutorProvider, this.provideNonUiSerialExecutorServiceProvider, this.toProvider2, this.missingPermissionsOperationsProvider);
        this.emergencyPhoneLookupProvider = new EmergencyPhoneLookup_Factory(this.provideContextProvider, this.provideBackgroundExecutorProvider);
        this.systemBlockedNumberPhoneLookupProvider = new SystemBlockedNumberPhoneLookup_Factory(this.provideContextProvider, this.provideBackgroundExecutorProvider, this.markDirtyObserverProvider);
        this.spamStubProvider = new SpamStub_Factory(this.provideBackgroundExecutorProvider);
        this.bindSpamProvider = this.spamStubProvider;
        this.spamPhoneLookupProvider = new SpamPhoneLookup_Factory(this.provideBackgroundExecutorProvider, this.provideLightweightExecutorProvider, this.provideUnencryptedSharedPrefsProvider, this.bindSpamProvider);
        this.providePhoneLookupListProvider = new PhoneLookupModule_ProvidePhoneLookupListFactory(this.cequintPhoneLookupProvider, this.cnapPhoneLookupProvider, this.cp2DefaultDirectoryPhoneLookupProvider, this.cp2ExtendedDirectoryPhoneLookupProvider, this.emergencyPhoneLookupProvider, this.systemBlockedNumberPhoneLookupProvider, this.spamPhoneLookupProvider);
        this.stubMetricsProvider = DoubleCheck.provider(StubMetrics_Factory.INSTANCE);
        this.bindMetricsProvider = this.stubMetricsProvider;
        this.futureTimerProvider = new FutureTimer_Factory(this.bindMetricsProvider, this.provideLightweightExecutorProvider);
        this.callLogStateProvider = new CallLogState_Factory(this.provideUnencryptedSharedPrefsProvider, this.provideBackgroundExecutorProvider);
        this.compositePhoneLookupProvider = new CompositePhoneLookup_Factory(this.provideContextProvider, this.providePhoneLookupListProvider, this.futureTimerProvider, this.callLogStateProvider, this.provideLightweightExecutorProvider);
        this.phoneLookupHistoryDatabaseHelperProvider = DoubleCheck.provider(new PhoneLookupHistoryDatabaseHelper_Factory(MembersInjectors.noOp(), this.provideContextProvider, this.provideBackgroundExecutorProvider));
        this.phoneLookupDataSourceProvider = new PhoneLookupDataSource_Factory(this.provideContextProvider, this.compositePhoneLookupProvider, this.provideBackgroundExecutorProvider, this.provideLightweightExecutorProvider, this.phoneLookupHistoryDatabaseHelperProvider);
        this.voicemailDataSourceProvider = new VoicemailDataSource_Factory(this.provideContextProvider, this.provideBackgroundExecutorProvider);
        this.provideCallLogDataSourcesProvider = new CallLogModule_ProvideCallLogDataSourcesFactory(this.systemCallLogDataSourceProvider, this.phoneLookupDataSourceProvider, this.voicemailDataSourceProvider);
        this.mutationApplierProvider = new MutationApplier_Factory(this.provideBackgroundExecutorProvider);
        this.callLogCacheUpdaterProvider = new CallLogCacheUpdater_Factory(this.provideContextProvider, this.provideBackgroundExecutorProvider, this.callLogStateProvider);
        this.refreshAnnotatedCallLogWorkerProvider = DoubleCheck.provider(new RefreshAnnotatedCallLogWorker_Factory(this.provideContextProvider, this.provideCallLogDataSourcesProvider, this.provideUnencryptedSharedPrefsProvider, this.mutationApplierProvider, this.futureTimerProvider, this.callLogStateProvider, this.callLogCacheUpdaterProvider, this.provideBackgroundExecutorProvider, this.provideLightweightExecutorProvider));
        this.annotatedCallLogMigratorProvider = new AnnotatedCallLogMigrator_Factory(this.provideUnencryptedSharedPrefsProvider, this.provideBackgroundExecutorProvider, this.refreshAnnotatedCallLogWorkerProvider);
        this.provideUiThreadExecutorServiceProvider = DoubleCheck.provider(DialerExecutorModule_ProvideUiThreadExecutorServiceFactory.INSTANCE);
        this.callLogFrameworkProvider = DoubleCheck.provider(new CallLogFramework_Factory(this.provideContextProvider, this.provideCallLogDataSourcesProvider, this.annotatedCallLogMigratorProvider, this.provideUiThreadExecutorServiceProvider, this.callLogStateProvider));
        this.helpProvider = new Help_Factory(this.provideContextProvider);
        this.versionProvider = new Version_Factory(this.provideContextProvider);
        this.blockingCommandProvider = new BlockingCommand_Factory(this.provideContextProvider, this.provideBackgroundExecutorProvider);
        this.callCommandProvider = new CallCommand_Factory(this.provideContextProvider);
        this.activeCallsCommandProvider = new ActiveCallsCommand_Factory(this.provideContextProvider);
        this.aospCommandInjectorProvider = new CommandLineModule_AospCommandInjector_Factory(this.helpProvider, this.versionProvider, Echo_Factory.INSTANCE, this.blockingCommandProvider, this.callCommandProvider, this.activeCallsCommandProvider);
        this.provideCommandSupplierProvider = new CommandLineModule_ProvideCommandSupplierFactory(this.aospCommandInjectorProvider);
        this.contactDisplayPreferencesImplProvider = new ContactDisplayPreferencesImpl_Factory(this.provideContextProvider);
        this.provideContactDisplayPreferencesProvider = new ContactsModule_ProvideContactDisplayPreferencesFactory(this.provideContextProvider, this.contactDisplayPreferencesImplProvider, ContactDisplayPreferencesStub_Factory.INSTANCE);
        this.provideUiSerialExecutorServiceProvider = DoubleCheck.provider(DialerExecutorModule_ProvideUiSerialExecutorServiceFactory.INSTANCE);
        this.provideEnrichedCallManagerProvider = DoubleCheck.provider(StubEnrichedCallModule_ProvideEnrichedCallManagerFactory.INSTANCE);
        this.providesRcsVideoShareFactoryProvider = DoubleCheck.provider(StubEnrichedCallModule_ProvidesRcsVideoShareFactoryFactory.INSTANCE);
        this.provideCallFeedbackListenerProvider = new StubFeedbackModule_ProvideCallFeedbackListenerFactory(this.provideContextProvider);
        this.glidePhotoManagerImplProvider = new GlidePhotoManagerImpl_Factory(this.provideContextProvider);
        this.bindGlidePhotoManagerProvider = DoubleCheck.provider(this.glidePhotoManagerImplProvider);
        this.bindMapsProvider = DoubleCheck.provider(StubMapsModule_StubMaps_Factory.INSTANCE);
        this.bindPhoneNumberGeoUtilProvider = DoubleCheck.provider(PhoneNumberGeoUtilImpl_Factory.INSTANCE);
        this.duoActionProvider = new DuoAction_Factory(this.provideUiThreadExecutorServiceProvider);
        this.preferredAccountWorkerImplProvider = new PreferredAccountWorkerImpl_Factory(this.provideContextProvider, this.provideBackgroundExecutorProvider);
        this.toProvider3 = this.preferredAccountWorkerImplProvider;
        this.callingAccountSelectorProvider = new CallingAccountSelector_Factory(this.toProvider3);
        this.provideActionsProvider = new PreCallModule_ProvideActionsFactory(this.duoActionProvider, this.callingAccountSelectorProvider);
        this.preCallImplProvider = new PreCallImpl_Factory(this.provideActionsProvider);
        this.toProvider4 = DoubleCheck.provider(this.preCallImplProvider);
        this.rttPromotionProvider = new RttPromotion_Factory(this.provideContextProvider, this.provideUnencryptedSharedPrefsProvider, this.toProvider2);
        this.duoPromotionProvider = new DuoPromotion_Factory(this.provideContextProvider, this.toProvider2, this.bindsDuoProvider, this.provideUnencryptedSharedPrefsProvider);
        this.providePriorityPromotionListProvider = new PromotionModule_ProvidePriorityPromotionListFactory(this.rttPromotionProvider, this.duoPromotionProvider);
        this.highResolutionPhotoRequesterImplProvider = HighResolutionPhotoRequesterImpl_Factory.create(this.provideContextProvider, this.provideBackgroundExecutorProvider);
        this.toHighResolutionPhotoRequesterImplProvider = this.highResolutionPhotoRequesterImplProvider;
        this.speedDialUiItemMutatorProvider = DoubleCheck.provider(new SpeedDialUiItemMutator_Factory(this.provideContextProvider, this.provideBackgroundExecutorProvider, this.provideContactDisplayPreferencesProvider, this.toHighResolutionPhotoRequesterImplProvider));
        this.bindSuggestionProvider = DoubleCheck.provider(StubSuggestionProvider_Factory.INSTANCE);
        this.bindsSimulatorProvider = DoubleCheck.provider(SimulatorImpl_Factory.INSTANCE);
        this.bindsSimulatorEnrichedCallProvider = DoubleCheck.provider(SimulatorEnrichedCallStub_Factory.INSTANCE);
        this.bindsSimulatorConnectionsBankProvider = DoubleCheck.provider(SimulatorConnectionsBankImpl_Factory.INSTANCE);
        this.bindDialerStrictModeProvider = DoubleCheck.provider(SystemDialerStrictMode_Factory.INSTANCE);
        this.provideThemeModuleProvider = new AospThemeModule_ProvideThemeModuleFactory(this.provideContextProvider);
        this.provideVoicemailClientProvider = DoubleCheck.provider(new VoicemailModule_ProvideVoicemailClientFactory(this.provideContextProvider));
    }

    public ActiveCallsComponent activeCallsComponent() {
        return new ActiveCallsComponentImpl((C03661) null);
    }

    public BubbleComponent bubbleComponent() {
        return new BubbleComponentImpl((C03661) null);
    }

    public CallLocationComponent callLocationComponent() {
        return new CallLocationComponentImpl(this, (C03661) null);
    }

    public CallLogComponent callLogComponent() {
        return new CallLogComponentImpl((C03661) null);
    }

    public CallLogConfigComponent callLogConfigComponent() {
        return new CallLogConfigComponentImpl((C03661) null);
    }

    public CallLogDatabaseComponent callLogDatabaseComponent() {
        return new CallLogDatabaseComponentImpl((C03661) null);
    }

    public CallLogUiComponent callLogUiComponent() {
        return new CallLogUiComponentImpl((C03661) null);
    }

    public CommandLineComponent commandLineComponent() {
        return new CommandLineComponentImpl((C03661) null);
    }

    public ConfigProviderComponent configProviderComponent() {
        return new ConfigProviderComponentImpl((C03661) null);
    }

    public ContactsComponent contactsComponent() {
        return new ContactsComponentImpl((C03661) null);
    }

    public DialerExecutorComponent dialerExecutorComponent() {
        return new DialerExecutorComponentImpl((C03661) null);
    }

    public DuoComponent duoComponent() {
        return new DuoComponentImpl((C03661) null);
    }

    public EnrichedCallComponent enrichedCallComponent() {
        return new EnrichedCallComponentImpl((C03661) null);
    }

    public FeedbackComponent feedbackComponent() {
        return new FeedbackComponentImpl((C03661) null);
    }

    public GlidePhotoManagerComponent glidePhotoManagerComponent() {
        return new GlidePhotoManagerComponentImpl((C03661) null);
    }

    public MapsComponent mapsComponent() {
        return new MapsComponentImpl((C03661) null);
    }

    public MetricsComponent metricsComponent() {
        return new MetricsComponentImpl((C03661) null);
    }

    public PhoneLookupComponent phoneLookupComponent() {
        return new PhoneLookupComponentImpl((C03661) null);
    }

    public PhoneLookupDatabaseComponent phoneLookupDatabaseComponent() {
        return new PhoneLookupDatabaseComponentImpl((C03661) null);
    }

    public PhoneNumberGeoUtilComponent phoneNumberGeoUtilComponent() {
        return new PhoneNumberGeoUtilComponentImpl((C03661) null);
    }

    public PreCallComponent preCallActionsComponent() {
        return new PreCallComponentImpl((C03661) null);
    }

    public PreferredSimComponent preferredSimComponent() {
        return new PreferredSimComponentImpl((C03661) null);
    }

    public PromotionComponent promotionComponent() {
        return new PromotionComponentImpl((C03661) null);
    }

    public SimSuggestionComponent simSuggestionComponent() {
        return new SimSuggestionComponentImpl((C03661) null);
    }

    public SimulatorComponent simulatorComponent() {
        return new SimulatorComponentImpl((C03661) null);
    }

    public SpamComponent spamComponent() {
        return new SpamComponentImpl((C03661) null);
    }

    public SpeakEasyComponent speakEasyComponent() {
        return new SpeakEasyComponentImpl(this, (C03661) null);
    }

    public StorageComponent storageComponent() {
        return new StorageComponentImpl((C03661) null);
    }

    public StrictModeComponent strictModeComponent() {
        return new StrictModeComponentImpl((C03661) null);
    }

    public ThemeComponent themeComponent() {
        return new ThemeComponentImpl((C03661) null);
    }

    public UiItemLoaderComponent uiItemLoaderComponent() {
        return new UiItemLoaderComponentImpl((C03661) null);
    }

    public VoicemailComponent voicemailComponent() {
        return new VoicemailComponentImpl((C03661) null);
    }
}
