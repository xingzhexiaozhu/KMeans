# coding=utf-8
import math

from KMeansModel import Point

# KMeans聚类实现
class KMeans(object):
    def __init__(self, data, num):
        # 数据集中的点
        self.points = []
        # 簇中心点
        self.center_points = []
        # 聚类结果的集合簇
        self.clusters = {}
        # 类簇个数
        self.num = num
        # 读文件
        self.loadData(data)

    def loadData(self, dataset):
        # 读取数据集中的数据点
        for data in dataset:
            self.points.append(Point.Point(data[0], data[1]))
        # 初始化KMeans模型，选择前K个数据点作为簇中心点
        for i in range(self.num):
            self.center_points.append(self.points[i])
            self.clusters[i] = []

    def doKMeans(self):
        err = 100.0
        while err > 0.01*self.num:
            # 每次计算新簇时，清空原来聚类的簇
            for key in self.clusters:
                self.clusters[key] = []
            # 计算每个点所划分的簇
            for point in self.points:
                self.dispatchPointToCluster(point)
            # 计算每个簇的中心点，并得到更新后中心点的偏移误差
            err = self.getClusterCenterPoint()
            self.Show()
            print("******************")

    # 计算每个点所划分的簇
    def dispatchPointToCluster(self, point):
        index = 0
        min_distance = 100.0
        for i in range(len(self.center_points)):
            distance = self.getDistance(point, self.center_points[i])
            if distance < min_distance:
                min_distance = distance
                index = i
        point_list = self.clusters[index]
        point_list.append(point)
        self.clusters[index] = point_list

    # 计算每个簇的中心点，并得到更新后中心点的偏移误差
    def getClusterCenterPoint(self):
        err = 0
        for i in range(len(self.center_points)):
            tmpCenterPoint = self.center_points[i]
            centerX = 0
            centerY = 0
            point_list = self.clusters[i]
            for point in point_list:
                centerX += point.get_x()
                centerY += point.get_y()
            centerX /= len(point_list)
            centerY /= len(point_list)
            err += math.fabs(centerX - tmpCenterPoint.get_x())
            err += math.fabs(centerY - tmpCenterPoint.get_y())
            center = Point.Point(centerX, centerY)
            self.center_points[i] = center
        return err


    # 计算两点之间的距离
    def getDistance(self, point1, point2):
        return math.pow(point1.get_x()-point2.get_x(), 2) + math.pow(point1.get_y()-point2.get_y(), 2)


    def Show(self):
        for i in range(len(self.center_points)):
            print("Center point: ")
            self.center_points[i].show_point()
            print("cluster: ")
            for p in self.clusters[i]:
                p.show_point()

if __name__=='__main__':
    data = [[3,3],[4,10],[9,6],[14,8],[18,11],[21,7]]
    kmeans = KMeans(data, 3)
    kmeans.doKMeans()
