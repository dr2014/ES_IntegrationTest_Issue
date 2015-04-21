package plugin;

import org.elasticsearch.common.inject.AbstractModule;

public class TestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TestRestHandler.class).asEagerSingleton();
    }
}
