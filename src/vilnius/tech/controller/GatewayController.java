package vilnius.tech.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import vilnius.tech.dal.City;
import vilnius.tech.dal.Country;
import vilnius.tech.dal.User;
import vilnius.tech.session.Session;
import vilnius.tech.utils.ChoiceBoxUtils;
import vilnius.tech.utils.MessageBox;
import vilnius.tech.utils.controls.CityCountryChoiceBoxPair;
import vilnius.tech.validation.Validator;
import vilnius.tech.validation.validators.ChoiceBoxNotNullValidation;
import vilnius.tech.validation.validators.TextLengthValidation;
import vilnius.tech.view.View;

import java.io.IOException;
import java.util.List;
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
        signInValidator.register(new TextLengthValidation(sitfUsername, 6, 32));
        signInValidator.register(new TextLengthValidation(sipfPassword, 6, 32));

        signUpValidator.register(new TextLengthValidation(sutfUsername, 6, 32));
        signUpValidator.register(new TextLengthValidation(supfPassword, 6, 32));
        signUpValidator.register(new ChoiceBoxNotNullValidation<String>(sucbUserType));

        sucbUserType.setItems(FXCollections.observableArrayList(PHYSICAL, JURIDICAL));
        ChoiceBoxUtils.OnSelectionChanged(sucbUserType, this::onUserTypeChanged);

        physicalChoiceBoxPair = new CityCountryChoiceBoxPair(getSession(), sucbCountry, sucbCity, signInValidator);
        juridicalChoiceBoxPair = new CityCountryChoiceBoxPair(getSession(), sucbJuridicalCountry, sucbJuridicalCity, signInValidator);


        enableControls(null);
    }

    private CityCountryChoiceBoxPair physicalChoiceBoxPair;
    private CityCountryChoiceBoxPair juridicalChoiceBoxPair;
    private Validator signInValidator;
    private Validator signUpValidator;

    @FXML
    public TextField sitfUsername;

    @FXML
    public PasswordField sipfPassword;

    public void buttonSignInAction(ActionEvent actionEvent) throws IOException {

        if(!signInValidator.validate())
            return;

        List<User> users = getSession().query(User.class, user -> matchCredentials(user, sitfUsername.getText(), sipfPassword.getText()));

        if(users.size() != 1) {
            MessageBox.show(Alert.AlertType.ERROR, "Authentication Failure", "Username or password incorrect.");
            return;
        }

        new View(new MainController(users.get(0), getSession()), getStage(), "Main").render("main.fxml");

    }

    public void buttonSignUpAction(ActionEvent actionEvent) throws IOException {

        if(!signUpValidator.validate())
            return;

        if(sucbUserType.getValue() == null)
        {
            MessageBox.show(Alert.AlertType.ERROR, "User type unspecified", "Please select the type of the account you want to create.");
            return;
        }

        List<User> users = getSession().query(User.class, user -> matchUsername(user, sutfUsername.getText()));

        if(users.size() > 0) {
            MessageBox.show(Alert.AlertType.ERROR, "User exists", String.format("User with username %s already exists.", sutfUsername.getText()));
            return;
        }

        new View(new MainController(users.get(0), getSession()), getStage(), "Main").render("main.fxml");

    }


    private boolean matchCredentials(User user, String username, String password) {
        return matchUsername(user, username) && matchPassword(user, password);
    }

    private boolean matchPassword(User user, String value) {
        return Objects.equals(user.getPassword(), value);
    }

    private boolean matchUsername(User user, String value) {
        return Objects.equals(user.getUsername(), value);
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

    @FXML
    public TextField sutfUsername;

    @FXML
    public PasswordField supfPassword;

    @FXML
    public ChoiceBox<String> sucbUserType;

    @FXML
    public Label suPhysicalLabel;

    @FXML
    public TextField signUpName;

    @FXML
    public TextField signUpSurname;

    @FXML
    public Label sucbCountryLabel;

    @FXML
    public ChoiceBox<Country> sucbCountry;

    @FXML
    public Label sucbCityLabel;

    @FXML
    public ChoiceBox<City> sucbCity;

    @FXML
    public TextField signUpStreet;

    @FXML
    public TextField signUpPostal;

    @FXML
    public TextField signUpEmail;

    @FXML
    public TextField signUpPhone;

    @FXML
    public Label suJuridicalLabel;

    @FXML
    public TextField signUpJuridicalName;

    @FXML
    public Label sucbJuridicalCountryLabel;

    @FXML
    public ChoiceBox<Country> sucbJuridicalCountry;

    @FXML
    public Label sucbJuridicalCityLabel;

    @FXML
    public ChoiceBox<City> sucbJuridicalCity;

    @FXML
    public TextField signUpJuridicalStreet;

    @FXML
    public TextField signUpJuridicalPostal;

}
