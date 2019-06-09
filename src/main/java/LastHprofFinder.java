import com.squareup.leakcanary.AnalysisResult;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import anaylzer.AnalyzerHelper;

public class LastHprofFinder implements LeakFinder {

    private File mDir;
    private File mLastFile;

    @Override
    public void prepare(Map<String, String> map) {
        mDir = new File("./hprof");
        if (!mDir.exists()) {
            return;
        }
        List<String> names = new ArrayList<>();
        Collections.addAll(names, mDir.list());
        Collections.sort(names);
        for (String name: names) {
            System.out.println("exits:" + name);
        }
        if (names.size() > 0) {
            String last = names.get(names.size() - 1);
            mLastFile = new File(mDir, last);
            System.out.println("use:" + mLastFile);
        }


    }

    @Override
    public List<AnalysisResult> find() {
        if (mLastFile != null) {
            return AnalyzerHelper.anaylzer(mLastFile, DestroyedActivityLeakFinder.APP_ACTIVITY);
        }
        return null;
    }
}
