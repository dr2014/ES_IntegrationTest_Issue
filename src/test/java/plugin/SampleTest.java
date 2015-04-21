package plugin;

import static org.elasticsearch.common.settings.ImmutableSettings.settingsBuilder;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.test.ElasticsearchIntegrationTest;
import org.elasticsearch.test.rest.client.http.HttpResponse;
import org.junit.Test;

public class SampleTest extends ElasticsearchIntegrationTest {

	static {
		ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
	}

	@Override
	protected Settings nodeSettings(final int nodeOrdinal) {

		return settingsBuilder().put(super.nodeSettings(nodeOrdinal))
				.put("force.http.enabled", true)
				.put("plugin.types", TestPlugin.class.getName()).build();
	}

	@Test
	public void recreateIssueTest() throws Exception {
		ensureGreen();
		HttpResponse response = httpClient().method("GET")
				.path("/test-plugin/status").execute();

		assertEquals(200, response.getStatusCode());

	}

}
