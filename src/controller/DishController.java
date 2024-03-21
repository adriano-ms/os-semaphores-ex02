package controller;

import java.util.concurrent.Semaphore;

public class DishController extends Thread {

	private int id;
	private String name;
	private Semaphore mutex;
	private long minTime;
	private long maxTime;

	public DishController() {
		super();
	}

	public DishController(int id, Semaphore mutex) {
		this.id = id;
		this.mutex = mutex;
		if (id % 2 == 0) {
			this.name = "Onion soup";
			this.minTime = 500;
			maxTime = 800;
		} else {
			this.name = "Lasagna Bolognese";
			this.minTime = 600;
			this.maxTime = 1200;
		}
	}
	
	@Override
	public void run() {
		try {
			cook();
			delivey();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void cook() throws InterruptedException {
		System.out.println(this + ": cooking started");
		double time = rollTime();
		double progress = 0;
		while(progress < time) {
			System.out.printf("%s: %.0f%% complete\n", this, ((progress/time)*100));
			sleep(100);
			progress += 100;
		}
		System.out.println(this + ": completed!");
	}
	
	private long rollTime() {
		return (long) (Math.random() * (maxTime - minTime) + minTime);
		
	}
	
	private void delivey() throws InterruptedException {
		mutex.acquire();
		System.out.println(this + ": Out for delivery");
		sleep(500);
		System.out.println(this + ": Delivered!");
		mutex.release();
	}

	@Override
	public String toString() {
		return name + "(" + id + ")";
	}
}
