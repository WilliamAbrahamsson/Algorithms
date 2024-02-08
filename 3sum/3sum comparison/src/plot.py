import matplotlib.pyplot as plt
import numpy as np

# FIG 1

# Define the data
standard_y = [0.007684625, 0.01215975, 0.015437125, 0.035897958, 0.0701565, 0.119056833, 0.187467041, 0.278598375, 0.396076584, 0.544142875, 0.717807334, 0.93293025, 1.183028208, 1.472521917, 1.812441417, 2.194400292, 2.626887959, 3.118993583, 3.6665875, 4.262797208]
better_y = [1.464079E-4, 1.0186958999999994E-4, 2.4299375E-4, 2.922095100000001E-4, 2.5139041000000003E-4, 2.9576333000000014E-4, 4.554329300000002E-4, 5.496249500000001E-4, 7.376112800000002E-4, 8.113229200000004E-4, 0.00111683672, 0.00135283171, 0.0013644387900000002, 0.0018718596200000004, 0.0017280946300000004, 0.0022871691799999996, 0.0026395196299999995, 0.002485977529999999, 0.00278269795, 0.00313129421]

# x-axis all the way to 2000
x = [100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000]

# Create a figure and axis
fig, ax = plt.subplots()

# Plot the data
ax.scatter(x, better_y, label='Better 3Sum Algorithm', color='green')
ax.scatter(x, standard_y, label='Standard 3Sum Algorithm', color='red')

# math equation for standard 3sum
standard_b = 2.99
standard_c = -30.67
math_standard = [(2**standard_c * (x**standard_b)) for x in x]
ax.plot(x, math_standard, label='Standard 3Sum Algorithm (Math model)', color='red')

better_b = 2.15
better_c = -31.57
math_better = [(2**better_c * (x**better_b)) for x in x]

ax.plot(x, math_better, label='Better 3Sum Algorithm (Math model)', color='green')


# Add axis labels
ax.set(xlabel='Number of elements', ylabel='Time (seconds)',
       title='3Sum Algorithm Performance')

ax.legend()



# FIG 2

# Create a new figure and axis
fig2, ax2 = plt.subplots()

# to 1 milly
x_range = np.linspace(1000, 20000)

# Math model with 10x range
math_standard = [(2**standard_c * (x**standard_b)) for x in x_range]


# Plot the data
ax2.scatter(x, standard_y, label='Standard 3Sum Algorithm', color="red")
ax2.plot(x_range, math_standard, label='Standard 3Sum Algorithm (Math model)', color='red')

# Add axis labels and a title
ax2.set(xlabel='Number of elements', ylabel='Time (seconds)',
       title='Standard 3sum Algorithm Performance (Zoomed Out 10X)')

ax2.legend()


# FIG 3

# Create a new figure and axis
fig3, ax3 = plt.subplots()


# to 1 milly
x_range = np.linspace(250, 20000)

# Math model with 10x range
math_better = [(2**better_c * (x**better_b)) for x in x_range]


# Plot the data
ax3.scatter(x, better_y, label='Better 3Sum Algorithm', color='green')
ax3.plot(x_range, math_better, label='Better 3Sum Algorithm (Math model)', color='green')

# Add axis labels and a title
ax3.set(xlabel='Number of elements', ylabel='Time (seconds)',
       title='Better 3sum Algorithm Performance (Zoomed Out 10X)')

ax3.legend()


# zoomed out version of better 3sum
# Create a new figure and axis
fig4, ax4 = plt.subplots()

math_better = [(2**better_c * (x**better_b)) for x in x]

ax4.scatter(x, better_y, label='Better 3Sum Algorithm', color='green')
ax4.plot(x, math_better, label='Better 3Sum Algorithm (Math model)', color='green')

# Add axis labels and a title
ax4.set(xlabel='Number of elements', ylabel='Time (seconds)',

       title='Better 3sum Algorithm Performance (Zoomed In)')
ax4.legend()

# Show the plot
plt.show()