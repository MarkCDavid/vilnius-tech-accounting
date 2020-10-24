package vilnius.tech.session;

import vilnius.tech.dal.BaseOid;

public class InvalidSessionObjectTypeException extends RuntimeException {

    public InvalidSessionObjectTypeException(Class<? extends BaseOid> given, Class<? extends BaseOid> expected) {
        this.given = given;
        this.expected = expected;
    }

    @Override
    public String toString() {
        return String.format("Given: '%s'%nExpected: '%s'%n", given, expected);
    }

    private final Class<? extends BaseOid> given;
    private final Class<? extends BaseOid> expected;
}
