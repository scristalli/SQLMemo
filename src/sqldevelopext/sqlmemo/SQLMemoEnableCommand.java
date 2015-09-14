package sqldevelopext.sqlmemo;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import oracle.ide.Ide;
import oracle.ide.controller.Command;
import oracle.ide.extension.RegisteredByExtension;

@RegisteredByExtension("sqldevelopext.sqlmemo")
public final class SQLMemoEnableCommand extends Command {
    public SQLMemoEnableCommand() {
        super(actionId());
    }

    public static int actionId() {
        final Integer cmdId = Ide.findCmdID("sqldevelopext.sqlmemo.SQLMemoEnable");
        if (cmdId == null)
            throw new IllegalStateException("Action sqldevelopext.sqlmemo.SQLMemoEnable not found.");
        return cmdId;
    }

    public int doit() {
        QueryManagementDialog.model = new DefaultTableModel();
        Vector<String> dummyHeader = new Vector<String>();
        dummyHeader.addElement("");
        QueryManagementDialog.model.setDataVector(QueryManagementDialog.stringListToVectorOfVectors(QueryManagementDialog.readQueriesFromFile()), dummyHeader);
        SQLMemoEnableController.enabled = false;
        return OK;
    }
}
