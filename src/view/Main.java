package view;

import java.util.concurrent.Semaphore;

import controller.DishController;

public class Main {

	public static void main(String[] args) {
		
		final int DISHES_AMOUNT = 5;
		
		DishController[] dishes = new DishController[DISHES_AMOUNT];
		
		Semaphore mutex = new Semaphore(1);
		
		for(int i = 0; i < DISHES_AMOUNT; i++) 
			dishes[i] = new DishController(i+1,mutex);
		
		for(int i = 0; i < DISHES_AMOUNT; i++) 
			dishes[i].start();
	}

}
