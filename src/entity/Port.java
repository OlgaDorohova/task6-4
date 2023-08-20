package entity;

public class Port {
	private int dockNums;
	private int maxWeight;
	private int currentWeight = 0;
	private ShipList ships;

	public Port(ShipList ships) {
		dockNums = 2;
		maxWeight = 50;
		this.ships = ships;
	}

	public Port(int dockNums, int maxWeight, ShipList ships) {
		this.dockNums = dockNums;
		this.maxWeight = maxWeight;
		this.ships = ships;
	}

	public void start() {
		for (int i = 0; i < dockNums; i++) {
			Dock dock = new Dock("dock" + i);
			dock.start();

		}
	}

	public synchronized int getCurrentWeight() {
		return currentWeight;
	}

	public synchronized void setCurrentWeight(int currentWeight) {
		this.currentWeight = currentWeight;
	}

	public synchronized void increaseCurrentWeight(int weigth) {
		currentWeight += weigth;
	}

	public synchronized void decreaseCurrentWeight(int weight) {
		currentWeight -= weight;
	}

	class Dock extends Thread {
		private boolean isFree;
		private Ship ship;
		private boolean isActive = true;

		public Dock(String name) {
			super(name);
			isFree = true;
		}

		public void run() {
			while (isActive) {
				if (isFree) {

					getShip();
//					}
					if (ship == null || ships.length() == 0 || ships == null) {
						isActive = false;
						System.out.println(getName() + " finish");
						return;
					}
					isFree = false;
					System.out.println(getName() + " is busy with " + ship.getName());
					if (ship.getEmpty()) {
						System.out.println("loading " + ship.getName() + "...");
						load();
						System.out.println(getName() + " is free");
					} else {
						System.out.println("unloading " + ship.getName() + "...");
						unload();
						System.out.println(getName() + " is free");

					}
				}

			}
		}

		public synchronized void getShip() {
			this.ship = ships.getShip();
		}

		public void load() {
			if (currentWeight - ship.getCargoLimit() < 0) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			ship.loading();
			System.out.println("loading " + ship.getName() + "...");
			decreaseCurrentWeight(ship.getCargoLimit());
			isFree = true;
			this.ship = null;
			System.out.println(getName() + " is free");

		}

		public void unload() {

			if (currentWeight + ship.getCargoLimit() > maxWeight) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			ship.unloading();
			increaseCurrentWeight(ship.getCargoLimit());
			isFree = true;
			this.ship = null;
		}

	}
}
