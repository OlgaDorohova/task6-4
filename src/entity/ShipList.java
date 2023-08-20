package entity;

public class ShipList {
	private Ship[] ships;
	private int size;
	private int amount;

	public ShipList() {
		this.size = 10;
		this.amount = 0;
		ships = new Ship[size];
	}

	public int length() {
		return size;

	}

	public ShipList(int size) {
		this.size = size;
		this.amount = 0;
		ships = new Ship[size];

	}

	public synchronized Ship getShip() {
		amount--;
		if (amount >= 0) {
			Ship ship = ships[amount];
			ships[amount] = null;

			return ship;
		}
		return null;
	}


	public boolean addShip(Ship ship) {
		if (ship == null || amount >= size) {
			return false;
		}

		ships[amount] = ship;
		amount++;
		return true;
	}

	public void showList() {
		for (Ship ship : ships) {
			System.out.println(ship);
		}
	}
}
