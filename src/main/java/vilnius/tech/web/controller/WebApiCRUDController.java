package vilnius.tech.web.controller;

import org.springframework.http.ResponseEntity;
import vilnius.tech.error.DatabaseExceptionPolicy;
import vilnius.tech.error.ErrorRouter;
import vilnius.tech.hibernate.BaseEntity;
import vilnius.tech.web.controller.proxy.controller.ControllerProxy;

public abstract class WebApiCRUDController<T extends BaseEntity, P extends ControllerProxy<T>>
{

    protected WebApiCRUDController(P controllerProxy, ErrorRouter<ResponseEntity<String>> errorRouter) {
        this.controllerProxy = controllerProxy;
        this.errorRouter = errorRouter;
    }

    public ResponseEntity<String> getAll(Integer take, Integer skip) {
        try {
            return controllerProxy.getAll(take, skip);
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
            return controllerProxy.get(id);
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
            return controllerProxy.delete(id);
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
            return controllerProxy.post(object);
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
            return controllerProxy.put(object);
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

    private final P controllerProxy;

    public P getControllerProxy() {
        return controllerProxy;
    }

    public ErrorRouter<ResponseEntity<String>> getErrorRouter() {
        return errorRouter;
    }
}
