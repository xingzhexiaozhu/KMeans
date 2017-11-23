package KMeans.RefernceMethod;

public class Client {
    public static void main(String[] args){
        String filePath = "D:\\Program\\MachineLearn\\src\\main\\java\\KMeans\\Data\\dataset.txt";
        int classNum = 3;
        KMeansUtil kMeansUtil = new KMeansUtil(filePath, classNum);
        kMeansUtil.KMeansClustering();
    }
}
