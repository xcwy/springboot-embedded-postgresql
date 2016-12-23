package io.davis.config;

import de.flapdoodle.embed.process.runtime.Network;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.jpa.EntityManagerFactoryDependsOnPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import ru.yandex.qatools.embed.postgresql.PostgresExecutable;
import ru.yandex.qatools.embed.postgresql.PostgresProcess;
import ru.yandex.qatools.embed.postgresql.PostgresStarter;
import ru.yandex.qatools.embed.postgresql.config.AbstractPostgresConfig;
import ru.yandex.qatools.embed.postgresql.config.PostgresConfig;
import ru.yandex.qatools.embed.postgresql.distribution.Version;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Davis on 16/12/23.
 */
@Configuration
public class EmbeddedPostgresConfiguration extends EntityManagerFactoryDependsOnPostProcessor {
  private final static String POSTGRES_PROCESS_BEAN_NAME = "postgresProcess";

  public EmbeddedPostgresConfiguration() {
    super(POSTGRES_PROCESS_BEAN_NAME);
  }

  @Bean(name = POSTGRES_PROCESS_BEAN_NAME, destroyMethod = "stop")
  public PostgresProcess postgresProcess(
      @Value("${postgres.host}") String host,
      @Value("${postgres.port}") int port,
      @Value("${postgres.database}") String database) throws IOException {

    PostgresStarter<PostgresExecutable, PostgresProcess> postgresStarter = PostgresStarter.getDefaultInstance();
    PostgresConfig postgresConfig = new PostgresConfig(Version.Main.PRODUCTION, host, port, database);
    postgresConfig.getAdditionalInitDbParams().addAll(Arrays.asList(
        "-E", "UTF-8",
        "--locale=en_US.UTF-8",
        "--lc-collate=en_US.UTF-8",
        "--lc-ctype=en_US.UTF-8"));
    PostgresExecutable postgresExecutable = postgresStarter.prepare(postgresConfig);
    PostgresProcess postgresProcess = postgresExecutable.start();

    return postgresProcess;
  }
}
