package com.schoolmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="Book",uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Book {

	@Id
	//To provide primary key generation strategy
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private int prize;
	
	@ManyToOne()
	private Author author;
	
	public Book() {
		super();
	}

	public Book(int id, String name, Author author, int prize) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.prize = prize;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public int getPrize() {
		return prize;
	}

	public void setPrize(int prize) {
		this.prize = prize;
	}
	
	
	
	
	
	
}
