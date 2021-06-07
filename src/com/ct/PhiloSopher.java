package com.ct;

import java.util.Random;

public class PhiloSopher implements Runnable{
	private int id;
	private Chopstick leftChopstick;
	private Chopstick rightChopstick;
	private int eatingCounter;
	private Random random;
	private volatile boolean isFull;
	
	public PhiloSopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
		this.id = id;
		this.leftChopstick = leftChopstick;
		this.rightChopstick = rightChopstick;
		random = new Random(); 
	}

	@Override
	public void run() {
		try {
			while(!isFull) {
				think();
				if(leftChopstick.pickUp()) {
					System.out.println(this + " picked up left " + leftChopstick);
					if(rightChopstick.pickUp()) {
						System.out.println(this + " picked up right " + rightChopstick);
						eat();
						rightChopstick.putDown();
						System.out.println(this + " put down left " + rightChopstick);
					}  
					leftChopstick.putDown();
					System.out.println(this + " put down left " + leftChopstick);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void think() throws InterruptedException{
		System.out.println(this + " is thinking");
		Thread.sleep(random.nextInt(1000));
	}
	
	private void eat() throws InterruptedException{
		System.out.println(this + " is eating");
		eatingCounter++;
		Thread.sleep(Constants.SIMULATION_RUNNIG_TIME);
	}
	
	public void setFull(boolean isFool) {
		this.isFull = isFool;
	}

	@Override
	public String toString() {
		return "PhiloSopher " + id;
	}

	public int getEatingCounter() {
		return eatingCounter;
	}
	
	
}

