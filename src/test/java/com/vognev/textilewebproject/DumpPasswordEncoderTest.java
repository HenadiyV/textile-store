package com.vognev.textilewebproject;


import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * textilewebproject  07/10/2021-6:52
 */
class DumpPasswordEncoderTest {

    @Test
    void encode() {
        DumpPasswordEncoder encoder = new DumpPasswordEncoder();

        Assert.assertEquals("secret: mypwd",encoder.encode("mypwd"));
        Assert.assertThat(encoder.encode("mypwd"),Matchers.containsString("mypwd"));
    }
}