package net.codejava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 45)
	private String email;
	
	@Column(nullable = false, length = 64)
	private String password;
	
	@Column(name = "first_name", nullable = false, length = 20)
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 20)
	private String lastName;
	
	@Column(name = "verification_code", length = 64)
	private String verificationCode;

	private boolean enabled;
	/*
		This role can be on mulitple users
	 */
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;



	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}


}
