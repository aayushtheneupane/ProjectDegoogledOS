package com.google.common.collect;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;

class CollectSpliterators$1FlatMapSpliterator implements Spliterator<T> {
    int characteristics;
    long estimatedSize;
    final Spliterator<F> from;
    Spliterator<T> prefix;
    final /* synthetic */ Function val$function;

    /*  JADX ERROR: JadxRuntimeException in pass: SSATransform
        jadx.core.utils.exceptions.JadxRuntimeException: Not initialized variable reg: 6, insn: 0x0000: IPUT  
          (r6 I:java.util.function.Function)
          (r0 I:com.google.common.collect.CollectSpliterators$1FlatMapSpliterator)
         com.google.common.collect.CollectSpliterators$1FlatMapSpliterator.val$function java.util.function.Function, block:B:0:0x0000
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVarsInBlock(SSATransform.java:165)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:137)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:55)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:41)
        */
    CollectSpliterators$1FlatMapSpliterator(
/*
Method generation error in method: com.google.common.collect.CollectSpliterators$1FlatMapSpliterator.<init>(java.util.Spliterator, java.util.Spliterator, int, long, java.util.function.Function):void, dex: classes.dex
    jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r1v0 ?
    	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
    	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:157)
    	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:129)
    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
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
    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
    
*/

    public int characteristics() {
        return this.characteristics;
    }

    public long estimateSize() {
        Spliterator<T> spliterator = this.prefix;
        if (spliterator != null) {
            this.estimatedSize = Math.max(this.estimatedSize, spliterator.estimateSize());
        }
        return Math.max(this.estimatedSize, 0);
    }

    public void forEachRemaining(Consumer<? super T> consumer) {
        Spliterator<T> spliterator = this.prefix;
        if (spliterator != null) {
            spliterator.forEachRemaining(consumer);
            this.prefix = null;
        }
        this.from.forEachRemaining(new Consumer(this.val$function, consumer) {
            private final /* synthetic */ Function f$0;
            private final /* synthetic */ Consumer f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                ((Spliterator) this.f$0.apply(obj)).forEachRemaining(this.f$1);
            }
        });
        this.estimatedSize = 0;
    }

    public /* synthetic */ void lambda$tryAdvance$0$CollectSpliterators$1FlatMapSpliterator(Function function, Object obj) {
        this.prefix = (Spliterator) function.apply(obj);
    }

    public boolean tryAdvance(Consumer<? super T> consumer) {
        do {
            Spliterator<T> spliterator = this.prefix;
            if (spliterator == null || !spliterator.tryAdvance(consumer)) {
                this.prefix = null;
            } else {
                long j = this.estimatedSize;
                if (j == Long.MAX_VALUE) {
                    return true;
                }
                this.estimatedSize = j - 1;
                return true;
            }
        } while (this.from.tryAdvance(new Consumer(this.val$function) {
            private final /* synthetic */ Function f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                CollectSpliterators$1FlatMapSpliterator.this.lambda$tryAdvance$0$CollectSpliterators$1FlatMapSpliterator(this.f$1, obj);
            }
        }));
        return false;
    }

    public Spliterator<T> trySplit() {
        Spliterator<F> trySplit = this.from.trySplit();
        if (trySplit != null) {
            int i = this.characteristics & -65;
            Spliterator<T> spliterator = this.prefix;
            if (spliterator != null) {
                this.estimatedSize = Math.max(this.estimatedSize, spliterator.estimateSize());
            }
            long max = Math.max(this.estimatedSize, 0);
            if (max < Long.MAX_VALUE) {
                max /= 2;
                this.estimatedSize -= max;
                this.characteristics = i;
            }
            CollectSpliterators$1FlatMapSpliterator collectSpliterators$1FlatMapSpliterator = new CollectSpliterators$1FlatMapSpliterator(this.prefix, trySplit, i, max, this.val$function);
            this.prefix = null;
            return collectSpliterators$1FlatMapSpliterator;
        }
        Spliterator<T> spliterator2 = this.prefix;
        if (spliterator2 == null) {
            return null;
        }
        this.prefix = null;
        return spliterator2;
    }
}
