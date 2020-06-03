import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class GUI extends JPanel{
    int mar=50;
    RootLocus rl;

    public GUI (RootLocus rl){
        this.rl = rl;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g1=(Graphics2D)g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int width=getWidth();
        int height=getHeight();
        g1.draw(new Line2D.Double(width/2 + width/8,mar,width/2 + width/8,height-mar));
        g1.draw(new Line2D.Double(mar,height/2,width-mar,height/2));
        g1.setPaint(Color.BLACK);
        for (int i = 0; i < 4; i++){
            g1.fill(new Ellipse2D.Double((width/2 + i*width/8)-2, height/2-2,4,4));
            g1.drawString(String.valueOf(20*i - 20),(width/2 + i*width/8)-2,height/2-2);
            g1.fill(new Ellipse2D.Double((width/2 - i*width/8)-2, height/2-2,4,4));
            g1.drawString(String.valueOf(20*-i - 20),(width/2 - i*width/8)-2,height/2-2);
            if ( i < 2){
                g1.fill(new Ellipse2D.Double((width/2+width/8)-2, (height/2 + i*width/8)-2,4,4));
                g1.drawString(String.valueOf(20*-i),(width/2 + width/8)-2,(height/2 + i*width/8)-2);
                g1.fill(new Ellipse2D.Double((width/2+width/8)-2, (height/2 - i*width/8)-2,4,4));
                g1.drawString(String.valueOf(20*i),(width/2 + width/8)-2,(height/2 - i*width/8)-2);

            }
        }


        double[][] points = rl.getPoints();
        g1.setPaint(Color.RED);
        double scale = (width/8) / 20;
        for (int i = 0 ; i < points.length - 1; i++){
            g1.fill(new Ellipse2D.Double((width/2+width/8)+(points[i][0] * scale)-3,(height/2)+(points[i][1] * scale)-3 ,6,6));
        }
        g1.fill(new Ellipse2D.Double((width/2+width/8) +(points[5][0] * scale) -3,(height/2)-(points[5][1] * scale)-3,6,6));
        g1.setPaint(Color.YELLOW);
        g1.fill(new Ellipse2D.Double((width/2+width/8)+(points[points.length - 1][0] * scale)-4,(height/2)+(points[points.length - 1][1] * scale)-4 ,8,8));

        int[] angles = rl.getAsymptotesAngles();
        g1.setPaint(Color.BLACK);
        double x1 = (width/2+width/8)+(points[points.length - 1][0] * scale);
        double y1 = (height/2)+(points[points.length - 1][1] * scale);
        for (int i = 0 ; i < angles.length; i++){
            g1.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
            g1.drawLine((int)x1,(int)y1,(int) (x1 + 700 * Math.cos(angles[i] * Math.PI / 180)),(int)(y1 + 700 * Math.sin(angles[i] * Math.PI / 180)));

        }
        g1.setPaint(Color.BLUE);
        g1.setStroke(new BasicStroke(2));
        g1.drawLine((int)((width/2+width/8) +(points[0][0] * scale)),(int)((height/2)+(points[0][1] * scale)),(int)((width/2+width/8) +(points[1][0] * scale)),(int)((height/2)+(points[1][1] * scale)));

        QuadCurve2D curve2D = new QuadCurve2D.Double();
        curve2D.setCurve((width/2+width/8) + (points[5][0] * scale)+400,(height/2)+(points[5][1] * scale)+315,(width/2+width/8) + (points[4][0] * scale) - 510,(height/2)+(points[4][1] * scale),(width/2+width/8) + (points[5][0] * scale)+400,(height/2)-(points[5][1] * scale)-315);
        g1.draw(curve2D);


        curve2D = new QuadCurve2D.Double();
        curve2D.setCurve((width/2+width/8) + (points[2][0] * scale),(height/2)+(points[2][1] * scale),0,1650,(width/2+width/8) + (points[6][0] * scale) -20000,(height/2)+(points[6][1] * scale)+20000);
        g1.draw(curve2D);

        curve2D = new QuadCurve2D.Double();
        curve2D.setCurve((width/2+width/8) + (points[3][0] * scale),(height/2)+(points[3][1] * scale),0,-700,(width/2+width/8) + (points[6][0] * scale) -20000,(height/2)+(points[6][1] * scale)-20000);
        g1.draw(curve2D);

        g1.setPaint(Color.MAGENTA);

        g1.fill(new Ellipse2D.Double((width/2+width/8)+(-20*scale)-4,(height/2) + (0*scale) -4,8,8));
        g1.fill(new Ellipse2D.Double((width/2+width/8)+(-1.77508467636853*scale)-4,(height/2) + (0*scale) -4,8,8));
        g1.fill(new Ellipse2D.Double((width/2+width/8)+(-51.6124576618157*scale)-4,(height/2) + (12.3661331343658*scale) -4,8,8));
        g1.fill(new Ellipse2D.Double((width/2+width/8)+(-51.6124576618157*scale)-4,(height/2) + (-12.3661331343658*scale) -4,8,8));

        g1.fill(new Ellipse2D.Double((width/2+width/8)+(-20*scale)-4,(height/2) + (0*scale) -4,8,8));
        g1.fill(new Ellipse2D.Double((width/2+width/8)+(-1.77508467636853*scale)-4,(height/2) + (0*scale) -4,8,8));
        g1.fill(new Ellipse2D.Double((width/2+width/8)+(-51.6124576618157*scale)-4,(height/2) + (12.3661331343658*scale) -4,8,8));
        g1.fill(new Ellipse2D.Double((width/2+width/8)+(-51.6124576618157*scale)-4,(height/2) + (-12.3661331343658*scale) -4,8,8));


    }

    public static void main(String args[]){
        JFrame frame =new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int[][] poles = {{0,0},{-25,0},{-50,10},{-50,-10}};
        RootLocus rl = new RootLocus(poles);
        frame.add(new GUI(rl));
        frame.setBounds(0,0,Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        JOptionPane.showMessageDialog(frame,rl.print());
    }
}