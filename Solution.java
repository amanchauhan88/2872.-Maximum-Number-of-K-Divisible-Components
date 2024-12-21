class Solution {
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        // Build the adjacency list for the tree
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        // Use an array to track visited nodes
        boolean[] visited = new boolean[n];

        // Helper function to perform DFS and calculate components
        int[] result = new int[1]; // Store the number of valid components
        dfs(0, graph, values, visited, k, result);

        return result[0] + 1; // Add 1 for the root component
    }

    private int dfs(int node, List<Integer>[] graph, int[] values, boolean[] visited, int k, int[] result) {
        visited[node] = true;

        // Start with the value of the current node
        int currentSum = values[node];

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                // Perform DFS on the child node
                int childSum = dfs(neighbor, graph, values, visited, k, result);

                // Check if the child component can form a valid split
                if (childSum % k == 0) {
                    result[0]++; // Increment the number of valid components
                } else {
                    // Otherwise, add the child's sum to the current node's sum
                    currentSum += childSum;
                }
            }
        }

        // Return the total sum of the current component
        return currentSum % k == 0 ? 0 : currentSum;
    }
}
