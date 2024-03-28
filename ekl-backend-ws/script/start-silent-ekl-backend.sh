#!/usr/bin/bash
nohup ./tmp/ekl-backend/script/start-ekl-backend.sh >> ./tmp/ekl-backend/log/ekl-backend.log 2>&1 < /dev/null &
