package com.dvlcube.app.jpa;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.HttpHeaders;

import com.dvlcube.utils.interfaces.MxBean;

/**
 * Default repository interface.
 * 
 * @see DvlJpaRepository
 * @param <T>
 * @param <ID>
 * @since 28 de fev de 2019
 * @author Ulisses Lima
 */
@NoRepositoryBean
public interface DvlRepository<T extends MxBean<?>, ID> extends PagingAndSortingRepository<T, ID> {
	public static final Integer DEFAULT_PAGE_SIZE = 20;
	public static final PageRequest DEFAULT_PAGINATION = PageRequest.of(0, DEFAULT_PAGE_SIZE);

	/**
	 * @return first page of results.
	 * @since 28 de fev de 2019
	 * @author Ulisses Lima
	 */
	List<T> firstPage();

	/**
	 * @param sorting
	 * @return first page of results.
	 * @since 2019-05-31
	 * @author Ulisses Lima
	 */
	List<T> firstPage(Sort sorting);

	/**
	 * @param number
	 * @return page n
	 * @since 4 de mar de 2019
	 * @author Ulisses Lima
	 */
	List<T> page(int number);

	/**
	 * @param number
	 * @param sorting
	 * @return page n
	 * @since 31 de mai de 2019
	 * @author Ulisses Lima
	 */
	List<T> page(int number, Sort sorting);

	/**
	 * @param newT
	 * @return newT or findById(newT.id)
	 * @since 22 de mar de 2019
	 * @author Ulisses Lima
	 */
	T findOr(T newT);

	/**
	 * @param newT
	 * @param consumer
	 * @return newT or findById(newT.id)
	 * @since 22 de mar de 2019
	 * @author Ulisses Lima
	 */
	T findOr(T newT, Consumer<T> consumer);

	/**
	 * Based on https://materializecss.com/autocomplete.html
	 * 
	 * @param search
	 * @return autocomplete map
	 * @since 2 de abr de 2019
	 * @author Ulisses Lima
	 */
	Map<String, String> autocomplete(String search);

	/**
	 * @param search
	 * @param test
	 * @return autocomplete map
	 * @since 2 de abr de 2019
	 * @author Ulisses Lima
	 */
	Map<String, String> autocomplete(String search, Predicate<T> test);

	/**
	 * @return all. sorted by id.
	 * @since 5 de abr de 2019
	 * @author Ulisses Lima
	 */
	List<T> findAll();

	/**
	 * @param headers
	 * @param params
	 * @return entities matching headers and request query string
	 * @since 7 de abr de 2019
	 * @author Ulisses Lima
	 */
	List<T> findAllByToggles(HttpHeaders headers, Map<String, String> params);

	/**
	 * @param params
	 * @return list
	 * @since 16 de abr de 2019
	 * @author Ulisses Lima
	 */
	List<T> findAllBy(Map<String, String> params);

	/**
	 * @param key bean property
	 * @param val property value. the type will be guessed
	 * @return items found
	 * @since 16 de abr de 2019
	 * @author Ulisses Lima
	 */
	List<T> findAllBy(String key, String val);

	/**
	 * @param params
	 * @param group  optional
	 * @return results
	 * @since 17 de abr de 2019
	 * @author Ulisses Lima
	 */
	List<T> findAllBy(Map<String, String> params, String group);

	/**
	 * @param id
	 * @return List<T> with id like id
	 * @since 29 de abr de 2019
	 * @author Ulisses Lima
	 */
	List<T> findAllLike(String id);
}
