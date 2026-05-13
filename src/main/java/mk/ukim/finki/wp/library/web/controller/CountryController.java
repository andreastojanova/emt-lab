package mk.ukim.finki.wp.library.web.controller;


import mk.ukim.finki.wp.library.model.domain.Author;
import mk.ukim.finki.wp.library.model.domain.Country;
import mk.ukim.finki.wp.library.model.dto.AuthorDto;
import mk.ukim.finki.wp.library.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> findAll() {
        return this.countryService.findAll();
    }

    @GetMapping("/{id}")
    public Country findById(@PathVariable Long id) {
        return this.countryService.findById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Country> save(@RequestParam String name,@RequestParam String continent) {
        return ResponseEntity.ok(this.countryService.save(name, continent));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Country> edit(@PathVariable Long id, @RequestParam String name,@RequestParam String continent) {
        return ResponseEntity.ok(this.countryService.edit(id,name,continent));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.countryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
