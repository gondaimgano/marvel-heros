package zw.co.marvel.marvelheros.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import zw.co.marvel.marvelheros.entities.Hero;

import java.util.List;
import java.util.Optional;


public interface HeroRepository extends JpaRepository<Hero, Long> {

    Optional<Hero> findByName(String name);

    List<Hero> findByNameContainsIgnoreCase(String name);

}