import networkx as nx
import matplotlib.pyplot as plt
from directed_data import directed_data as directed_adj_list
from undirected_data import undirected_data as undirected_adj_list

def create_graph(adj_list, title):
    G = nx.Graph() if "undirected" in title.lower() else nx.DiGraph()
    G.add_nodes_from(adj_list.keys())
    G.add_edges_from([(node, neighbor) for node in adj_list for neighbor in adj_list[node] if node != neighbor])
    return G

directed_graph = create_graph(directed_adj_list, "Directed Graph")
undirected_graph = create_graph(undirected_adj_list, "Undirected Graph")

fig1, ax1 = plt.subplots()
nx.draw(directed_graph, with_labels=True, ax=ax1)
ax1.set_title("Directed Graph")

fig2, ax2 = plt.subplots()
nx.draw(undirected_graph, with_labels=True, ax=ax2)
ax2.set_title("Undirected Graph")

plt.show()
