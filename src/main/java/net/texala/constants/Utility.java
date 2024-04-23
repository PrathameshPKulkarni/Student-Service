package net.texala.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import io.micrometer.common.util.StringUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utility {

	public static String getContainsLikePattern(String searchTerm) {
		if (StringUtils.isEmpty(searchTerm)) {
			return "%";
		} else {
			 return "%"+searchTerm.toLowerCase()+"%";
		}
	}

	public static Map<String, String> prepareFilterByMap(String filterby) {
		Map<String, String> filterByMaps = new HashMap<>();
		String[] arr = filterby.split(";");
		for (String key : arr) {
			String[] temparr = key.split(":", 2);
			filterByMaps.put(temparr[0], temparr[1]);
		}
		return filterByMaps;
	}

	public static List<Order> sortByValues(String sortBy) {
		List<Order> filterByMaps = new ArrayList<>();
		String[] arr = sortBy.split(";");
		for (String key : arr) {
			String[] temparr = key.split(":", 2);
			final Direction direction = temparr[1].equalsIgnoreCase("desc") ? Direction.DESC : Direction.ASC;
			filterByMaps.add(new Order(direction, temparr[0]));
		}
		return filterByMaps;
	}
}
