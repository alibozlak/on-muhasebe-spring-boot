#!/bin/bash 
echo "--- Current Working Directory ---" && \
pwd && \
SUCCESS_COLOR='\033[0;32m' && \
ERROR_COLOR='\033[1;31m' && \
INFO_COLOR='\033[0;34m' && \
NO_COLOR='\033[0m' && \
INFO_MESSAGE="[${INFO_COLOR}INFO${NO_COLOR}]" && \
ERROR_MESSAGE="[${ERROR_COLOR}INFO${NO_COLOR}]" && \
if [ $(pwd) = "/mnt/c/angularAndJava/on_muhasebe/on-muhasebe-spring-boot" ]; then
    echo -e "$INFO_MESSAGE ${SUCCESS_COLOR}Correct Directory${NO_COLOR}"
    else
      echo -e "$ERROR_MESSAGE ${ERROR_COLOR}Wrong Directory${NO_COLOR}"
fi && echo "" && \
echo "--- Does Docker has a 'pre_accounting_db' container ---" && \
# ToDo
echo -e "$INFO_MESSAGE ${SUCCESS_COLOR} TEST PIPELINE SUCCESS ${NO_COLOR}" && echo ""