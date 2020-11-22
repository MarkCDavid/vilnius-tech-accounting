package vilnius.tech.error;

import vilnius.tech.error.ApplicationError;

public interface ErrorRouter<T> {

    T route(ApplicationError error);
}
