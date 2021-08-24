package zw.co.marvel.marvelheros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.marvel.marvelheros.entities.Hero;

import java.util.List;
import java.util.Optional;


public interface HeroRepository extends JpaRepository<Hero, Long> {

    Optional<Hero> findByName(String name);

    List<Hero> findByNameContainsIgnoreCase(String name);

}