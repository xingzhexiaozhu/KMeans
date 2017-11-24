# coding=utf-8

import matplotlib.pyplot as plt

x = [3,4,9,14,18,21]
y = [3,10,6,8,11,7]

plt.plot(x, y, 'ro')
plt.xlabel("X")
plt.ylabel("Y")
plt.title("Point")
plt.show()