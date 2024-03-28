#!/usr/bin/bash

set +x

curl_return_code=$(curl --dump-header artifactory-response-header.txt -H "Authorization: Bearer ${ARTIFACTORY_TOKEN}" -X PUT "https://qiaohandev.jfrog.io/artifactory/ekl-snapshot-local/de/han/ekl/backend/ekl-backend-0.0.1-SNAPSHOT.jar" -T ./target/ekl-backend-0.0.1-SNAPSHOT.jar)

echo $ARTIFACTORY_TOKEN

echo "cur return code : $curl_return_code"

cat artifactory-response-header.txt
