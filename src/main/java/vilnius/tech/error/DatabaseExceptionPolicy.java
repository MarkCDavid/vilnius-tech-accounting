package vilnius.tech.error;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.service.spi.ServiceException;

import java.sql.SQLException;
import java.sql.SQLInvalidAuthorizationSpecException;

public class DatabaseExceptionPolicy {

    public static ApplicationError apply(Exception exception) {
        if(exception instanceof ConstraintViolationException) {
            var constraintViolationException = (ConstraintViolationException) exception;
            return new ApplicationError("Constraint Violation", String.format(
                    "The constraint %s was violated due to:%n%s",
                    constraintViolationException.getConstraintName(), constraintViolationException.getCause()
            ));
        }
        else if (exception instanceof JDBCConnectionException) {
            var jdbcConnectionException = (JDBCConnectionException) exception;
            return new ApplicationError("Connection Error", String.format(
                    "Connection to the database failed due to:%n%s",
                    jdbcConnectionException.getCause()
            ));
        }
        else if (exception instanceof ServiceException) {
            var serviceException = (ServiceException) exception;
            return new ApplicationError("Service Error", String.format(
                    "Failed to establish a service:%n%s",
                    String.join(System.lineSeparator(), ExceptionUtils.unwind(serviceException))
            ));
        }
        return null;
    }

    private DatabaseExceptionPolicy() { }
}
