package com.kent.log;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResolveInfo {
    private boolean subInParam;
    private boolean pickInParam;
    private boolean pickOutParam;
    private boolean bsInParam;
    private boolean bsOutParam;

    public boolean allFalse() {
        return !subInParam && !pickInParam && !pickOutParam
                && !bsInParam && !bsOutParam;
    }
}
