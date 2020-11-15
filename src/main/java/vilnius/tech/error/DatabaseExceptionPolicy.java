package vilnius.tech.error;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.service.spi.ServiceException;

import javax.persistence.PersistenceException;

public class DatabaseExceptionPolicy {

    public static ApplicationError apply(Throwable throwable) {
        if(throwable instanceof PersistenceException) {
            var persistenceException = (PersistenceException) throwable;
            throwable = persistenceException.getCause();
        }

        if(throwable instanceof ConstraintViolationException) {
            var constraintViolationException = (ConstraintViolationException) throwable;
            return constraintViolationExceptionToApplicationError(constraintViolationException);
        }
        else if (throwable instanceof JDBCConnectionException) {
            var jdbcConnectionException = (JDBCConnectionException) throwable;
            return new ApplicationError("Connection Error", String.format(
                    "Connection to the database failed due to:%n%s",
                    jdbcConnectionException.getCause()
            ));
        }
        else if (throwable instanceof ServiceException) {
            var serviceException = (ServiceException) throwable;
            return new ApplicationError("Service Error", String.format(
                    "Failed to establish a service:%n%s",
                    String.join(System.lineSeparator(), ExceptionUtils.unwind(serviceException))
            ));
        }
        return null;
    }

    private static ApplicationError constraintViolationExceptionToApplicationError(ConstraintViolationException exception) {
        return switch (exception.getSQLException().getErrorCode()) {
            case 1048 -> new ApplicationError(
                    "Constraint Violation",
                    "Cannot delete this entry. This entry is being used in a not-null constrained field."
            );
            default -> new ApplicationError(
                    "Constraint Violation",
                    String.format(
                            "The constraint %s was violated due to:%n%s",
                            exception.getConstraintName(), exception.getCause()
                    )
            );
        };
    }

    private DatabaseExceptionPolicy() { }
}
