package com.pujanov.util;


import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckApp extends Thread {
	static String appId = "googleSearchClient";

	public static boolean isRunning() {
		boolean alreadyRunning = false;
		try {
			JUnique.acquireLock(appId);
			alreadyRunning = false;
		} catch (AlreadyLockedException e) {
			log.error("Unable to acquire lock. There is an instance already running", e);
			alreadyRunning = true;
		} catch (Throwable t) {
			log.error("Unable to acquire lock. ", t);
		}
		return alreadyRunning;
	}

	public static void release() {
		try {
			JUnique.releaseLock(appId);
		} catch (Throwable t) {
			log.error("Error releasing the lock", t);
		}
	}
}
