package java_swing_study.windowbuilder_conf;

import java.awt.EventQueue;

public class TestMain {
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    BuilderConf frame = new BuilderConf();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }
}
