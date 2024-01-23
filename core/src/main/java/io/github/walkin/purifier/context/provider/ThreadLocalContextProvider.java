package io.github.walkin.purifier.context.provider;

public class ThreadLocalContextProvider extends AbstractPurifierContextProvider {

    @Override
    protected String getFilter(Class beanClass) {
        return PurifierFilterHolder.getFilter();
    }

}
