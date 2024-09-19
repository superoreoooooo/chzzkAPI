#!/bin/bash
cd "$(dirname "$0")"
exec java -Xms1G -Xmx1G -jar paper-1.21.1-83.jar nogui
