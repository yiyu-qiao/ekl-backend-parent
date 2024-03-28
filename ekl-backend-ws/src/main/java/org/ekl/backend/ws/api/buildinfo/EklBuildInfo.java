package org.ekl.backend.ws.api.buildinfo;

import org.springframework.boot.actuate.info.BuildInfoContributor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;

@Component
public class EklBuildInfo extends BuildInfoContributor {

    public EklBuildInfo(BuildProperties properties) {
        super(properties);
    }

    public String getBuildVersion(){
        return this.getProperties().getVersion();
    }
}
