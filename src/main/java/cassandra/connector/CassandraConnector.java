package cassandra.connector;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class CassandraConnector {

    private Cluster cluster;
    private Session session;

    public void connect(String node, Integer port, String username, String password) {

        // Had to add connection string manually, because it was giving a weird error.
        Cluster.Builder builder = Cluster.builder()
                .addContactPoint("13.59.226.165")
                .withCredentials("cassandra_user", "triton2019exito");

        if (port != null) {
            builder.withPort(port);
        }

        cluster = builder.build();

        session = cluster.connect();
    }

    public Session getSession() {
        return this.session;
    }

    public void close() {
        session.close();
        cluster.close();
    }
}
