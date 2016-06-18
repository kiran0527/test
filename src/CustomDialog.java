import javax.swing.JOptionPane;

	 

	public class CustomDialog {

	    public static void main(String[] args) {

	        String[] choices = { "A", "B", "C" };

	        String response = (String) JOptionPane.showInputDialog(null, null, "What?", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

	 

	    }

	}