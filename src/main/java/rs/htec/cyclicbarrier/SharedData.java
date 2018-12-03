package rs.htec.cyclicbarrier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SharedData {

    private List<Integer> result;

    public SharedData() {
        result = Collections.synchronizedList(new ArrayList<>());
    }

    public List<Integer> getResult() {
        return result;
    }
}
