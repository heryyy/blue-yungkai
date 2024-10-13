import javax.swing.*;
import java.awt.*;

public class Blue extends JPanel {

    private String[] lyrics = {
            "Your morning eyes, I could stare like watching stars",
            "I could walk you by, and I'll tell without a thought",
            "You'd be mine",
            "Would you mind if I took your hand tonight",
            "Know you're all that I want this life",
            "I'll imagine we fell in love",
            "I'll nap under moonlight skies with you",
            "I think I'll picture us, you with the waves",
            "The oceans colors on your face",
            "I'll leave my heart with your air",
            "So let me fly with you",
            "Will you be forever with me",
            "[Guitar]",
            "My love will always stay by you",
            "I'll keep it safe so don't you worry a thing,",
            "I'll tell you I love you more",
            "It's stuck with you forever so promise you won't let it go",
            "I'll trust the universe will always bring me to you",
            "I'll imagine we fell in love",
            "I'll nap under moonlight skies with you",
            "I think I'll picture us, you with the waves",
            "The oceans colors on your face",
            "I'll leave my heart with your air",
            "So let me fly with you",
            "Will you be forever with me"
    };

    private int[] delays = {
            400, 300, 300, 400, 3300, 200, 200, 200, 400, 400, 400, 400,
            30000, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 1000
    };

    private int currentIndex = 0;
    private String currentLine = "";
    private int currentCharIndex = 0;
    private ImageIcon backgroundGif;

    public Blue() {
        setPreferredSize(new Dimension(1280, 720));
        backgroundGif = new ImageIcon("bluesky.gif");
        new Thread(() -> {
            try {
                while (currentIndex < lyrics.length) {
                    if (currentCharIndex < lyrics[currentIndex].length()) {
                        currentLine += lyrics[currentIndex].charAt(currentCharIndex);
                        currentCharIndex++;
                        repaint();
                        Thread.sleep(110);
                    } else {
                        Thread.sleep(delays[currentIndex]);
                        currentIndex++;
                        currentLine = "";
                        currentCharIndex = 0;
                        repaint();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundGif != null) {
            g.drawImage(backgroundGif.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
        g.setFont(new Font("Serif", Font.PLAIN, 42));
        g.setColor(Color.WHITE);

        // Hitung posisi tengah vertikal
        int stringWidth = g.getFontMetrics().stringWidth(currentLine);
        int x = (getWidth() - stringWidth) / 2;
        int y = getHeight() / 2;

        // Gambar lirik di tengah
        g.drawString(currentLine, x, y);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lyrics");
        Blue panel = new Blue();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
