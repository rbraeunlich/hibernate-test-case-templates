package org.hibernate.bugs;

import java.util.Map;

public class CustomProjection {

    private Map<String, Object> properties;

    public CustomProjection(Map<String, Object> properties){
        this.properties = properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    Map<String, Object> getProperties() {
        return properties;
    }
}
