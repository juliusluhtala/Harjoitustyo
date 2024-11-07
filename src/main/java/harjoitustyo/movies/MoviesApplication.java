package harjoitustyo.movies;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import harjoitustyo.movies.domain.AppUser;
import harjoitustyo.movies.domain.AppUserRepository;
import harjoitustyo.movies.domain.Category;
import harjoitustyo.movies.domain.CategoryRepository;
import harjoitustyo.movies.domain.Movie;
import harjoitustyo.movies.domain.MovieRepository;

@SpringBootApplication
public class MoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

	@Bean
	public CommandLineRunner movieDemo(MovieRepository movieRepository, CategoryRepository categoryRepository, AppUserRepository appUserRepository) {
		return (args) -> {
			Category category1 = new Category("Scifi");
			Category category2 = new Category("Horror");
			categoryRepository.save(category1);
			categoryRepository.save(category2);

			Movie movie1 = new Movie("Interstellar", "170min", 13, category1);
			Movie movie2 = new Movie("IT", "125min", 16, category2);
			movieRepository.save(movie1);
			movieRepository.save(movie2);

			AppUser user1 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin.admin@gmail.com", "ADMIN");
			appUserRepository.save(user1);
		};
	}
}
