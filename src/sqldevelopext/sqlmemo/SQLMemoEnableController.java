package sqldevelopext.sqlmemo;

import oracle.ide.Context;
import oracle.ide.controller.Controller;
import oracle.ide.controller.IdeAction;
import oracle.ide.extension.RegisteredByExtension;

@RegisteredByExtension("sqldevelopext.sqlmemo")
public final class SQLMemoEnableController implements Controller {
    static boolean enabled = true;

    public boolean update(IdeAction action, Context context) {
        action.setEnabled(enabled);
        return true;
    }

    public boolean handleEvent(IdeAction action, Context context) {
        return false;
    }
}
