package org.shenme.threadPool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

public class SerialExecutor implements Executor {
    // 任务队列
    final Queue<Runnable> tasks = new ArrayDeque<Runnable>();
    // 执行器
    final Executor executor;
    // 当前正在执行的任务
    public void execute(Runnable command) {
        // TODO Auto-generated method stub
        
    }
    
    // 初始化指定执行器
    public SerialExecutor(Executor executor) {
        this.executor = executor;
    }
    
    // 添加任务到线程池： 将任务添加到任务队列

}
