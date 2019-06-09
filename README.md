# AndroidLeakFinder
基于leakCanny内存泄露工具，用于Pc端
./gradlew jar

android-studio 运行参数
-t com.seekting.demo2019.LeakActivity -f ./skt/com.seekting.demo2019.LeakActivity.hprof
-p com.seekting.demo2019



java -cp  .:./build/libs/AndroidLeakFinder.jar:./libs/haha-2.0.3.jar  App

java -cp  .:./build/libs/AndroidLeakFinder.jar:./libs/haha-2.0.3.jar  App -t com.seekting.demo2019.LeakActivity -f ./skt/com.seekting.demo2019.LeakActivity


java -cp  .:./build/libs/AndroidLeakFinder.jar:./libs/haha-2.0.3.jar  App -l