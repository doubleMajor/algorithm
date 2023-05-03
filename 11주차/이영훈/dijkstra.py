
from collections import deque

graph = [
    [0,  3, 4,  0, 0,  0, 0, 0],
    [3,  0, 5,  0, 0,  9, 0, 0],
    [4,  5, 0,  8, 5,  0, 0, 0],
    [0, 10, 8,  0, 6, 10, 7, 3],
    [0,  0, 5,  6, 0,  0, 4, 0],
    [0,  9, 0, 10, 0,  0, 0, 2],
    [0,  0, 0,  7, 5,  0, 0, 5],
    [0,  0, 0,  4, 0,  2, 5, 0],
]

distance = [0] * len(graph)
visited = [0] * len(graph)

INF = 0xFFFFFFFF
# start = 1
for i in range(len(distance)):
    if (i+1) != 1:
        distance[i] = INF

q = deque([0])
while q:
    cur = q.popleft()
    visited[cur] = 1
    for i in range(len(graph)):
        edge = graph[cur][i]
        if edge != 0 and visited[i] == 0:
            dist = distance[cur] + edge
            distance[i] = min(distance[i], dist)
            q.append(i)

print(distance)