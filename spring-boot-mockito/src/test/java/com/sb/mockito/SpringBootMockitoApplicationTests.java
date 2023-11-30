package com.sb.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.sb.mockito.entity.User;
import com.sb.mockito.repository.UserRepository;
import com.sb.mockito.service.UserService;


@SpringBootTest
@RunWith(SpringRunner.class)
class SpringBootMockitoApplicationTests {

	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	public void getUsereTest() {
		when(userRepository.findAll())
		.thenReturn((List<User>) Stream.of(new User(376,"shree",26,"India"),new User(950,"Omi",26,"USA")));
		
		assertEquals(2,userService.getUsers().size());
	}

}
