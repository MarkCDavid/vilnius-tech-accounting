package vilnius.tech.view.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.Session;
import vilnius.tech.hibernate.City;
import vilnius.tech.hibernate.Country;
import vilnius.tech.hibernate.User;
import vilnius.tech.hibernate.controller.*;
import vilnius.tech.utils.ChoiceBoxUtils;
import vilnius.tech.utils.PasswordUtils;
import vilnius.tech.utils.controls.CityCountryChoiceBoxPair;
import vilnius.tech.validation.ValidationResult;
import vilnius.tech.validation.Validator;
import vilnius.tech.validation.validators.ChoiceBoxNotNullValidation;
import vilnius.tech.validation.validators.TextLengthValidation;
import vilnius.tech.validation.validators.UserExistsValidation;
import vilnius.tech.validation.validators.UserValidValidation;
import vilnius.tech.view.View;

import java.io.IOException;
import java.util.Objects;

public class GatewayController extends SessionController {

    public GatewayController(Session session) {
        super(session);
        signInValidator = new Validator();
        signUpValidator = new Validator();
    }

    private static final String PHYSICAL = "Physical";
    private static final String JURIDICAL = "Juridical";

    @FXML
    public void initialize() {
        signInValidator.register(0, new TextLengthValidation(sitfUsername, 5, 32));
        signInValidator.register(0, new TextLengthValidation(sipfPassword, 5, 32));
        signInValidator.register(5, new UserValidValidation(getSession(), sitfUsername, sipfPassword));

        signUpValidator.register(0, new TextLengthValidation(sutfUsername, 5, 32));
        signUpValidator.register(0, new TextLengthValidation(supfPassword, 5, 32));
        signUpValidator.register(0, new ChoiceBoxNotNullValidation<>(sucbUserType));
        signUpValidator.register(5, new UserExistsValidation(getSession(), sutfUsername));

        new CityCountryChoiceBoxPair(getSession(), sucbCountry, sucbCity, signUpValidator);
        new CityCountryChoiceBoxPair(getSession(), sucbJuridicalCountry, sucbJuridicalCity, signUpValidator);

        sucbUserType.setItems(FXCollections.observableArrayList(PHYSICAL, JURIDICAL));
        ChoiceBoxUtils.OnSelectionChanged(sucbUserType, this::onUserTypeChanged);

        enableControls(null);
    }

    public void buttonSignInAction(ActionEvent actionEvent) throws IOException {
        if(!signInValidator.validate())
            return;

        var userController = new UserController(getSession());

        var user = userController.find_Username(sitfUsername.getText());
        if(user == null)
            throw new IllegalStateException("No user fetched after validation!");

        var valid = PasswordUtils.verifyUserPassword(sipfPassword.getText(), user.getPassword(), user.getSalt());
        if(!valid)
            throw new IllegalStateException("Password invalid after validation!");

        new View(new MainController(user, getSession()), getStage(), "Main", "main.fxml").render();
    }

    public void buttonSignUpAction(ActionEvent actionEvent) throws IOException {

        if(!signUpValidator.validate())
            return;

        var user = createUser();

        new View(new MainController(user, getSession()), getStage(), "Main", "main.fxml").render();
    }

    private User createUser() {
        var userType = sucbUserType.getValue();

        var addressController = new AddressController(getSession());
        var contactInformationController = new ContactInformationController(getSession());
        var physicalUserController = new PhysicalUserController(getSession());

        var address = addressController.create(sucbCity.getValue(), signUpPhone.getText(), signUpEmail.getText());
        var contactInformation = contactInformationController.create(
                address, signUpEmail.getText(), signUpPhone.getText()
        );

        if(Objects.equals(userType, PHYSICAL)) {
            return physicalUserController.create(
                    sutfUsername.getText(), supfPassword.getText(),
                    signUpName.getText(), signUpSurname.getText(),
                    contactInformation
            );
        }
        else if (Objects.equals(userType, JURIDICAL)) {
            var juridicalAddress = addressController.create(
                    sucbJuridicalCity.getValue(), signUpJuridicalStreet.getText(), signUpJuridicalPostal.getText()
            );

            var physicalUser = physicalUserController.create(
                    null, null,
                    signUpName.getText(), signUpSurname.getText(),
                    contactInformation
            );

            var juridicalUserController = new JuridicalUserController(getSession());
            return juridicalUserController.create(
                    sutfUsername.getText(), supfPassword.getText(), signUpJuridicalName.getText(),
                    juridicalAddress, physicalUser
            );
        }
        throw new IllegalStateException(String.format("The selected user type '%s' is not valid!", userType));
    }


    private void onUserTypeChanged(ChoiceBox<String> choiceBox, String userType) {
        enableControls(userType);
    }

    private void enableControls(String selected) {
        boolean selectedPhysical = Objects.equals(selected, PHYSICAL);
        boolean selectedJuridical = Objects.equals(selected, JURIDICAL);
        boolean selectedAny = selectedPhysical || selectedJuridical;

        suPhysicalLabel.setVisible(selectedAny);

        if(selectedPhysical)
            suPhysicalLabel.setText("Information");
        else if(selectedJuridical)
            suPhysicalLabel.setText("Contact Person");

        signUpName.setVisible(selectedAny);
        signUpSurname.setVisible(selectedAny);
        sucbCountryLabel.setVisible(selectedAny);
        sucbCountry.setVisible(selectedAny);
        sucbCityLabel.setVisible(selectedAny);
        sucbCity.setVisible(selectedAny);
        signUpStreet.setVisible(selectedAny);
        signUpPostal.setVisible(selectedAny);
        signUpEmail.setVisible(selectedAny);
        signUpPhone.setVisible(selectedAny);
        suJuridicalLabel.setVisible(selectedJuridical);
        signUpJuridicalName.setVisible(selectedJuridical);
        sucbJuridicalCountryLabel.setVisible(selectedJuridical);
        sucbJuridicalCountry.setVisible(selectedJuridical);
        sucbJuridicalCityLabel.setVisible(selectedJuridical);
        sucbJuridicalCity.setVisible(selectedJuridical);
        signUpJuridicalStreet.setVisible(selectedJuridical);
        signUpJuridicalPostal.setVisible(selectedJuridical);
    }


    private final Validator signInValidator;
    private final Validator signUpValidator;


    @FXML public TextField sitfUsername;
    @FXML public PasswordField sipfPassword;
    @FXML public TextField sutfUsername;
    @FXML public PasswordField supfPassword;
    @FXML public ChoiceBox<String> sucbUserType;
    @FXML public Label suPhysicalLabel;
    @FXML public TextField signUpName;
    @FXML public TextField signUpSurname;
    @FXML public Label sucbCountryLabel;
    @FXML public ChoiceBox<Country> sucbCountry;
    @FXML public Label sucbCityLabel;
    @FXML public ChoiceBox<City> sucbCity;
    @FXML public TextField signUpStreet;
    @FXML public TextField signUpPostal;
    @FXML public TextField signUpEmail;
    @FXML public TextField signUpPhone;
    @FXML public Label suJuridicalLabel;
    @FXML public TextField signUpJuridicalName;
    @FXML public Label sucbJuridicalCountryLabel;
    @FXML public ChoiceBox<Country> sucbJuridicalCountry;
    @FXML public Label sucbJuridicalCityLabel;
    @FXML public ChoiceBox<City> sucbJuridicalCity;
    @FXML public TextField signUpJuridicalStreet;
    @FXML public TextField signUpJuridicalPostal;
}
