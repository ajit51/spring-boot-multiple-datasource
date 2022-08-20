package com.multiple.ds.api;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multiple.ds.api.book.repository.BookRepository;
import com.multiple.ds.api.model.book.Book;
import com.multiple.ds.api.model.user.User;
import com.multiple.ds.api.user.repository.UserRepository;

@SpringBootApplication
@RestController
public class SpringBootMultipleDatasourceApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;

	@PostConstruct
	public void addData2Db() {
		userRepository.saveAll(Stream.of(new User(744, "John"), new User(455, "Pitter")).collect(Collectors.toList()));

		bookRepository.saveAll(
				Stream.of(new Book(111, "Core Java"), new Book(222, "Spring Boot")).collect(Collectors.toList()));
	}

	@GetMapping("/getUser")
	public List<User> getUser() {
		return userRepository.findAll();
	}

	@GetMapping("/getBook")
	public List<Book> getBook() {
		return bookRepository.findAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMultipleDatasourceApplication.class, args);
	}

}
