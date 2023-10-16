package com.microservices.user.service.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.user.service.entity.Hotel;

import jakarta.ws.rs.Path;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {

	
	@GetMapping("/hotels/gethotel/{hotelId}")
	Hotel getHotel(@PathVariable String hotelId); 
}
