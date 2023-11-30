package com.sb.mockito.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

	public User(int i, String string, int j, String string2) {
		// TODO Auto-generated constructor stub
	}
	@Id
	private int id;
	private String name;
	private int age;
	private String address;
	
	
}
