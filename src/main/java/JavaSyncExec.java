import java.io.IOException;
import java.io.InputStream;

import utils.IOUtil;

public class JavaSyncExec {
    public JavaSyncExec(String cmd) {
        this.cmd = cmd;
    }

    private String cmd;

    public String execute(long wait) {
        try {


            System.out.println("execute:" + cmd);
            Process process = Runtime.getRuntime().exec(cmd);
            InputStream errorInputStream = process.getErrorStream();
            String errorStr = IOUtil.readAndClose(errorInputStream);
//            System.out.println("error:" + errorStr);
            InputStream msgInputStream = process.getInputStream();
            String msgInputStreamStr = IOUtil.readAndClose(msgInputStream);
//            System.out.println("msg:" + msgInputStreamStr);

            process.waitFor();
            Thread.sleep(wait);
            return msgInputStreamStr;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
