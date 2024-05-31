package com.learn.LinkedList;

public class LinkedList {

	Node head;
	
	void insert(int data) {
		
		Node newNode = new Node(data, null);
		
		if(head == null) {
			System.out.println("head" + data);
			head = newNode;
		}else {
			System.out.println(" not head" + data);
			Node curr = head;
			while(curr.next != null) {
				curr = curr.next;
			}
			curr.next = newNode;
		}
	}
	void display() {
		Node node = head;
		while(node.next !=null) {
			System.out.println(node.data);
			node = node.next;
		}
	}
}
