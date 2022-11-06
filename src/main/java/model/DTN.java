package model;

import java.util.List;

public class DTN {
    List<STN> network;

    public DTN(List<STN> network) {
        this.network = network;
    }

    public List<STN> getNetwork() {
        return network;
    }

    public void setNetwork(List<STN> network) {
        this.network = network;
    }
}
