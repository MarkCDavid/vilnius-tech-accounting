package vilnius.tech.utils.controls;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import org.hibernate.Session;
import vilnius.tech.hibernate.City;
import vilnius.tech.hibernate.Country;
import vilnius.tech.hibernate.service.CityService;
import vilnius.tech.hibernate.service.CountryService;
import vilnius.tech.utils.ChoiceBoxUtils;
import vilnius.tech.validation.Validator;
import vilnius.tech.validation.validators.CountryCityValidation;

public class CityCountryChoiceBoxPair {

    public CityCountryChoiceBoxPair(Session session, ChoiceBox<Country> countries, ChoiceBox<City> cities, Validator validator) {
        this.countries = countries;
        this.cities = cities;

        this.countryController = new CountryService(session);
        this.cityController = new CityService(session);

        this.countries.setItems(FXCollections.observableArrayList(countryController.find()));
        ChoiceBoxUtils.OnSelectionChanged(countries, this::onCountryChanged);

        validator.register(1, new CountryCityValidation(this.countries, this.cities));
    }


    private void onCountryChanged(ChoiceBox<Country> choiceBox, Country country) {
        cities.setItems(FXCollections.observableArrayList(cityController.find_Country(country)));
    }

    private final ChoiceBox<City> cities;
    private final ChoiceBox<Country> countries;

    private final CityService cityController;
    private final CountryService countryController;
}
