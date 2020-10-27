package vilnius.tech.utils.controls;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import vilnius.tech.dal.City;
import vilnius.tech.dal.Country;
import vilnius.tech.session.Session;
import vilnius.tech.utils.ChoiceBoxUtils;
import vilnius.tech.validation.Validation;
import vilnius.tech.validation.Validator;
import vilnius.tech.validation.validators.CountryCityValidation;

public class CityCountryChoiceBoxPair {

    public CityCountryChoiceBoxPair(Session session, ChoiceBox<Country> countries, ChoiceBox<City> cities, Validator validator) {
        this.session = session;
        this.countries = countries;
        this.cities = cities;
        this.validator = validator;

        this.countries.setItems(FXCollections.observableArrayList(this.session.query(Country.class)));
        this.cities.setItems(FXCollections.observableArrayList(this.session.query(City.class)));
        ChoiceBoxUtils.OnSelectionChanged(countries, this::onCountryChanged);
        ChoiceBoxUtils.OnSelectionChanged(cities, this::onCityChanged);

        validator.register(new CountryCityValidation(this.countries, this.cities));
    }


    private boolean suspend_onCountryChanged = false;
    private void onCountryChanged(ChoiceBox<Country> choiceBox, Country country) {

        if(suspend_onCountryChanged)
            return;

        updateAvailableCities(country, cities.getValue());

        if(cities.getValue() == null)
            return;

        if(cities.getValue().getCountry() == country)
            return;

        cities.setValue(null);
    }

    private boolean suspend_onCityChanged = false;
    private void onCityChanged(ChoiceBox<City> choiceBox, City city) {
        if(suspend_onCityChanged)
            return;

        if(countries.getValue() != null)
            return;

        suspend_onCountryChanged = true;
        try {
            countries.setValue(city.getCountry());
            updateAvailableCities(city.getCountry(), city);
        }
        finally {
            suspend_onCountryChanged = false;
        }
    }

    private void updateAvailableCities(Country country, City previousCity) {
        cities.setItems(FXCollections.observableArrayList(session.query(City.class, city -> city.getCountry() == country)));

        if(cities.getItems().contains(previousCity))
        {
            suspend_onCityChanged = true;
            try {
                cities.setValue(previousCity);
            }
            finally {
                suspend_onCityChanged = false;
            }
        }
    }

    private final Session session;
    private final ChoiceBox<City> cities;
    private final ChoiceBox<Country> countries;
    private final Validator validator;
}
