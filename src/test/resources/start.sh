#!/bin/bash
set -euo pipefail

cd ..
cd ..
cd ..

mvn spring-boot:run

docker compose down
