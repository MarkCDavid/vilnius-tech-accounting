package vilnius.tech.web.controller.proxy.controller;

import org.springframework.http.ResponseEntity;
import vilnius.tech.hibernate.FinancialCategory;
import vilnius.tech.hibernate.User;
import vilnius.tech.hibernate.service.FinancialCategoryService;
import vilnius.tech.hibernate.service.UserService;
import vilnius.tech.web.utils.HibernateUtils;
import vilnius.tech.web.utils.JsonResponseUtils;
import vilnius.tech.web.utils.Messages;

import java.util.Objects;

public class FinancialCategoryProxy extends AbstractControllerProxy<FinancialCategory, FinancialCategoryService> {
    @Override
    public ResponseEntity<String> post(FinancialCategory financialCategory) {
        if(post_Invalid(financialCategory))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        var parent = getParent(financialCategory);

        var owner = getOwner(financialCategory);
        if(owner == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound_Field("User", "username", financialCategory.getOwner().getUsername()));

        return JsonResponseUtils.OK(createService().create(
                parent,
                financialCategory.getName(),
                owner
        ));
    }

    @Override
    public ResponseEntity<String> put(FinancialCategory financialCategory) {
        if(put_Invalid(financialCategory))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        var service = createService();

        var databaseFinancialCategory = service.find(financialCategory.getId());
        if(databaseFinancialCategory == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound(getEntityName(), financialCategory.getId()));

        if(namePresent(financialCategory)) {
            databaseFinancialCategory.setName(financialCategory.getName());
        }

        return JsonResponseUtils.OK(service.update(databaseFinancialCategory));
    }

    public ResponseEntity<String> get_ResponsibleUser(Integer id) {
        if(id == null)
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        var service = createService();
        var databaseFinancialCategory = service.find(id);

        if(databaseFinancialCategory == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound(getEntityName(), id));

        return JsonResponseUtils.OK(databaseFinancialCategory.getResponsibleUsers());
    }

    public ResponseEntity<String> put_ResponsibleUser(Integer id, User user) {
        if(!rest_ResponsibleUserValid(id, user))
            return JsonResponseUtils.BAD(Messages.invalidData("User"));

        var service = createService();
        var databaseFinancialCategory = service.find(id);

        if(databaseFinancialCategory == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound(getEntityName(), id));

        var userService = new UserService(HibernateUtils.getSession());
        var databaseUser = userService.find_Username(user.getUsername());

        if(databaseUser == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound_Field("User", "username", user.getUsername()));

        var alreadyPresent = databaseFinancialCategory.getResponsibleUsers().stream().anyMatch(ru -> Objects.equals(ru.getId(), databaseUser.getId()));
        if(alreadyPresent) {
            return JsonResponseUtils.BAD(
                    String.format("User '%s' is already responsible for category '%s'.",
                        databaseUser.getUsername(),
                        databaseFinancialCategory.getName()
                    )
            );
        }

        return JsonResponseUtils.OK(service.add_ResponsibleUser(databaseFinancialCategory, databaseUser)
                .getResponsibleUsers());
    }



    public ResponseEntity<String> delete_ResponsibleUser(Integer id, User user) {
        if(!rest_ResponsibleUserValid(id, user))
            return JsonResponseUtils.BAD(Messages.invalidData("User"));

        var service = createService();
        var databaseFinancialCategory = service.find(id);

        if(databaseFinancialCategory == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound(getEntityName(), id));

        var userService = new UserService(HibernateUtils.getSession());
        var databaseUser = userService.find_Username(user.getUsername());

        if(databaseUser == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound_Field("User", "username", user.getUsername()));

        var alreadyPresent = databaseFinancialCategory.getResponsibleUsers().stream().anyMatch(ru -> Objects.equals(ru.getId(), databaseUser.getId()));
        if(!alreadyPresent) {
            return JsonResponseUtils.BAD(
                    String.format("User '%s' is not responsible for category '%s'.",
                            databaseUser.getUsername(),
                            databaseFinancialCategory.getName()
                    )
            );
        }

        return JsonResponseUtils.OK(service.remove_ResponsibleUser(databaseFinancialCategory, databaseUser)
                .getResponsibleUsers());
    }

    private boolean rest_ResponsibleUserValid(Integer id, User user) {
        return id != null && user.getUsername() != null && !user.getUsername().isBlank();
    }

    private boolean post_Invalid(FinancialCategory financialCategory) {
        return !namePresent(financialCategory) ||
               !ownerPresent(financialCategory);
    }

    private boolean put_Invalid(FinancialCategory financialCategory) {
        if(financialCategory.getId() == null)
            return true;

        return !namePresent(financialCategory);
    }

    private boolean namePresent(FinancialCategory financialCategory) {
        return financialCategory.getName() != null &&
                !financialCategory.getName().isBlank();
    }

    private boolean ownerPresent(FinancialCategory financialCategory) {
        return financialCategory.getOwner() != null &&
                financialCategory.getOwner().getUsername() != null &&
                !financialCategory.getOwner().getUsername().isBlank();
    }


    private boolean parentPresent(FinancialCategory financialCategory) {
        return financialCategory.getParent() != null &&
                financialCategory.getParent().getId() != null;
    }

    private User getOwner(FinancialCategory financialCategory) {
        var userService = new UserService(HibernateUtils.getSession());

        if(!ownerPresent(financialCategory))
            return null;

        return userService.find_Username(financialCategory.getOwner().getUsername());
    }


    private FinancialCategory getParent(FinancialCategory financialCategory) {
        var financialCategoryService = new FinancialCategoryService(HibernateUtils.getSession());

        if(!parentPresent(financialCategory))
            return null;

        return financialCategoryService.find(financialCategory.getParent().getId());
    }

    @Override
    protected FinancialCategoryService createService() {
        return new FinancialCategoryService(HibernateUtils.getSession());
    }

    @Override
    protected String getEntityName() {
        return ENTITY_NAME;
    }

    public final static String ENTITY_NAME = "Financial Category";
}
