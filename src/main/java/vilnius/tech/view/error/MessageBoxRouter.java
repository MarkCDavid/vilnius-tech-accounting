package vilnius.tech.view.error;

import vilnius.tech.utils.MessageBox;

public class MessageBoxRouter implements ErrorRouter {
    @Override
    public void route(Exception exception) {
        MessageBox.show();
    }
}
