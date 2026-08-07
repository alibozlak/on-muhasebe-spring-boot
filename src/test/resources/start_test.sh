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
trap 'docker compose down' EXIT

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

#echo -e "$INFO_MESSAGE --- Login ---"
#curl -X POST http://localhost:8080/api/login -H "Content-Type: application/json" -d "{\"username\" : \"bozlak\", \"password\" : \"bozlak\"}" > src/test/resources/test_responses/login_response.json
#echo -e "$INFO_MESSAGE Response : $(cat src/test/resources/test_responses/login_response.json)"
#echo ""
#echo -e "$INFO_MESSAGE ${SUCCESS_COLOR}(1) Login succeed${NO_COLOR}"
#token=$(jq -r '.object.jwtToken' src/test/resources/test_responses/login_response.json)
#echo -e "$INFO_MESSAGE JWT Token : $token"
#echo ""
#
#echo -e "$INFO_MESSAGE --- Admin Change Password ---"
#curl -X PUT "http://localhost:8080/api/v1/users/change-password" -H "Authorization: Bearer $token" -H "Content-Type: application/json" -d "{\"currentPassword\":\"bozlak\",\"newPassword\":\"Test5678!\"}" > src/test/resources/test_responses/admin_change_password_response.json
## new_password=Test5678!
#echo -e "$INFO_MESSAGE Response : $(cat src/test/resources/test_responses/admin_change_password_response.json)"
#echo ""
#
#echo -e "$INFO_MESSAGE Login Trial Admin Previous Password : "
#curl -X POST http://localhost:8080/api/login -H "Content-Type: application/json" -d "{\"username\" : \"bozlak\", \"password\" : \"bozlak\"}" > src/test/resources/test_responses/login_previous_password.json
#echo -e "$INFO_MESSAGE Response : $(cat src/test/resources/test_responses/login_previous_password.json)"
#echo -e "$INFO_MESSAGE ${SUCCESS_COLOR}Login trial with previous admin password rejected${NO_COLOR}"
#echo ""
#
#echo -e "$INFO_MESSAGE Login Trial with correct (new) admin password : "
#curl -X POST http://localhost:8080/api/login -H "Content-Type: application/json" -d "{\"username\" : \"bozlak\", \"password\" : \"Test5678!\"}" > src/test/resources/test_responses/login_new_password.json
#echo -e "$INFO_MESSAGE Response : $(cat src/test/resources/test_responses/login_new_password.json)"
#echo -e "$INFO_MESSAGE ${SUCCESS_COLOR}Login trial with new (correct) admin password accepted${NO_COLOR}"
#echo ""
#
#echo -e "$INFO_MESSAGE ${SUCCESS_COLOR}(2) Admin change password succeed :)${NO_COLOR}"
#token=$(jq -r '.object.jwtToken' src/test/resources/test_responses/login_new_password.json)
#curl -X PUT "http://localhost:8080/api/v1/users/change-password" -H "Authorization: Bearer $token" -H "Content-Type: application/json" -d "{\"currentPassword\":\"Test5678!\",\"newPassword\":\"bozlak\"}" > src/test/resources/test_responses/admin_change_password_response.json
#echo -e "$INFO_MESSAGE ${SUCCESS_COLOR}Returned previous password : bozlak${NO_COLOR}"
#echo ""
#
#echo -e "$INFO_MESSAGE ${SUCCESS_COLOR}TEST PIPELINE SUCCESS${NO_COLOR}"
#echo ""
