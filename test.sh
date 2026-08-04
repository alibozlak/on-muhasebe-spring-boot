#!/bin/bash 
echo "" && echo "--- Current Working Directory ---" && \
pwd && echo "" && echo "" && \
echo "--- 1. We are throwing Post Login Request ---" && \
response_body=$(curl -X POST http://localhost:8080/api/login -H "Content-Type: application/json" -d "{\"username\" : \"bozlak\", \"password\" : \"bozlak\"}") && \
echo "Login Request Successful. Response Body (JSON) : " && \
echo "$response_body" && \
SUCCESS_COLOR='\033[0;32m' && \
NO_COLOR='\033[0m' && \
echo -e "${SUCCESS_COLOR}--- (1) Login Successful. We have a token ---${NO_COLOR}" && echo "" && echo "" 