import com.squareup.leakcanary.AnalysisResult;

import java.util.List;
import java.util.Map;

import anaylzer.AnalyzerHelper;
import anaylzer.LeakFinderError;

public class TargetActivityFinder implements LeakFinder {


    private String targetClazz;
    private String mHprofFile;

    @Override
    public void prepare(Map<String, String> map) {
        targetClazz = map.get("-t");
        mHprofFile = map.get("-f");


    }

    @Override
    public List<AnalysisResult> find() {

        String clazz = targetClazz;
        String file = mHprofFile;
        if ("".equals(clazz) || clazz == null) {

            throw new LeakFinderError("-t: is null!");
        }
        return AnalyzerHelper.anaylzer(file,targetClazz);
    }
}
