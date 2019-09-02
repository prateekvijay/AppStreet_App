package com.example.android.appstreet_app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
@RunWith(JUnit4.class)
public class APIAvailabilityTest {

    @Test public void testAvailability() throws Exception {
        URLConnection connection = new URL("https://github-trending-api.now.sh/developers?language=java&since=weekly").openConnection();
        InputStream response = connection.getInputStream();

        StringBuffer buffer = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response, Charset.defaultCharset()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                buffer.append(line);
            }
        }

        assert buffer.length() > 0;
    }
}
