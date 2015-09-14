package sqldevelopext.sqlmemo;

import oracle.ide.Ide;
import oracle.ide.controller.Command;
import oracle.ide.extension.RegisteredByExtension;

@RegisteredByExtension("sqldevelopext.sqlmemo")
public class SQLMemoManageCommand extends Command {
    public SQLMemoManageCommand() {
        super(actionId());
    }

    public static int actionId() {
        final Integer cmdId = Ide.findCmdID("sqldevelopext.sqlmemo.SQLMemoManage");
        if (cmdId == null)
            throw new IllegalStateException("Action sqldevelopext.sqlmemo.SQLMemoManage not found.");
        return cmdId;
    }

    public int doit() {
        QueryManagementDialog qmd = new QueryManagementDialog(null);
        qmd.setLocationRelativeTo(null);
        qmd.setVisible(true);
        return OK;
    }
}
