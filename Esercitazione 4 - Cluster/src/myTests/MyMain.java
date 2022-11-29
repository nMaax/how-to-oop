package myTests;

import java.util.ArrayList;
import java.util.Collections;

import cluster.myMethods.*;

public class MyMain {

	public static void main(String[] args) {
		ArrayList<Timestamp> lista = new ArrayList<Timestamp>();
		lista.add(new Timestamp("20030310 13:30:30"));
		lista.add(new Timestamp("20030310 12:25:30"));
		lista.add(new Timestamp("20030310 12:30:35"));
		lista.add(new Timestamp("20030310 12:30:10"));
		System.out.println(lista);
		Collections.sort(lista);
		System.out.println(lista);
		
	}

}
