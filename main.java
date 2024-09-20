import javax.swing.JFrame;

public class main {
    public static void main(String[] args) {
        GUI gui = new GUI();
        JFrame frame = new JFrame();
        frame.add(gui);
        frame.setVisible(true);
        frame.setSize(1104, 621);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("3D Renderer");
        System.out.println("GUI created");
    }
}