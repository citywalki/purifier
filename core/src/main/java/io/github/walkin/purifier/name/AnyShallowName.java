package io.github.walkin.purifier.name;

public class AnyShallowName implements SquigglyName {

    public static final String ID = "*";

    private static final AnyShallowName INSTANCE = new AnyShallowName();

    public static AnyShallowName get() {
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
        return 1;
    }

}
