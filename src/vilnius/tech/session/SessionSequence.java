package vilnius.tech.session;

import vilnius.tech.dal.BaseOid;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SessionSequence implements Serializable {

    private final Map<Class<? extends BaseOid>, Integer> sequenceData;

    public SessionSequence() {
        this.sequenceData = new HashMap<>();
    }

    public int next(Class<? extends BaseOid> classInfo) {
        if(!sequenceData.containsKey(classInfo))
            sequenceData.put(classInfo, 1);

        var value = sequenceData.get(classInfo);
        sequenceData.put(classInfo, value + 1);
        return value;
    }
}
