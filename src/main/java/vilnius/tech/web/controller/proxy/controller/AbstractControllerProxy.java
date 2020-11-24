package vilnius.tech.web.controller.proxy.controller;

import org.springframework.http.ResponseEntity;
import vilnius.tech.hibernate.BaseEntity;
import vilnius.tech.hibernate.service.HibernateService;
import vilnius.tech.web.utils.JsonResponseUtils;
import vilnius.tech.web.utils.Message;
import vilnius.tech.web.utils.Messages;

public abstract class AbstractControllerProxy<T extends BaseEntity, S extends HibernateService<T>> implements ControllerProxy<T> {

    @Override
    public ResponseEntity<String> getAll(Integer take, Integer skip) {

        return JsonResponseUtils.OK(createService().find(take, skip));
    }

    @Override
    public ResponseEntity<String> get(Integer id) {
        var result = createService().find(id);
        if(result == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound(getEntityName(), id));
        return JsonResponseUtils.OK(result);
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        var service = createService();
        var result = service.find(id);
        if(result == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound(getEntityName(), id));

        service.remove(result);
        return JsonResponseUtils.OK(new Message(Messages.deleteSuccessful(getEntityName(), id)));
    }

    public abstract ResponseEntity<String> post(T object);
    public abstract ResponseEntity<String> put(T object);

    protected abstract S createService();
    protected abstract String getEntityName();
}
