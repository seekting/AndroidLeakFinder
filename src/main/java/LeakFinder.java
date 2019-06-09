import com.squareup.leakcanary.AnalysisResult;

import java.util.List;
import java.util.Map;

public interface LeakFinder {

    public void prepare(Map<String, String> map);

    public List<AnalysisResult> find();


}
