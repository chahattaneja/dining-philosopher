package com.ct;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
	private int id;
	private Lock lock;
	
	public Chopstick(int id) {
		super();
		this.id = id;
		this.lock = new ReentrantLock();
	}

	public boolean pickUp() {
		try {
			return lock.tryLock(10, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void putDown() {
		lock.unlock();
	}

	@Override
	public String toString() {
		return "Chopstick " + id;
	}
	
}