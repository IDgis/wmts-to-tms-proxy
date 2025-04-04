#!/bin/bash

echo building docker image of wmts-to-tms-proxy...

if [ -z "$1" ]; then
    echo no tag provided
    exit 1
fi

docker build --no-cache -t idgis/wmts-to-tms-proxy:$1 .
