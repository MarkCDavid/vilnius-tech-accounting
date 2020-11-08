package vilnius.tech.error;

import vilnius.tech.error.ApplicationError;

public interface ErrorRouter {

    void route(ApplicationError error);
}
