package vilnius.tech.web.controller.proxy;

import org.springframework.http.ResponseEntity;
import vilnius.tech.hibernate.BaseEntity;

public interface Proxy<T extends BaseEntity> {

    ResponseEntity<String> getAll(Integer take, Integer skip);
    ResponseEntity<String> get(Integer id);
    ResponseEntity<String> delete(Integer id);
    ResponseEntity<String> post(T object);
    ResponseEntity<String> put(T object);

}
