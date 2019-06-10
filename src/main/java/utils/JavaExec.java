package utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class JavaExec {
    public JavaExec(String cmd) {
        this.cmd = cmd;
    }

    private String cmd;

    public void execute(long wait) {
        try {


            System.out.println("execute:" + cmd);
            Process process = Runtime.getRuntime().exec(cmd);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    InputStream j = process.getErrorStream();
                    String s = IOUtil.readAndClose(j);
                    if (s != null && !"".equals(s)) {
                        System.out.println("error:" + s);
                    }

                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    InputStream j = process.getInputStream();
                    String s = IOUtil.readAndClose(j);
                    if (s != null && !"".equals(s)) {
                        System.out.println("msg:" + s);
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    OutputStream outputStream = process.getOutputStream();
//                    String pull = "adb pull " + deviceFile;
//                    try {
//                        outputStream.write(pull.getBytes());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            }).start();
            process.waitFor();
            Thread.sleep(wait);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
