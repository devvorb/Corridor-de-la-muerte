#!/bin/bash

../gradlew :engine:build && \

    unzip -o build/distributions/engine-1.0.0-SNAPSHOT.zip -d build/distributions/ && \

    build/distributions/engine-1.0.0-SNAPSHOT/bin/engine