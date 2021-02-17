package academy.softserve.model.library;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum UserRole {
    ADMIN("Admin"),
    USER ("User"),
    ANONYMOUS ("Anonymous");

    private static final Map<String, UserRole> VALUES = new HashMap<>();

    static {
        Arrays.stream(values()).forEach(value -> VALUES.put(value.getName(), value));
    }

    private String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static UserRole getByName(String name) {
        return VALUES.get(name);
    }
}
