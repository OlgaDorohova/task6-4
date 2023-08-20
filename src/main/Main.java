package main;

import entity.Port;
import entity.Ship;
import entity.ShipList;

//Задание 4. Многопоточность. Порт . Корабли заходят в порт для 
//разгрузки/загрузки контейнеров. Число контейнеров, находящихся в текущий момент 
//в порту и на корабле, должно быть неотрицательным и превышающим заданную 
//грузоподъемность судна и вместимость порта. В порту работает несколько причалов. 
//У одного причала может стоять один корабль. Корабль может загружаться у причала 
//или разгружаться

public class Main {
	public static void main(String[] args) {
		ShipList list = new ShipList(5);
		Ship ship1 = new Ship("first", 10, 0);
		Ship ship2 = new Ship("second", 10, 10);
		Ship ship3 = new Ship("third", 5, 5);
		Ship ship4 = new Ship("fourth", 3, 3);
		Ship ship5 = new Ship("fifth", 8, 0);

		System.out.println(list.addShip(ship1));
		System.out.println(list.addShip(ship2));
		System.out.println(list.addShip(ship3));
		System.out.println(list.addShip(ship4));
		System.out.println(list.addShip(ship5));
//		System.out.println(list.addShip(new Ship("sixth", 3, 3)));
		list.showList();

		Port port = new Port(list);

		port.start();

	}
}
