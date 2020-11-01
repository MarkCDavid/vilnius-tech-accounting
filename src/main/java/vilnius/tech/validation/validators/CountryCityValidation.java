package vilnius.tech.validation.validators;

import javafx.scene.control.ChoiceBox;
import vilnius.tech.dal.City;
import vilnius.tech.dal.Country;
import vilnius.tech.validation.Validation;
import vilnius.tech.validation.ValidationResult;

public class CountryCityValidation implements Validation {

    public CountryCityValidation(ChoiceBox<Country> countryChoiceBox, ChoiceBox<City> cityChoiceBox) {
        this.countryChoiceBox = countryChoiceBox;
        this.cityChoiceBox = cityChoiceBox;
    }

    @Override
    public ValidationResult validate() {

        Country country = countryChoiceBox.getValue();
        City city = cityChoiceBox.getValue();

        if(country == null && city != null)
            throw new IllegalStateException("Country has no value, when city is selected!");

        if(country != null && city == null)
            return ValidationResult.BAD(String.format("%s - Please select a city from %s", countryChoiceBox.getAccessibleText(), countryChoiceBox.getValue()));

        return ValidationResult.OK();
    }

    private final ChoiceBox<Country> countryChoiceBox;
    private final ChoiceBox<City> cityChoiceBox;
}
