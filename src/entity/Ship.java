package entity;

public class Ship {
	private String name;
	private int cargoLimit;
	private int currentCargo;
	private boolean isEmpty;

	public Ship(String name) {
		this.name = name;
		cargoLimit = 10;
		currentCargo = 0;
		isEmpty = true;
	}

	public Ship(String name, int cargoLimit, int currentCargo) {
		this.name = name;
		this.cargoLimit = cargoLimit;
		this.currentCargo = currentCargo;
		isEmpty = (currentCargo == 0) ? true : false;
	}

	public boolean getEmpty() {
		return isEmpty;
	}

	public synchronized int getCurrentCargo() {
		return this.currentCargo;
	}

	public synchronized int getCargoLimit() {
		return this.cargoLimit;
	}

	public void setEmpty() {
		this.isEmpty = (currentCargo == 0) ? true : false;

	}

	public void loading() {
		while (currentCargo < cargoLimit) {
			currentCargo++;
			System.out.print(name + " cargo: " + currentCargo + ";\t");
		}
		System.out.println();
		setEmpty();
	}

	public void unloading() {
		while (currentCargo > 0) {
			currentCargo--;
			System.out.print(name + " cargo: " + currentCargo + ";\t");

		}
		System.out.println();
		setEmpty();

	}

	@Override
	public String toString() {
		return "Ship [name=" + name + ", cargoLimit=" + cargoLimit + ", currentCargo=" + currentCargo + "]";
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
