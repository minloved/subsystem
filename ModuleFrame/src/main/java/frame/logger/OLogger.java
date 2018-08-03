
/*
 * Copyright 2017 Gary YUZHANG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package frame.logger;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OLogger {

    public static final String TAG = OLogger.class.getSimpleName();

    public static final String SHIFTER = "\n";

    public static final char LOG_INFO_FLAG = '-';
    public static final char LOG_WARN_FLAG = '*';
    public static final char LOG_ERROR_FLAG = '+';

    public static final int CHAR_COUNT_PER_LINE = 100;

    public static void logI(String log){
        logI(TAG,log);
    }

    public static void logI(String logTag,String log,String... logPlus){

        String nlog = logWrapper(LOG_INFO_FLAG,logTag,log,logPlus);

        Log.i(logTag,nlog);
    }

    public static void logW(String log){
        logW(TAG,log);
    }

    public static void logW(String logTag,String log,String... logPlus){


        String nlog = logWrapper(LOG_WARN_FLAG,logTag,log,logPlus);

        Log.w(logTag,nlog);
    }

    public static void logE(String log){
        logE(TAG,log);
    }

    public static void logE(String logTag,String log,String... logPlus){


        String nlog = logWrapper(LOG_ERROR_FLAG,logTag,log,logPlus);

        Log.e(logTag,nlog);
    }

    private static List<String> autoShifter(String log,int charMaxCountPerLine){

        List<String> autoShifter = new ArrayList<>();

        int charCount = log.length();
        int start = 0;
        do {
            if (start + charMaxCountPerLine < charCount){
                autoShifter.add(log.substring(start,start + charMaxCountPerLine));
            }else{
                autoShifter.add(log.substring(start,charCount));
            }
            start += charMaxCountPerLine;
        }while (start < charCount);

        return autoShifter;
    }

    private static List<String> insertNoAndAutoShifter(String log, String... logPlus){

        List<String> logList = new ArrayList<>();

        List<String> tl = new ArrayList<>();
        tl.add(log);

        for (String nlog : logPlus) {
            tl.add(nlog);
        }

        List<String> allAutoShifter = new ArrayList<>();
        int lineNo = 1;
        boolean fline;

        for (String tt : tl){
            String[] splice = tt.split(SHIFTER);

            allAutoShifter.clear();

            for (String sp : splice) {
                if (TextUtils.isEmpty(sp))continue;
                allAutoShifter.addAll(autoShifter(sp,CHAR_COUNT_PER_LINE));
            }

            fline = true;
            String preLine = "(" + lineNo+") ";
            StringBuilder preSpace = new StringBuilder();
            fillUpWithChar(' ',preSpace,preLine.length());

            for (String cInfo : allAutoShifter) {
                if (fline){
                    fline = false;
                    logList.add(preLine+cInfo);
                    continue;
                }
                logList.add(preSpace+cInfo);
            }

            lineNo ++;
        }

        return Collections.unmodifiableList(logList);
    }

    private static String logWrapper(char logFlag ,String logTag,String log, String...logPlus){


        List<String> logList = new ArrayList<>();

        logList.addAll(insertNoAndAutoShifter(log,logPlus));

        if (logList.isEmpty()){
            return "log is nothing";
        }


        StringBuilder logFlagBuilder = new StringBuilder();
        fillUpWithChar(logFlag,logFlagBuilder,Math.min(CHAR_COUNT_PER_LINE  + 20,120));



        StringBuilder allLogBuilder = new StringBuilder();
        allLogBuilder.append(" Lookup === > ").append(logTag).append(SHIFTER);
        allLogBuilder.append(logFlagBuilder).append(SHIFTER);

        final char split = '|';
        final String  tab = "  ";
        final String tagHead = logTag + tab + split;

        StringBuilder span = new StringBuilder();
        fillUpWithChar(' ',span,tagHead.length());
        span.replace(span.length() - 1,span.length(),String.valueOf(split));

        int tagAtIndex = logList.size() / 2;
        int index = 0;

        for (String nlog : logList) {
            if (index == tagAtIndex){
                allLogBuilder.append(tagHead);
            }else{
                allLogBuilder.append(span);
            }
            index++;
            allLogBuilder.append(tab).append(nlog).append(SHIFTER);
        }
        allLogBuilder.append(logFlagBuilder).append(SHIFTER).append(SHIFTER);

        return allLogBuilder.toString();
    }


    private static void fillUpWithChar(char c, StringBuilder sBuilder, int length){

        if (length > 0 && length > sBuilder.length()){
            do {
                sBuilder.append(c);
            }while (length != sBuilder.length());
        }

    }
}
