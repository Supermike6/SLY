class TransportEdge {
    private int destination; // The destination node
    private String transportType; // The type of transport (e.g., taxi, bus, underground, black card, ferry)

    public TransportEdge(int destination, String transportType) {
        this.destination = destination;
        this.transportType = transportType;
    }

    public int getDestination() {
        return destination;
    }

    public String getTransportType() {
        return transportType;
    }
}
