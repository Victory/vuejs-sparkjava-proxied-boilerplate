#!/bin/bash

cd $(dirname $0)
codeBase=$1

source config-paths.sh

echo "This script requires NVM and jdk8 be installed."
echo "Using nvm: $NVM_DIR"
echo "Using JAVA_HOME: $JAVA_HOME"
nvm install 6 --lts
nvm use 6 --lts

nodeVersion=$(node --version)
echo "node version v6.10 or later needed using $nodeVersion"
npmVersion=$(npm --version)
echo "npm version 0.21.3 or later required using $npmVersion "

usage() {
    echo ""
    echo ""
    echo "USAGE: "
    echo "    $0 front [command]"
    echo "    $0 back [command]"
    echo "    $0 upgrade"
    echo "    $0 startdev"

    echo ""
    echo " ==== examples === "
    echo ""
    echo "To run 'cd frontvue; gulp run dev'"
    echo "    $0 front dev"
    echo "To run 'cd backspark; ./gradlew run'"
    echo "    $0 back run"
    exit 1
}

runFront() {
    cd frontvue
    pwd
    echo "Running: gulp $1 $2 $3 $4 $5"
    gulp $1 $2 $3 $4 $5
    exit 0
}

runBack() {
    cd backspark
    pwd
    echo "Running: ./gradlew $1 $2 $3 $4 $5"
    ./gradlew $1 $2 $3 $4 $5
    exit 0
}

dieNotZero() {
    if [ "$1" -eq "0" ]; then
        return
    fi

    echo "Existing to do error"
    exit 1;
}

startDev() {
    thisPid=$$

    cd devproxy;
    node dev.js &
    proxyPid=$!
    result=$?
    dieNotZero $?

    cd ../frontvue
    node build/dev-server.js &
    frontPid=$!
    dieNotZero $?

    cd ../backspark
    ./gradlew run &
    backPid=$!
    dieNotZero $?
    cd ..

    sleep 5

    echo "backPid $backPid"
    echo "frontPid $frontPid"
    echo "proxyPid $proxyPid"

    echo ""
    echo ""
    echo ""
    echo ""
    op="keepgoing"
    while [ "$op" != "q" ]; do
        echo "Now Serving type 'q' then ENTER to exit"
        read op
    done

    kill -9 ${proxyPid}
    sleep .5
    kill -9 ${frontPid}
    sleep .5
    kill -9 ${backPid}
    sleep 1
    pkill ${thisPid}
}

runUpgrade() {
    npm install --global gulp-cli
    npm install --global yarn

    cd ./devproxy
    npm upgrade
    yarn

    cd ../frontvue
    npm upgrade
    yarn

    cd ../backspark
    buildConfigProperties
    ./gradlew build
}

buildConfigProperties() {
    outFile="config.properties"
    echo "# This files is created by tasks.sh upgrade" | tee ${outFile}
    echo "" | tee -a ${outFile}
    echo "isDev=$BS_ISDEV" | tee -a ${outFile}
    echo "" | tee -a ${outFile}
    echo "mongoUri=$BS_MONGODB" | tee -a ${outFile}
}

case "$codeBase" in
    upgrade)
        runUpgrade
        ;;
    front)
        runFront $2 $3 $4 $5 $6
        ;;
    back)
        runBack $2 $3 $4 $5 $6
        ;;
    startdev)
        startDev
        ;;
    *|help)
        usage;
        ;;
esac
