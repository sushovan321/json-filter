package com.parser.json;

import junit.framework.TestCase;
import org.junit.Test;

public class JsonFilterTest extends TestCase {

    private static final String jsonString = "{\"k1\" : \"v1\",\"k2\" : [\"v2\", \"v3\", \"\", {\"k21\": \"v21\", \"k22\": \"\"}],\"k3\" : {\"k4\" : \"v4\", \"k5\" : [\"v5\", \"v6\", null], \"k6\": {\"k7\" : \"v7\", \"k8\" : \"\"}},\"k9\" : null}";

    @Test
    public void testGetFilteredJSON() {
        String expectedJSON = "{\"k1\":\"v1\",\"k2\":[\"v2\",\"v3\",{\"k21\":\"v21\"}],\"k3\":{\"k4\":\"v4\",\"k5\":[\"v5\",\"v6\"],\"k6\":{\"k7\":\"v7\"}}}";

        String actualJSON = new JsonFilter().getFilteredJSON(jsonString);
        assertEquals(expectedJSON, actualJSON);
    }
}