package ihm;

import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;

public class ActionFermer extends AbstractAction{
	
	private Dialog dial;
	
	public ActionFermer(Dialog dial) {
		super("ok");
		this.dial = dial;
	}
	
	public void actionPerformed(java.awt.event.ActionEvent e) {
		dial.dispose();
	}
}
