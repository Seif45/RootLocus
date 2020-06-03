import javafx.scene.shape.ArcTo;

public class RootLocus {

    private int[][] poles;
    private int[] asymptotesAngles = new int[4];
    private double centroid;
    private int[] pointsRealAxis;
    private double breakaway;
    private double departureAngle;
    private double pointImaginaryAxis;

    public RootLocus(int[][] poles){
        this.poles = poles;
        anglesAsymptotes();
        calculateCentroid();
        realAxis();
        breakawayPoint();
        angleDeparture();
        imaginaryAxis();
    }

    private void anglesAsymptotes(){
        for (int i = 0 ; i < poles.length; i++){
            asymptotesAngles[i] = (2*i+1)*180/poles.length;
        }
    }

    private void calculateCentroid(){
        int total = 0 ;
        for (int i = 0 ; i < poles.length ; i++){
            total += poles[i][0];
        }
        centroid =(double) total / (double) poles.length;
    }

    private void realAxis(){
        int[] temp = new int[2];
        int k = 0;
        for (int i = 0 ; i < poles.length ; i++){
            if (poles[i][1] == 0){
                temp[k++] = poles[i][0];
            }
        }
        pointsRealAxis = temp;
    }

    private void breakawayPoint(){
        double[] rootsDerivative = {-9.150390137,-38.6401693,-45.95944056};
        for (int i = 0 ; i < rootsDerivative.length; i++){
            if (rootsDerivative[i] <= pointsRealAxis[0] && rootsDerivative[i] >= pointsRealAxis[1]){
                breakaway = rootsDerivative[i];
                break;
            }
            else if (rootsDerivative[i] >= pointsRealAxis[0] && rootsDerivative[i] <= pointsRealAxis[1]){
                breakaway = rootsDerivative[i];
                break;
            }
        }
    }

    private void angleDeparture(){
        int [][] complex = new int[1][2];
        for (int i = 0 ; i < poles.length ; i++){
            if (poles[i][1] > 0){
                complex[0] = poles[i];
            }
        }
        double phi = -90.0;
        for (int i = 0 ; i < poles.length ; i++){
            if (poles[i][1] == 0){
                double denomerator = (complex[0][0]*-1.0) - (poles[i][0]*-1.0);
                double tana = Math.atan(complex[0][1] / denomerator) * 180 / Math.PI;
                phi -= 180.0 - tana;
            }
        }
        departureAngle = phi + 180.0;
    }

    private void imaginaryAxis(){
        int s2 = 520;
        pointImaginaryAxis = Math.sqrt(s2);
    }

    public double[][] getPoints(){
        double[][] points = new double[poles.length+3][2];
        int i = 0;
        for (int j = 0 ; j < poles.length; j++){
            points[i][0] = poles[j][0];
            points[i][1] = poles[j][1];
            i++;
        }
        points[i][0] = breakaway;
        points[i][1] = 0;
        points[i+1][0] = 0;
        points[i+1][1] = pointImaginaryAxis;
        points[i+2][0] = centroid;
        points[i+2][1] = 0;
        return points;
    }

    public int[] getAsymptotesAngles() {
        return asymptotesAngles;
    }

    public String print(){
        String toReturn = "";
        toReturn += "The Poles are " + poles[0][0] + ", " + poles[1][0] + ", " + poles[2][0] + " ± " + poles[2][1] + "i";
        toReturn += "\r\n";
        toReturn += "Angles of Asymptotes are " + asymptotesAngles[0] + ", " + asymptotesAngles[1] + ", " + asymptotesAngles[2] + ", " + asymptotesAngles[3];
        toReturn += "\r\n";
        toReturn += "The Centroid is " + centroid;
        toReturn += "\r\n";
        toReturn += "Breakaway point is " + breakaway;
        toReturn += "\r\n";
        toReturn += "Angle of Departure is " + departureAngle;
        toReturn += "\r\n";
        toReturn += "Intersect with imaginary axis in ±" + pointImaginaryAxis + "i";
        return toReturn;
    }
}