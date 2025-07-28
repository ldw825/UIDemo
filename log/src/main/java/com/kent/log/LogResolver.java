package com.kent.log;

import java.util.List;

public class LogResolver {

    public void resolve(List<String> filePathList, ResolveInfo resolveInfo) {
        if (resolveInfo.allFalse()) {
            return;
        }
        for (String filePath : filePathList) {
            resolveSubInParam(filePath, resolveInfo);
            resolvePickInParam(filePath, resolveInfo);
            resolvePickOutParam(filePath, resolveInfo);
            resolveBsInParam(filePath, resolveInfo);
            resolveBsOutParam(filePath, resolveInfo);
        }
    }

    private void resolveSubInParam(String filePath, ResolveInfo resolveInfo) {
        if (!resolveInfo.isSubInParam()) {
            return;
        }
    }

    private void resolvePickInParam(String filePath, ResolveInfo resolveInfo) {
        if (!resolveInfo.isPickInParam()) {
            return;
        }
    }

    private void resolvePickOutParam(String filePath, ResolveInfo resolveInfo) {
        if (!resolveInfo.isPickOutParam()) {
            return;
        }
    }

    private void resolveBsInParam(String filePath, ResolveInfo resolveInfo) {
        if (!resolveInfo.isBsInParam()) {
            return;
        }
    }

    private void resolveBsOutParam(String filePath, ResolveInfo resolveInfo) {
        if (!resolveInfo.isBsOutParam()) {
            return;
        }
    }
}
