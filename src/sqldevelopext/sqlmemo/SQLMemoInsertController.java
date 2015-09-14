package sqldevelopext.sqlmemo;

import oracle.dbtools.worksheet.editor.Worksheet;

import oracle.ide.Context;
import oracle.ide.controller.Controller;
import oracle.ide.controller.IdeAction;
import oracle.ide.extension.RegisteredByExtension;

@RegisteredByExtension("sqldevelopext.sqlmemo")
public class SQLMemoInsertController implements Controller {
    private String query;

    public SQLMemoInsertController(String query) {
        this.query = query;
    }

    public boolean update(IdeAction action, Context context) {
        return true;
    }

    public boolean handleEvent(IdeAction action, Context context) {
        ((Worksheet) context.getView()).appendText(query);
        return true;
    }
}
