package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameMain extends JFrame implements ActionListener {
	
	public static final int WIDHT = 800, HEIGHT = 600;
	private Container container;
	private JButton btnPlayPause;
	private JButton btnFin;
	private JButton btnStart;
	private PanelGame panelGame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new GameMain();
	}

	/**
	 * Create the frame.
	 */
	public GameMain() {
		setTitle("Space Ship Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(300, 200, WIDHT, HEIGHT);
		container = getContentPane();
		container.setLayout(new BorderLayout(0, 0));
		
		// Panel Game
		panelGame = new PanelGame();
		
		// Panel footer
		JPanel panelFooter = new JPanel();
		panelFooter.setBackground(Color.decode("#6A6DE2"));
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(this);
		panelFooter.add(btnStart);

		btnPlayPause = new JButton("Play/Pause");
		btnPlayPause.addActionListener(this);
		btnPlayPause.setEnabled(false);
		panelFooter.add(btnPlayPause);

		btnFin = new JButton("Fin");
		btnFin.addActionListener(this);
		panelFooter.add(btnFin);

		container.add(panelGame, BorderLayout.CENTER);
		container.add(panelFooter, BorderLayout.SOUTH);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object boton = e.getSource();
		if (boton == btnStart) {
			btnStartOnClick();
		} else if (boton == btnPlayPause) {
			btnPlayPauseOnClick();
		} else if (boton == btnFin) {
			btnFinOnClick();
		}
	}

	private void btnStartOnClick() {
		panelGame.gameLoop.start();
		panelGame.requestFocus();
		btnStart.setEnabled(false);
		btnPlayPause.setEnabled(true);
	}

	private void btnPlayPauseOnClick() {
		panelGame.isPause=!(panelGame.isPause);
		panelGame.requestFocus();
	}

	private void btnFinOnClick() {
		panelGame.isRunning = false;
		System.exit(0);
	}
	
}
