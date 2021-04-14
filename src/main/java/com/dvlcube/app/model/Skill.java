package com.dvlcube.app.model;

import javax.persistence.*;

import com.dvlcube.utils.BaseEntity;
import com.dvlcube.utils.interfaces.MxBean;
import com.dvlcube.utils.interfaces.Nameable;
import com.dvlcube.utils.interfaces.Presentable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

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
@Table(name = "skillbean",uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
public class Skill implements Nameable, MxBean<Long>, Presentable, BaseEntity<Long> {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	private String pic;

//	@ManyToMany
//	private List<Hero> heroes;
}
