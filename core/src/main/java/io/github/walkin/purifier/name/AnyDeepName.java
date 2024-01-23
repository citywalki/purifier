package io.github.walkin.purifier.name;

public class AnyDeepName implements SquigglyName {

    public static final String ID = "**";

    private static final AnyDeepName INSTANCE = new AnyDeepName();

    public static AnyDeepName get() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return ID;
    }

    @Override
    public String getRawName() {
        return ID;
    }

    @Override
    public int match(String name) {
        return 0;
    }

}
