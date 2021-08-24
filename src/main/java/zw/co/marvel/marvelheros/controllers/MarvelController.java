package zw.co.marvel.marvelheros.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.marvel.marvelheros.entities.Hero;
import zw.co.marvel.marvelheros.response.ApiResponse;
import zw.co.marvel.marvelheros.service.hero.HeroService;

import java.util.List;


@RestController
@RequestMapping("/marvel")
public class MarvelController extends  BaseController{
    @Autowired
    HeroService heroService;

    @GetMapping("/heroes")
    public ResponseEntity<ApiResponse<List<Hero>>> fetchAllHeroes() {
       return tryToExecute(()-> invokeService(heroService.fetchHeroes(),"Success!","No records found"));
    }
    @GetMapping("/heroes/name/{name}")
    public ResponseEntity<ApiResponse<List<Hero>>> fetchName(
            @PathVariable
                    String name) {

      return tryToExecute(()-> invokeService(heroService.findByName(name),"Success person is found!",name+" is not found"));

    }


    @GetMapping("/heroes/id/{id}")
    public ResponseEntity<ApiResponse<Hero>> fetchById(
            @PathVariable
                    Long id) {

        return tryToExecute(()-> invokeService(heroService.findById(id),"Success person is found!",id+" is not found"));

    }
    @PutMapping("/heroes")
    public ResponseEntity<ApiResponse<Hero>> updateHero(
            @RequestBody
                    Hero hero)  {
   return tryToExecute(()-> invokeService(heroService.update(hero),"Success","unable to update"));

    }
    @PostMapping("/heroes")
    public ResponseEntity<ApiResponse<Hero>> saveHero(
            @RequestBody
                    Hero hero)  {


            return tryToExecute(()-> invokeService(heroService.addHero(hero), "Hero is saved!", "unable to save!"));


    }


}
