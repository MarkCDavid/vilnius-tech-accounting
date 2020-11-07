package vilnius.tech.controller;

import org.hibernate.Session;

public abstract class SessionController extends Controller {

    public SessionController(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    private final Session session;

}
