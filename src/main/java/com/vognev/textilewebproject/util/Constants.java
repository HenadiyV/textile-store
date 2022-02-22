package com.vognev.textilewebproject.util;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * textile-store  06/01/2022-17:03
 */
public final class  Constants {

    public static final  int LENGHT_TOKEN=20;

    public static final String NAME_TOKEN="textile-basket";

    public static final long ONE_DAY=86_400_000;

    public static final int MESSAGES_PER_PAGE = 6;

    public static final int LIST_SIZE = 20;

    public static String shortUUID() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }
}
