package springlogin.services;

import springlogin.repositories.CountryRepository;
import springlogin.repositories.CovidVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    CovidVariantRepository covidVariantRepository;
    CountryRepository countryRepository;

    @Autowired
    public DataLoader(CovidVariantRepository covidVariantRepository,CountryRepository countryRepository) {
        this.covidVariantRepository = covidVariantRepository;
        this.countryRepository = countryRepository;
        loadXml();
        loadHtml();
    }

    private void loadXml() {
        // parse xml into array of Country and CovidVariant objects
        // load these arrays into repositories

    }
    private void loadHtml() {
        // parse html into array of Country and CovidVariant objects
        // load these arrays into repositories

    }
}