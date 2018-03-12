package tsp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Stream;

public class FileReader {


	public Queue<Node> readFile(String fileName) {

		Queue<Node> list = new LinkedList<Node>();
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach((l)->{
				Map<Integer, Double> map = new HashMap<>();
				String[] strArray = l.split(" ");
				Integer id = null;
				String name = null;
				String address = null;
				
				for(int i = 0; i < strArray.length; i++) {
					if(i == 0) {
						id = Integer.valueOf(strArray[i]);
						name = strArray[i];
						address = strArray[i];
					}else {
						map.put(i, Double.valueOf((strArray[i])));
					}
					
				}
				
				Node node = new Node(id, name, address, map);
				list.add(node);
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
		
		

	}

}
