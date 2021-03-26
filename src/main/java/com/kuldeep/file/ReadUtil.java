package com.kuldeep.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.min;
import static java.nio.channels.FileChannel.MapMode.READ_ONLY;

class ReadUtil {
    private static final Logger log = LoggerFactory.getLogger(ReadUtil.class);
    private ReadUtil() {}

    public static Function<ReadParams, Long> wordsCounter() {
        return params -> {
            try(FileChannel channel = FileChannel.open(params.getPath())) {
                long position = params.getStartPosition();
                long maxBuffer = params.getMaxBuffer() + position;
                long wc = 0;

                StringBuilder sb = new StringBuilder();

                while(position < maxBuffer) {
                    long remaining = maxBuffer - position;
                    long bytesToRead = min(maxBuffer, remaining);

                    log.debug("Reading from position {}", position);

                    MappedByteBuffer bf = channel.map(READ_ONLY, position, bytesToRead);
                    if(bf != null) {
                        String content = StandardCharsets.UTF_8.decode(bf).toString();
                        sb.append(content);

                        bf.clear();
                    }
                    position += bytesToRead;
                }

                Pattern pattern = Pattern.compile("\\w+");
                Matcher matcher = pattern.matcher(sb.toString());

                while (matcher.find())
                    wc++;

                return wc;
            }catch (IOException ex) {
                log.error("Something is not right with position calculation", ex);
            }
            return -1L;
        };
    }
}
