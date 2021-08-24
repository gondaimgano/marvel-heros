package zw.co.marvel.marvelheros.service.hero;

import lombok.Getter;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.marvel.marvelheros.entities.Hero;
import zw.co.marvel.marvelheros.repositories.HeroRepository;
import zw.co.marvel.marvelheros.dtos.ApiException;

import java.util.List;
import java.util.Optional;




@Service
@Getter
public class HeroServiceImpl implements HeroService {
    @Autowired
  private HeroRepository heroRepository;

    public Optional<List<Hero>> fetchHeroes() throws ApiException{

        val result = heroRepository.findAll();
        if(result.isEmpty())
            throw new ApiException("The universe of heroes is empty - please create your heroes");
    return Optional.of(result);
    }

    @Override
    public Optional<Hero> saveHero(Hero item) throws  ApiException {
        if(item.getDescription() == null || item.getDescription().isEmpty() )
            throw new ApiException("You did not supply a super power description dude!");
        else if(item.getName() == null || item.getName().isEmpty() )
            throw new ApiException("You did not supply a name for your hero!");
        return Optional.of(heroRepository.save(item));
    }

    @Override
    public Optional<List<Hero>> findByName(String name) throws ApiException {
        if (name.isEmpty())
            throw new ApiException("Please give me a name to search the universe");
        val result = heroRepository.findByNameContainsIgnoreCase(name);
                if(result.isEmpty())
                    throw new ApiException("No heroes are found containing "+name);
        return Optional.of(result);
    }

    @Override
    public Optional<Hero> findById(Long id) throws ApiException {
        if(id==null || id.toString().isEmpty())
            throw  new ApiException("please supply an Id");
        val result = heroRepository.findById(id);
        if (result.isPresent())
            throw new ApiException("No hero with that ID is found");
        return result;
    }

    @Override
    public Optional<Hero> update(Hero item) throws  ApiException{
        heroRepository.save(item);

        return Optional.empty();
    }


}
