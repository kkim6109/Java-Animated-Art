//***************************************************************************
// Kevin Kim
// AOOD
// 2/8/18
//
// Art.java
//***************************************************************************

import java.awt.*;

import javax.swing.JApplet;

public class Art extends JApplet
{
	private Rainbows rain1 = new Rainbows();
	private Rainbows rain2 = new Rainbows();
	private Rainbows rain3 = new Rainbows();
	private int[] intArr1;
	private int[] intArr2;
	private int[] intToArr1;
	private int[] intToArr2;
	final private int AMOUNT = 75;
	final private int OFFSET = 5;
	final private int HEIGHT = 600;

	//-----------------------------------------------------------------
	//  Sets up the art
	//-----------------------------------------------------------------
	public Art () {
		rain1.size = rain2.size = rain3.size = 7;
		rain3.step = 3;
		rain2.step = 2;
		intArr1 = new int[AMOUNT];
		intArr2 = new int[AMOUNT];
		intToArr1 = new int[AMOUNT];
		intToArr2 = new int[AMOUNT];
		for (int i = 0; i < AMOUNT; i++) {
			intArr1[i] = (int)(Math.random() * HEIGHT);
			intArr2[i] = (int)(Math.random() * HEIGHT);
			intToArr1[i] = (int)(Math.random() * HEIGHT);
			intToArr2[i] = (int)(Math.random() * HEIGHT);
		}
	}

	//-----------------------------------------------------------------
	//  Paints the animation
	//-----------------------------------------------------------------
	public void paint (Graphics g)
	{
		Image temp = createImage(this.getWidth(), this.getHeight());
		Graphics page = temp.getGraphics();
		page.setColor(rain2.rainbow());
		page.fillRect(0, 0, this.getWidth(), this.getHeight());
		if (this.getSize().width < HEIGHT) {
			this.setSize(HEIGHT * 2 + 2 + OFFSET, HEIGHT + OFFSET);
		}
		else {
			int[] tempArr1 = new int[AMOUNT];
			int[] tempArr2 = new int[AMOUNT];
			for (int i = 0; i < AMOUNT; i++) {
				tempArr1[i] = intArr1[i] + HEIGHT + 2 + OFFSET;
				tempArr2[i] = intArr2[i] + OFFSET;
			}
			page.setColor(Color.black);
			page.drawLine((this.getWidth() - OFFSET) / 2, 0, (this.getWidth() - OFFSET) / 2, this.getHeight());
			page.fillOval((this.getWidth() - 2)/4 - 55, this.getHeight()/2 - 45, 30, 30);
			page.fillOval((this.getWidth() - 2)/4 + 25, this.getHeight()/2 - 45, 30, 30);
			page.fillOval((this.getWidth() - 2)/4 - 25, this.getHeight()/2, 50, 50);
			page.fillOval((this.getWidth() - 2)/4 * 3 - 55 - OFFSET, this.getHeight()/2 - 45, 30, 30);
			page.fillOval((this.getWidth() - 2)/4 * 3 + 25 - OFFSET, this.getHeight()/2 - 45, 30, 30);
			page.fillOval((this.getWidth() - 2)/4 * 3 - 25 - OFFSET, this.getHeight()/2, 50, 50);
			/*page.fillRect(170, 170, 60, 60);
			page.fillRect(572, 170, 60, 60);*/
			page.setColor(rain1.rainbow());
			page.fillPolygon(intArr1, intArr2, AMOUNT);
			page.setColor(rain3.rainbow());
			page.fillPolygon(tempArr1, tempArr2, AMOUNT);
			for (int i = 0; i < AMOUNT; i++) {
				if (intArr1[i] < intToArr1[i]) {
					intArr1[i]++;
				}
				else if (intArr1[i] > intToArr1[i]) {
					intArr1[i]--;
				}
				if (intArr2[i] < intToArr2[i]) {
					intArr2[i]++;
				}
				else if (intArr2[i] > intToArr2[i]) {
					intArr2[i]--;
				}
				while (intArr1[i] == intToArr1[i]) {
					intToArr1[i] = (int)(Math.random() * HEIGHT);
				}
				while (intArr2[i] == intToArr2[i]) {
					intToArr2[i] = (int)(Math.random() * HEIGHT);
				}
			}
			g.drawImage(temp, 0, 0, this);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}

}