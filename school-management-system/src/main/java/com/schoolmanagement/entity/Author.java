package com.schoolmanagement.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="Author",uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Author {

	@Id
	//To provide primary key generation strategy
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private int rating;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="author_book_table",
					joinColumns = {@JoinColumn(name="author_id",referencedColumnName = "id")}, //id of student entity
					inverseJoinColumns = {@JoinColumn(name="book_id",referencedColumnName = "id")}) //id of course entity
	
	private Set<Book> no_of_books;

	
	public Author() {
		super();
	}

	public Author(int id, String name, int rating, Set<Book> no_of_books) {
		super();
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.no_of_books = no_of_books;
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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Set<Book> getNo_of_books() {
		return no_of_books;
	}

	public void setNo_of_books(Set<Book> no_of_books) {
		this.no_of_books = no_of_books;
	}
	
	
	
	
	
}
