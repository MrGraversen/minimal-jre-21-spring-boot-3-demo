package io.graversen.demo.city;

import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    private final List<City> cityRepository = List.of(
            new City("Copenhagen", "55.6761", "12.5683"),
            new City("Stockholm", "59.3293", "18.0686"),
            new City("Oslo", "59.9139", "10.7522"),
            new City("Helsinki", "60.1699", "24.9384")
    );

    public Optional<City> getCity(@NonNull String name) {
        return cityRepository.stream()
                .filter(city -> city.name().equals(name))
                .findFirst();
    }

    public List<City> getCities() {
        return List.copyOf(cityRepository);
    }
}
