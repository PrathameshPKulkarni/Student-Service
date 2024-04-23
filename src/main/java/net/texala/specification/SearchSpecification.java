package net.texala.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import net.texala.constants.Utility;
import net.texala.model.Student;

public class SearchSpecification {

	public static Specification<Student> searchStudent(String filterby, String searchtext) {

		return (root, query, cb) -> {
			String containsLikePattern = Utility.getContainsLikePattern(searchtext);
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotBlank(filterby)) {

				Map<String, String> filterByMap = Utility.prepareFilterByMap(filterby);
				for (Map.Entry<String, String> filterByEntry : filterByMap.entrySet()) {
					predicates.add(cb.equal(root.<String>get(filterByEntry.getKey()).as(String.class),
							filterByEntry.getValue()));
				}
			}
			predicates.add(cb.or(cb.like(cb.lower(root.<String>get("name")), containsLikePattern),
					cb.like(cb.lower(root.<String>get("address")), containsLikePattern),
					cb.like(cb.lower(root.<String>get("salary").as(String.class)), containsLikePattern),
					cb.like(cb.lower(root.<String>get("status").as(String.class)), containsLikePattern)));

			return cb.and(predicates.toArray(new Predicate[0]));

		};
	}
}
