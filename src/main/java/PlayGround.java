import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgniteRunnable;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

public class PlayGround {
    static final Logger LOGGER = LoggerFactory.getLogger("Ignite PlayGround");

    // we have shipment and node objects, these objects are designed based on object oriented design principles.
    public static void main(String[] args) {
        IgniteConfiguration cfg = new IgniteConfiguration();

        cfg.setClientMode(true);
        cfg.setPeerClassLoadingEnabled(true);

        TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
        ipFinder.setAddresses(Collections.singletonList("127.0.0.1:47500..47509"));
        cfg.setDiscoverySpi(new TcpDiscoverySpi().setIpFinder(ipFinder));

        Ignite ignite = Ignition.start(cfg);

        IgniteCache<Integer, String> cache = ignite.getOrCreateCache("myCache");
        cache.put(1, "hello");
        cache.put(2, "world");

        LOGGER.info("Created the cache and added the value");

        ignite.compute(ignite.cluster().forServers()).broadcast(new RemoteTask());

        LOGGER.info("Done with task, closing now");
        ignite.close();
    }

    private static class RemoteTask implements IgniteRunnable {
        @IgniteInstanceResource
        Ignite ignite;

        @Override
        public void run() {
            LOGGER.info("Executing the compute task");
            LOGGER.info("Node id {}, OS {}, JRE {};", ignite.cluster().localNode().id(),
                    System.getProperty("os.name"), System.getProperty("java.runtime.name"));

            IgniteCache<Integer, String> cache = ignite.cache("myCache");

            LOGGER.info("Adding cache 1 and 2 is {}", cache.get(1) + cache.get(2));
        }
    }
}
