cp="lib/scribe-1.3.0.jar:lib/commons-codec-1.6.jar:lib/gson-2.1.jar:lib/mysql-connector-java-5.1.18-bin.jar:lib/jsoup-1.6.1.jar:."
pdir="me/walkable"
java="/usr/bin/java"

cd /var/git/hackers/walkable/java
./compile.sh

$java -classpath $cp $pdir/RefreshGiltCity

