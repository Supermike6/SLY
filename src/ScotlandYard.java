import java.util.*;

class ScotlandYard {
    private Map<Integer, List<TransportEdge>> graph; // A graph with transport types on edges
    private int currentPosition; // Mr. X's current position

    // Constructor that initializes the graph and starting position
    public ScotlandYard(int startingPosition) {
        graph = new HashMap<>();
        currentPosition = startingPosition;
    }

    // Add a connection between two locations with a specific transport type
    public void addConnection(int from, int to, String transportType) {
        graph.putIfAbsent(from, new ArrayList<>());
        graph.putIfAbsent(to, new ArrayList<>());
        graph.get(from).add(new TransportEdge(to, transportType));
        graph.get(to).add(new TransportEdge(from, transportType)); // Reverse connection
        graph.putIfAbsent(from, new ArrayList<>());
        graph.putIfAbsent(to, new ArrayList<>());
        graph.get(from).add(new TransportEdge(to, "f"));
        graph.get(to).add(new TransportEdge(from, "f")); // Reverse connection
    }
    public void addFConnection(int from, int to, String transportType) {
        graph.putIfAbsent(from, new ArrayList<>());
        graph.putIfAbsent(to, new ArrayList<>());
        graph.get(from).add(new TransportEdge(to, transportType));
        graph.get(to).add(new TransportEdge(from, transportType)); // Reverse connection

    }

    // Set Mr. X's current position (can be used to reveal his position)
    public void setCurrentPosition(int position) {
        currentPosition = position;
    }

    // Get Mr. X's current position
    public int getCurrentPosition() {
        return currentPosition;
    }

    // Get the possible moves from the current position based on a transport type
    public List<Integer> getPossibleMoves(String transportType) {
        List<Integer> possibleMoves = new ArrayList<>();
        for (TransportEdge edge : graph.getOrDefault(currentPosition, new ArrayList<>())) {
            if (edge.getTransportType().equals(transportType)) {
                possibleMoves.add(edge.getDestination());
            }
        }
        return possibleMoves;
    }

    // Determine where Mr. X could be after a series of moves
    public Set<Integer> getPossiblePositionsAfterMoves(List<String> moves) {
        Set<Integer> possiblePositions = new HashSet<>();
        possiblePositions.add(currentPosition);  // Start from the initial position

        // Iterate through each move (each transport type)
        for (String move : moves) {
            Set<Integer> newPossiblePositions = new HashSet<>();
            // For each possible position, find the next possible locations based on the move type
            for (Integer position : possiblePositions) {
                setCurrentPosition(position); // Update current position to check from this position
                List<Integer> neighbors = getPossibleMoves(move);
                // Add neighbors to the new possible positions
                newPossiblePositions.addAll(neighbors);
            }
            possiblePositions = newPossiblePositions;  // Update the possible positions after this move
        }
        return possiblePositions;
    }

    // For debugging: Display the graph
    public void displayGraph() {
        for (Map.Entry<Integer, List<TransportEdge>> entry : graph.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (TransportEdge edge : entry.getValue()) {
                System.out.print(edge.getDestination() + "(" + edge.getTransportType() + ") ");
            }
            System.out.println();
        }
    }
}
