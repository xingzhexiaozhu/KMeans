package KMeans.RefernceMethod;

import java.io.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;

public class KMeansUtil {
    //聚类的类别个数
    private int classNum;
    //聚类的类名称
    private ArrayList<String> classNames;
    //聚类的中心点
    private ArrayList<Point> classPoints;
    //数据集中所有数据点
    private ArrayList<Point> points;

    public KMeansUtil(String filePath, int classNum){
        this.classNum = classNum;
        LoadDataSet(filePath);
    }

    public void LoadDataSet(String filePath){
        File file = new File(filePath);
        ArrayList<String[]> data = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            while((str = br.readLine()) != null){
                String[] tmpArray = str.split(" ");
                data.add(tmpArray);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        classPoints = new ArrayList<>();
        points = new ArrayList<>();
        classNames = new ArrayList<>();
        for (int i=0, j=1; i<data.size(); i++){
            if (j <= classNum){
                classPoints.add(new Point(Double.parseDouble(data.get(i)[0]), Double.parseDouble(data.get(i)[1]), j+""));
                classNames.add(j + "");
                j++;
            }
            points.add(new Point(Double.parseDouble(data.get(i)[0]), Double.parseDouble(data.get(i)[1])));
        }
    }

    public void KMeansClustering(){
        double tmpX = 0;
        double tmpY = 0;
        int count = 0;
        double err = Integer.MAX_VALUE;
        Point tmpPoint;

        //设置迭代阈值
        while(err > 0.01 * classNum){
            //计算数据集中各点到每个中心点的距离，并将其划分到最近那个中心点
            for (Point point1 : points){
                for (Point point2 : classPoints){
                    point2.getDistance(point1);
                }
                Collections.sort(classPoints);
                //设置每个坐标点的所属类的类名
                point1.setClassName(classPoints.get(0).getClassName());
            }

            err = 0;
            //重新计算每个类的中心点
            for (Point point1 : classPoints){
                count = 0;
                tmpX = 0;
                tmpY = 0;
                for (Point point2 : points){
                    if (point2.getClassName().equals(point1.getClassName())){
                        count++;
                        tmpX += point2.getX();
                        tmpY += point2.getY();
                    }
                }
                tmpX /= count;
                tmpY /= count;
                err += Math.abs((tmpX - point1.getX()));
                err += Math.abs((tmpY - point1.getY()));
                point1.setX(tmpX);
                point1.setY(tmpY);
            }

            for (int i=0; i<classPoints.size(); i++){
                tmpPoint = classPoints.get(i);
                System.out.println(MessageFormat.format("聚类中心点{0}，x={1}，y={2}", (i+1), tmpPoint.getX(), tmpPoint.getY()));
            }
            System.out.println("**************");
        }

        System.out.println("最终的聚类结果：");
        for (int i=0; i<classPoints.size(); i++){
            tmpPoint = classPoints.get(i);
            System.out.println(MessageFormat.format("聚类中心点{0}，x={1}，y={2}", (i+1), tmpPoint.getX(), tmpPoint.getY()));
        }
    }
}
