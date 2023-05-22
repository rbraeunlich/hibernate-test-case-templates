package org.hibernate.bugs;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Type;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "custom_entity")
public class CustomEntity {

    @Id
    private UUID id;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> properties;

    public CustomEntity(UUID id, Map<String, Object> properties) {
        this.id = id;
        this.properties = properties;
    }

    public CustomEntity() {
        this.id = UUID.randomUUID();
        this.properties = new HashMap<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomEntity)) return false;
        CustomEntity that = (CustomEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, properties);
    }
}
