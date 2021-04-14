package com.dvlcube.app.model;

import javax.persistence.*;

import com.dvlcube.utils.BaseEntity;
import com.dvlcube.utils.interfaces.MxBean;
import com.dvlcube.utils.interfaces.Nameable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * @since 3 de jun de 2019
 * @author Ulisses Lima
 */
@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "bagherobean")
public class BagHero implements MxBean<Long>, Nameable, BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private Integer amount;

	@ManyToOne
	@JoinColumn(name = "owner_id", referencedColumnName = "id")
	private User owner;

	@ManyToOne
	@JoinColumn(name = "herobean", referencedColumnName = "id")
	private Hero hero;

	@ManyToOne
	@JoinColumn(name = "bagbean", referencedColumnName = "id")
	private Bag bag;

	@ManyToOne
	@JoinColumn(name = "fusionbean", referencedColumnName = "id")
	private Fusion fusion;

	@Override
	public String getName() {
		if (hero == null)
			return null;
		return hero.getName();
	}
}
