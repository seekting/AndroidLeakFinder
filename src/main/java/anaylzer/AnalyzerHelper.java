package anaylzer;

import android.support.annotation.NonNull;

import com.squareup.leakcanary.AnalysisResult;
import com.squareup.leakcanary.AnalyzerProgressListener;
import com.squareup.leakcanary.AndroidExcludedRefs;
import com.squareup.leakcanary.ExcludedRefs;
import com.squareup.leakcanary.HeapAnalyzer;
import com.squareup.leakcanary.HeapDump;
import com.squareup.leakcanary.Reachability;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class AnalyzerHelper {
    public static List<AnalysisResult> anaylzer(File heapDumpFile, String clazz) {
        if (!heapDumpFile.exists()) {
            throw new LeakFinderError("file:" + heapDumpFile.getAbsolutePath() + "not exits");
        }
        HeapDump.Builder heapDumpBuilder = new HeapDump.Builder();
        String key = UUID.randomUUID().toString();
        ExcludedRefs excludedRefs = AndroidExcludedRefs.createAppDefaults().build();
        HeapDump heapDump = heapDumpBuilder.heapDumpFile(heapDumpFile).referenceKey(key)
                .referenceName(clazz)
                .watchDurationMs(2000)
                .gcDurationMs(3000)
                .heapDumpDurationMs(3000)
                .excludedRefs(excludedRefs)
                .reachabilityInspectorClasses(defaultReachabilityInspectorClasses())
                .build();
        HeapAnalyzer heapAnalyzer =
                new HeapAnalyzer(heapDump.excludedRefs, new AnalyzerProgressListener() {
                    @Override
                    public void onProgressUpdate(@NonNull AnalyzerProgressListener.Step step, String desc) {
                        System.out.println(step + ":" + desc);
                    }
                }, heapDump.reachabilityInspectorClasses);

        List<AnalysisResult> results = heapAnalyzer.checkForLeaks(heapDump.heapDumpFile, clazz,
                heapDump.computeRetainedHeapSize);
        return results;
    }

    public static List<AnalysisResult> anaylzer(String file, String clazz) {
        File heapDumpFile = new File(file);
        return anaylzer(heapDumpFile, clazz);
    }

    public static List<Class<? extends Reachability.Inspector>> defaultReachabilityInspectorClasses() {
        return Collections.emptyList();
    }
}
