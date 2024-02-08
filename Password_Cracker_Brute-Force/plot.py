from matplotlib import pyplot as plt

plt.style.use('seaborn')

# lenght of password that is tested
x = [1, 2, 3, 4]

all_combos_threads = [0.01400303840637207, 0.027005434036254883, 0.04799842834472656, 1.123262882232666]
all_combos_processes = [0.20799469947814941, 0.2100365161895752, 0.2110443115234375, 0.37593555450439453]

find_password_threads = [0.0, 0.0009982585906982422, 0.007989168167114258, 0.21106314659118652]
find_password_processes = [0.16403841972351074, 0.17303919792175293, 0.1780414581298828, 0.20703744888305664]

# time plot for all comintaions
plt.scatter(x, all_combos_threads, s = 100, c = 'yellow', edgecolor = 'black', linewidth = 1, alpha = 0.75)
plt.scatter(x, all_combos_processes, s = 100, c = 'orange', edgecolor = 'black', linewidth = 1, alpha = 0.75)
plt.xlabel("Length of password        thread version(yellow), process version(orange)")
plt.ylabel("Time for all password combos(s)")

# time plot for finding all passwords
# plt.scatter(x, find_password_threads, s = 100, c = 'yellow', edgecolor = 'black', linewidth = 1, alpha = 0.75)
# plt.scatter(x, find_password_processes, s = 100, c = 'orange', edgecolor = 'black', linewidth = 1, alpha = 0.75)
# plt.xlabel("Length of password     thread version(yellow), process version(orange)")
# plt.ylabel("Time to find password(s)")
# plt.tight_layout()


plt.tight_layout()
plt.show()