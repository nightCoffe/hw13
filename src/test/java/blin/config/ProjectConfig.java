package blin.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties",
        "classpath:config/remote.properties"
})
public interface ProjectConfig extends Config{

    @DefaultValue("chrome")
    String browserName();

    @DefaultValue("96.0")
    String browserVersion();

    @DefaultValue("1920x1080")
    String browserSize();

    String remoteDriverUrl();

    String videoStorage();
}

