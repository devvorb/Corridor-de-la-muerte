#!/bin/bash

if [ "$#" -ne 1 ]; then 
    echo "USAGE: $0 \"description de ce que vous avez changé\""
    exit -1
fi

git add .
git commit -m "$1"
git pull --rebase
git push
