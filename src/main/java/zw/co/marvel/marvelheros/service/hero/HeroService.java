package zw.co.marvel.marvelheros.service.hero;

import zw.co.marvel.marvelheros.entities.Hero;
import zw.co.marvel.marvelheros.dtos.ApiException;

import java.util.List;
import java.util.Optional;

public interface HeroService {
    Optional<List<Hero>> fetchHeroes() throws ApiException;
    Optional<Hero> addHero(Hero item) throws ApiException;
    Optional<List<Hero>> findByName(String name) throws ApiException;
    Optional<Hero> findById(Long id) throws  ApiException;
    Optional<Hero> update(Hero item) throws  ApiException;
}
