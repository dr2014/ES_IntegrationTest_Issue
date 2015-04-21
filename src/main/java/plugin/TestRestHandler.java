package plugin;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.BytesRestResponse;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestHandler;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.RestRequest.Method;
import org.elasticsearch.rest.RestResponse;
import org.elasticsearch.rest.RestStatus;

public class TestRestHandler implements RestHandler {

	private ESLogger logger = Loggers.getLogger(TestRestHandler.class);
	private final Client client;

	@Inject
	public TestRestHandler(final Settings settings,
			final RestController controller, final Client client) {
		this.client = client;

		controller.registerHandler(Method.GET, "/test-plugin/status", this);
	}

	@Override
	public void handleRequest(RestRequest request, RestChannel channel)
			throws Exception {

		ClusterHealthResponse clusterHealthResponse = this.client.admin()
				.cluster().prepareHealth().execute().actionGet();

		RestResponse response = new BytesRestResponse(RestStatus.OK,
				String.format("Cluster %s is in %s status",
						clusterHealthResponse.getClusterName(),
						clusterHealthResponse.getStatus()));

		channel.sendResponse(response);

	}
}
