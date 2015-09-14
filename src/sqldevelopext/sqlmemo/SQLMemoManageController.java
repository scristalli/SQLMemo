package sqldevelopext.sqlmemo;

import oracle.ide.Context;
import oracle.ide.controller.Controller;
import oracle.ide.controller.IdeAction;
import oracle.ide.extension.RegisteredByExtension;

@RegisteredByExtension("sqldevelopext.sqlmemo")
public class SQLMemoManageController implements Controller {
    public boolean update(IdeAction action, Context context) {
        return true;
    }

    public boolean handleEvent(IdeAction action, Context context) {
        return false;
    }
}
