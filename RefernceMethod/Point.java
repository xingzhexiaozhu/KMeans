package KMeans.RefernceMethod;

/**
 * 二维坐标点类
 */
public class Point implements Comparable<Point>{
    //横坐标
    private double x;
    //纵坐标
    private double y;
    //以此坐标为聚类中心的类的类名称
    private String className;
    //坐标之间的欧氏距离
    private Double distance;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y, String className){
        this.x = x;
        this.y = y;
        this.className = className;
    }

    public void getDistance(Point p){
        if (p == null)
            return;
        this.distance = (this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y);
    }

    @Override
    public int compareTo(Point point) {
        return this.distance.compareTo(point.distance);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
