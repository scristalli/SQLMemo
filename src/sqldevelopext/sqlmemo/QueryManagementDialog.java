package sqldevelopext.sqlmemo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import oracle.ide.Ide;

public class QueryManagementDialog extends JDialog {
    @SuppressWarnings("compatibility:-7931175876957076155")
    private static final long serialVersionUID = 5449134007661279795L;
    static DefaultTableModel model;

    public QueryManagementDialog(JFrame parent) {
        super(parent, true);
        setTitle("SQLMemo Query Management");
        initDialog();
        pack();
    }

    private void initDialog() {
        final JTable table = new JTable(model);
        table.setShowGrid(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollTable = new JScrollPane(table);
        scrollTable.setColumnHeader(null);
        scrollTable.setMinimumSize(new Dimension(100, 80));
        Box tableBox = new Box(BoxLayout.Y_AXIS);
        tableBox.add(scrollTable);
        tableBox.add(new JLabel("Queries"));

        JButton addButton = new JButton("Add Element");
        JButton removeButton = new JButton("Remove Element");

        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent event) {
                String file_path = Ide.getUserSettingsDirectory() + File.separator + "sqlmemo.conf";
                File f = new File(file_path);
                PrintWriter printWriter = null;
                try {
                    printWriter = new PrintWriter(f);
                    for (int i = 0; i < model.getRowCount(); i++) {
                        printWriter.println(model.getValueAt(i, 0));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    printWriter.close();
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = model.getRowCount() + 1;
                Vector<String> v = new Vector<String>();
                v.add("Query " + index + "|SELECT SYSDATE FROM DUAL;");
                model.addRow(v);
            }
        });
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    model.removeRow(selectedRow);
                }
            }
        });

        add(tableBox, BorderLayout.NORTH);
        add(addButton, BorderLayout.WEST);
        add(removeButton, BorderLayout.EAST);
    }


    static Vector stringListToVectorOfVectors(List<String> l) {
        Vector<Vector> vectors = new Vector<Vector>();
        for (String s : l) {
            Vector<String> v = new Vector<String>();
            v.addElement(s);
            vectors.addElement(v);
        }
        return vectors;
    }

    @SuppressWarnings("oracle.jdeveloper.java.nested-assignment")
    static List<String> readQueriesFromFile() {
        List<String> queries = new ArrayList<String>();
        String file_path = Ide.getUserSettingsDirectory() + File.separator + "sqlmemo.conf";
        File f = new File(file_path);
        if (f.exists() && !f.isDirectory()) {
            BufferedReader br = null;

            try {
                String sCurrentLine;
                br = new BufferedReader(new FileReader(file_path));
                while ((sCurrentLine = br.readLine()) != null) {
                    queries.add(sCurrentLine);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null)
                        br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return queries;
    }
}
