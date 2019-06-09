# AndroidLeakFinder
基于leakCanny内存泄露工具，用于Pc端

```java {.line-numbers}

./gradlew jar

```

android-studio 运行参数
```shell {.line-numbers}

#指定hprof和指定类的排查
-t com.seekting.demo2019.LeakActivity -f ./skt/com.seekting.demo2019.LeakActivity.hprof
#指定包名排查
-p com.seekting.demo2019
#基于指定包名最新的hprof排查
-l
```



## 显示帮助
```java {.line-numbers}

java -cp  .:./build/libs/AndroidLeakFinder.jar:./libs/haha-2.0.3.jar  App

```
## 指定hprof和指定类的排查
```java {.line-numbers}

java -cp  .:./build/libs/AndroidLeakFinder.jar:./libs/haha-2.0.3.jar  App -t com.seekting.demo2019.LeakActivity -f ./skt/com.seekting.demo2019.LeakActivity

```

## 指定包名排查
```java {.line-numbers}
java -cp  .:./build/libs/AndroidLeakFinder.jar:./libs/haha-2.0.3.jar  App -p com.seekting.demo2019
```

## 基于指定包名最新的hprof排查
```java {.line-numbers}

java -cp  .:./build/libs/AndroidLeakFinder.jar:./libs/haha-2.0.3.jar  App -l

```
