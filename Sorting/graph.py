import matplotlib.pyplot as plt
import numpy as np
from insert_times import insert_times
from heap_times import heap_times

# Figure for Insert Times
plt.figure(1)
depth = np.arange(0, len(insert_times))
plt.scatter(depth, insert_times, c='blue', label='Insert Times')
plt.title('Insert Times / recursion depth')
plt.xlabel('Recursion Depth')
plt.ylabel('Time (ms)')
plt.legend()

# Figure for Heap Times
plt.figure(2)
depth = np.arange(0, len(heap_times))
plt.scatter(depth, heap_times, c='green', label='Heap Times')
plt.title('Heap Times / recursion depth')
plt.xlabel('Recursion Depth')
plt.ylabel('Time (ms)')
plt.legend()

# Figure for Insert Times and Heap Times
plt.figure(3)
depth = np.arange(0, len(insert_times))
insert_times = np.array(insert_times)
heap_times = np.array(heap_times)
plt.plot(depth, insert_times, c='blue', label='Insert Times')
plt.plot(depth, heap_times, c='green', label='Heap Times')
plt.title('Insert Times and Heap Times / recursion depth')
plt.xlabel('Recursion Depth')
plt.ylabel('Time (ms)')
plt.legend()

# show the plot
plt.show()
