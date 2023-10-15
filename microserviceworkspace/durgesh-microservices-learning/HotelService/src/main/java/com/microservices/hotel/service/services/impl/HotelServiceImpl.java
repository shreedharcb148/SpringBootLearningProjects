package com.microservices.hotel.service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.hotel.service.entity.Hotel;
import com.microservices.hotel.service.repository.HotelRepository;
import com.microservices.hotel.service.services.HotelService;
import com.microservices.user.service.exceptions.ResourceNotFoundException;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	private HotelRepository  hotelRepository;
	
	@Override
	public Hotel create(Hotel hotel) {
		
		String hotlId = UUID.randomUUID().toString();
		
		hotel.setId(hotlId);
		// TODO Auto-generated method stub
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotels() {
		// TODO Auto-generated method stub
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotel(String id) {
		// TODO Auto-generated method stub
		return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel with given id is not found ...!!! : "+id));
	}

}
