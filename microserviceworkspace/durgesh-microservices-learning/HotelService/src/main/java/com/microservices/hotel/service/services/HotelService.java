package com.microservices.hotel.service.services;

import java.util.List;

import com.microservices.hotel.service.entity.Hotel;

public interface HotelService {

	Hotel create(Hotel hotel);
	
	List<Hotel> getAllHotels();
	
	Hotel getHotel(String id);
}
