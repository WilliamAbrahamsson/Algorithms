import matplotlib.pyplot as plt
import numpy as np
from offsets import offsets


# Figure for Insert Times
plt.figure(1)
depth = np.arange(0, len(offsets))
plt.scatter(depth, offsets, c='blue', label='Offsets')
plt.title('Offsets / nth insertion')
plt.xlabel('nth insertion')
plt.ylabel('Offset')
plt.legend()

plt.show()
