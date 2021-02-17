package academy.softserve.model.library;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum AdvertGenre {
    DESIGN ("Design"),
    LIFE ("Life"),
    IT ("It"),
    SPORT ("Sport");

    private static final Map<String, AdvertGenre> VALUES = new HashMap<>();

    static {
        Arrays.stream(values()).forEach(value -> VALUES.put(value.getName(), value));
    }

    private String name;

    AdvertGenre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static AdvertGenre getByName(String name) {
        return VALUES.get(name);
    }
}
