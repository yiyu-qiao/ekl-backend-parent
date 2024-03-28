package org.ekl.backend.ws.api.buildinfo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BuildInfoController {

    private final EklBuildInfo eklBuildInfo;

    @GetMapping(path = "/api/build/version", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCurrentVersion(){
        return ResponseEntity.ok(this.eklBuildInfo.getBuildVersion());
    }

}
