package com.dvlcube.app.manager.data;

import javax.persistence.*;

import com.dvlcube.utils.interfaces.MxBean;
import com.dvlcube.utils.interfaces.Nameable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

/**
 * @since 31 de outubro de 2020
 * @author Vitor Henrique
 */
@Entity
@Getter
@Setter
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
@EqualsAndHashCode(of = {"id"})
public class JobBean implements MxBean<Long>, Nameable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private Integer max = 0;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date dateCreated = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date lastUpdated = new Date();

	@Version
	private Long version = 0L;

	public static String[] ignoreProperties = {"id", "dateCreated", "lastUpdated", "version"};
}
