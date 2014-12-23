import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameOptions extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField numberOfColumns = new JTextField(5);
	private JTextField numberOfRows = new JTextField(5);
	
	public GameOptions() {
		add(new JLabel("Number of Columns: "));
		add(numberOfColumns);
		
		add(new JLabel("Number of Rows: "));
		add(numberOfRows);
	}

	public JTextField getNumberOfColumns() {
		return numberOfColumns;
	}

	public void setNumberOfColumns(JTextField numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}

	public JTextField getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(JTextField numberOfRows) {
		this.numberOfRows = numberOfRows;
	}
	
}
