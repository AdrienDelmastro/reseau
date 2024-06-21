package ihm;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing.SwingGraphRenderer;
import org.graphstream.ui.swing_viewer.DefaultView;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.Viewer;

public class PanelGraph extends JPanel {
	private DefaultView view;

	public PanelGraph(SingleGraph graph) {
		setLayout(new BorderLayout());

		SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.enableAutoLayout();

		SwingGraphRenderer renderer = new SwingGraphRenderer();
		view = new DefaultView(viewer, "PanelGraphe", renderer);

		this.add(view, BorderLayout.CENTER);
		maj();
	}

	public void maj() {
		Thread threadUpdate = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(2);
					view.updateUI();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		threadUpdate.start();
	}
}