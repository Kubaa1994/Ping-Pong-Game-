




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ping implements ActionListener {
    JFrame frame;

    int ballPositionX = 70;
    int ballPositionY = 70;
    int ballSpeedX = 1;
    int ballSpeedY = 1;
    int ballWidth = 20;
    int ballHeight = 20;
    int baddlePositionX = 50;
    int baddlePositionY = 300;
    int baddleWidth = 20;
    int baddleHeight = 80;
    int baddleSpeedY = 5;
    int paddleRightX =550;
    int paddleRightY = 300;
    int paddleRightWidth = 20;
    int paddleRightHeight = 80;
    int paddleRightSpeedY = 5;
    int frameWidth = 600;
    int frameHeight = 400;
    int rightPlayerScore = 0;
    int leftPlayerScore = 0;

    public static void main(String[] args) {
        Ping gui = new Ping();
        gui.go();
    }


    public void actionPerformed(ActionEvent event) {
        // Empty implementation or any other code you want to execute when an action is performed
    }

    public void go() {
        frame = new JFrame();

        KeyboardListener keyboardListener = new KeyboardListener();
        frame.addKeyListener(keyboardListener);

        MyDrawPanel myDrawPanel = new MyDrawPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, myDrawPanel);
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);

        while (true) {
            ballPositionX = ballPositionX + ballSpeedX;
            ballPositionY = ballPositionY + ballSpeedY;

    
           // Ball bouncing off the walls
            if (ballPositionX > (frameWidth - ballWidth)) {
                // Increase the score and reset ball position
                rightPlayerScore++;
                ballPositionX = frameWidth / 2;
                ballPositionY = frameHeight / 2;
                // You can also add additional logic here if needed
            }


                // left wall when the ball hit the left wall
            if (ballPositionX < 0) {
                
                leftPlayerScore++;
                ballPositionX = frameWidth / 2;
                ballPositionY = frameHeight / 2;
            }


            if (ballPositionY > (frameHeight - ballHeight) || ballPositionY < 0) {
                ballSpeedY = -ballSpeedY;
            }

            // Ball interaction with paddles
            if (ballPositionX < baddlePositionX + baddleWidth && ballPositionY > baddlePositionY && ballPositionY < baddlePositionY + baddleHeight) {        
                ballSpeedX = -ballSpeedX;
            }






            if (ballPositionX > paddleRightX && ballPositionY > paddleRightY
                    && ballPositionY < paddleRightY + paddleRightHeight) {
                ballSpeedX = -ballSpeedX;
            }

            myDrawPanel.repaint();

            try {
                Thread.sleep(8);
            } catch (Exception ex) {
            }
        }
    }

    class MyDrawPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            g.setColor(Color.blue);
            g.fillOval(ballPositionX, ballPositionY, ballWidth, ballHeight);

            g.setColor(Color.orange);
            g.fillRect(baddlePositionX, baddlePositionY, baddleWidth, baddleHeight);

            g.setColor(Color.green);
            g.fillRect(paddleRightX, paddleRightY, paddleRightWidth, paddleRightHeight);




            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Score: " + rightPlayerScore, frameWidth - 100, 20);

            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Score: " + leftPlayerScore, 20, 20);
            
            
        }
    }   

    class KeyboardListener implements KeyListener {
        public void keyPressed(KeyEvent e) {
            // Move the left paddle up
            if (e.getKeyCode() == KeyEvent.VK_W && baddlePositionY > 0) {
                baddlePositionY -= baddleSpeedY;
            }

            // Move the left paddle down
            if (e.getKeyCode() == KeyEvent.VK_S && baddlePositionY < frameHeight - baddleHeight) {
                baddlePositionY += baddleSpeedY;
            }

            // Move the right paddle up
            if (e.getKeyCode() == KeyEvent.VK_UP && paddleRightY > 0) {
                paddleRightY -= paddleRightSpeedY;
            }

            // Move the right paddle down
            if (e.getKeyCode() == KeyEvent.VK_DOWN && paddleRightY < frameHeight - paddleRightHeight) {
                paddleRightY += paddleRightSpeedY;
            }
        }

        public void keyReleased(KeyEvent e) {
        }

        public void keyTyped(KeyEvent e) {
        }
    }
}
