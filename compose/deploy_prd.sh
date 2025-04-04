#!/bin/bash

echo deploying wmts-to-tms-proxy...

export DOMAIN=pdok-tms.idgis.nl

export APP_VERSION=1.3.2

docker compose -f base.yaml -f apps.yaml -f base-https.yaml -p pdok-tms build
docker compose -f base.yaml -f apps.yaml -f base-https.yaml -p pdok-tms up -d
