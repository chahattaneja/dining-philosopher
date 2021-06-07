package com.ct;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(Constants.NUMBERS_OF_PHILOSOPHERS);
		PhiloSopher[] philosophers = new PhiloSopher[Constants.NUMBERS_OF_PHILOSOPHERS];
		Chopstick[] chopsticks = new Chopstick[Constants.NUMBERS_OF_CHOPSTICKS];
		try {
			for(int i=0; i<Constants.NUMBERS_OF_CHOPSTICKS; i++) {
				chopsticks[i] = new Chopstick(i);
			}
			
			for(int i=0; i<Constants.NUMBERS_OF_PHILOSOPHERS; i++) {
				philosophers[i] = new PhiloSopher(i, chopsticks[i], chopsticks[(i+1)%Constants.NUMBERS_OF_CHOPSTICKS]);
				executor.execute(philosophers[i]);
			}
			
			Thread.sleep(100000);
					
			for(PhiloSopher phil: philosophers) {
				phil.setFull(true);
			}
		} finally {
			executor.shutdown();
			while(!executor.isTerminated())
				Thread.sleep(1000);
			for(PhiloSopher phil: philosophers) {
				System.out.println("Philosphor" + phil + " has eaten " + phil.getEatingCounter() + " times");
			}
		}
	}
}
