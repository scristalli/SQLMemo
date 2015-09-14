package sqldevelopext.sqlmemo;

import javax.swing.JMenu;

import oracle.ide.Context;
import oracle.ide.Ide;
import oracle.ide.controller.ContextMenu;
import oracle.ide.controller.ContextMenuListener;
import oracle.ide.controller.IdeAction;
import oracle.ide.controller.MenuManager;
import oracle.ide.extension.RegisteredByExtension;

@RegisteredByExtension("sqldevelopext.sqlmemo")
public class SQLMemoContextMenuListener implements ContextMenuListener {
    private static int counter = 0;

    @Override
    public void menuWillShow(ContextMenu contextMenu) {
        JMenu sm = contextMenu.createSubMenu("SQLMemo", null, 0.1f);

        for (int i = 0; i < QueryManagementDialog.model.getRowCount(); i++) {
            String modelRow = (String) QueryManagementDialog.model.getValueAt(i, 0);
            IdeAction localIdeAction = IdeAction.findOrCreate(Ide.findOrCreateCmdID("QueryInsertCommand" + counter++), null, parseRow(modelRow, 0));
            localIdeAction.addController(new SQLMemoInsertController(parseRow(modelRow, 1)));
            sm.add(contextMenu.createMenuItem(localIdeAction));
        }
        IdeAction manageAction = IdeAction.find("sqldevelopext.sqlmemo.SQLMemoManage");
        sm.add(contextMenu.createMenuItem(manageAction));
        contextMenu.add(sm, 1.0f);
    }

    @Override
    public void menuWillHide(ContextMenu contextMenu) {
        contextMenu.remove(MenuManager.getJMenu("SQLMemo"));
    }

    @Override
    public boolean handleDefaultAction(Context context) {
        return false;
    }

    private String parseRow(String row, int part) {
        if (!row.contains("|")) {
            return row;
        } else {
            String[] parts = row.split("\\|");
            return parts[part];
        }
    }
}
