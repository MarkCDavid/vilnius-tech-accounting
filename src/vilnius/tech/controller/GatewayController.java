package vilnius.tech.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import vilnius.tech.controller.crud.JuridicalUserCU;
import vilnius.tech.controller.crud.PhysicalUserCU;
import vilnius.tech.dal.City;
import vilnius.tech.dal.Country;
import vilnius.tech.dal.User;
import vilnius.tech.session.Session;
import vilnius.tech.utils.ChoiceBoxUtils;
import vilnius.tech.utils.UsersUtils;
import vilnius.tech.utils.controls.CityCountryChoiceBoxPair;
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

        var users = getSession().query(User.class, user -> UsersUtils.matchCredentials(user, sitfUsername.getText(), sipfPassword.getText()));
        if(users.isEmpty())
            throw new IllegalStateException("No user fetched after validation!");

        new View(new MainController(users.get(0), getSession()), getStage(), "Main").render("main.fxml");

    }

    public void buttonSignUpAction(ActionEvent actionEvent) throws IOException {

        if(!signUpValidator.validate())
            return;

        var user = createUser();

        new View(new MainController(user, getSession()), getStage(), "Main").render("main.fxml");
    }

    private User createUser() {
        var userType = sucbUserType.getValue();
        if(Objects.equals(userType, PHYSICAL)) {
            return PhysicalUserCU.create(getSession(), sutfUsername.getText(), supfPassword.getText(), sucbCity.getValue(),
                    signUpStreet.getText(), signUpPostal.getText(), signUpPhone.getText(), signUpEmail.getText(),
                    signUpName.getText(), signUpSurname.getText());
        }
        else if (Objects.equals(userType, JURIDICAL)) {
            return JuridicalUserCU.create(getSession(), sutfUsername.getText(), supfPassword.getText(), sucbCity.getValue(),
                    signUpStreet.getText(), signUpPostal.getText(), signUpPhone.getText(), signUpEmail.getText(),
                    signUpName.getText(), signUpSurname.getText(), signUpJuridicalName.getText(), sucbJuridicalCity.getValue(),
                    signUpJuridicalStreet.getText(), signUpJuridicalPostal.getText());
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
