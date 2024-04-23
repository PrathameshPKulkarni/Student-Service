package net.texala.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import net.texala.enums.Status;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", length = 4, nullable = false, updatable = false)
	private int id;

	@Column(name = "name", length = 25, nullable = true)
	private String name;

	@Column(name = "address", length = 50, nullable = true)
	private String address;

	@Column(name = "salary", precision = 20, scale = 2, nullable = true)
	private double salary;

	@Column(name = "status", length = 15, nullable = true)
	@Enumerated(EnumType.STRING)
	public Status status;

}
