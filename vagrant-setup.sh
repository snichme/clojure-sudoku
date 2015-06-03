#!/bin/sh

apt-get -y -q update
apt-get -y -q upgrade

#Java 8

apt-get -y -q install software-properties-common htop
add-apt-repository ppa:webupd8team/java
apt-get -y -q update
echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
apt-get -y -q install oracle-java8-installer
apt-get -y -q install oracle-java7-installer
update-java-alternatives -s java-8-oracle

# Lein

wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein -O /usr/local/bin/lein
chmod a+x /usr/local/bin/lein
