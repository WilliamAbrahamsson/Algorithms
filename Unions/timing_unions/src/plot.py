import matplotlib.pyplot as plt
import numpy as np

# FIG 1

# Define the data
x = [10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000]
union_y = [0.0404867958, 0.1497616665, 0.3367359293, 0.6028086291999999, 0.9408153124000002, 1.3602797458000002, 1.8510549166, 2.4149790792999997, 3.0625999292, 3.8183760959000006]
quickunion_y = [0.012525965299999486, 0.022046548599999972, 0.02996784799999999, 0.03988688309999972, 0.049440250300000044, 0.05984210030000013, 0.07095866870000003, 0.07947220199999985, 0.09093096470000009, 0.09941708270000023]

c=-31.78
b=2.03


math_union_y = [(2**c * (x**b)) for x in x]


# Create a figure and axis
fig, ax = plt.subplots()

# Plot the data
ax.scatter(x, union_y, label='Union Model')
ax.plot(x, math_union_y, label='Computed Union Model')
ax.scatter(x, quickunion_y, label='QuickUnion Model')

# Add axis labels
ax.set(xlabel='Number of Unions', ylabel='Time (seconds)',
       title='Union Algorithm Performance')

ax.legend()

# FIG 2

# Create a new figure and axis
fig2, ax2 = plt.subplots()


# to 1 milly
x_range = np.linspace(100000, 1000000)

# Math model with 10x range
math_union_y_range = [(2**c * (x**b)) for x in x_range]

# Plot the data
ax2.scatter(x, union_y, label='Union Model')
ax2.plot(x_range, math_union_y_range, label='Computed Union Model (extended)')

# Add axis labels and a title
ax2.set(xlabel='Number of Unions', ylabel='Time (seconds)',
       title='Union Algorithm Performance (Zoomed Out 10X)')

ax2.legend()


# FIG 3 (Only QuickUnion)

# Create a new figure and axis
fig3, ax3 = plt.subplots()

# Plot the QuickUnion data
b2 = 1
c2 = -19.96
math_qunion_y = [(2**c2 * (x**b2)) for x in x]
ax3.scatter(x, quickunion_y, label='QuickUnion Model', color='orange')
ax3.plot(x, math_qunion_y, label='Computed QuickUnion Model', color='orange')


# Add axis labels and a title
ax3.set(xlabel='Number of Unions', ylabel='Time (seconds)',
       title='QuickUnion Algorithm Performance')


# Add a legend
ax3.legend()


# FIG 4

# Create a new figure and axis
fig4, ax4 = plt.subplots()

# to 1 milly
x_range = np.linspace(100000, 1000000)

# Math model with 10x range
math_qunion_y_range = [(2**c2 * (x**b2)) for x in x_range]

# Plot the data
ax4.scatter(x, quickunion_y, label='QuickUnion Model', color='orange')
ax4.plot(x_range, math_qunion_y_range, label='Computed QuickUnion Model (extended)', color='orange')

# Add axis labels and a title
ax4.set(xlabel='Number of Unions', ylabel='Time (seconds)',
       title='QuickUnion Algorithm Performance (Zoomed Out 10X)')

ax4.legend()

plt.show()



