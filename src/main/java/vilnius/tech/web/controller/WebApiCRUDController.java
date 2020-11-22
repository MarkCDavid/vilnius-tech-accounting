package vilnius.tech.web.controller;

import org.springframework.http.ResponseEntity;
import vilnius.tech.error.DatabaseExceptionPolicy;
import vilnius.tech.error.ErrorRouter;
import vilnius.tech.hibernate.BaseEntity;
import vilnius.tech.web.controller.proxy.Proxy;

public abstract class WebApiCRUDController<T extends BaseEntity>
{

    protected WebApiCRUDController(Proxy<T> proxy, ErrorRouter<ResponseEntity<String>> errorRouter) {
        this.proxy = proxy;
        this.errorRouter = errorRouter;
    }

    public ResponseEntity<String> getAll(Integer take, Integer skip) {
        try {
            return proxy.getAll(take, skip);
        } catch (Exception ex) {
            var error = DatabaseExceptionPolicy.apply(ex);
            if(error == null)
                throw ex;

            if(errorRouter == null)
                throw ex;

            return errorRouter.route(error);
        }
    }

    public ResponseEntity<String> get(Integer id) {
        try {
            return proxy.get(id);
        } catch (Exception ex) {
            var error = DatabaseExceptionPolicy.apply(ex);
            if(error == null)
                throw ex;

            if(errorRouter == null)
                throw ex;

            return errorRouter.route(error);
        }
    }

    public ResponseEntity<String> delete(Integer id) {
        try {
            return proxy.delete(id);
        } catch (Exception ex) {
            var error = DatabaseExceptionPolicy.apply(ex);
            if(error == null)
                throw ex;

            if(errorRouter == null)
                throw ex;

            return errorRouter.route(error);
        }
    }

    public ResponseEntity<String> post(T object) {
        try {
            return proxy.post(object);
        } catch (Exception ex) {
            var error = DatabaseExceptionPolicy.apply(ex);
            if(error == null)
                throw ex;

            if(errorRouter == null)
                throw ex;

            return errorRouter.route(error);
        }
    }

    public ResponseEntity<String> put(T object) {
        try {
            return proxy.put(object);
        } catch (Exception ex) {
            var error = DatabaseExceptionPolicy.apply(ex);
            if(error == null)
                throw ex;

            if(errorRouter == null)
                throw ex;

            return errorRouter.route(error);
        }
    }

    private final ErrorRouter<ResponseEntity<String>> errorRouter;
    private final Proxy<T> proxy;
}
