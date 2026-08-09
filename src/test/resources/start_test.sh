#!/bin/bash
set -euo pipefail

# -----------------------------------------------------------
# ------> This bash script ASSUMES project is running!  <----
# -----------------------------------------------------------

SUCCESS_COLOR='\033[0;32m'
ERROR_COLOR='\033[1;31m'
INFO_COLOR='\033[0;34m'
NO_COLOR='\033[0m'
INFO_MESSAGE="[${INFO_COLOR}INFO${NO_COLOR}]"
ERROR_MESSAGE="[${ERROR_COLOR}ERROR${NO_COLOR}]"

cd ..
cd ..
cd ..
echo -e "$INFO_MESSAGE --- Go To Project Root Directory ---"
pwd
if [ "$(find . -name pom.xml)" = "./pom.xml" ]; then
    echo -e "$INFO_MESSAGE ${SUCCESS_COLOR}Correct Directory${NO_COLOR}"
else
    echo -e "$ERROR_MESSAGE ${ERROR_COLOR}Wrong Directory${NO_COLOR}"
    exit 1
fi
echo ""

#echo "--- mvn verify (Unit and Integration Tests) ---"
#./mvnw verify
#echo ""
#echo -e "$INFO_MESSAGE ${SUCCESS_COLOR}Unit and Integration Tests passed :)${NO_COLOR}"

# Like try-catch's final block
#trap 'docker compose down' EXIT

echo -e "$INFO_MESSAGE --- Create Table In Docker PostgreSQL Container ---"
docker exec -i pre_accounting_db psql -U bozlak -d test_db -v ON_ERROR_STOP=1 < src/test/resources/schema.sql
docker exec pre_accounting_db psql -U bozlak -d test_db -c "\dt"
echo ""

echo -e "$INFO_MESSAGE --- Adding first user and first admin ---"
docker exec -i pre_accounting_db psql -U bozlak -d test_db -v ON_ERROR_STOP=1 < src/test/resources/first_user.sql
docker exec pre_accounting_db psql -U bozlak -d test_db -c "select * from users;"
echo -e "$INFO_MESSAGE ${SUCCESS_COLOR}First user (bozlak) created${NO_COLOR}"
echo ""

docker exec -i pre_accounting_db psql -U bozlak -d test_db -v ON_ERROR_STOP=1 < src/test/resources/first_admin.sql
docker exec pre_accounting_db psql -U bozlak -d test_db -c "select * from admins;"
echo -e "$INFO_MESSAGE ${SUCCESS_COLOR}First admin = First user (bozlak) created${NO_COLOR}"
echo ""
