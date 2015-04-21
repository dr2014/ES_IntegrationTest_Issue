package plugin;

import java.util.Arrays;
import java.util.Collection;

import org.elasticsearch.common.inject.Module;
import org.elasticsearch.plugins.AbstractPlugin;

public class TestPlugin extends AbstractPlugin {
	
	public String name() {
        return "test-plugin";
    }

    public String description() {
        return "Test plugin";
    }

    @Override
    public Collection<Class<? extends Module>> modules() {
        return Arrays.asList(TestModule.class);
    }

}
