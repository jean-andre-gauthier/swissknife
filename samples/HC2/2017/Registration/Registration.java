import java.lang.*;
import java.util.*;

class Registration {
		// static class City {
		//         String name;
		//         String distance
		public static void main(String[] args) {
				Map<String, Map<String, Integer>> cityNeighbours = new HashMap<>();
				Scanner in = new Scanner(System.in);
				while (in.hasNextLine()) {
						String line = in.nextLine();
						String[] citiesDistance = line.split(":");
						Integer distance = Integer.parseInt(citiesDistance[1].trim());
						String[] city1city2 = citiesDistance[0].split("->");
						city1city2[0] = city1city2[0].trim();
						city1city2[1] = city1city2[1].trim();
						/* System.out.println(city1city2[0] + " " + city1city2[1]); */
						if (!cityNeighbours.containsKey(city1city2[0]))
								cityNeighbours.put(city1city2[0], new HashMap<>());
						Map<String, Integer> neighbours = cityNeighbours.get(city1city2[0]);
						if (!neighbours.containsKey(city1city2[1]) || neighbours.get(city1city2[1]) > distance)
								neighbours.put(city1city2[1], distance);
				}
				String lastCity = null;
				String currentCity = "Lausanne";
				Set<String> visitedCities = new HashSet<>();
				int cash = 200;
				while (cash >= 0) {
						System.out.println(currentCity);
						System.out.println(cash);
						String cheapestNextCity = null;
						int cheapestNextRide = Integer.MAX_VALUE;
						/* System.out.println(lastCity); */
						for (Map.Entry<String, Integer> neighbour: cityNeighbours.get(currentCity).entrySet()) {
								/* System.out.println(neighbour.getKey()); */
								/* System.out.println(neighbour.getValue()); */
								if (neighbour.getValue() < cheapestNextRide) {// && !visitedCities.contains(neighbour.getKey())) {
										cheapestNextCity = neighbour.getKey();
										cheapestNextRide = neighbour.getValue();
								}
						}
						visitedCities.add(currentCity);
						lastCity = currentCity;
						currentCity = cheapestNextCity;
						cash -= cheapestNextRide;
				}
				System.out.println(lastCity);
		}
}
