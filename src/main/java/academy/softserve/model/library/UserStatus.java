package academy.softserve.model.library;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum UserStatus {
    NEWCOMER("Newcomer"),
    EXPERIENCED("Experienced");

    private static final Map<String, UserStatus> VALUES = new HashMap<>();

    static {
        Arrays.stream(values()).forEach(value -> VALUES.put(value.getName(), value));
    }

    private String name;

    UserStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static UserStatus getByName(String name) {
        return VALUES.get(name);
    }
}
