import javax.swing.JButton;

public class TButton extends JButton{
	
	private int value;
	
	public TButton() {
		value = 0;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
