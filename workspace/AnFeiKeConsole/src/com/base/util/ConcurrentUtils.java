package com.base.util;

import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentUtils {

	//public static final ExecutorService SYS_PROGRESS_MSG_THREAD_POOL = Executors.newSingleThreadExecutor();
	
	public static final ExecutorService SYS_AUDIT_LOG_THREAD_POOL = Executors.newCachedThreadPool();
	
	public static Hashtable<String, ExecutorService> THREAD_POOL = new Hashtable<String, ExecutorService>();
	
	public final static Hashtable<String, String> SYS_PROCESS_MSG_TBL = new Hashtable<String, String>();
	
	public static synchronized ExecutorService getThreadExecutor(String sessionId){
		if (THREAD_POOL.contains(sessionId)){
			return THREAD_POOL.get(sessionId);
		}else{
			ExecutorService newThreadPool = Executors.newSingleThreadExecutor();
			THREAD_POOL.put(sessionId, newThreadPool);
			return newThreadPool;
		}
	}
	
	public static synchronized String getProcessMsg(String sessionId){
		return SYS_PROCESS_MSG_TBL.get(sessionId);
	} 
	public static synchronized void updateProcessMsg(String sessionId, String msg){
		SYS_PROCESS_MSG_TBL.put(sessionId, msg);
	}
	public static synchronized void remvoeProcessMsg(String sessionId){
		SYS_PROCESS_MSG_TBL.remove(sessionId);
	} 

	public static synchronized void shutDownExecutors(String sessionId){
		try{
			THREAD_POOL.get(sessionId).shutdownNow();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		THREAD_POOL.remove(sessionId);
	}
	
	public static void main(String[] args){
		for (int i = 0; i < 200000; i++){
			ExecutorService s = Executors.newSingleThreadExecutor();
			s.execute(new MyThread("Test " + i));
		}
//		SYS_PROGRESS_MSG_THREAD_POOL.shutdownNow();
//		SYS_PROGRESS_MSG_THREAD_POOL.execute(new MyThread("Test ...."));
		System.out.println("Complete");
	}
}

class MyThread extends Thread{
	private String s; 
	public MyThread(String s){
		this.s = s;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(s);
		
	}
}
