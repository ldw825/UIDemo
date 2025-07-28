package com.kent.log;

import java.util.List;

public interface OnStartListener {
    void onStart(List<String> filePath, ResolveInfo resolveInfo);
}
