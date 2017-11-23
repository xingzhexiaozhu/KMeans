package KMeans.MyMethod;

/**
 * 客户端实现KMeans工具类的调用
 */

public class Client {
    public static void main(String[] args){
        String path = "D:\\Program\\MachineLearn\\src\\main\\java\\KMeans\\Data\\dataset.txt";
        KMeans kMeans = new KMeans(path, 3);
        kMeans.doKMeans();
    }
}
