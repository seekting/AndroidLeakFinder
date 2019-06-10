import com.squareup.leakcanary.AnalysisResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import anaylzer.AnalyzerHelper;
import utils.JavaExec;

public class DestroyedActivityLeakFinder implements LeakFinder {


    private String pkgName;
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
    public static final String APP_ACTIVITY = "android.app.Activity";

    @Override
    public void prepare(Map<String, String> map) {
        pkgName = map.get("-p");


    }

    @Override
    public List<AnalysisResult> find() {

//        String s = new JavaSyncExec("which adb").execute(0);
//        if (s == null || s.equals("")) {
//
//            return null;
//        }
        String timeStr = SIMPLE_DATE_FORMAT.format(new Date());
        String deviceFile = "/data/local/tmp/" + timeStr + ".hprof";

        String adb = "adb";
        String cmd = adb + " shell am dumpheap " + pkgName + " " + deviceFile;
        JavaExec javaExec = new JavaExec(cmd);
        System.out.println("dump " + pkgName + "hprof file,wait 10s...");
        javaExec.execute(10000);
        String dirStr = "./hprof";
        String pullCmd = adb + " pull " + deviceFile + " " + dirStr;

        File file = new File(dirStr);
        if (!file.exists()) {
            file.mkdirs();
        }
        javaExec = new JavaExec(pullCmd);
        javaExec.execute(0);
        File localFile = new File(dirStr, timeStr + ".hprof");
        if (!localFile.exists()) {
            return null;
        }
        System.out.println("get hprof" + localFile.getAbsolutePath());

        List<AnalysisResult> results = AnalyzerHelper.anaylzer(localFile, APP_ACTIVITY);
        return results;
    }


}
