package com.chancetop.helpers;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Reloadable;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources("classpath:config.properties")
public interface BaseConfig extends Config, Reloadable {

    @Key("username")
    String username();

    @Key("apiKey")
    String apiKey();

    @Key("testrailUrl")
    String testrailUrl();
}
