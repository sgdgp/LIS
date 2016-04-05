package libreria;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
class ButtonEditor extends DefaultCellEditor {
  protected JButton button;

  private String label;
  @SuppressWarnings("unused")
private JTable tbl;
  private boolean isPushed;
  private int r;
  public ButtonEditor(JCheckBox checkBox) {
    super(checkBox);
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
      }
    });
  }

  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else {
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
    label = (value == null) ? "" : value.toString();
    button.setText(label);
    isPushed = true;
    r = row;
    tbl = table;
    return button;
  }

  public Object getCellEditorValue() {
    if (isPushed) {          
      if(button.getText().equals("Return")){
    	  libraryfunc l = new libraryfunc();
    	  l.returned(ReturnWrapper.uid.get(r).getIssuedBook(), ReturnWrapper.username);
      }
      
    }
    isPushed = false;
    return new String(label);
  }

  public boolean stopCellEditing() {
    isPushed = false;
    return super.stopCellEditing();
  }

  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }
}


