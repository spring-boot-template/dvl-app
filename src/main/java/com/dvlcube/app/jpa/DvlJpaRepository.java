package com.dvlcube.app.jpa;

import static com.dvlcube.utils.query.MxQuery.$;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;

import com.dvlcube.utils.MxStringUtils;
import com.dvlcube.utils.annotations.sql.Like;
import com.dvlcube.utils.interfaces.CompositeId;
import com.dvlcube.utils.interfaces.MxBean;

/**
 * @since 28 de fev de 2019
 * @author Ulisses Lima
 */
@SuppressWarnings("unchecked")
public class DvlJpaRepository<T extends MxBean<? extends Serializable>, ID extends Serializable>
		extends SimpleJpaRepository<T, ID> implements DvlRepository<T, ID> {
	public static String DVL_TOGGLE_HEADER_PREFIX = "dvl-toggle";

	private Logger log = LogManager.getLogger(this.getClass());
	private final EntityManager em;
	private final JpaEntityInformation<T, ID> ei;

	/**
	 * @param ei
	 * @param em
	 * @since 7 de abr de 2019
	 */
	public DvlJpaRepository(JpaEntityInformation<T, ID> ei, EntityManager em) {
		super(ei, em);
		this.em = em;
		this.ei = ei;
	}

	public void noop() {
		System.out.println(em);
	}

	@Override
	public List<T> firstPage(Sort sorting) {
		return page(0, sorting);
	}

	@Override
	public List<T> firstPage() {
		return page(0, null);
	}

	@Transactional
	public T findOr(T newT) {
		return findOr(newT, null);
	}

	@Transactional
	public T findOr(T newT, Consumer<T> consumer) {
		if (newT == null)
			return null;

		if (consumer != null)
			consumer.accept(newT);

		return findById((ID) newT.getId()).orElse(save(newT));
	}

	@Override
	public List<T> page(int number) {
		return page(number, null);
	}

	//Esse é o endpoint do @RequestMapping /skills
	@Override
	public List<T> page(int number, Sort sorting) {
		if (sorting == null) {
			sorting = Sort.by("name");
		}
		return findAll(PageRequest.of(number, DEFAULT_PAGE_SIZE, sorting)).getContent();
	}

	@Override
	public Map<String, String> autocomplete(String search) {
		return autocomplete(search, null);
	}

	@Override
	public Map<String, String> autocomplete(String search, Predicate<T> test) {
		// TODO implement generic 'id like' query
		// FIXME @performance(ulisses) não recomendado pra tabelas grandes
		List<T> all = findAll(Sort.by("id").descending());

		// map baseado no autocomplete do materialize
		HashMap<String, String> map = new HashMap<>();
		for (T i : all) {
			if (search == null || MxStringUtils.containsIgnoreCase(i.autocompleteField(), search)) {
				if (test == null || test.test(i))
					map.put(i.autocompleteField(), null);
			}
		}
		return map;
	}

	@Override
	public List<T> findAll() {
		return findAll(Sort.by("id").descending());
	}

	@Override
	public List<T> findAllByToggles(HttpHeaders headers, Map<String, String> params) {
		Class<T> type = ei.getJavaType();
		final String togglePrefix = DVL_TOGGLE_HEADER_PREFIX + "-" + $(type).shortBeanName() + ".";
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<T> q = cb.createQuery(type);
		Root<T> props = q.from(type);

		List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
		headers.forEach((k, v) -> {
			if (!k.startsWith(togglePrefix) || !v.iterator().hasNext())
				return;

			String key = k.replace(togglePrefix, "");
			String value = v.iterator().next();
			predicates.add(cb.equal(props.get(key), Boolean.valueOf(value)));
		});

		q.where(cb.and(predicates.toArray(new javax.persistence.criteria.Predicate[predicates.size()])));
		q.orderBy(cb.desc(props.get("id")));

		log.trace(q);
		return em.createQuery(q.select(props)).getResultList();
	}

	@Override
	public List<T> findAllBy(Map<String, String> params) {
		return findAllBy(params, null);
	}

	@Override
	public List<T> findAllLike(String name) {
		if ($(name).isBlank())
			return firstPage();

		name = name.toLowerCase();

		String idParam = "name";

		Like like = ei.getJavaType().getAnnotation(Like.class);
		if (like != null) {
			idParam = like.value();
		} else {
			T testInstance = null;
			try {
				testInstance = ei.getJavaType().getDeclaredConstructor().newInstance();
			} catch (Throwable e) {
				e.printStackTrace();
			}

			if (testInstance instanceof CompositeId)
				idParam += ".name";
		}

		return em.createQuery(//
				"SELECT e FROM " + ei.getEntityName() //
						+ " e WHERE lower(" + idParam + ") LIKE :name") //
				.setParameter("name", "%" + name + "%") //
				.setMaxResults(DEFAULT_PAGE_SIZE) //
				.getResultList();
	}

	//Esse é o endpoint @GetMapping("/filtered")
	@Override
	public List<T> findAllBy(Map<String, String> params, String group) {
		int lidvl = Optional.ofNullable($(params.remove("lidvl")).i()).orElse(DEFAULT_PAGE_SIZE * 5);

		Class<T> type = ei.getJavaType();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<T> q = cb.createQuery(type);
		Root<T> props = q.from(type);

		List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
		params.forEach((key, val) -> {
			String[] path = key.split("\\.");
			Path<Object> x = props.get(path[0]);
			if (path.length > 1) {
				for (int i = 1; i < path.length; i++) {
					x = x.get(path[i]);
				}
			}
			predicates.add(cb.equal(x, $(val).guess()));
		});

		q.where(cb.and(predicates.toArray(new javax.persistence.criteria.Predicate[predicates.size()])));

		// TODO not working
		if (group != null && !"null".equals(group)) {
			String[] groupings = group.split(",");
			for (String grouping : groupings) {
				String[] path = grouping.split("\\.");
				Path<Object> y = props.get(path[0]);
				if (path.length > 1) {
					for (int i = 1; i < path.length; i++) {
						y = y.get(path[i]);
					}
				}
				q.groupBy(y);
			}
		}

		q.orderBy(cb.desc(props.get("id")));

		// TODO
		// q.multiselect(cb.count(props), props.get("action"));

		log.trace(q);
		// TODO @performance(ulisses) handle lidvl/pagination
		return em.createQuery(q.select(props)).setMaxResults(lidvl).getResultList();
	}

	@Override
	public List<T> findAllBy(String key, String val) {
		HashMap<String, String> map = new HashMap<>();
		map.put(key, val);
		return findAllBy(map);
	}
}
